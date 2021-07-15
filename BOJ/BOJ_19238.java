/**
 * @문제 스타트 택시_G4
 * @날짜 210715
 * @분류 시뮬레이션, BFS
 * @조건
 * # 현재 위치에서 최단거리의 승객을 고른다. => 여러명이면 행번호가 가장 작은, 열 번호가 가장 작은
 * # 목적지로 이동 시키면 소모한 연료 양의 2배가 충전
 * # 2 <= N <= 20
 * @풀이
 * # Pair[21][21]로 각 승객의 목적지 정보를 저장해두고
 * # map에서는 승객의 위치를 2로 마킹한다.
 * # BFS 돌면서 2인 부분에서 목적지까지 다시 BFS 
 * @comment
 * # 한 승객의 목적지가 다른 승객의 출발점인 경우 체크 놓침
 * # 시뮬 문제 풀때 항상 경계조건, 숨어있는 함정 조건 체크하기!
 */

import java.util.*;
import java.io.*;

public class BOJ_19238 {
	
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	// -----------------------------------------------------------------------------
	
	static class Pair {
		int r, c;

		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int N, M, fuel;
	static Pair[][] passengers; // 승객의 목적지 좌표
	static List<Pair> list; // 승객을 담는 리스트
	static char[][] map;
	static Pair taxi; // 택시 초기 위치

	static int[] dr = {-1, 0, 1, 0}; // 상우하좌 시계방향
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		fuel = Integer.parseInt(st.nextToken());
		// init
		map = new char[N][N];
		passengers = new Pair[N][N];
		list = new ArrayList<>();
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < N; c++) {
				map[r][c] = st.nextToken().charAt(0);
			}
		}
		st = new StringTokenizer(br.readLine(), " ");
		int taxiR = Integer.parseInt(st.nextToken()) - 1; // 초기 택시 위치
		int taxiC = Integer.parseInt(st.nextToken()) - 1;
		taxi = new Pair(taxiR, taxiC);
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine(), " ");
			int psr = Integer.parseInt(st.nextToken()) - 1; // passenger start R 
			int psc = Integer.parseInt(st.nextToken()) - 1; 
			int per = Integer.parseInt(st.nextToken()) - 1; // passenger end R
			int pec = Integer.parseInt(st.nextToken()) - 1;
			passengers[psr][psc] = new Pair(per, pec); // 승객 목적지 좌표 저장
			map[psr][psc] = '2'; // 승객 위치 마킹
		} // input end
		
		solve();
		
		// print
		System.out.println(fuel);
	}
	
	static void solve() {
		// 손님 수만큼 반복
		while (M-- > 0) {
			if (!(findPassenger() && findDestination())) { // 이동중에 연료가 다 떨어지는 경우
				fuel = -1;
				
				return;
			}
		}
		
		// 모든 손님을 이동시킬 수 없는 경우
		if (M != -1) {
			fuel = -1;
		}
		
		return;
	}
	
	static boolean findPassenger() {
		// 택시 위치로부터 bfs 돌려서 최단거리 승객을 찾는다.
		Queue<Pair> q = new LinkedList<>();
		q.offer(new Pair(taxi.r, taxi.c));
		int[][] visited = new int[N][N]; // 이동시마다 visited 생성
		int moves = 1; // visited에 이동 횟수 마킹
		visited[taxi.r][taxi.c] = moves;
		
		// 출발지가 목적지인 경우
		if (map[taxi.r][taxi.c] == '2') {
			map[taxi.r][taxi.c] = '0'; // 승객 마킹 해제 
			return true;
		}
		
		while (!q.isEmpty()) {
			int size = q.size();
			moves++;
			
			// 사이즈만큼 돌면서(한 싸이클) 승객이 있는 지점을 갔는지 체크
			while (size-- > 0) {
				Pair cur = q.poll();
				
				for (int d = 0; d < 4; d++) {
					int nr = cur.r + dr[d];
					int nc = cur.c + dc[d];
					
					if (isOut(nr, nc)) continue; // 경계체크
					if (visited[nr][nc] == 0) { // 방문하지 않은 곳 중 
						if (map[nr][nc] == '2') { // 승객이 있는 지점이면
							visited[nr][nc] = moves;
							// 최소값 갱신을 위해 리스트에 담는다
							list.add(new Pair(nr, nc));
						} else if (map[nr][nc] == '0') { // 빈칸이면 이동
							visited[nr][nc] = moves;
							q.offer(new Pair(nr, nc));
						}
					}
				}
			}
			
			// 한 싸이클마다 list 크기 검사 => 태울 승객을 찾았냐?
			int lsize = list.size();
			if (lsize >= 1) {
				// 리스트 정렬해서 맨 앞 원소가 태울 승객
				list.sort(new Comparator<Pair>() {
					@Override
					public int compare(Pair o1, Pair o2) {
						if (o1.r == o2.r) {
							return Integer.compare(o1.c, o2.c);
						}
						
						return Integer.compare(o1.r, o2.r);
					}
				});
				
				Pair rp = list.get(0); // 태울 승객
				fuel -= visited[rp.r][rp.c] - 1; // 연료 소모
				// 택시 위치 태운 승객 위치로 수정
				taxi.r = rp.r;
				taxi.c = rp.c; 
				list.clear(); // 리스트 비우기
				map[rp.r][rp.c] = '0'; // 승객 마킹 해제 
				
				if (fuel >= 0) { // 연료 남았냐?
					return true; // 태울 승객을 찾았으므로 종료
				}
				
				return false; // 이동 도중 연료 다떨어짐
			}
		}
		
		return false;
	}
	
	static boolean findDestination() {
		int r = taxi.r;
		int c = taxi.c;
		// 목적지까지 이동
		Pair dest = new Pair(passengers[r][c].r, passengers[r][c].c); // 목적지
		Queue<Pair> q = new LinkedList<>();
		q.offer(new Pair(r, c));
		int[][] visited = new int[N][N]; // 이동시마다 visited 생성
		int moves = 1; // visited에 이동 횟수 마킹
		visited[r][c] = moves;
		
		while (!q.isEmpty()) {
			int size = q.size();
			moves++;
			
			// 사이즈만큼 돌면서(한 싸이클) 목적지 도착했는지 체크
			while (size-- > 0) {
				Pair cur = q.poll();
				
				// 목적지 도착 체크
				if (cur.r == dest.r && cur.c == dest.c) {
					fuel -= moves - 2; // 연료 소모
					// 택시 위치 수정
					taxi.r = cur.r;
					taxi.c = cur.c;
					
					if (fuel >= 0) { // 연료가 충분하면
						fuel += (moves - 2) * 2; // 소모한 연료의 2배 채우기
						
						return true;
					}
					
					return false; // 연료 부족
				}
				
  				for (int d = 0; d < 4; d++) {
   					int nr = cur.r + dr[d];
					int nc = cur.c + dc[d];
					
					if (isOut(nr, nc)) continue; // 경계체크
					if (visited[nr][nc] == 0) { // 방문하지 않은 곳 중 
						if (map[nr][nc] == '1') continue; // 벽이이면 이동x
						q.offer(new Pair(nr, nc));
						visited[nr][nc] = moves; // 방문 체크
					}
				}
			}
		}
		
		return false; // 목적지 못찾음
	}
	
	static boolean isOut(int r, int c) {
		return (r < 0 || r >= N || c < 0 || c >= N);
	}

}

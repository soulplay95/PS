/**
 * @문제 불!_G4
 * @날짜 210426
 * @분류 그래프 탐색, bfs
 * @조건
 * 4방
 * 불은 4방 모두로 확산
 * 미로의 가장자리에 가면 탈출
 * R, C <= 1000
 * @풀이
 * bfs
 * 매 이동마다 가장자리 체크(도착했는지) => 끝자락 가고 그 다음턴에 탈출하는지
 * 불 먼저 확산
 * @comment
 * 문제 조건 잘 따지기
 */

import java.util.*;
import java.io.*;

public class BOJ_4179 {
	
	// input, output handler
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
	
	static int ans;

	static int R, C;
	static char[][] map;
	static List<Pair> fires; // 불 좌표를 담는 리스트
	static Pair start;
	static int[] dr = {-1, 0, 1, 0}; // 상우하좌 시계방향
	static int[] dc = {0, 1, 0, -1}; // 상우하좌 시계방향
	static int moveCount;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		fires = new ArrayList<>();
		for (int r = 0; r < R; r++) {
			map[r] = br.readLine().toCharArray();
			for (int c = 0; c < C; c++) {
				if (map[r][c] == 'F') {
					fires.add(new Pair(r, c));
				} else if (map[r][c] == 'J') {
					start = new Pair(r, c);
				}
			}
		}
		
		moveCount = 1;
		if (isGoal(start.r, start.c)) {
			System.out.println("1");
			System.exit(0);
		}
		
		// print
		System.out.println(bfs() ? moveCount : "IMPOSSIBLE");
	}
	
	static boolean bfs() {
		Queue<Pair> q = new LinkedList<>(); // 지훈이 이동 좌표를 다루는 큐
		Queue<Pair> fq = new LinkedList<>(); // 불 확산을 다루는 큐
		boolean[][] visited = new boolean[R][C];
		
		q.offer(start);
		visited[start.r][start.c] = true; 
		// 불 좌표 큐에 넣기
		for (int i = 0, size = fires.size(); i < size; i++) {
			fq.offer(fires.get(i));
		}
		
		while (!q.isEmpty()) {
			// 불 먼저 확산
			int fqSize = fq.size();
			while (fqSize-- > 0) {
				Pair cur = fq.poll();
				
				for (int d = 0; d < 4; d++) {
					int nr = cur.r + dr[d];
					int nc = cur.c + dc[d];
					
					if (!isIn(nr, nc)) continue;
					if (map[nr][nc] == '.') { // 빈칸일때만 불 확산
						map[nr][nc] = 'F';
						fq.offer(new Pair(nr, nc));
					}
				}
			}
			
			// 지훈이 이동
			int qSize = q.size();
			while (qSize-- > 0) {
				Pair cur = q.poll();
				map[cur.r][cur.c] = '.'; 
				
				for (int d = 0; d < 4; d++) {
 					int nr = cur.r + dr[d];
					int nc = cur.c + dc[d];
					
					if (!isIn(nr, nc)) continue;
					if (visited[nr][nc]) continue;
					if (map[nr][nc] == '.') { // 빈칸일때만 이동
						if (isGoal(nr, nc)) {
							moveCount++;
							return true; // 도착 여부 체크
						}
						map[nr][nc] = 'J';
						visited[nr][nc] = true;
						q.offer(new Pair(nr, nc));
					}
				}
			}
			moveCount++;
		}
		
		return false;
	}
	
	// 경계 체크 메소드
	static boolean isIn(int r, int c) {
		return (r >= 0 && r < R && c >= 0 && c < C);
	}
	
	// 가장자리(도착)인지 체크하는 메소드
	static boolean isGoal(int r, int c) {
		return (r == 0 || r == R - 1) || (c == 0 || c == C - 1);
	}

}
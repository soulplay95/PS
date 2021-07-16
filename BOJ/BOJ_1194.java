/**
 * @문제 달이 차오른다, 가자._G1
 * @날짜 210716
 * @분류 
 * @조건
 * 미로탈출. 4방이동
 * 같은 타입 열쇠, 문 여러개 가능
 * 출구 여러개 가능
 * 열쇠 여러번 사용 가능
 * 이동 횟수 최소값 구하기
 * @풀이
 * BFS로 시뮬레이션 해보기
 * 열쇠 먹으면 전까지의 시간 누적하고, visited 초기화하고, bfs 다시 시작
 * 새로운 BFS의 모든 경우에 대해 최소 이동 거리 구하기
 * 탈출 불가 => 중간에 큐가 비면
 * @comment
 * 
 */

import java.util.*;
import java.io.*;

public class BOJ_1194 {
	
	// input, output handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	// -----------------------------------------------------------------------------
	
	// 좌표 클래스
	static class Pair {
		int r, c;
		int time; // 경과 시간

		public Pair(int r, int c, int time) {
			this.r = r;
			this.c = c;
			this.time = time;
		}
	}
	
	static int ans;
	static int N, M;
	static char[][] map;
	static Pair start; // 시작 좌표
	static int[] dr = {-1, 0, 1, 0}; // 상우하좌 - 시계방향
	static int[] dc = {0, 1, 0, -1}; // 상우하좌 - 시계방향
	static int keysInHand;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		keysInHand = 0;
		for (int r = 0; r < N; r++) {
			String row = br.readLine();
			for (int c = 0; c < M; c++) {
				map[r][c] = row.charAt(c);
				if (map[r][c] == '0') {
					start = new Pair(r, c, 0);
				}
			}
		} // input end

		ans = Integer.MAX_VALUE;
		move(start, 0);
		
		// print
		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}

	static void move(Pair newStart, int keys) {
		// bfs로 움직임을 구현한다.
		Queue<Pair> queue = new LinkedList<>();
		queue.offer(newStart);
		
		boolean[][] visited = new boolean[N][M];
		visited[newStart.r][newStart.c] = true;
		
		while (!queue.isEmpty()) {
			Pair cur = queue.poll();
			
			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				
				// 경계 벗어나거나 벽이면
				if (!isIn(nr, nc) || visited[nr][nc] || map[nr][nc] == '#') {
					continue;
				}
				
				// 도착하면
				if (map[nr][nc] == '1') {
					ans = Math.min(ans, cur.time + 1);
					return;
				}
				
				// key 이면
				if (map[nr][nc] >= 'a' && map[nr][nc] <= 'f') {
					// key 저장하고 새로운 bfs 시작
					if ((keysInHand & (1 << map[nr][nc] - 'a')) != 0) { // 이미 그 key를 먹은 상태이면 
						continue;
					} else {
						keysInHand = (keysInHand | (1 << map[nr][nc] - 'a'));
						move(new Pair(nr, nc, cur.time + 1), (keys | (1 << map[nr][nc] - 'a')));
					}
				}
				
				// 문이면
				if (map[nr][nc] >= 'A' && map[nr][nc] <= 'F') {
					if ((keys & (1 << map[nr][nc] - 'A')) == 0) { // 열쇠가 없으면
						continue;
					}
				}
				
				// 정상 진행
				queue.offer(new Pair(nr, nc, cur.time + 1));
				visited[nr][nc] = true;
			}
		}
	}
	
	// map 경계 체크 메소드
	static boolean isIn(int r, int c) {
		return (r >= 0 && r < N && c >= 0 && c < M);
	}
	
}

/**
 * @문제 말이 되고픈 원숭이_G4
 * @날짜 210713
 * @분류 BFS
 * @조건
 * # 말은 장애물을 뛰어 넘을 수 있다.
 * # 1 <= R, C <= 200
 * # 0 <= K <= 30
 * @풀이
 * # K개의 차원을 넓혀서 visited를 관리한다.
 * @comment
 * R, C 순서 바뀌는거 항상 조심!!!
 */

import java.util.*;
import java.io.*;

public class BOJ_1600 {
	
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	// -----------------------------------------------------------------------------

	static class Pair {
		int r, c, moves, k;

		public Pair(int r, int c, int moves, int k) {
			this.r = r;
			this.c = c;
			this.moves = moves;
			this.k = k;
		}
	}
	
	static int K, R, C;
	static char[][] map;
	static boolean[][][] visited;
	
	static int[] dr = {-1, 0, 1, 0}; // 상우하좌 시계방향
	static int[] dc = {0, 1, 0, -1};
	static int[] ddr = {-2, -1, 1, 2, 2, 1, -1, -2}; // 말의 움직임. 1시부터 시계방향
	static int[] ddc = {1, 2, 2, 1, -1, -2, -2, -1};
	
	public static void main(String[] args) throws IOException {
		K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		// init
		map = new char[R][C];
		visited = new boolean[K + 1][R][C];
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < C; c++) {
				map[r][c] = st.nextToken().charAt(0);
			}
		} // input end
		
		// print
		System.out.println(solve());
	}
	
	static int solve() {
		// (0, 0)에서 출발
		Queue<Pair> q = new LinkedList<>();
		q.offer(new Pair(0, 0, 0, 0));
		visited[0][0][0] = true;
		int nr = 0, nc = 0;
		
		while (!q.isEmpty()) {
			Pair cur = q.poll();
			
			// 종료조건 체크
			if (cur.r == R - 1 && cur.c == C - 1) {
				return cur.moves;
			}
			
			// 말의 움직임으로 이동할 수 있는 횟수가 남아 있으면 말처럼 이동하거나 그냥 이동한다.
			if (cur.k < K) {
				// 말처럼 이동
				for (int d = 0; d < 8; d++) {
					nr = cur.r + ddr[d];
					nc = cur.c + ddc[d];
					
					if (isOut(nr, nc)) continue; // 경계체크
					if (!visited[cur.k + 1][nr][nc] && map[nr][nc] == '0') { // 평지만 이동할 수 있다.
						q.offer(new Pair(nr, nc, cur.moves + 1, cur.k + 1)); // 한번 소모
						visited[cur.k + 1][nr][nc] = true;
					}
				}
				
				// 그냥 이동
				for (int d = 0; d < 4; d++) {
					nr = cur.r + dr[d];
					nc = cur.c + dc[d];
					
					if (isOut(nr, nc)) continue; // 경계체크
					if (!visited[cur.k][nr][nc] && map[nr][nc] == '0') { // 평지만 이동할 수 있다.
						q.offer(new Pair(nr, nc, cur.moves + 1, cur.k));
						visited[cur.k][nr][nc] = true;
					}
				}
			} else { // 기회를 다 썼으면 4방 인접만 이동한다.
				for (int d = 0; d < 4; d++) {
					nr = cur.r + dr[d];
					nc = cur.c + dc[d];
					
					if (isOut(nr, nc)) continue; // 경계체크
					if (!visited[cur.k][nr][nc] && map[nr][nc] == '0') { // 평지만 이동할 수 있다.
						q.offer(new Pair(nr, nc, cur.moves + 1, cur.k));
						visited[cur.k][nr][nc] = true;
					}
				}
			}
		}
		
		return -1;
	}
	
	static boolean isOut(int r, int c) {
		return (r < 0 || r >= R || c < 0 || c >= C);
	}

}

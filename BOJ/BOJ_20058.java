/**
 * @문제 마법사 상어와 파이어스톰_G4
 * @날짜 210720
 * @분류 구현, 그래프 이론, 그래프 탐색, 시뮬레이션
 * @조건
 * 
 * @풀이
 * # 90도 회전 : map 새로 만들어서 구현
 * # 얼음 양 줄이기 : 얼음 있는 칸에서 4방 탐색
 * @comment
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_20058 {
	
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

	static int N, N2, Q, L[];
	static int[][] map;
	static boolean[][] visited;

	static int[] dr = {-1, 0, 1, 0}; // 상우하좌
	static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		// init
		N2 = (int)Math.pow(2, N);
		map = new int[N2][N2];
		L = new int[Q];
		for (int r = 0; r < N2; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < N2; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < Q; i++) {
			L[i] = Integer.parseInt(st.nextToken());
		} // input end

		solve();

		// print
		System.out.println(sb.toString());
	}
	
	static void solve() {
		for (int i = 0; i < Q; i++) {
			// 90도 회전
			rotate(L[i]);

			// 얼음 녹이기
			melt();
		}

		// 결과 2가지 구하기
		int sum = 0;
		int max = 0;
		visited = new boolean[N2][N2];
		for (int r = 0; r < N2; r++) {
			for (int c = 0; c < N2; c++) {
				sum += map[r][c];
				if (map[r][c] > 0) {
					max = Math.max(max, bfs(r, c));
				}
			}
		}

		sb.append(sum).append('\n').append(max);
	}

	static int bfs(int r, int c) {
		int ret = 1;
		Queue<Pair> q = new LinkedList<>();
		q.offer(new Pair(r, c));
		visited[r][c] = true;

		while (!q.isEmpty()) {
			Pair cur = q.poll();

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				if (isOut(nr, nc)) continue;
				if (!visited[nr][nc] && map[nr][nc] > 0) {
					ret++;
					q.offer(new Pair(nr, nc));
					visited[nr][nc] = true;
				}
			}
		}

		return ret;
	}

	static void rotate(int l) {
		int[][] new_map = new int[N2][N2];
		int l2 = (int)Math.pow(2, l);
		if (l2 == 1) {
			return;
		}

		for (int r = 0; r < N2; r += l2) {
			for (int c = 0; c < N2; c += l2) {
				// 한 셀을 90도 회전
				for (int r2 = 0; r2 < l2; r2++) {
					for (int c2 = 0; c2 < l2; c2++) {
						new_map[r + c2][c + l2 - r2 - 1] = map[r + r2][c + c2];
					}
				}
			}
		}

		map = new_map;
	}

	static void melt() {
		int[][] new_map = new int[N2][N2];
		// copy
		for (int r = 0; r < N2; r++) {
			new_map[r] = Arrays.copyOf(map[r], map[r].length);
		}

		for (int r = 0; r < N2; r++) {
			for (int c = 0; c < N2; c++) {
				if (map[r][c] > 0) { // 얼음이 있는 칸이면
					int cnt = 0;
					// 4방탐색
					for (int d = 0; d < 4; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];

						if (isOut(nr, nc)) continue; // 경계체크
						if (map[nr][nc] > 0) cnt++;
					}

					// 개수 체크
					if (cnt < 3) {
						new_map[r][c]--;
					}
				}
			}
		}

		map = new_map;
	}

	static boolean isOut(int r, int c) {
		return (r < 0 || r >= N2 || c < 0 || c >= N2);
	}

}

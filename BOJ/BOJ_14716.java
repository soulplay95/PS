/**
 * @문제 현수막_S1
 * @날짜 211029
 * @분류 그래프 탐색, DFS, BFS
 * @조건
 * # 1 <= 맵 크기 M, N <= 250
 * @풀이
 * # 방문하지 않았고 1인 부분에서 dfs 돌면서 방문 체크하기
 * @comment
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_14716 {

	static int M, N, ans;
	static int[][] map;

	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1}; // 상부터 시계방향
	static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		// init
		map = new int[M][N];
		ans = 0;

		for (int r = 0; r < M; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		solve();

		// print
		System.out.println(ans);
	}

	static void solve() {
		for (int r = 0; r < M; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c] == 1) {
					ans++;
					dfs(r, c);
				}
			}
		}
	}

	static void dfs(int r, int c) {
		map[r][c] = 2; // 방문 체크

		for (int d = 0; d < 8; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (nr >= 0 && nr < M && nc >= 0 && nc < N && map[nr][nc] == 1) {
				dfs(nr, nc);
			}
		}
	}

}

/**
 * @문제 농장 관리_G5
 * @날짜 211104
 * @분류 그래프 탐색, BFS, DFS
 * @조건
 * # 1 <= 맵 크기 <= 100
 * @풀이
 * # DFS
 * # 꼭대기의 개수를 센다.
 * @comment
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_1245 {

	static class Pair {
		int r, c;

		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static int N, M;
	public static int[][] map;
	public static boolean[][] visited;
	public static boolean isTop;

	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1}; // 상부터 시계방향
	static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// init
		map = new int[N][M];
		visited = new boolean[N][M];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// print
		System.out.println(solve());
	}

	static int solve() {
		int ans = 0;

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (!visited[r][c]) {
					isTop = true;
					dfs(r, c);
					if (isTop) ans++;
				}
			}
		}

		return ans;
	}

	static void dfs(int r, int c) {
		visited[r][c] = true;
		int height = map[r][c];

		for (int d = 0; d < 8; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (isOut(nr, nc)) continue;
			if (map[nr][nc] > height) {
				isTop = false;
			}
			if (!visited[nr][nc] && map[nr][nc] == height) {
				dfs(nr, nc);
			}
		}
	}

	static boolean isOut(int r, int c) {
		return (r < 0 || r >= N || c < 0 || c >= M);
	}

}

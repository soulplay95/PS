/**
 * @문제 내리막 길_G4
 * @날짜 211112
 * @분류 
 * @조건
 * # 1 <= 맵 크기(M, N) <= 500
 * # 1 <= 각 칸의 높이 <= 10000
 * @풀이
 * # DFS + DP
 * # DP[r][c] => (r, c)에서 도착지까지 가는 경로의 개수
 * @comment
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_1520 {

	static int N, M;
	static int[][] map, dp;

	static int[] dr = {-1, 0, 1, 0}; // 상우하좌 시계방향
	static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// init
		map = new int[N][M];
		dp = new int[N][M];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			Arrays.fill(dp[r], -1);
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// print
		System.out.println(dfs(0, 0));
	}

	static int dfs(int r, int c) {
		// base condition
		if (r == N - 1 && c == M - 1) {
			return 1;
		}

		// memoization
		if (dp[r][c] != -1) {
			return dp[r][c];
		}

		// memo가 안됐다 => 현재 위치부터 dfs시작
		dp[r][c] = 0;
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (isOut(nr, nc)) continue;
			if (map[nr][nc] < map[r][c]) { // 높이가 낮은 칸만 탐색
				dp[r][c] += dfs(nr, nc);
			}
		}

		return dp[r][c];
	}

	static boolean isOut(int r, int c) {
		return (r < 0 || r >= N || c < 0 || c >= M);
	}

}

package study.week40;

/**
 * @문제 욕심쟁이 판다_G3
 * @날짜 211024
 * @분류 DP, 그래프 탐색, DFS
 * @조건
 * # 1 <= 맵 크기 n <= 500
 * @풀이
 * # 각 좌표까지 상하좌우로부터 도착할때의 이동한 칸의 최대값을 갱신한다.
 * # dp[from][r][c] : from 방향에서 (r, c)에 도착할때 이동한 칸수의 최대값
 * # 모든 좌표에서 dfs 타고 들어가면서 dp의 최대값을 채워간다.
 * @comment
 * # dp[r][c] : 어딘가로부터 (r, c)로 오는 경로 중 최대 이동칸 수
 */

import java.util.*;
import java.io.*;

public class BOJ_1937 {

	static int N;
	static int[][] map;
	static int[][] dp;

	static int[] dr = {-1, 0, 1, 0}; // 상우하좌 시계방향
	static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());

		// init
		map = new int[N][N];
		dp = new int[N][N];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		} // input end

		// print
		System.out.println(solve());
	}

	static int solve() {
		int max = 0;

		// 1. 모든 좌표에서 dfs를 돌아 dp를 갱신해간다.
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				max = Math.max(max, dfs(r, c));
			}
		}

		return max;
	}

	static int dfs(int r, int c) {
		if (dp[r][c] != 0) {
			return dp[r][c];
		}

		dp[r][c] = 1;
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (isOut(nr, nc)) continue; // 경계 체크
			if (map[nr][nc] > map[r][c]) { // 방문 체크 & 조건 체크
				dp[r][c] = Math.max(dp[r][c], dfs(nr, nc) + 1);
			}
		}

		return dp[r][c];
	}

	static boolean isOut(int r, int c) {
		return (r < 0 || r >= N || c < 0 || c >= N);
	}

}

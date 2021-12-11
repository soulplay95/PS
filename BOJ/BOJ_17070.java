package study.week46;

/**
 * @문제 파이프 옮기기 1_G5
 * @날짜 211211
 * @분류 DP, 그래프 탐색
 * @조건
 * # 3 <= 맵 크기 (N) <= 16
 * @풀이
 * # DP
 * dp[TYPE][r][c] : (r, c)를 끝점으로 TYPE 모양으로 놓여있게 이동시키는 방법의 수
 * dp[가로][r][c] = dp[가로][r][c - 1] + dp[대각선][r][c - 1]
 * dp[세로][r][c] = dp[세로][r - 1][c] + dp[대각선][r - 1][c]
 * dp[대각선][r][c] = dp[가로][r - 1][c - 1] + dp[세로][r - 1][c - 1] + dp[대각선][r - 1][c - 1]
 * => 정답 : dp[가로][N][N] + dp[세로][N][N] + dp[대각선][N][N]
 * @comment
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_17070 {

	private static int N;
	private static int[][] map;
	private static int[][][] dp;

	private static final int HORIZONTAL = 0;
	private static final int VERTICAL = 1;
	private static final int DIAGONAL = 2;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		// init
		map = new int[N][N];
		dp = new int[3][N][N];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		solve();

		// print
		System.out.println(dp[HORIZONTAL][N - 1][N - 1] + dp[VERTICAL][N - 1][N - 1] + dp[DIAGONAL][N - 1][N - 1]);
	}

	private static void solve() {
		dp[HORIZONTAL][0][1] = 1;

		for (int r = 0; r < N; r++) {
			for (int c = 2; c < N; c++) { // 초기에 가로 방향으로 끝점이 (0, 1)에 놓여 있으므로 0열에 절대 놓이지 않는다.
				if (map[r][c] == 1) continue;
				dp[HORIZONTAL][r][c] = dp[HORIZONTAL][r][c - 1] + dp[DIAGONAL][r][c - 1];
				if (r == 0) continue;
				dp[VERTICAL][r][c] = dp[VERTICAL][r - 1][c] + dp[DIAGONAL][r - 1][c];
				if (map[r - 1][c] == 1 || map[r][c - 1] == 1) continue;
				dp[DIAGONAL][r][c] = dp[HORIZONTAL][r - 1][c - 1] + dp[VERTICAL][r - 1][c - 1] + dp[DIAGONAL][r - 1][c - 1];
			}
		}
	}

}

package study.week34;

/**
 * @문제 1학년_G5
 * @날짜 210906
 * @분류 DP
 * @조건
 * # 3 <= N <= 100
 * # 0 <= 연산 결과 <= 20
 * @풀이
 * # dp[i][j] = i번째 입력 까지의 결과가 j(0 ~ 20)인 경우의 수
 * @comment
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_5557 {
	
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	// -----------------------------------------------------------------------------

	static int N, input[];
	static long[][] dp;
	static long ans;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		// init
		input = new int[N];
		dp = new long[N - 1][21];
		st = new StringTokenizer(br.readLine(), " ");
		for (int n = 0; n < N; n++) {
			input[n] = Integer.parseInt(st.nextToken());
		} // input end

		solve();

		// print
		System.out.println(ans);
	}
	
	static void solve() {
		dp[0][input[0]] = 1;

		ans = getDp(N - 2, input[N - 1]); // 마지막 입력 전까지의 연산 결과가 마지막 수인 경우의수 구하기
	}

	static long getDp(int depth, int result) {
		if (depth < 0 || result < 0 || result > 20) { // 중간 연산 결과가 0 ~ 20일때만
			return 0;
		}
		if (dp[depth][result] > 0) { // 계산된 값이 있으면
			return dp[depth][result];
		}

		// 현재 순서의 수를 더하거나 빼기 전의 경우의수
		return dp[depth][result] = getDp(depth - 1, result + input[depth]) +
				getDp(depth - 1, result - input[depth]);
	}

}

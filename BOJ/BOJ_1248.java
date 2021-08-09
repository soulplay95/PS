package study.week30;

/**
 * @문제 맞춰봐_G3
 * @날짜 210809
 * @분류 백트래킹
 * @조건
 * # 1 <= 수열 크기 N <= 10
 * # -10 <= 수 <= 10
 * @풀이
 * # 주어진 배열의 조건을 만족하지 않으면 백트래킹
 * @comment
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1248 {
	
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	// -----------------------------------------------------------------------------

	static int N, ans[];
	static char[][] condition;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		// init
		ans = new int[N];
		condition = new char[N][N];
		char[] input = br.readLine().toCharArray();
		// index mapping
		int index = 0;
		for (int r = 0; r < N; r++) {
			int c = r;
			for (; c < N; c++) {
				condition[r][c] = input[index++];
			}
		} // input end

		solve();
	}
	
	static void solve() {
		dfs(0);
	}

	static void append() {
		for (int i = 0; i < N; i++) {
			sb.append(ans[i]).append(" ");
		}
	}

	static void dfs(int depth) { // sum : 누적합
		if (depth == N) {
			// 조건 배열에 맞는 수를 전부 뽑음. 종료.
			append();
			System.out.println(sb.toString());
			System.exit(0);
		}

		// 1. 부호로 거른다.
		int sign = condition[depth][depth] == '+' ? 1 : (condition[depth][depth] == '0' ? 0 : -1);

		// 1 ~ 10 or -10 ~ -1 or 0
		if (sign == 0) {
			ans[depth] = 0;
			dfs(depth + 1);
		} else {
			for (int n = 1; n <= 10; n++) {
				ans[depth] = sign * n;
				if (check(depth)) dfs(depth + 1);
			}
		}
	}

	static boolean check(int depth) {
		// 2. 누적합으로 거른다.
		int sum = ans[depth];
		for (int r = depth - 1; r >= 0; r--) {
			char sign_sum = condition[r][depth];
			// 실제 뽑은 수들의 누적합 부호를 구함
			sum += ans[r];
			char real_sign_sum = sum > 0 ? '+' : (sum == 0 ? '0' : '-');
			if (sign_sum != real_sign_sum) { // 조건 만족
				return false;
			}
		}

		return true;
	}

}

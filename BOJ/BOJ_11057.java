package algorithm.DP;

/**
 * @문제 오르막 수_S1
 * @날짜 211102
 * @분류 DP
 * @조건
 * #
 * @풀이
 * @comment
 * #
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11057 {
	
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N;
	static int[] A;
	static int[][] Dy;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		// init
		A = new int[N + 1];
		Dy = new int[N + 1][10];
		pro();
	}

	static void pro() {
		// 초기값 구하기
		for (int num = 0; num <= 9; num++) {
			Dy[1][num] = 1;
		}

		// 점화식을 토대로 Dy 배열 채우기
		for (int len = 2; len <= N; len++) {
			for (int num = 0; num <= 9; num++) {
				// Dy[len][num] := ?
				for (int prev = 0; prev <= num; prev++) {
					Dy[len][num] += Dy[len - 1][prev];
					Dy[len][num] %= 10007;
				}
			}
		}

		// Dy 배열로 정답 계산하기
		int ans = 0;
		for (int num = 0; num <= 9; num++) {
			ans += Dy[N][num];
			ans %= 10007;
		}

		System.out.println(ans);
	}
	
}

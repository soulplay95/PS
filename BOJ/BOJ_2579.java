package algorithm.DP;

/**
 * @문제 계단 오르기_S3
 * @날짜 211025
 * @분류 DP
 * @조건
 * #
 * @풀이
 * # Dy[i] : 2xi 타일을 채우는 경우의 수
 * # Dy[i] = Dy[i - 1] + Dy[i - 2]
 * @comment
 * #
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2579 {
	
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
		Dy = new int[N + 1][2];
		for (int i = 1; i <= N; i++) {
			A[i] = Integer.parseInt(br.readLine());
		}
		pro();
	}

	static void pro() {
		// 초기값 구하기
		Dy[1][0] = 0;
		Dy[1][1] = A[1];

		if (N >= 2) {
			Dy[2][0] = A[2];
			Dy[2][1] = A[1] + A[2];
		}

		// 점화식을 토대로 Dy 배열 채우기
		for (int i = 3; i <= N; i++) {
			Dy[i][0] = Math.max(Dy[i - 2][0] + A[i], Dy[i - 2][1] + A[i]);
			Dy[i][1] = Dy[i - 1][0] + A[i];
		}

		// Dy 배열로 정답 계산하기
		int ans = Math.max(Dy[N][0], Dy[N][1]);

		System.out.println(ans);
	}
	
}

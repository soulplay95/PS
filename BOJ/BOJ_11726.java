package algorithm.DP;

/**
 * @문제 2xn 타일링_S3
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

public class BOJ_11726 {
	
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N;
	static int[] Dy;

	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		pro();
	}

	static void pro() {
		Dy = new int[1005];
		// 초기값 구하기
		Dy[1] = 1;
		Dy[2] = 2;

		// 점화식을 토대로 Dy 배열 채우기
		for (int i = 3; i <= N; i++) {
			Dy[i] = (Dy[i - 1] + Dy[i - 2]) % 10007;
		}

		System.out.println(Dy[N]);
	}
	
}

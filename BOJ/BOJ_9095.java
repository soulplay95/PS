package algorithm.DP;

/**
 * @문제 1, 2, 3 더하기_S3
 * @날짜 211030
 * @분류 DP
 * @조건
 * #
 * @풀이
 * # Dy[i] : i를 1, 2, 3의 합으로 나타내는 경우의 수
 * # Dy[i] = Dy[i - 1] + Dy[i - 2] + Dy[i - 3]
 * @comment
 * #
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_9095 {
	
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int[] Dy;

	public static void main(String[] args) throws Exception {
		preprocess(); // 전처리로 Dy 배열 계산 해놓기
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());

			// TC append
			sb.append(Dy[N]).append("\n");
		}
		
		// print
		System.out.println(sb.toString());
	}

	static void preprocess() {
		Dy = new int[15];
		// 초기값 구하기
		Dy[1] = 1;
		Dy[2] = 2;
		Dy[3] = 4;

		// 점화식을 토대로 Dy 배열 채우기
		for (int i = 4; i <= 11; i++) {
			Dy[i] = Dy[i - 1] + Dy[i - 2] + Dy[i - 3];
		}
	}
	
}

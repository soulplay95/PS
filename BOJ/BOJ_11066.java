package algorithm.DP;

/**
 * @문제 파일 합치기_G3
 * @날짜 211028
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

public class BOJ_11066 {
	
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int K;
	static int[] num;
	static int[][] Dy, sum;

	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			K = Integer.parseInt(br.readLine());
			// init
			num = new int[K + 1];
			sum = new int[K + 1][K + 1];
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 1; i <= K; i++) {
				num[i] = Integer.parseInt(st.nextToken());
			}
			pro();
		}
	}

	static void pro() {
		preprocess();

		Dy = new int[K + 1][K + 1];

		for (int len = 2; len <= K; len ++){
			for (int i = 1; i <= K - len + 1; i++){
				int j = i + len - 1;
				Dy[i][j] = Integer.MAX_VALUE;
				for (int k = i; k < j; k++){
					Dy[i][j] = Math.min(Dy[i][j], Dy[i][k] + Dy[k + 1][j] + sum[i][j]);
				}
			}
		}

		System.out.println(Dy[1][K]);
	}

	static void preprocess(){
		for (int i = 1; i <= K; i++){
			for (int j = i; j <= K; j++){
				sum[i][j] = sum[i][j - 1] + num[j];
			}
		}
	}

}

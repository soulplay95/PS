package study.week43;

/**
 * @문제 가장 긴 바이토닉 부분 수열_G3
 * @날짜 211122
 * @분류 DP
 * @조건
 * # 1 <= 수열의 크기 (N) <= 1000
 * # 1 <= 수열을 이루는 수 (Ai) <= 1000
 * @풀이
 * # LIS + LDS
 * @comment
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_11054 {

	static int N;
	static int[] input, lis, lds;

	public static void main(String[] args) throws IOException, NumberFormatException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		// init
		input = new int[N];
		lis = new int[N];
		lds = new int[N];

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
			// 1로 초기화
			lis[i] = 1;
			lds[i] = 1;
		}

		getLIS();
		getLDS();

		int max = 0;
		for (int i = 0; i < N; i++) {
			if (lis[i] + lds[i] > max) {
				max = lis[i] + lds[i];
			}
		}

		// print
		System.out.println(max - 1);
	}

	static void getLIS() {
		for (int i = 0; i < N; i++) {
			// i 이전의 원소들 탐색
			for (int j = 0; j < i; j++) {
				if (input[j] < input[i] && lis[i] < lis[j] + 1) {
					lis[i] = lis[j] + 1;
				}
			}
		}
	}

	static void getLDS() {
		for (int i = N - 1; i >= 0; i--) {
			// i 이후의 원소들 탐색
			for (int j = N - 1; j > i; j--) {
				if (input[j] < input[i] && lds[i] < lds[j] + 1) {
					lds[i] = lds[j] + 1;
				}
			}
		}
	}

}

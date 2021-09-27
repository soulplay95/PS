/**
 * @문제 수 찾기_S4
 * @날짜 210928
 * @분류 이분 탐색
 * @조건
 * # 1 <= 개수(N) <= 10만
 * # 1 <= M <= 10만
 * # -2^31 <= 정수 <= 2^31
 * @풀이
 * # 이분 탐색
 * # O(MlogN)
 * @comment
 * # Set.contains()로도 풀이 가능
 */

import java.util.*;
import java.io.*;

public class BOJ_1920 {

	static int N, M;
	static long[] input;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		// init
		input = new long[N];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			input[i] = Long.parseLong(st.nextToken());
		}

		Arrays.sort(input); // 이분 탐색을 위해 오름차순 정렬

		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < M; i++) {
			Long num = Long.parseLong(st.nextToken());
			sb.append(isIn(num)).append("\n");
		}

		sb.setLength(sb.length() - 1);
		// print
		System.out.print(sb.toString());
	}

	static int isIn(Long num) {
		// 이분 탐색으로 num 찾기
		int L = 0, R = N - 1;

		while (L <= R) {
			int mid = (L + R) / 2;

			if (input[mid] == num) {
				return 1;
			}

			if (input[mid] > num) {
				R = mid - 1;
			} else {
				L = mid + 1;
			}
		}

		return 0;
	}

}

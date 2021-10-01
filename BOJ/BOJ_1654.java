/**
 * @문제 랜선 자르기_S3
 * @날짜 211001
 * @분류 
 * @조건
 * # 1 <= 이미 가지고 있는 랜선의 개수 (K) <= 1만
 * # 1 <= 만들어야 하는 랜선의 개수 (N) <= 100만
 * # 랜선의 길이: Integer 범위 자연수
 * @풀이
 * # 이분 탐색으로 1 ~ Integer.MAX(주어진 랜선 길이 중 최대값) 사이의 랜선 길이 하나를 정해 몇개를 만들 수 있는지 센다.
 * # O(K * 31)
 * @comment
 * # 처음에 mid 구할때 1 + Integer.MAX_VALUE 하므로 오버플로우남. 조심
 */

import java.util.*;
import java.io.*;

public class BOJ_1654 {

	static int K, N;
	static int[] input;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		K = sc.nextInt();
		N = sc.nextInt();
		// init
		input = new int[K];

		for (int i = 0; i < K; i++) {
			input[i] = sc.nextInt();
		}

		// print
		System.out.println(solve());
		sc.close();
	}

	static long solve() {
		long L = 1, R = Integer.MAX_VALUE;
		long ans = 1;

		while (L <= R) {
			long mid = (L + R) / 2;

			if (getCount(mid) >= N) {
				ans = mid;
				L = mid + 1;
			} else {
				R = mid - 1;
			}
		}

		return ans;
	}

	static long getCount(long length) {
		long sum = 0;

		for (int i = 0; i < K; i++) {
			sum += input[i] / length;
		}

		return sum;
	}

}

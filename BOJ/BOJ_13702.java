/**
 * @문제 이상한 술집_S3
 * @날짜 211006
 * @분류 
 * @조건
 * # 1 <= 주전자 개수 N <= 1만
 * # 1 <= 사람 수 <= 100만
 * @풀이
 * # 가능한 막걸리의 용량(0~Integer.MAX_VALUE)을 이분탐색하여 몇명에게 나눠줄 수 있는지 확인한다.
 * # O(Nlog31)
 * @comment
 * # long 조심
 * # 나눌때는 분모가 0이 되는지 항상 고려
 */

import java.util.*;
import java.io.*;

public class BOJ_13702 {

	static int N, K;
	static int[] input;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		// init
		input = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			input[i] = sc.nextInt();
		}

		// print
		System.out.println(solve());

		sc.close();
	}

	static long solve() {
		long L = 0, R = Integer.MAX_VALUE;
		long ans = 0;

		while (L <= R) {
			long mid = (L + R) / 2;

			if (getCounts(mid) >= K) {
				ans = mid;
				L = mid + 1;
			} else {
				R = mid - 1;
			}
		}

		return ans;
	}

	static long getCounts(long limit) {
		if (limit == 0) {
			return 0;
		}

		int sum = 0;

		for (int i = 1; i <= N; i++) {
			sum += input[i] / limit;
		}

		return sum;
	}

}

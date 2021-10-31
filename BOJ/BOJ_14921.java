package study.week41;

/**
 * @문제 용액 합성하기_G5
 * @날짜 211031
 * @분류 두 포인터
 * @조건
 * # 2 <= 용액 수 N <= 10만
 * @풀이
 * # 용액 하나를 뽑았을 때 특성값을 a라하면 나머지 용액 b는 특성값 -a와 가장 가까운 특성값을 가진 용액으로 찾는다. => 이분탐색
 * @comment
 * # O(NlogN)
 */

import java.util.*;
import java.io.*;

public class BOJ_14921 {

	static int N, ans;
	static int[] input;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		// init
		input = new int[N + 1];
		ans = Integer.MAX_VALUE;
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}

		solve();

		// print
		System.out.println(ans);
	}

	static void solve() {
		int ans_abs = Integer.MAX_VALUE;

		for (int a = 1; a <= N - 1; a++) {
			// b용액은 -a값과 가장 가까운(b후보 -a b후보) 특성값을 가진 용액일 것이다.
			int b = lowerBound(a + 1, -input[a]);

			if (b <= N) {
				int sum_abs = Math.abs(input[a] + input[b]);
				if (sum_abs < ans_abs) { // 0에 더 가까우면
					ans_abs = sum_abs;
					ans = input[a] + input[b];
				}
			}

			// target 보다 큰 수를 못찾은 경우
			if (a < b - 1 && Math.abs(input[a] + input[b - 1]) < ans_abs) {
				ans_abs = Math.abs(input[a] + input[b - 1]);
				ans = input[a] + input[b - 1];
			}
		}
	}

	// target 이상의 수 중 최소값의 인덱스를 리턴
	static int lowerBound(int L, int target) {
		int R = N;
		int res = N + 1;

		while (L <= R) {
			int mid = (L + R) / 2;

			if (input[mid] >= target) {
				res = mid;
				R = mid - 1;
			} else {
				L = mid + 1;
			}
		}

		return res;
	}

}

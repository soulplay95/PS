/**
 * @문제 기타 레슨_S1
 * @날짜 211005
 * @분류 이분 탐색
 * @조건
 * # 1 <= 강의의 수 N <= 10만
 * # 1 <= 블루레이 개수 M <= N
 * # 1 <= 강의의 길이 <= 1만
 * @풀이
 * # 이분 탐색
 * # 1~10억의 가능한 블루레이 크기를 이분탐색으로 구하고 앞에서부터 이 크기에 맞춰 최대한 담았을 때 M개가 되는지 확인한다.
 * @comment
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_2343 {

	static int N, M;
	static int[] input;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		// init
		input = new int[N];
		for (int i = 0; i < N; i++) {
			input[i] = sc.nextInt();
		} // input end

		// print
		System.out.println(solve());

		sc.close();
	}

	static int solve() {
		int L = input[0], R = 1000000000;
		int ans = 1;

		for (int i = 1; i < N; i++) {
			L = Math.max(L, input[i]);
		}

		while (L <= R) {
			int mid = (L + R) / 2;

			if (determination(mid)) {
				ans = mid;
				R = mid - 1;
			} else {
				L = mid + 1;
			}
		}

		return ans;
	}

	static boolean determination(int mid) {
		int result = 1;
		int sum = 0;

		for (int i = 0; i < N; i++) {
			if (sum + input[i] > mid) {
				result++;
				sum = input[i];
			} else {
				sum += input[i];
			}
		}

		return result <= M;
	}

}

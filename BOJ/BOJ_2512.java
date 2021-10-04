/**
 * @문제 예산_S3
 * @날짜 211004
 * @분류 이분 탐색, 매개 변수 탐색
 * @조건
 * # 3 <= 지방의 수 N <= 1만
 * # 1 <= 각 지방의 요청된 예산 <= 10만
 * # N <= 총 예산 <= 10억
 * @풀이
 * # 1. 모든 요청이 배정될 수 있는지 체크한다. => true: 요청 예산 중 최대값 리턴
 * # 2. false: 이분 탐색을 통하여 [1, 10만] 사이의 상한액을 측정 후, 총 예산 이하가 되는 최대 금액을 찾는다.
 * # 2-1. 총 예산 이하이면 상한액을 높이고 초과이면 상한액을 낮추는 방식으로 진행한다.
 * @comment
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_2512 {

	static int N, M, ans, max;
	static int[] input;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		// init
		input = new int[N];
		max = 0;
		int sum = 0;
		for (int i = 0; i < N; i++) {
			input[i] = sc.nextInt();
			sum += input[i];
			max = Math.max(max, input[i]);
		}
		M = sc.nextInt();

		// 1. 모든 요청이 배정될 수 있는지 체크
		if (sum <= M) {
			ans = max;
		} else {
			solve();
		}

		// print
		System.out.println(ans);

		sc.close();
	}

	static void solve() {
		// 1 ~ 입력 최대값 사이의 금액 중 상한액을 측정
		int L = 1, R = max;

		while (L <= R) {
			int mid = (L + R) / 2;

			if (getTotalBudget(mid) <= M) { // 총 예산을 넘지 않으면
				ans = mid;
				L = mid + 1; // 상한액 범위를 큰 값쪽으로
			} else {
				R = mid - 1;
			}
		}
	}

	static int getTotalBudget(int mid) {
		int sum = 0;

		for (int i = 0; i < N; i++) {
			if (input[i] > mid) {
				sum += mid;
			} else {
				sum += input[i];
			}
		}

		return sum;
	}

}

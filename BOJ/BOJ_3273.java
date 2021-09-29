/**
 * @문제 두 수의 합_S3
 * @날짜 210929
 * @분류 정렬, 두 포인터
 * @조건
 * # 1 <= 수의 개수 (N) <= 10만
 * @풀이
 * # 이분 탐색
 * # 1. 주어진 수열을 오름차순 정렬한다.
 * # 2. A 수열에서 하나를 골라 X - Ai 값이 나머지 수열에 있는지 이분탐색한다.
 * @comment
 * # 전 단계에서 (답을 찾았다면) 구한 인덱스 근처에서 확인하는 방식의 풀이??
 */

import java.util.*;
import java.io.*;

public class BOJ_3273 {

	static int N, X;
	static int[] input;
	static int ans;

	public static void main(String[] args) throws IOException, NumberFormatException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		// init
		input = new int[N];
		ans = 0;

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		X = Integer.parseInt(br.readLine());

		solve();

		// print
		System.out.println(ans);
	}

	static void solve() {
		// 주어진 수열을 오름차순 정렬한다.
		Arrays.sort(input);

		for (int i = 0; i < N; i++) {
			int target = X - input[i];

			if (target < 0) {
				return;
			}
			ans += isIn(i + 1, target);
		}
	}

	static int isIn(int L, int target) {
		int R = N - 1;

		while (L <= R) {
			int mid = (L + R) / 2;

			if (input[mid] == target) {
				return 1;
			}
			if (input[mid] < target) {
				L = mid + 1;
			} else {
				R = mid - 1;
			}
		}

		return 0;
	}

}

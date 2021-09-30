/**
 * @문제 숫자 카드 2_S4
 * @날짜 210930
 * @분류 자료 구조, 정렬, 이분 탐색, 해시를 사용한 집합과 맵
 * @조건
 * # 1 <= 카드 개수 <= 50만
 * # -1000만 <= 숫자 <= 1000만
 * @풀이
 * # 이분 탐색
 * # 찾으면 앞뒤로 가면서 더 같은게 몇 개 있는지 카운팅한다.
 * # O(NlogN) + O(MlogN)
 * @comment
 * # L, R 인덱스를 1~N으로 할 때, 정렬 시 조심하기!!
 */

import java.util.*;
import java.io.*;

public class BOJ_10816 {

	static int N, M;
	static int[] input;

	public static void main(String[] args) throws IOException, NumberFormatException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		// init
		input = new int[N + 1];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(input, 1, N + 1);
		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < M; i++) {
			int target = Integer.parseInt(st.nextToken());
			int upper = upper_bound(target);
			int lower = lower_bound(target);

			sb.append(upper - lower).append(" ");
		}

		// print
		System.out.println(sb.toString());
	}

	static int upper_bound(int target) {
		int L = 1, R = N;
		int ans = R + 1;

		while (L <= R) {
			int mid = (L + R) / 2;

			if (input[mid] > target) {
				ans = mid;
				R = mid - 1;
			} else {
				L = mid + 1;
			}
		}

		return ans;
	}

	static int lower_bound(int target) {
		int L = 1, R = N;
		int ans = R + 1;

		while (L <= R) {
			int mid = (L + R) / 2;

			if (input[mid] >= target) {
				ans = mid;
				R = mid - 1;
			} else {
				L = mid + 1;
			}
		}

		return ans;
	}

}

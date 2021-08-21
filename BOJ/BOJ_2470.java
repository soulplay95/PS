package study.week32;

/**
 * @문제 두 용액_G5
 * @날짜 210821
 * @분류 정렬, 이분 탐색, 두 포인터
 * @조건
 * # 2 <= N <= 10만
 * @풀이
 * # 투 포인터
 * @comment
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2470 {
	
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	// -----------------------------------------------------------------------------

	static int N, input[], ans[], min;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		// init
		input = new int[N];
		ans = new int[2];
		min = Integer.MAX_VALUE;
		st = new StringTokenizer(br.readLine(), " ");
		for (int n = 0; n < N; n++) {
			input[n] = Integer.parseInt(st.nextToken());
		} // input end

		solve();

		// print
		System.out.println(ans[0] + " " + ans[1]);
	}
	
	static void solve() {
		// 1. 오름차순 정렬
		Arrays.sort(input);

		// 2. 투 포인터
		int start = 0;
		int end = N - 1;
		int sum, absSum = 0;

		while (start < end) {
			sum = input[start] + input[end]; // 두 용액의 합
			absSum = Math.abs(sum);

			// 최소값 계속 갱신
			if (absSum < min) {
				min = absSum;
				ans[0] = input[start];
				ans[1] = input[end];
			}

			// 합이 0이면 바로 종료, 0보다 크면 end 포인터 감소, 0보다 작으면 start 포인터 증가
			if (sum == 0) {
				ans[0] = input[start];
				ans[1] = input[end];
				return;
			} else if (sum > 0) {
				end--;
			} else {
				start++;
			}
		}
	}

}

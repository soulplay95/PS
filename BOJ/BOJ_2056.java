package study.week33;

/**
 * @문제 작업_G4
 * @날짜 210827
 * @분류 DP, 그래프, 위상 정렬
 * @조건
 * # 3 <= N <= 10000
 * # 선행 관계가 없는 작업들은 동시에 수행 가능
 * @풀이
 * # 선행 작업들 중 제일 오래 걸리는 작업 이후, 현재 작업을 시작할 수 있다.
 * # dp[n] : n번 작업을 완료하는데 필요한 최소 시간
 * # dp[n] = (선행 관계에 있는 작업들 중 완료하는데 필요한 최소 시간)중 최대 시간 + 현재 작업을 완료하는데 걸리는 시간
 * @comment
 * # 위상정렬 풀이 보기
 */

import java.util.*;
import java.io.*;

public class BOJ_2056 {

	static int N, dp[], ans;

	public static void main(String[] args) throws IOException, NumberFormatException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		// init
		dp = new int[N + 1]; // dp[i] : i번 작업을 완료하는데 필요한 최소 시간
		ans = 0;

		for (int n = 1; n <= N; n++) {
			st = new StringTokenizer(br.readLine(), " ");
			int t = Integer.parseInt(st.nextToken());
			int counts = Integer.parseInt(st.nextToken());

			dp[n] = t; // 해당 작업에 걸리는 시간
			for (int i = 0; i < counts; i++) {
				int num = Integer.parseInt(st.nextToken()); // 선행 관계에 있는 작업 번호

				dp[n] = Math.max(dp[n], dp[num] + t); // 선행 관계에 있는 작업들의 소요 시간 + 현재 작업의 소요 시간 중 최대값
			}

			ans = Math.max(ans, dp[n]);
		}

//		System.out.println(Arrays.toString(dp));
		
		// print
		System.out.println(ans);
	}

}

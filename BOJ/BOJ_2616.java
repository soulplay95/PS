package study.week45;

/**
 * @문제 소형기관차_G4
 * @날짜 211205
 * @분류 
 * @조건
 * # 객차의 수 <= 5만
 * # 한 객차의 손님 수 <= 100
 * @풀이
 * # dp[i][j] : i대의 소형 기관차가 객차 j번째까지 고려했을 때 최대로 운송할 수 있는 승객 수
 * # dp[i][j] = max(dp[i][j - 1], dp[i - 1][j - max] + (sum[j] - sum[j - max]))
 * # dp[i][j - 1] : j번째 객차를 고려하지 않은 경우 => 이전(j - 1) 객차까지의 최대 승객 수
 * # dp[i - 1][j - max] + (sum[j] - sum[j - max]) :
 * i - 1번째 소형기관차가 객차 j - max번째까지 고려했을 때 최대 승객 수
 * 객차 j - max ~ j 까지의 승객 누적 합
 * @comment
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_2616 {

	static int totalCounts, maxCounts; // 기관차가 끌고 가던 총 객차 수, 소형 기관차가 최대로 끌 수 있는 객차 수
	static int[] sum; // 객차에 타고 있는 손님 누적 합. sum[i] - sum[j] : [j + 1, i] 구간의 손님 수
	static int[][] dp;
	static final int SMALL_COUNTS = 3; // 소형 기관차 수

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		totalCounts = Integer.parseInt(br.readLine());
		// init
		sum = new int[totalCounts + 1];
		dp = new int[SMALL_COUNTS + 1][totalCounts + 1];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= totalCounts; i++) {
			sum[i] = sum[i - 1] + Integer.parseInt(st.nextToken());
		}
		maxCounts = Integer.parseInt(br.readLine());

		// print
		System.out.println(solve());
	}

	static int solve() {
		// bottom-up
		for (int i = 1; i <= SMALL_COUNTS; i++) {
			for (int j = i * maxCounts; j <= totalCounts; j++) { // i * maxCounts : 최소 각 소형기관차 전부가 끌고 갈 객차수가 필요하므로
				dp[i][j] = Math.max(dp[i][j - 1],
						dp[i - 1][j - maxCounts] + (sum[j] - sum[j - maxCounts]));
			}
		}

		return dp[3][totalCounts]; // 3대의 소형 기관차가 총 객차수까지 고려했을 때 최대로 운송할 수 있는 사람 수
	}

}

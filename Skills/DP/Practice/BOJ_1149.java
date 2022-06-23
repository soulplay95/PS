package algorithm.dp.practice;

/**
 * @문제 RGB거리 [S1]
 * @날짜 220623
 * @분류 DP
 * @조건
 * 2 <= N <= 1000
 * 1 <= 비용 <= 1000
 * @풀이
 * # DP
 * - dp[i][R, G, B] := i번째 집까지 칠하는데, i번 집을 R or G or B로 칠하는 최소 비용
 * - 정답: min(dp[N][R], dp[N][G], dp[N][B])
 * - dp[i][R] = min(dp[i - 1][G], dp[i - 1][B]) + cost[i][R]
 * - dp[i][G] = min(dp[i - 1][R], dp[i - 1][B]) + cost[i][G]
 * - dp[i][B] = min(dp[i - 1][R], dp[i - 1][G]) + cost[i][B]
 * @comments
 * # 정답의 최대치: Integer
 * - 최대 100만(1000 * 1000) => 최대 N개의 집을 모두 최대 비용으로 칠할 때
 * # 시간 복잡도: O(N)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_1149 {

    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[][] cost, dp;

    static final int COLOR_COUNTS = 3;
    static final int R = 0;
    static final int G = 1;
    static final int B = 2;

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        // init
        cost = new int[N + 1][COLOR_COUNTS];
        dp = new int[N + 1][COLOR_COUNTS];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            cost[i][R] = Integer.parseInt(st.nextToken());
            cost[i][G] = Integer.parseInt(st.nextToken());
            cost[i][B] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        // 1. 초기값을 구한다.
        dp[1][R] = cost[1][R];
        dp[1][G] = cost[1][G];
        dp[1][B] = cost[1][B];

        // 2. 점화식을 토대로 dp 배열 채우기
        for (int i = 2; i <= N; i++) {
            dp[i][R] = Math.min(dp[i - 1][G], dp[i - 1][B]) + cost[i][R];
            dp[i][G] = Math.min(dp[i - 1][R], dp[i - 1][B]) + cost[i][G];
            dp[i][B] = Math.min(dp[i - 1][R], dp[i - 1][G]) + cost[i][B];
        }

        // print
        System.out.println(Math.min(dp[N][R], Math.min(dp[N][G], dp[N][B])));
    }

}

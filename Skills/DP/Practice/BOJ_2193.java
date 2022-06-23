package algorithm.dp.practice;

/**
 * @문제 이친수 [S3]
 * @날짜 220623
 * @분류 DP
 * @조건
 * 1 <= N <= 90
 * @풀이
 * # DP
 * - dp[i][0] := 마지막 숫자가 0인 i자리 이친수의 개수
 * - dp[i][1] := 마짖막 숫자가 1인 i자리 이친수의 개수
 * - 정답: dp[N][0] + dp[N][1]
 * - dp[i][0] = dp[i - 1][0] + dp[i - 1][1]
 * - dp[i][1] = dp[i - 1][0]
 * @comments
 * # 정답의 최대치: Long
 * # 시간 복잡도: O(N)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_2193 {

    static StringBuilder sb = new StringBuilder();

    static int N;
    static long[][] dp;

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        // init
        dp = new long[N + 1][2];
    }

    static void solve() {
        // 초기 값을 구한다.
        dp[1][1] = 1;

        // 점화식을 토대로 dp 배열을 채운다.
        for (int i = 2; i <= N; i++) {
            dp[i][0] = dp[i - 1][0] + dp[i - 1][1];
            dp[i][1] = dp[i - 1][0];
        }

        // print
        System.out.println(dp[N][0] + dp[N][1]);
    }

}

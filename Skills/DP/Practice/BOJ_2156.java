package algorithm.dp.practice;

/**
 * @문제 포도주 시식 [S1]
 * @날짜 220623
 * @분류 DP
 * @조건
 * 1 <= n <= 1만
 * 0 <= 포도주 양 <= 1000
 * @풀이
 * # DP
 * - dp[i][0] := i번째 잔까지 마시면서, i - 1 번째 잔을 안마신 경우 현재까지 마신 최대 포도주의 양
 * - dp[i][1] := i번째 잔까지 마시면서, i - 1 번째 잔을 마신 경우 현재까지 마신 최대 포도주의 양
 * - dp[i][0] = max(dp[i - 2][0], dp[i - 2][1]) + amount[i]
 * - dp[i][1] = dp[i - 1][0] + amount[i]
 * @comments
 * # 정답의 최대치: Integer
 * - 그냥 최대 1만개의 잔을 최대 양으로 모두 마신 경우 => 1천만
 * # 시간 복잡도: O(N)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_2156 {

    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[] amount;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        // init
        amount = new int[N + 1];
        dp = new int[N + 1][2];

        for (int i = 1; i <= N; i++) {
            amount[i] = Integer.parseInt(br.readLine());
        }
    }

    static void solve() {
        // 초기값 구하기
        dp[1][0] = 0;
        dp[1][1] = amount[1];

        if (N >= 2){
            dp[2][0] = amount[2];
            dp[2][1] = amount[1] + amount[2];
        }

        // 점화식을 토대로 Dy 배열 채우기
        for (int i = 3; i <= N; i++){
            dp[i][0] = Math.max(dp[i - 2][0] + amount[i], dp[i - 2][1] + amount[i]);
            dp[i][0] = Math.max(dp[i][0], Math.max(dp[i - 3][0], dp[i - 3][1]) + amount[i]);
            dp[i][1] = dp[i - 1][0] + amount[i];
        }
        System.out.println(Math.max(Math.max(dp[N][0], dp[N][1]), Math.max(dp[N - 1][0], dp[N - 1][1])));
    }

}

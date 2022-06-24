package algorithm.dp.practice;

/**
 * @문제 동물원 [S1]
 * @날짜 220624
 * @분류 DP
 * @조건
 * 1 <= N <= 10만
 * @풀이
 * # DP
 * - dp[i][0, 1, 2] := 2 * i 배열에 사자를 배치하는데, i번째 행에 사자를 배치하지 않거나(0) i행 1열에 배치하거나(1) i행 2열에 배치(2)할 때의 경우의 수
 * - 정답: dp[N][0] + dp[N][1] + dp[N][2]
 * - dp[i][0] = dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2]
 * - dp[i][1] = dp[i - 1][0] + dp[i - 1][2]
 * - dp[i][2] = dp[i - 1][0] + dp[i - 1][1]
 * @comments
 * # 정답의 최대치: Integer
 * # 시간 복잡도: O(N)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_1309 {

    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[][] dp;

    static final int MOD = 9901;

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        // init
        dp = new int[N + 1][3];
    }

    static void solve() {
        // 초기값을 구한다.
        dp[1][0] = 1;
        dp[1][1] = 1;
        dp[1][2] = 1;

        // 점화식을 토대로 dp 배열을 채운다.
        for (int i = 2; i <= N; i++) {
            dp[i][0] = (dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2]) % MOD;
            dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % MOD;
            dp[i][2] = (dp[i - 1][0] + dp[i - 1][1]) % MOD;
        }

        // print
        System.out.println((dp[N][0] + dp[N][1] + dp[N][2]) % MOD);
    }

}

package algorithm.dp.practice;

/**
 * @문제 1, 2, 3 더하기 3 [S2]
 * @날짜 220620
 * @분류 DP
 * @조건
 * 1 <= n <= 100만
 * @풀이
 * - dp[i]: i를 1, 2, 3의 합으로 나타내는 방법의 수
 * - partitioning: 마지막에 1, 2, 3중 어떤 수를 더했는가
 * - dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3]
 * @comments
 * # 정답의 최대치: long => max MOD * 3
 * # 시간 복잡도: O(N)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_15988 {

    static StringBuilder sb = new StringBuilder();

    static long[] dp;

    static final int MAX = 1000000;
    static final int MOD = 1000000009;

    public static void main(String[] args) throws IOException {
        input();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        solve();

        int TC = Integer.parseInt(br.readLine());
        while (TC-- > 0) {
            int N = Integer.parseInt(br.readLine());
            // append
            sb.append(dp[N]).append('\n');
        }

        // print
        System.out.println(sb);
    }

    static void solve() {
        // init
        dp = new long[MAX + 1];

        // 초기값 구하기
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        // 점화식을 토대로 dp 배열 채우기
        for (int i = 4; i <= MAX; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3]) % MOD;
        }
    }

}

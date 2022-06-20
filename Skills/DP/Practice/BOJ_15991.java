package algorithm.dp.practice;

/**
 * @문제 1, 2, 3 더하기 6 [S1]
 * @날짜 220620
 * @분류 DP
 * @조건
 * 1 <= n <= 10만
 * @풀이
 * - dp[i] := i를 1, 2, 3의 합으로 나타내는 방법 중 합의 순서가 대칭되는 경우의 수
 * - partitioning: 가운데에 0, 1, 2, 3중 어떤 것을 더했냐 => 짝수개, 홀수개를 더한 경우
 * - dp[i] = 다음의 경우의 수의 합
 * -- dp[i / 2]: 가운데에 0을 더한 경우
 * -- dp[i - 1 / 2]: 가운데에 1을 더한 경우
 * -- dp[i - 2 / 2]: 가운데에 2를 더한 경우
 * -- dp[i - 3 / 2]: 가운데에 3을 더한 경우
 * @comments
 * # 정답의 최대치:
 * # 시간 복잡도:
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_15991 {

    static StringBuilder sb = new StringBuilder();

    static long[] dp;

    static final int MAX = 100000;
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

            int result = 0;
            for (int center = 0; center <= 3; center++) {
                if (N - center >= 0 && (N - center) % 2 == 0) {
                    result += dp[(N - center) / 2];
                    result %= MOD;
                }
            }
            // append
            sb.append(result).append('\n');
        }

        // print
        System.out.println(sb);
    }

    static void solve() {
        // init
        dp = new long[MAX + 1];

        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        for (int i = 4; i <= MAX; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3]) % MOD;
        }
    }

}

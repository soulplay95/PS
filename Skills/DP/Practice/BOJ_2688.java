package algorithm.dp.practice;

/**
 * @문제 줄어들지 않아 [S1]
 * @날짜 220624
 * @분류 DP
 * @조건
 * 1 <= n <= 64
 * @풀이
 * # DP
 * - dp[i][last] := 끝이 last로 끝나는 i자리 수의 개수
 * - 정답: dp[N][0] + dp[N][1] + ... + dp[N][9]
 * - dp[i][last] = sigma[k = 0 ~ last](dp[i - 1][k])
 * @comments
 * # 정답의 최대치:
 * # 시간 복잡도: O(N)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_2688 {

    static StringBuilder sb = new StringBuilder();

    static long[][] dp;

    static final int MAX_N = 64;

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
            long ans = 0;
            for (int last = 0; last <= 9; last++) {
                ans += dp[N][last];
            }
            sb.append(ans).append('\n');
        }

        // print
        System.out.println(sb.toString());
    }

    static void solve() {
        // init
        dp = new long[MAX_N + 1][10];

        // 초기값을 구한다.
        Arrays.fill(dp[1], 1);

        // 점화식을 토대로 dp 배열을 채운다.
        for (int i = 2; i <= MAX_N; i++) {
            for (int last = 0; last <= 9; last++) {
                for (int k = 0; k <= last; k++) {
                    dp[i][last] += dp[i - 1][k];
                }
            }
        }
    }

}

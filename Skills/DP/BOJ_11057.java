package algorithm.dp;

/**
 * @문제 오르막 수 [S1]
 * @날짜 220622
 * @분류 DP
 * @조건
 * 1 <= N <= 1천
 * @풀이
 * # DP
 * - dp[i][last] := 길이가 i이며 last로 끝나는 오르막 수의 개수
 * - 정답: dp[N][0] + dp[N][1] + ... + dp[N][9]
 * - dp[i][last] = sigma k = 0 ~ last (dp[i - 1][k])
 * @comments
 * # 정답의 최대치: Integer
 * - 문제를 푸는 과정에서 계속 나눈 나머지만 가지고 있다면 Integer 범위로 충분
 * # 시간 복잡도: O(N)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_11057 {

    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[][] dp;

    static final int MOD = 10007;

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        // init
        dp = new int[N + 1][10];
    }

    static void solve() {
        // 1. 초기값 구하기
        Arrays.fill(dp[1], 1);

        // 2. 점화식을 토대로 dp 배열 채우기
        for (int i = 2; i <= N; i++) {
            for (int last = 0; last < 10; last++) {
                for (int k = 0; k <= last; k++) {
                    dp[i][last] += dp[i - 1][k];
                    dp[i][last] %= MOD;
                }
            }
        }

        // print
        int ans = 0;
        for (int last = 0; last < 10; last++) {
            ans += dp[N][last];
            ans %= MOD;
        }
        System.out.println(ans);
    }

}

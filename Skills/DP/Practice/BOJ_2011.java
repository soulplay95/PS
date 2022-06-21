package algorithm.dp.practice;

/**
 * @문제 암호코드 [G5]
 * @날짜 220621
 * @분류 DP
 * @조건
 * 1 <= 자릿수 <= 5천
 * @풀이
 * # DP
 * - dp[i] := 앞에서부터 i자리 암호의 해석 가짓수
 * - dp[i] = 끝 두자리가 26을 초과하면 dp[i - 1], 26이하이면 dp[i - 2] + dp[i - 1]
 * @comments
 * # 정답의 최대치: Integer => MOD가 100만이므로 100만 이하 2개를 더해도 Integer 범위
 * # 시간 복잡도: O(N)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_2011 {

    static StringBuilder sb = new StringBuilder();

    static String password;
    static int[] dp;

    static final int MOD = 1000000;

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        password = br.readLine();
    }

    static void solve() {
        // init
        int N = password.length();
        dp = new int[N];

        if (password.charAt(0) == '0') {
            System.out.println(0);
            System.exit(0);
        }

        dp[0] = 1;

        // 점화식을 토대로 dp 배열 채우기
        for (int i = 1; i < N; i++) {
            if (password.charAt(i) != '0') {
                dp[i] = dp[i - 1];
            }

            if (check(password.charAt(i - 1), password.charAt(i))) {
                if (i >= 2) dp[i] += dp[i - 2];
                else dp[i] += 1;
                dp[i] %= MOD;
            }
        }

        // print
        System.out.println(dp[N - 1]);
    }

    static boolean check(char a, char b) {
        if (a == '0') return false;
        if (a == '1') return true;
        if (a >= '3') return false;
        return b <= '6';
    }

}

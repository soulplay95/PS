package algorithm.dp.practice;

/**
 * @문제 카드 구매하기 [S1]
 * @날짜 220621
 * @분류 DP
 * @조건 1 <= N <= 1000
 * 1 <= Pi <= 1만
 * @풀이 # DP
 * - dp[i] := 카드 i개를 갖기 위해 지불해야 하는 금액의 최댓값
 * - dp[i] = max(1개 카드팩을 i장 사는 경우, i개 카드팩을 1장 사는 경우, (1 ~ i - 1) 범위에서 양 끝의 카드팩을 1장씩 사는 경우)
 * @comments # 정답의 최대치: Integer => 1천만 (max N * max Pi)
 * # 시간 복잡도: O(N^2)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_11052 {

    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[] P, dp;

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        // init
        P = new int[N + 1];
        dp = new int[N + 1];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            P[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        // 초기값 세팅
        dp[1] = P[1];

        for (int i = 2; i <= N; i++) {
            int L = 1;
            int R = i - 1;
            int max = 0;
            while (L <= R) {
                max = Math.max(max, dp[L++] + dp[R--]);
            }
            dp[i] = Math.max(Math.max(P[1] * i, P[i]), max);
        }

        // print
        System.out.println(dp[N]);
    }

}

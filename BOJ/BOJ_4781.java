package study.week48;

/**
 * @문제 사탕 가게_G5
 * @날짜 211226
 * @분류 
 * @조건
 * # 1 <= 사탕 종류 (n) <= 5000
 * # 0.01 <= 돈 (m) <= 100.00
 * @풀이
 * # 0-1 Knapsack problem => dp
 * # dp[i] => i원으로 구매할 수 있는 가장 높은 칼로리
 * # dp[i] = max(dp[i], dp[j - 가격] + 칼로리)
 * @comment
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_4781 {

    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        while (true) {
            st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken());
            int m = (int) (Double.parseDouble(st.nextToken()) * 100.0 + 0.5);
            if (n == 0 && m == 0) {
                break;
            }

            int[] dp = new int[m + 1];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int c = Integer.parseInt(st.nextToken());
                int p = (int) (Double.parseDouble(st.nextToken()) * 100.0 + 0.5);

                for (int j = p; j <= m; j++) {
                    dp[j] = Math.max(dp[j], dp[j - p] + c);
                }
            }

            sb.append(dp[m]).append("\n");
        }

        // print
        System.out.println(sb.toString());
    }

}

package algorithm.dp.practice;

/**
 * @문제 스티커 [S1]
 * @날짜 220624
 * @분류 DP
 * @조건
 * 1 <= n <= 10만
 * 0 <= 점수 <= 100
 * @풀이
 * # DP
 * - dp[i][0, 1, 2] := i번째 열까지 고려했을 때, i번째 열의 스티커를 0(떼지 않는 경우), 1(첫 번째 행의 스티커를 뗀 경우), 2(두번째 행의 스티커를 뗀 경우) 했을 때 최대 점수
 * - 정답: max(dp[N][0], dp[N][1], dp[N][2])
 * - dp[i][0] = max(dp[i - 1][0], dp[i - 1][1], dp[i - 1][2])
 * - dp[i][1] = max(dp[i - 1][0], dp[i - 1][2]) + score[i][1]
 * - dp[i][2] = max(dp[i - 1][0], dp[i - 1][1]) + score[i][2]
 * @comment
 * # 정답의 최대치: Integer
 * - 각 스티커당 최대 점수로 모든 스티커를 다 뗐을 때 1000만(max N * max 점수)
 * # 시간 복잡도: O(N)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_9465 {

    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[][] score, dp;

    public static void main(String[] args) throws IOException {
        input();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int TC = Integer.parseInt(br.readLine());
        while (TC-- > 0) {
            N = Integer.parseInt(br.readLine());
            // init
            score = new int[N + 1][2];
            dp = new int[N + 1][3];

            for (int row = 0; row <= 1; row++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int i = 1; i <= N; i++) {
                    score[i][row] = Integer.parseInt(st.nextToken());
                }
            }

            // append
            sb.append(solve()).append('\n');
        }

        // print
        System.out.println(sb.toString());
    }

    static int solve() {
        // 초기값 구하기
        dp[1][1] = score[1][0];
        dp[1][2] = score[1][1];

        // 점화식을 토대로 dp 배열 채우기
        for (int i = 2; i <= N; i++) {
            for (int state = 0; state <= 2; state++) {
                dp[i][0] = Math.max(dp[i][0], dp[i - 1][state]);
            }
            dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][2]) + score[i][0];
            dp[i][2] = Math.max(dp[i - 1][0], dp[i - 1][1]) + score[i][1];
        }

        return Math.max(Math.max(dp[N][0], dp[N][1]), dp[N][2]);
    }

}

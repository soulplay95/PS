package algorithm.dp;

/**
 * @문제 계단 오르기 [S3]
 * @날짜 220622
 * @분류 DP
 * @조건
 * 1 <= 계단 수 <= 300
 * 1 <= 점수 <= 1만
 * @풀이
 * # DP
 * - dp[i][0] := i - 1 번째 계단은 밟지 않고, i번째 계단에 도착하며 얻는 최대 점수
 * - dp[i][1] := i - 1 번째 계단을 밟고, i번째 계단에 도착하며 얻는 최대 점수
 * - 정답: max(dp[N][0], max[N][1])
 * - dp[i][0] = max(dp[i - 2][1] + A[i], dp[i - 2][0] + A[i])
 * -- dp[i - 2][1] + A[i]: i - 2, i - 3 두 계단 모두 밟은 경우
 * -- dp[i - 2][0] + A[i]: i - 2만 밟은 경우
 * - dp[i][1] = dp[i - 1][0] + A[i]
 * -- dp[i - 1][0] + A[i]: i - 1은 밟고 i - 2는 안 밟은 경우
 * @comments
 * # 정답의 최대치: Integer => N개의 계단을 모두 밟는다 치면 max 300만 := 300(최대 계단 수) * 1만(최대 점수)
 * # 시간 복잡도: O(N)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_2579 {

    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[] A;
    static int[][] dp;

    static final int NOT_STEP = 0;
    static final int STEP = 1;

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        // init
        A = new int[N + 1];
        dp = new int[N + 1][2];

        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(br.readLine());
        }
    }

    static void solve() {
        // 1. 초기값 구하기
        dp[1][NOT_STEP] = A[1];

        // 2. 점화식을 토대로 dp 배열 채우기
        for (int i = 2; i <= N; i++) {
            dp[i][NOT_STEP] = Math.max(dp[i - 2][NOT_STEP] + A[i], dp[i - 2][STEP] + A[i]);
            dp[i][STEP] = dp[i - 1][NOT_STEP] + A[i];
        }

        // print
        System.out.println(Math.max(dp[N][NOT_STEP], dp[N][STEP]));
    }

}

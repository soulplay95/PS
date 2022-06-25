package algorithm.dp;

/**
 * @문제 파일 합치기 [G3]
 * @날짜 220625
 * @분류 DP
 * @조건
 * 3 <= 파일 개수(K) <= 500
 * 1 <= 파일 크기 <= 10000
 * @풀이
 * # DP
 * - dp[i][j] := i ~ j (i <= j) 번 파일을 하나로 합치는 최소 비용
 * - 정답: dp[1][K]
 * - Partitioning: 마지막에 어떤 구간끼리 합쳤나 => 구간의 길이 - 1개 만큼 파티션 개수가 나온다.
 * - dp[i][j] = min[k = i ~ j - 1](dp[i][k] + dp[k + 1][j] + (i ~ j 파일 총량))
 * -- k: 마지막에 어떤 구간끼리 합쳤냐를 따질 때 기준이 되었던 자른 구간 => 왼쪽 구간의 끝
 * -- k를 i부터 j - 1까지 변화하면서 각 파티션을 구하고, 각 파티션중 최소 비용이 dp[i][j]
 * -- dp[i][k]: k를 기준으로 왼쪽 구간을 합칠 때 최소 비용
 * -- dp[k + 1][k]: k를 기준으로 오른쪽 구간을 합칠 때 최소 비용
 * -- i ~ j 파일 총량: k를 기준으로 두 구간을 합칠 때 비용
 * --- 점화식에 영향이 있는(k가 변할때 마다 새롭게 구해줘야 하는) 값이 아니므로, 전처리를 통해 미리 구해놓는다.
 * --- sum[i][j] = sum[i][j - 1] + A[j]
 * -- 길이가 짧은 구간부터 구한다.
 * @comments
 * # 정답의 최대치:
 * # 시간 복잡도: O(K^3)
 */

import java.io.*;
import java.util.*;

public class BOJ_11066 {

    static StringBuilder sb = new StringBuilder();

    static int K;
    static int[] fileSize;
    static int[][] dp, sum;

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int TC = Integer.parseInt(br.readLine());
        while (TC-- > 0) {
            K = Integer.parseInt(br.readLine());
            // init
            fileSize = new int[K + 1];
            dp = new int[K + 1][K + 1];
            sum = new int[K + 1][K + 1];

            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 1; i <= K; i++) {
                fileSize[i] = Integer.parseInt(st.nextToken());
            }

            solve();
        }

        // print
        System.out.println(sb.toString());
    }

    static void preprocess() {
        // sum 배열을 구한다.
        for (int i = 1; i <= K; i++) {
            for (int j = i; j <= K; j++) {
                sum[i][j] = sum[i][j - 1] + fileSize[j];
            }
        }
    }

    static void solve() {
        preprocess();

        // 길이가 짧은 구간부터 구한다.
        for (int len = 2; len <= K; len++) {
            for (int L = 1; L <= K - len + 1; L++) {
                int R = L + len - 1;
                dp[L][R] = Integer.MAX_VALUE;

                // 각 파티션 중 최소값을 구한다.
                for (int split = L; split < R; split++) {
                    dp[L][R] = Math.min(
                            dp[L][R],
                            dp[L][split] + dp[split + 1][R] + sum[L][R]
                    );
                }
            }
        }

        // append
        sb.append(dp[1][K]).append('\n');
    }

}

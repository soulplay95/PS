package algorithm.dp.practice;

/**
 * @문제 피보나치 함수 [S3]
 * @날짜 220620
 * @분류 DP
 * @조건
 * 0 <= N <= 40
 * @풀이
 * - dy[i][0, 1] = fibonacci(i)를 호출했을 때, 0과 1이 출력되는 횟수
 * - dy[i][0] = dy[i - 1][0] + dy[i - 2][0]
 * - dy[i][1] = dy[i - 1][1] + dy[i - 2][1]
 * @comments
 * # 정답의 최대치:
 * # 시간 복잡도: O(N)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_1003 {

    static StringBuilder sb = new StringBuilder();

    static int[][] dy;

    static final int MAX = 40;

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
            sb.append(dy[N][0]).append(' ').append(dy[N][1]).append('\n');
        }

        // print
        System.out.println(sb.toString());
    }

    static void solve() {
        // init
        dy = new int[MAX + 1][2];

        // 초기값 구하기
        dy[0][0] = 1;
        dy[0][1] = 0;
        dy[1][0] = 0;
        dy[1][1] = 1;

        // 점화식을 토대로 dy 배열 채우기
        for (int n = 2; n <= MAX; n++) {
            dy[n][0] = dy[n - 1][0] + dy[n - 2][0];
            dy[n][1] = dy[n - 1][1] + dy[n - 2][1];
        }
    }

}

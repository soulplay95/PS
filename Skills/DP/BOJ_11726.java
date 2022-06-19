package algorithm.dp;

/**
 * @문제 2xn 타일링 [S3]
 * @날짜 220619
 * @분류 DP
 * @조건
 * 1 <= n <= 1000
 * @풀이
 * - dy[i] = 2xi 크기의 직사각형을 1x2, 2x1 타일로 채우는 방법의 수
 * - partitioning: 마지막에 2x1 타일을 붙이냐 or 1x2 타일을 2개 붙이냐
 * - dy[i] = dy[i - 1] + dy[i - 2]
 * @comments
 * # 정답의 최대치:
 * # 시간 복잡도: O(N)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_11726 {

    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[] dy;

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
        dy = new int[N + 1];
    }

    static void solve() {
        // 초기값 구하기
        dy[1] = 1;
        dy[2] = 2;

        // 점화식을 토대로 dy 배열 채우기
        for (int i = 3; i <= N; i++) {
            dy[i] = (dy[i - 1] + dy[i - 2]) % MOD;
        }

        // print
        System.out.println(dy[N]);
    }

}

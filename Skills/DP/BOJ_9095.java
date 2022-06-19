package algorithm.dp;

/**
 * @문제 1, 2, 3 더하기 [S3]
 * @날짜 220619
 * @분류 DP
 * @조건
 * 1 <= N < 11
 * @풀이
 * - dy[i] = i를 1, 2, 3의 합으로 나타내는 경우의 수
 * - partitioning: 마지막에 어떤 수를 더했는가
 * - dy[i] = dy[i - 1] + dy[i - 2] + dy[i - 3]
 * @comments
 * # 정답의 최대치:
 * # 시간 복잡도: O(N)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_9095 {

    static StringBuilder sb = new StringBuilder();

    static int[] dy;

    static final int MAX = 10;

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
            sb.append(dy[N]).append('\n');
        }

        // print
        System.out.println(sb.toString());
    }

    static void solve() {
        // init
        dy = new int[MAX + 1];

        // 초기값 구하기
        dy[1] = 1;
        dy[2] = 2;
        dy[3] = 4;

        // 점화식을 토대로 dy 채우기
        for (int i = 4; i <= MAX; i++) {
            dy[i] = dy[i - 1] + dy[i - 2] + dy[i - 3];
        }
    }

}

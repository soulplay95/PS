package algorithm.dp.practice;

/**
 * @문제 피보나치 수 5 [B5]
 * @날짜 220620
 * @분류 수학 / 구현 / 재귀
 * @조건
 * 0 <= n <= 20
 * @풀이
 * - dy[i]: i번째 피보나치 수
 * - dy[i] = dy[i - 1] + dy[i - 2]
 * @comments
 * # 정답의 최대치:
 * # 시간 복잡도: O(N)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_10870 {

    static StringBuilder sb = new StringBuilder();

    static int[] dy;

    static final int MAX = 20;

    public static void main(String[] args) throws IOException {
        input();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        solve();

        int N = Integer.parseInt(br.readLine());
        // print
        System.out.println(dy[N]);
    }

    static void solve() {
        // init
        dy = new int[MAX + 1];

        dy[0] = 0;
        dy[1] = 1;

        for (int i = 2; i <= MAX; i++) {
            dy[i] = dy[i - 1] + dy[i - 2];
        }
    }

}

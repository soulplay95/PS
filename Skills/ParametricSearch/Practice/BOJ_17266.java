package algorithm.parametric_search.practice;

/**
 * @문제 어두운 굴다리 [S4]
 * @날짜 220528
 * @분류 구현 / 이분 탐색
 * @조건
 * 1 <= N <= 10만
 * 1 <= M <= N
 * @풀이
 * - 가로등 사이의 간격을 따져본다.
 * @comments
 * # 정답의 최대치: Integer => max 10만
 * # 시간 복잡도: O(N)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_17266 {

    static StringBuilder sb = new StringBuilder();

    static int N, M, ans;
    static int[] x;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        print();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        // init
        x = new int[M];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < M; i++) {
            x[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        ans = Math.max(x[0], N - x[M - 1]);
        for (int i = 0; i < M - 1; i++) {
            int gap = x[i + 1] - x[i];
            if (gap % 2 == 1) {
                ans = Math.max(ans, gap / 2 + 1);
            } else {
                ans = Math.max(ans, gap / 2);
            }
        }
    }

    static void print() {
        System.out.println(ans);
    }

}

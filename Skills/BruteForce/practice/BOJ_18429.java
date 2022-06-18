package algorithm.brute_force.practice;

/**
 * @문제 근손실 [S3]
 * @날짜 220618
 * @분류 완전 탐색 / 백트래킹
 * @조건
 * 1 <= N <= 8
 * 1 <= K <= 50
 * 1 <= A <= 50
 * @풀이
 * # 순열, 백트래킹
 * @comments
 * # 정답의 최대치: Integer
 * # 시간 복잡도: O(N!)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_18429 {

    static StringBuilder sb = new StringBuilder();

    static int N, K, ans;
    static int[] A;
    static boolean[] used;

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        // init
        A = new int[N + 1];
        used = new boolean[N + 1];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        recursive(1, 500);

        // print
        System.out.println(ans);
    }

    static void recursive(int k, int weight) {
        if (weight < 500) {
            return;
        }
        if (k == N + 1) {
            ans++;
        }

        for (int candidateIndex = 1; candidateIndex <= N; candidateIndex++) {
            if (used[candidateIndex]) continue;

            used[candidateIndex] = true;
            recursive(k + 1, weight - K + A[candidateIndex]);
            used[candidateIndex] = false;
        }
    }

}

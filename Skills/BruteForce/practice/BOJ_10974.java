package algorithm.brute_force.practice;

/**
 * @문제 모든 순열 [S3]
 * @날짜 220514
 * @분류 완전 탐색 / 백트래킹
 * @조건
 * # 1 <= N <= 8
 * @풀이
 * # 순열
 * @comments
 * # 정답의 최대치: Integer
 * # 시간 복잡도: O(N!) => 8!
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_10974 {

    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[] selected;
    static boolean[] used;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        print();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());

        init();
    }

    static void init() {
        selected = new int[N + 1];
        used = new boolean[N + 1];
    }

    static void solve() {
        recursive(1);
    }

    static void recursive(int depth) {
        if (depth == N + 1) {
            for (int i = 1; i <= N; i++) {
                sb.append(selected[i]).append(' ');
            }
            sb.append("\n");

            return;
        }

        for (int candidate = 1; candidate <= N; candidate++) {
            if (used[candidate]) continue;

            used[candidate] = true;
            selected[depth] = candidate;
            recursive(depth + 1);
            used[candidate] = false;
            selected[depth] = 0;
        }
    }

    static void print() {
        System.out.println(sb.toString());
    }

}

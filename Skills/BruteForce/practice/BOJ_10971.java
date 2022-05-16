package algorithm.brute_force.practice;

/**
 * @문제 외판원 순회 2 [S2]
 * @날짜 220516
 * @분류 완전 탐색 / 백트래킹 / 외판원 순회 문제
 * @조건
 * # 2 <= N <= 10
 * # 1 <= 비용 <= 100만
 * @풀이
 * # 순열
 * - N개의 도시를 순서 있게 나열 => O(N!)
 * - 가는 길이 없거나 (W[i][j] == 0), 이미 구한 최소 비용보다 크면 백트래킹
 * @comments
 * # 정답의 최대치: Integer => 100만(max W[i][j]) * 10(max N)
 * # 시간 복잡도: O(N!)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_10971 {

    static StringBuilder sb = new StringBuilder();

    static int N, min;
    static int[][] weights;
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
        // init
        min = Integer.MAX_VALUE;
        weights = new int[N][N];
        used = new boolean[N];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int c = 0; c < N; c++) {
                weights[r][c] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void solve() {
        for (int start = 0; start < N; start++) {
            used[start] = true;
            recursive(1, 0, start, start);
            used[start] = false;
        }
    }

    static void recursive(int depth, int weightSum, int before, int start) {
        if (depth == N) {
            if (weights[before][start] != 0) {
                min = Math.min(min, weightSum + weights[before][start]);
            }
            return;
        }
        if (weightSum > min) {
            return;
        }

        for (int candidate = 0; candidate < N; candidate++) {
            if (used[candidate]) continue;
            if (weights[before][candidate] == 0) continue;

            used[candidate] = true;
            recursive(depth + 1, weightSum + weights[before][candidate], candidate, start);
            used[candidate] = false;
        }
    }

    static void print() {
        System.out.println(min);
    }

}

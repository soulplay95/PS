package algorithm.brute_force.practice;

/**
 * @문제 에너지 모으기 [S1]
 * @날짜 220522
 * @분류 완전 탐색 / 백트래킹
 * @조건
 * # 3 <= N <= 10
 * # 1 <= W <= 1000
 * @풀이
 * # 순열
 * - 양 끝의 구슬을 제외한 N - 2개의 구슬을 순서있게 나열한다. => 고른 순서
 * @comments
 * # 정답의 최대치: Integer => max 8 * (1000 * 1000)
 * # 시간 복잡도: O((N-2)!) => max 8!
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_16198 {

    static StringBuilder sb = new StringBuilder();

    static int N, max;
    static int[] W;
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
        max = 0;
        W = new int[N];
        used = new boolean[N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            W[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        recursive(0, 0);
    }

    static void recursive(int depth, int energy) {
        if (depth == N - 2) { // 양 끝 구슬을 제외한 모든 구슬을 뽑은 경우
            max = Math.max(max, energy);
        } else {
            // 양 끝 구슬을 제외한 모두가 후보군
            for (int candidateIndex = 1; candidateIndex < N - 1; candidateIndex++) {
                if (used[candidateIndex]) continue;

                used[candidateIndex] = true;
                recursive(depth + 1, energy + getEnergy(candidateIndex));
                used[candidateIndex] = false;
            }
        }
    }

    static int getEnergy(int index) {
        // index 좌우로 인접한 구슬의 에너지를 곱한 값을 리턴한다.
        return getLeftCloseEnergy(index) * getRightCloseEnergy(index);
    }

    static int getLeftCloseEnergy(int index) {
        for (int i = index - 1; i >= 0; i--) {
            if (!used[i]) {
                return W[i];
            }
        }

        return W[0];
    }

    static int getRightCloseEnergy(int index) {
        for (int i = index + 1; i < N; i++) {
            if (!used[i]) {
                return W[i];
            }
        }

        return W[N - 1];
    }

    static void print() {
        System.out.println(max);
    }

}

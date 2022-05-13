package algorithm.brute_force.practice;

/**
 * @문제 차이를 최대로 [S2]
 * @날짜 220513
 * @분류 완전 탐색 / 백트래킹
 * @조건
 * # 3 <= N <= 8
 * @풀이
 * # 순열
 * - N개를 나열하여 식에 해당하는 값 구하기
 * @comments
 * # 정답의 최대치: Integer
 * # 시간 복잡도: O(N!) => 8!
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_10819 {

    static StringBuilder sb = new StringBuilder();

    static int N, max;
    static int[] numbers, selected;
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
        numbers = new int[N + 1];
        selected = new int[N + 1];
        used = new boolean[N + 1];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        recursive(1, 0);
    }

    static void recursive(int depth, int absoluteDiffSum) {
        if (depth == N + 1) {
            max = Math.max(max, absoluteDiffSum);
        } else {
            for (int candidateIndex = 1; candidateIndex <= N; candidateIndex++) {
                if (used[candidateIndex]) continue;

                used[candidateIndex] = true;
                selected[depth] = numbers[candidateIndex];
                int diff = depth >= 2 ? Math.abs(selected[depth] - selected[depth - 1]) : 0;
                recursive(depth + 1, absoluteDiffSum + diff);
                used[candidateIndex] = false;
                selected[depth] = 0;
            }
        }
    }

    static void print() {
        System.out.println(max);
    }

}

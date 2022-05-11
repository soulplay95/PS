package algorithm.brute_force.practice;

/**
 * @문제 로또 [S2]
 * @날짜 220511
 * @분류 수학 / 조합론 / 백트래킹 / 재귀
 * @조건
 * # 6 < k < 13
 * @풀이
 * # 조합
 * @comments
 * # 정답의 최대치: Integer => 12C6
 * # 시간 복잡도: O(kC6)
 * # 공간 복잡도: O(1) => O(6)
 */

import java.io.*;
import java.util.*;

public class BOJ_6603 {

    static StringBuilder sb = new StringBuilder();

    static final int PICK_COUNTS = 6;

    static int k;
    static int[] S, selected;

    public static void main(String[] args) throws IOException {
        input();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        while (true) {
            st = new StringTokenizer(br.readLine(), " ");

            k = Integer.parseInt(st.nextToken());
            if (k == 0) {
                print();
                return;
            }

            // init
            S = new int[k];
            selected = new int[PICK_COUNTS];

            for (int i = 0; i < k; i++) {
                S[i] = Integer.parseInt(st.nextToken());
            }

            solve();
        }
    }

    static void recursive(int depth, int start) {
        if (depth == PICK_COUNTS) {
            for (int i = 0; i < PICK_COUNTS; i++) {
                sb.append(selected[i]).append(" ");
            }
            sb.append("\n");
        } else {
            for (int candidateIndex = start; candidateIndex < k; candidateIndex++) {
                selected[depth] = S[candidateIndex];
                recursive(depth + 1, candidateIndex + 1);
                selected[depth] = 0;
            }
        }
    }

    static void solve() {
        recursive(0, 0);
        sb.append("\n");
    }

    static void print() {
        System.out.println(sb.toString());
    }

}

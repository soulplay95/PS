package algorithm.brute_force.practice;

/**
 * @문제 N과 M (11) [S2]
 * @날짜 220517
 * @분류 백트래킹
 * @조건
 * # 1 <= M <= N <= 7
 * @풀이
 * # 중복 순열 + 현재 depth에서 이전에 뽑은 값 제외
 * @comments
 * # 정답의 최대치: Integer
 * # 시간 복잡도: O(< N^M)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_15665 {

    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static int[] numbers, selected;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        print();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        // init
        numbers = new int[N + 1];
        selected = new int[M + 1];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        Arrays.sort(numbers, 1, N + 1);
        recursive(1);
    }

    static void recursive(int depth) {
        if (depth == M + 1) {
            for (int i = 1; i <= M; i++) {
                sb.append(selected[i]).append(" ");
            }
            sb.append("\n");
        } else {
            int beforeSelectedValue = 0;
            for (int candidateIndex = 1; candidateIndex <= N; candidateIndex++) {
                if (numbers[candidateIndex] == beforeSelectedValue) continue;
                selected[depth] = numbers[candidateIndex];
                recursive(depth + 1);
                selected[depth] = 0;
                beforeSelectedValue = numbers[candidateIndex];
            }
        }
    }

    static void print() {
        System.out.println(sb.toString());
    }

}

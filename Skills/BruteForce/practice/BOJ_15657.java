package algorithm.brute_force.practice;

/**
 * @문제 N과 M (8) [S3]
 * @날짜 220514
 * @분류 백트래킹
 * @조건
 * # 1 <= M <= N <= 8
 * @풀이
 * # 중복 조합
 * - 현재 숫자는 앞에 뽑은 수부터 뽑는다.
 * @comments
 * # 정답의 최대치: Integer
 * # 시간 복잡도: O(< N^M)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_15657 {

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
        recursive(1, 1);
    }

    static void recursive(int depth, int start) {
        if (depth == M + 1) {
            for (int i = 1; i <= M; i++) {
                sb.append(selected[i]).append(" ");
            }
            sb.append("\n");

            return;
        }

        for (int candidateIndex = start; candidateIndex <= N; candidateIndex++) {
            selected[depth] = numbers[candidateIndex];
            recursive(depth + 1, candidateIndex);
            selected[depth] = 0;
        }
    }

    static void print() {
        System.out.println(sb.toString());
    }

}

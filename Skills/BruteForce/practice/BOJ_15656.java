package algorithm.brute_force.practice;

/**
 * @문제 N과 M (7) [S3]
 * @날짜 220515
 * @분류 백트래킹
 * @조건
 * # 1 <= M <= N <= 7
 * @풀이
 * # 중복 순열
 * @comments
 * # 정답의 최대치: Integer
 * # 시간 복잡도: O(N^M)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_15656 {

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
        numbers = new int[N];
        selected = new int[M];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        Arrays.sort(numbers, 0, N);
        recursive(0);
    }

    static void recursive(int depth) {
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                sb.append(selected[i]).append(" ");
            }
            sb.append("\n");
        } else {
            for (int candidateIndex = 0; candidateIndex < N; candidateIndex++) {
                selected[depth] = numbers[candidateIndex];
                recursive(depth + 1);
                selected[depth] = 0;
            }
        }
    }

    static void print() {
        System.out.println(sb.toString());
    }

}

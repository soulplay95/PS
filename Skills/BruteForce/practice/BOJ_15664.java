package algorithm.brute_force.practice;

/**
 * @문제 N과 M (10) [S2]
 * @날짜 220517
 * @분류 백트래킹
 * @조건
 * # 1 <= M <= N <= 8
 * @풀이
 * # 조합
 * - 현재 depth에서 전에 뽑은 수를 저장하고 같은 값이면 뽑지 않는다.
 * @comments
 * # 정답의 최대치: Integer
 * # 시간 복잡도: O(NCM)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_15664 {

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
        recursive(0, 0);
    }

    static void recursive(int depth, int start) {
        if (depth == M) {
            // 출력
            for (int i = 0; i < M; i++) {
                sb.append(selected[i]).append(' ');
            }
            sb.append('\n');
        } else {
            int beforeSelectedValue = 0; // 현재 depth에서 이전에 뽑은 값
            // start부터 뽑는다.
            for (int candidateIndex = start; candidateIndex < N; candidateIndex++) {
                if (numbers[candidateIndex] == beforeSelectedValue) continue;

                selected[depth] = numbers[candidateIndex];
                recursive(depth + 1, candidateIndex + 1);
                selected[depth] = 0;
                beforeSelectedValue = numbers[candidateIndex];
            }
        }
    }

    static void print() {
        System.out.println(sb.toString());
    }

}

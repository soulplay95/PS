package algorithm.brute_force.practice;

/**
 * @문제 N과 M (5) [S3]
 * @날짜 220512
 * @분류 백트래킹
 * @조건
 * # 1 <= M <= N <= 8
 * @풀이
 * # 순열
 * - 입력 수 오름차순 정렬
 * @comments
 * # 정답의 최대치: Integer
 * # 시간 복잡도: O(nPr) => 8!
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_15654 {

    static StringBuilder sb = new StringBuilder();

    static int N, R;
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

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        // init
        numbers = new int[N + 1];
        selected = new int[R + 1];
        used = new boolean[N + 1];

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
        if (depth == R + 1) {
            // 출력
            for (int i = 1; i <= R; i++) {
                sb.append(selected[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int candidateIndex = 1; candidateIndex <= N; candidateIndex++) {
            if (used[candidateIndex]) continue;

            selected[depth] = numbers[candidateIndex];
            used[candidateIndex] = true;
            recursive(depth + 1);
            selected[depth] = 0;
            used[candidateIndex] = false;
        }
    }

    static void print() {
        System.out.println(sb.toString());
    }

}

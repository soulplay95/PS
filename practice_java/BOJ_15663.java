package practice.bruteforce;

/**
 * @문제 N과 M (9) [S2]
 * @날짜 220825
 * @분류 백트래킹
 * @조건
 * # 1 <= M <= N <= 8
 * @풀이
 * # 순열
 * - 현재 depth 에서 이전에 뽑았던 수와 같으면 continue
 * @복잡도
 * # 정답의 최대치: Integer
 * # 시간 복잡도: O(nPr)
 * # 공간 복잡도:
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_15663 {

    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static int[] numbers, selected;
    static boolean[] used;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        print();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        // init
        numbers = new int[N];
        selected = new int[M];
        used = new boolean[N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        // 오름차순 정렬
        Arrays.sort(numbers);

        dfs(0);
    }

    static void dfs(int depth) {
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                sb.append(selected[i]).append(' ');
            }
            sb.append("\n");
            return;
        }

        int previousSelectedIndex = -1;
        for (int candidateIndex = 0; candidateIndex < N; candidateIndex++) {
            if (used[candidateIndex]) continue;
            if (previousSelectedIndex != -1 && numbers[previousSelectedIndex] == numbers[candidateIndex]) continue;

            previousSelectedIndex = candidateIndex;
            used[candidateIndex] = true;
            selected[depth] = numbers[candidateIndex];
            dfs(depth + 1);
            used[candidateIndex] = false;
            selected[depth] = -1;
        }
    }

    static void print() {
        System.out.println(sb.toString());
    }

}

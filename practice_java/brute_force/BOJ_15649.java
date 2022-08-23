package practice.bruteforce;

/**
 * @문제 N과 M (1) [S3]
 * @날짜 220823
 * @분류 백트래킹
 * @조건
 * # 1 <= M <= N <= 8
 * @풀이
 * # 순열
 * @comments
 * # 정답의 최대치:
 * # 시간 복잡도: max 8!
 * # 공간 복잡도:
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15649 {

    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static int[] selected;
    static boolean[] used;

    public static void main(String[] args) throws IOException {
        input();
        solve(1);

        // print
        System.out.println(sb.toString());
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // init
        selected = new int[M + 1];
        used = new boolean[N + 1];
    }

    static void solve(int depth) {
        if (depth == M + 1) {
            for (int i = 1; i <= M; i++) {
                sb.append(selected[i]).append(' ');
            }
            sb.append('\n');
        } else {
            for (int candidate = 1; candidate <= N; candidate++) {
                if (used[candidate]) continue;

                selected[depth] = candidate;
                used[candidate] = true;

                solve(depth + 1);

                selected[depth] = -1;
                used[candidate] = false;
            }
        }
    }

}

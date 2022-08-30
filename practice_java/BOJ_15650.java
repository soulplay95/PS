package practice.bruteforce;

/**
 * @문제 N과 M (2) [S3]
 * @날짜 220823
 * @분류 백트래킹
 * @조건
 * # 1 <= M <= N <= 8
 * @풀이
 * # 조합
 * @comments
 * # 정답의 최대치:
 * # 시간 복잡도:
 * # 공간 복잡도:
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15650 {

    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static int[] selected;

    public static void main(String[] args) throws IOException {
        input();
        solve(1, 1);
        print();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // init
        selected = new int[M + 1];
    }

    static void solve(int depth, int start) {
        if (depth == M + 1) {
            for (int i = 1; i <= M; i++) {
                sb.append(selected[i]).append(' ');
            }
            sb.append('\n');
        } else {
            for (int candidate = start; candidate <= N; candidate++) {
                selected[depth] = candidate;
                solve(depth + 1, candidate + 1); // 다음 것부터 뽑는다.
                selected[depth] = -1;
            }
        }
    }

    static void print() {
        System.out.println(sb.toString());
    }

}

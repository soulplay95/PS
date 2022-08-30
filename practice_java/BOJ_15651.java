package practice.bruteforce;

/**
 * @문제 N과 M (3) [S3]
 * @날짜 220823
 * @분류 백트래킹
 * @조건 # 1 <= M <= N <= 7
 * @풀이 # 중복 순열
 * - 각 칸에 1부터 N까지 올 수 있다.
 * @comments # 정답의 최대치: Integer => max 7^7
 * # 시간 복잡도: O(N^M) => 7^7 ~= 82만
 * # 공간 복잡도: O(M)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15651 {

    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static int[] selected;

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
        selected = new int[M + 1];
    }

    static void solve() {
        recursive(1);
    }

    static void recursive(int depth) {
        if (depth == M + 1) {
            // 다 뽑은 상황
            for (int i = 1; i <= M; i++) {
                sb.append(selected[i]).append(" ");
            }
            sb.append("\n");
        } else {
            for (int candidate = 1; candidate <= N; candidate++) {
                selected[depth] = candidate;
                recursive(depth + 1);
                selected[depth] = -1;
            }
        }
    }

    static void print() {
        System.out.println(sb.toString());
    }

}

/**
 * @문제 N과 M (4)_S3
 * @날짜 220222
 * @분류 백트래킹
 * @조건
 * # 1 <= M <= N <= 8
 * @풀이
 * # 중복 조합
 * @comments
 * # 시간 복잡도: O(N^M) ~= 8^8보다 작다
 * # 공간 복잡도: O(M)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15652 {

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
        recursiveFunc(1, 1);
    }

    static void recursiveFunc(int k, int start) {
        if (k == M + 1) {
            for (int i = 1; i <= M; i++) {
                sb.append(selected[i]).append(' ');
            }
            sb.append('\n');
        } else {
            for (int candidate = start; candidate <= N; candidate++) {
                selected[k] = candidate;
                recursiveFunc(k + 1, candidate); // 다음 칸은 직전에 뽑은(candidate)부터 뽑는다.
                selected[k] = 0;
            }
        }
    }

    static void print() {
        System.out.println(sb.toString());
    }

}

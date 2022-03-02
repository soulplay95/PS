/**
 * @문제 귀여운 라이언_S1
 * @날짜 220302
 * @분류 두 포인터
 * @조건
 * # 1 <= K <= N <= 10^6
 * @풀이
 * # 두 포인터
 * @comments
 * # 정답의 최대치: Integer
 * # 시간 복잡도: O(N)
 * # 공간 복잡도: O(N)
 */

import java.io.*;
import java.util.*;

public class BOJ_15565 {

    static StringBuilder sb = new StringBuilder();

    static int N, K, min;
    static int[] A;

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
        K = Integer.parseInt(st.nextToken());
        // init
        A = new int[N + 1];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        // init
        int R = 0, counts = 0;
        min = Integer.MAX_VALUE;
        for (int L = 1; L <= N; L++) {
            // K개 이상의 counts를 가질 때 까지 R을 이동
            while (R + 1 <= N && counts < K) {
                if (A[++R] == 1) {
                    counts++;
                }
            }

            // 길이 갱신
            if (counts >= K) {
                min = Math.min(min, R - L + 1);
            }

            if (A[L] == 1) {
                counts--;
            }
        }

        if (min == Integer.MAX_VALUE) {
            min = -1;
        }
    }

    static void print() {
        System.out.println(min);
    }

}

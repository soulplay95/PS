package algorithm.two_pointers.practice;

/**
 * @문제 귀여운 라이언 [S1]
 * @날짜 220601
 * @분류 두 포인터
 * @조건
 * 1 <= K <= N <= 100만
 * @풀이
 * # 두 포인터
 * @comments
 * # 정답의 최대치: Integer => 100만(max N)
 * # 시간 복잡도: O(N)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_15565 {

    static StringBuilder sb = new StringBuilder();

    static int N, K;
    static int[] A;

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

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
        int R = 0, ans = -1, lionCounts = 0;
        for (int L = 1; L <= N; L++) {
            // R을 옮길 수 있을 때 까지 옮긴다.
            while (R < N && lionCounts < K) {
                R++;
                if (A[R] == 1) {
                    lionCounts++;
                }
            }

            // 정답 갱신
            if (lionCounts == K) {
                if (ans == -1) { // 최초 갱신이면
                    ans = R - L + 1;
                } else {
                    ans = Math.min(ans, R - L + 1);
                }
            }

            // L 넘어가기 전에 라이언인지 체크
            if (A[L] == 1) {
                lionCounts--;
            }
        }

        // print
        System.out.println(ans);
    }

}

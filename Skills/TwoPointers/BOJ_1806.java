package algorithm.two_pointers;

/**
 * @문제 부분합 [G4]
 * @날짜 220529
 * @분류 두 포인터
 * @조건
 * 10 <= N <= 10만
 * 0 < S <= 1억
 * 1 <= 각 원소 <= 1만
 * @풀이
 * # 두 포인터
 * @comments
 * # 정답의 최대치: Integer
 * # 시간 복잡도: O(N)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_1806 {

    static StringBuilder sb = new StringBuilder();

    static int N, S, ans;
    static int[] sequence;

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
        S = Integer.parseInt(st.nextToken());
        // init
        sequence = new int[N + 1];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            sequence[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        int R = 0, sum = 0;
        ans = N + 1;

        for (int L = 1; L <= N; L++) {
            // L - 1 을 구간에서 제외하기
            sum -= sequence[L - 1];

            // R 을 옮길 수 있을 때 까지 옮기기
            while (R + 1 <= N && sum < S) {
                sum += sequence[++R];
            }

            // [L ... R] 의 합, 즉 sum이 조건을 만족하면 정답 갱신하기
            if (sum >= S) {
                ans = Math.min(ans, R - L + 1);
            }
        }

        if (ans == N + 1) { // 정답이 갱신이 안되었으면
            ans = 0;
        }
    }

    static void print() {
        System.out.println(ans);
    }

}

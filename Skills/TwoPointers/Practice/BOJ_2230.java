package algorithm.two_pointers.practice;

/**
 * @문제 수 고르기 [G5]
 * @날짜 220601
 * @분류 정렬 / 두 포인터
 * @조건
 * 1 <= N <= 10만
 * 0 <= M <= 20억
 * -10억 <= A[i] <= 10억
 * @풀이
 * # 두 포인터
 * 1. 수열을 오름차순 정렬 한다.
 * 2. L, R 포인터로 차이가 M 이상인 두 수의 차이를 구한다.
 * @comments
 * # 정답의 최대치: Integer => 20억(max M)
 * # 시간 복잡도: O(NlogN)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_2230 {

    static StringBuilder sb = new StringBuilder();

    static int N, M;
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
        M = Integer.parseInt(st.nextToken());
        // init
        A = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(br.readLine());
        }
    }

    static void solve() {
        Arrays.sort(A, 1, N + 1);

        int R = 1, ans = A[N] - A[1];
        for (int L = 1; L <= N; L++) {
            // R을 옮길 수 있을 때 까지 옮긴다. => A[R]과 A[L]의 차이가 M 이상이 될 때 까지
            while (R + 1 <= N && A[R] - A[L] < M) {
                R++;
            }

            // 정답 갱신
            if (A[R] - A[L] >= M) {
                ans = Math.min(ans, A[R] - A[L]);
            }
        }

        // print
        System.out.println(ans);
    }

}

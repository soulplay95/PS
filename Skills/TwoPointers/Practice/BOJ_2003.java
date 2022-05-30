package algorithm.two_pointers.practice;

/**
 * @문제 수들의 합 2 [S3]
 * @날짜 220530
 * @분류 두 포인터
 * @조건
 * 1 <= N <= 1만
 * 1 <= M <= 3억
 * 1 <= A[x] <= 3만
 * @풀이
 * # 두 포인터
 * @comments
 * # 정답의 최대치: Integer
 * # 시간 복잡도: O(N)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_2003 {

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

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        int ans = 0, sum = 0;
        for (int L = 1, R = 0; L <= N; L++) {
            // L - 1번째 인덱스에 있는 수 제거
            sum -= A[L - 1];

            // R을 옮길 수 있을 때 까지 옮긴다.
            while (R + 1 <= N && sum + A[R + 1] <= M) {
                sum += A[++R];
            }

            // 정답 갱신
            if (sum == M) {
                ans++;
            }
        }

        System.out.println(ans);
    }

}

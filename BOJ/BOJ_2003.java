/**
 * @문제 수들의 합 2_S3
 * @날짜 220302
 * @분류 두 포인터
 * @조건
 * # 1 <= 수열 길이 (N) <= 1만
 * # 1 <= 합 (M) <= 3억
 * # 1 <= 수열의 각 숫자 <= 3만
 * @풀이
 * # 두 포인터
 * @comments
 * # 정답의 최대치: Integer
 * - n + (n - 1) + (n - 2) + ... + 1
 * # 시간 복잡도: O(N)
 * # 공간 복잡도: O(N)
 */

import java.io.*;
import java.util.*;

public class BOJ_2003 {

    static StringBuilder sb = new StringBuilder();

    static int N, M, ans;
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
        M = Integer.parseInt(st.nextToken());
        // init
        A = new int[N + 1];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        ans = 0;
        int R = 0, sum = 0;
        for (int L = 1; L <= N; L++) {
            // L - 1을 구간에서 제외
            sum -= A[L - 1];

            // 구간합이 M미만일 동안 반복
            while (R + 1 <= N && sum < M) {
                R++;
                sum += A[R];
            }

            // 정답 갱신
            if (sum == M) {
                ans++;
            }
        }
    }

    static void print() {
        System.out.println(ans);
    }

}

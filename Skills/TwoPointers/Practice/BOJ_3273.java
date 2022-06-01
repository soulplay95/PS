package algorithm.two_pointers.practice;

/**
 * @문제 두 수의 합 [S3]
 * @날짜 220601
 * @분류 정렬 / 두 포인터
 * @조건
 * 1 <= ai <= 100만
 * 1 <= N <= 10만
 * 1 <= x <= 200만
 * @풀이
 * # 두 포인터
 * - 정렬
 * - 양 끝에서 시작
 * - 두 수의 합이 x보다 크면 R을 왼쪽으로 옮긴다.
 * - 두 수의 합이 x보다 작으면 L을 오른쪽으로 옮긴다.
 * @comments
 * # 정답의 최대치:
 * # 시간 복잡도: O(NlogN)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_3273 {

    static StringBuilder sb = new StringBuilder();

    static int N, x;
    static int[] A;

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        // init
        A = new int[N + 1];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        x = Integer.parseInt(br.readLine());
    }

    static void solve() {
        Arrays.sort(A, 1, N + 1);

        int L = 1, R = N, ans = 0;
        while (L < R) {
            if (A[L] + A[R] == x) {
                ans++;
            }

            if (A[L] + A[R] >= x) {
                R--;
            } else {
                L++;
            }
        }

        // print
        System.out.println(ans);
    }

}

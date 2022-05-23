package algorithm.binary_search.practice;

/**
 * @문제 두 수의 합 [S3]
 * @날짜 220523
 * @분류 정렬 / 두 포인터
 * @조건
 * # 1 <= N <= 10만
 * @풀이
 * # 이분 탐색
 * - 정렬 후, 각각 원소에 대해 합이 x가 되는 수를 이분 탐색으로 찾는다.
 * @comments
 * # 정답의 최대치: Integer
 * # 시간 복잡도: O(NlogN)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_3273 {

    static StringBuilder sb = new StringBuilder();

    static int N, x, ans;
    static int[] A;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        print();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        // init
        A = new int[N + 1];
        ans = 0;

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        x = Integer.parseInt(br.readLine());
    }

    static void solve() {
        Arrays.sort(A, 1, N + 1);

        for (int i = 1; i < N; i++) {
            int target = x - A[i];
            if (target == A[i]) {
                continue;
            }

            if (isIn(A, i + 1, N, target)) {
                ans++;
            }
        }
    }

    static boolean isIn(int[] A, int L, int R, int target) {
        while (L <= R) {
            int M = (L + R) / 2;
            if (A[M] == target) {
                return true;
            }

            if (A[M] < target) {
                L = M + 1;
            } else {
                R = M - 1;
            }
        }

        return false;
    }

    static void print() {
        System.out.println(ans);
    }

}

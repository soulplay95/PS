package algorithm.two_pointers.practice;

/**
 * @문제 세 용액 [G4]
 * @날짜 220601
 * @분류 정렬 / 이분 탐색 / 두 포인터
 * @조건
 * 3 <= N <= 5천
 * -10억 <= 특성값 <= 10억
 * @풀이
 * # 두 포인터
 * - 정렬한다.
 * - 타겟 넘버를 하나 잡고 우측 구간에서 -타겟 넘버를 찾는다.
 * @comments
 * # 정답의 최대치: Long => 30억(max 특성값 3개의 합)
 * # 시간 복잡도: O(N^2)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_2473 {

    static StringBuilder sb = new StringBuilder();

    static int N;
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
    }

    static void solve() {
        Arrays.sort(A, 1, N + 1);

        long bestSum = Long.MAX_VALUE;
        int v1 = 0, v2 = 0, v3 = 0;
        // 타겟을 선정한다.
        for (int i = 1; i <= N - 2; i++) {
            int target = -A[i];
            // 우측 구간에서 두 수의 합이 타겟에 가까운 두 숫자를 찾는다.
            int L = i + 1, R = N;
            while (L < R) {
                long sum = Math.abs(A[L] + A[R] -(long)target);
                if (sum < bestSum) {
                    bestSum = sum;
                    v1 = -target;
                    v2 = A[L];
                    v3 = A[R];
                }
                if (A[L] + A[R] > target) {
                    R--;
                } else {
                    L++;
                }
            }
        }

        // print
        System.out.println(v1 + " " + v2 + " " + v3);
    }

}

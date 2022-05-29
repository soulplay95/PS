package algorithm.two_pointers;

/**
 * @문제 두 용액 [G5]
 * @날짜 220529
 * @분류 정렬 / 이분 탐색 / 두 포인터
 * @조건
 * 2 <= N <= 10만
 * @풀이
 * # 두 포인터
 * @comments
 * # 정답의 최대치: Integer
 * # 시간 복잡도: O(NlogN)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_2470 {

    static StringBuilder sb = new StringBuilder();

    static int N;
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

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        Arrays.sort(A, 1, N + 1);

        int bestSum = Integer.MAX_VALUE;
        int v1 = 0, v2 = 0;
        int L = 1, R = N;

        while (L < R) {
            int absSum = Math.abs(A[L] + A[R]);
            if (absSum < bestSum) {
                bestSum = absSum;
                v1 = A[L];
                v2 = A[R];
            }

            if (A[L] + A[R] > 0) { // 현재 최대값(A[R]) 입장에서 최선을 찾은 상황
                R--;
            } else {
                L++;
            }
        }

        sb.append(v1).append(' ').append(v2);
    }

    static void print() {
        System.out.println(sb);
    }

}

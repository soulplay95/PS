package algorithm.binary_search;

/**
 * @문제 두 용액 [G5]
 * @날짜 220524
 * @분류 정렬 / 이분 탐색 / 두 포인터
 * @조건
 * # 2 <= N <= 10만
 * @풀이
 * # 이분 탐색
 * - 1. 수열을 오름차순 정렬한다.
 * - 2. 용액을 하나 정해서(A[left]), left + 1 ~ N 구간에서 -A[left]와 가장 가까운 값을 이분탐색으로 찾는다.
 * @comments
 * # 정답의 최대치: Integer => -20억 ~ 20억
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
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        // init
        A = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        Arrays.sort(A);

        int leftAnswer = 0, rightAnswer = 1;
        int minSum = Integer.MAX_VALUE;
        for (int left = 0; left < N - 1; left++) {
            int right = getLowerBound(A, left + 1, N - 1, -A[left]);

            // A[right - 1], A[right] 둘이 후보
            if (right - 1 > left && Math.abs(A[left] + A[right - 1]) < minSum) {
                minSum = Math.abs(A[left] + A[right - 1]);
                leftAnswer = A[left];
                rightAnswer = A[right - 1];
            }
            if (right < N && Math.abs(A[left] + A[right]) < minSum) {
                minSum = Math.abs(A[left] + A[right]);
                leftAnswer = A[left];
                rightAnswer = A[right];
            }
        }

        System.out.println(leftAnswer + " " + rightAnswer);
    }

    static int getLowerBound(int[] A, int L, int R, int target) {
        int result = N;

        while (L <= R) {
            int M = (L + R) / 2;
            if (A[M] >= target) {
                result = M;
                R = M - 1;
            } else {
                L = M + 1;
            }
        }

        return result;
    }

}

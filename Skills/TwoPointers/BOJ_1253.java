package algorithm.two_pointers;

/**
 * @문제 좋다 [G4]
 * @날짜 220530
 * @분류 자료 구조 / 정렬 / 이분 탐색 / 해시 / 두 포인터
 * @조건
 * 1 <= N <= 2000
 * |Ai| <= 10억
 * @풀이
 * # 두 포인터
 * - 타겟 넘버를 하나 정하고, 두 포인터를 통해 합이 타겟 넘버가 되는 수를 찾는다.
 * @comments
 * # 정답의 최대치: Integer => max N 이하 / 원소 2개의 합도 max 20억
 * # 시간 복잡도: O(N^2)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_1253 {

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

        int ans = 0;
        for (int i = 1; i <= N; i++) {
            if (isSumOfOtherNumbers(i)) {
                ans++;
            }
        }

        // print
        System.out.println(ans);
    }

    static boolean isSumOfOtherNumbers(int targetIndex) {
        int L = 1, R = N;
        int target = A[targetIndex];
        while (L < R) {
            if (L == targetIndex) L++;
            else if (R == targetIndex) R--;
            else {
                if (A[L] + A[R] > target) R--;
                else if (A[L] + A[R] == target) return true;
                else L++;
            }
        }

        return false;
    }

}

package algorithm.parametric_search.practice;

/**
 * @문제 예산 [S3]
 * @날짜 220527
 * @분류 이분 탐색 / 매개 변수 탐색
 * @조건
 * 3 <= N <= 1만
 * 1 <= 각 지방 예산 요청 금액 <= 10만
 * N <= 총 예산 <= 10억
 * @풀이
 * # 매개 변수 탐색
 * - 1원 ~ 최대 예산 요청 금액 사이의 금액 중 하나를 이분 탐색으로 정해서 총 예산 내에 지급 가능한지 여부를 따져본다.
 * @comments
 * # 정답의 최대치: Integer
 * # 시간 복잡도: O(Nlog(10만))
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_2512 {

    static StringBuilder sb = new StringBuilder();

    static int N, totalBudget, L, R, ans;
    static int[] requestBudgets;

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
        requestBudgets = new int[N];
        L = 1;
        R = 1;

        int sumOfRequestBudgets = 0;
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            requestBudgets[i] = Integer.parseInt(st.nextToken());
            R = Math.max(R, requestBudgets[i]);
            sumOfRequestBudgets += requestBudgets[i];
        }
        totalBudget = Integer.parseInt(br.readLine());

        if (sumOfRequestBudgets <= totalBudget) {
            System.out.println(R);
            System.exit(0);
        }
    }

    static void solve() {
        ans = 1;
        while (L <= R) {
            int M = (L + R) / 2;
            if (determination(M)) {
                ans = M;
                L = M + 1;
            } else {
                R = M - 1;
            }
        }
    }

    static boolean determination(int maxBudget) {
        int budgetSum = 0;
        for (int i = 0; i < N; i++) {
            if (requestBudgets[i] > maxBudget) {
                budgetSum += maxBudget;
            } else {
                budgetSum += requestBudgets[i];
            }
        }

        return budgetSum <= totalBudget;
    }

    static void print() {
        System.out.println(ans);
    }

}

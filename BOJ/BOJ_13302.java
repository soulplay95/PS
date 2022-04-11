package study.w220411;

/**
 * @문제 리조트_G5
 * @날짜 220411
 * @분류
 * @조건
 * # 1 <= 일수(N) <= 100
 * @풀이
 * # 완탐
 * @comments
 * # 정답의 최대치: Integer
 * # 시간 복잡도:
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_13302 {

    static StringBuilder sb = new StringBuilder();

    static int N, M, min;
    static boolean[] isNotOpen;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        input();
        solve();
//        print();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        // init
        isNotOpen = new boolean[N + 1];
        min = Integer.MAX_VALUE;

        dp = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(dp[i], -1);
        }

        if (M == 0) {
            return;
        }

        st = new StringTokenizer(br.readLine(), " ");
        for (int m = 0; m < M; m++) {
            int offDay = Integer.parseInt(st.nextToken());
            isNotOpen[offDay] = true;
        }
    }

//    static void dfs(int day, int money, int coupons) {
//        // 종료 조건 - 모든 날짜를 다 고려했으면 종료
//        if (day >= N + 1) {
//            min = Math.min(min, money); // 최소 금액 갱신
//            return;
//        }
//
//        // 백트래킹 - 이미 구한 최소 금액보다 크다면 돌아간다.
//        if (money >= min) {
//            return;
//        }
//
//        if (isNotOpen[day]) { // 이용 못하는 날
//            dfs(day + 1, money, coupons);
//        } else { // 이용 가능한 날
//            // 1. 쿠폰 이용
//            if (coupons >= 3) {
//                dfs(day + 1, money, coupons - 3);
//            }
//
//            // 2. 하루권 이용
//            dfs(day + 1, money + 10000, coupons);
//
//            // 3. 3일권 이용
//            dfs(day + 3, money + 25000, coupons + 1);
//
//            // 4. 5일권 이용
//            dfs(day + 5,money + 37000, coupons + 2);
//        }
//    }

    static int dfs(int day, int coupon) {
        // 종료 조건
        if (day > N) {
            return 0;
        }

        // 메모이제이션
        if (dp[day][coupon] >= 0) {
            return dp[day][coupon];
        }

        dp[day][coupon] = Integer.MAX_VALUE; // 최소값 갱신을 위해 최대값으로 초기화

        if (isNotOpen[day]) { // 이용 못하는 날
            return dp[day][coupon] = Math.min(
                    dp[day][coupon],
                    dfs(day + 1, coupon) // skip
            );
        } else {
            if (coupon >= 3) { // 쿠폰 사용 가능하면
                dp[day][coupon] = Math.min(
                        dp[day][coupon],
                        dfs(day + 1, coupon - 3) // 쿠폰 사용
                );
            }
            dp[day][coupon] = Math.min(
                    dp[day][coupon],
                    dfs(day + 1, coupon) + 10000 // 하루권
            );
            dp[day][coupon] = Math.min(
                    dp[day][coupon],
                    dfs(day + 3, coupon + 1) + 25000 // 3일권
            );
            dp[day][coupon] = Math.min(
                    dp[day][coupon],
                    dfs(day + 5, coupon + 2) + 37000 // 5일권
            );
        }

        return dp[day][coupon];
    }

    static void solve() {
//        dfs(1, 0, 0);
        System.out.println(dfs(1, 0));
    }

    static void print() {
        System.out.println(min);
    }

}

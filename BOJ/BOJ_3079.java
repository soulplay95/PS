package study.w220314;

/**
 * @문제 입국심사_G5
 * @날짜 220312
 * @분류 이분 탐색 / 매개 변수 탐색
 * @조건
 * # 1 <= 입국심사대 수 (N) <= 10만
 * # 1 <= 사람 수 (M) <= 10억
 * # 1 <= 심사를 하는데 걸리는 시간 (Tk) <= 10억
 * @풀이
 * # 매개 변수 탐색
 * - 심사를 받는데 걸리는 총 시간을 정한다. => 1 ~ 10^18 범위에서 이분 탐색
 * - 해당 시간 동안 M명 이상을 심사할 수 있는지 체크한다. => lower_bound 체크
 * -- 각 심사대별로 (총 시간 / Tk) 하면 해당 심사대에서 몇명까지 심사할 수 있는지 카운트 할 수 있다.
 * @comments
 * # 정답의 최대치: long
 * - 1개의 심사대가 있고, Tk가 10^9일때, 10^9명이 전부 심사를 받는데 걸리는 시간: 10^9 * 10^9 = 10^18 < Long.MAX_VALUE := 9*10^18
 * # 시간 복잡도: O(NlogM)
 * # 공간 복잡도: O(N)
 */

import java.io.*;
import java.util.*;

public class BOJ_3079 {

    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static int[] times;
    static long max, ans;

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
        times = new int[N];
        max = 0L;

        for (int i = 0; i < N; i++) {
            times[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, times[i]);
        }

        max *= M;
    }

    static boolean determination(long totalTime) {
        long counts = 0L;

        for (int i = 0; i < N; i++) {
            counts += totalTime / times[i];
        }

        return counts >= M;
    }

    static void solve() {
//        long L = 1L, R = (long) Math.pow(10, 18);
        long L = 1L, R = max;

        while (L <= R) {
            long mid = (L + R) / 2;

            if (determination(mid)) {
                ans = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
    }

    static void print() {
        System.out.println(ans);
    }

}

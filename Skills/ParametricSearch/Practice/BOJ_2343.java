package algorithm.parametric_search.practice;

/**
 * @문제 기타 레슨 [S1]
 * @날짜 220527
 * @분류 이분 탐색 / 매개 변수 탐색
 * @조건
 * 1 <= N <= 10만
 * 1 <= M <= N
 * 1 <= 각 강의 길이 <= 1만
 * @풀이
 * # 매개 변수 탐색
 * - 1 ~ 10억 사이의 블루레이 크기 중 하나를 정해 M개로 모두 녹화할 수 있는지 따져본다.
 * @comments
 * # 정답의 최대치: Integer
 * # 시간 복잡도: O(Nlog(10억))
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_2343 {

    static StringBuilder sb = new StringBuilder();

    static int N, M, ans;
    static int[] times;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        print();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        // init
        times = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            times[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        int L = 1;
        for (int i = 0; i < N; i++) {
            L = Math.max(L, times[i]);
        }

        int R = 1000000000;
        ans = 1;
        while (L <= R) {
            int M = (L + R) / 2;
            if (determination(M)) {
                ans = M;
                R = M - 1;
            } else {
                L = M + 1;
            }
        }
    }

    static boolean determination(int max) {
        int counts = 1, currentSum = 0;
        for (int i = 0; i < N; i++) {
            if (currentSum + times[i] <= max) {
                currentSum += times[i];
            } else {
                currentSum = times[i];
                counts++;
            }
        }

        return counts <= M;
    }

    static void print() {
        System.out.println(ans);
    }

}

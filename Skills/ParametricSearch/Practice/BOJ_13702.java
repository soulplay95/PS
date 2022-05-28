package algorithm.parametric_search.practice;

/**
 * @문제 이상한 술집 [S3]
 * @날짜 220528
 * @분류 이분 탐색
 * @조건
 * N <= 1만
 * K <= 100만
 * 0 <= 용량 <= Integer.MAX_VALUE
 * @풀이
 * - 1 ~ Integer.MAX_VALUE 의 범위에서 용량을 정하고 K명 이상 나눠줄 수 있는지 체크한다.
 * @comments
 * # 정답의 최대치: Long => max 몇명에게 나눠줄 수 있는지: 10000 * Integer.MAX_VALUE
 * # 시간 복잡도: O(Nlog(Integer.MAX_VALUE))
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_13702 {

    static StringBuilder sb = new StringBuilder();

    static int N, K;
    static long ans;
    static int[] capacity;

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
        K = Integer.parseInt(st.nextToken());
        // init
        capacity = new int[N];

        for (int i = 0; i < N; i++) {
            capacity[i] = Integer.parseInt(br.readLine());
        }
    }

    static void solve() {
        long L = 1L, R = Integer.MAX_VALUE;
        ans = 1L;
        while (L <= R) {
            long mid = (L + R) / 2L;
            if (determination(mid)) {
                ans = mid;
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
    }

    static boolean determination(long mod) {
        long counts = 0L;
        for (int i = 0; i < N; i++) {
            counts += capacity[i] / mod;
        }

        return counts >= K;
    }

    static void print() {
        System.out.println(ans);
    }

}

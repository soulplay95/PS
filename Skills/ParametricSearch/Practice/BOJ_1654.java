package algorithm.parametric_search.practice;

/**
 * @문제 랜선 자르기 [S3]
 * @날짜 220526
 * @분류 이분 탐색 / 매개 변수 탐색
 * @조건
 * 1 <= K <= 1만
 * 1 <= N <= 100만
 * 1 <= 랜선 길이 <= Integer.MAX_VALUE
 * @풀이
 * # 매개 변수 탐색
 * - 1 ~ Integer.MAX_VALUE 범위에서 길이를 이분 탐색으로 정하고, 이 길이로 잘랐을 때 N개 이상이 나오는지 구한다.
 * @comments
 * # 정답의 최대치: Long => < 개수(max 10000 * Integer.MAX_VALUE)
 * # 시간 복잡도: O(Klog(Integer.MAX_VALUE))
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_1654 {

    static StringBuilder sb = new StringBuilder();

    static int K, N;
    static long ans;
    static int[] lengths;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        print();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine(), " ");
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        // init
        lengths = new int[K];

        for (int i = 0; i < K; i++) {
            lengths[i] = Integer.parseInt(br.readLine());
        }
    }

    static void solve() {
        ans = 1L;
        long L = 1, R = Integer.MAX_VALUE;
        while (L <= R) {
            long M = (L + R) / 2L;
            if (determination(M)) {
                ans = M;
                L = M + 1;
            } else {
                R = M - 1;
            }
        }
    }

    static boolean determination(long length) {
        long counts = 0;
        for (int i = 0; i < K; i++) {
            counts += lengths[i] / length;
        }

        return counts >= N;
    }

    static void print() {
        System.out.println(ans);
    }

}

package algorithm.parametric_search.practice;

/**
 * @문제 용돈 관리 [S2]
 * @날짜 220528
 * @분류 이분 탐색 / 매개 변수 탐색
 * @조건
 * 1 <= N <= 10만
 * 1 <= M <= N
 * 1 <= 금액 <= 1만
 * @풀이
 * # 매개 변수 탐색
 * inputMin ~ 10억원 사이의 금액 중 하나를 정해서 M번 인출할 수 있는지 따져본다.
 * @comments
 * # 정답의 최대치: Integer => max 10억
 * # 시간 복잡도: O(Nlog(10억))
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_6236 {

    static StringBuilder sb = new StringBuilder();

    static int N, M, ans, L, R;
    static int[] moneys;

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
        moneys = new int[N];
        L = 0;
        R = 0;

        for (int i = 0; i < N; i++) {
            moneys[i] = Integer.parseInt(br.readLine());
            L = Math.max(L, moneys[i]);
            R += moneys[i];
        }
    }

    static void solve() {
        ans = R;
        while (L <= R) {
            int mid = (L + R) / 2;
            if (determination(mid)) {
                ans = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
    }

    static boolean determination(int K) {
        int counts = 1, money = K;

        for (int i = 0; i < N; i++) {
            if (money >= moneys[i]) {
                money -= moneys[i];
            } else {
                money = K - moneys[i];
                counts++;
            }
        }

        return counts <= M;
    }

    static void print() {
        System.out.println(ans);
    }

}

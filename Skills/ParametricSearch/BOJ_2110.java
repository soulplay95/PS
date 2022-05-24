package algorithm.parametric_search;

/**
 * @문제 공유기 설치 [G5]
 * @날짜 220525
 * @분류 이분 탐색 / 매개 변수 탐색
 * @조건
 * 2 <= N <= 20만
 * 2 <= C <= N
 * 0 <= xi <= 10억
 * @풀이
 * 1 ~ 10억 사이의 범위에서 숫자 하나를 골라 이 숫자 만큼 거리를 벌렸을 때 C개를 설치할 수 있는가
 * @comments
 * # 정답의 최대치: Integer => max 10억
 * # 시간 복잡도: O(NlogN + NlogX)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_2110 {

    static StringBuilder sb = new StringBuilder();

    static int N, C, max;
    static int[] x;

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
        C = Integer.parseInt(st.nextToken());
        // init
        x = new int[N];

        for (int i = 0; i < N; i++) {
            x[i] = Integer.parseInt(br.readLine());
        }
    }

    static void solve() {
        Arrays.sort(x);

        max = 1;
        int L = 1, R = 1000000000;
        while (L <= R) {
            int M = (L + R) / 2;
            if (determination(M)) {
                max = M;
                L = M + 1;
            } else {
                R = M - 1;
            }
        }
    }

    static boolean determination(int gap) {
        int counts = 1, lastX = x[0];
        for (int i = 1; i < N; i++) {
            if (x[i] - lastX >= gap) {
                counts++;
                lastX = x[i];
            }
        }

        return counts >= C;
    }

    static void print() {
        System.out.println(max);
    }

}

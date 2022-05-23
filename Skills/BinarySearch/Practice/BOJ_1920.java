package algorithm.binary_search.practice;

/**
 * @문제 수 찾기 [S4]
 * @날짜 220523
 * @분류 자료 구조 / 이분 탐색
 * @조건
 * # 1 <= N, M <= 10만
 * @풀이
 * # 이분 탐색
 * - M개의 수 각각에 대해 A 수열에서 이분 탐색으로 찾는다.
 * @comments
 * # 정답의 최대치: Integer
 * # 시간 복잡도: O((N + M)logN)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_1920 {

    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static int[] A, B;

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
        A = new int[N + 1];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        M = Integer.parseInt(br.readLine());
        // init
        B = new int[M + 1];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= M; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        Arrays.sort(A, 1, N + 1);

        for (int i = 1; i <= M; i++) {
            if (isIn(A, 1, N, B[i])) {
                sb.append(1);
            } else {
                sb.append(0);
            }
            sb.append("\n");
        }
    }

    static boolean isIn(int[] A, int L, int R, int x) {
        while (L <= R) {
            int M = (L + R) / 2;
            if (A[M] == x) {
                return true;
            } else if (A[M] < x) {
                L = M + 1;
            } else {
                R = M - 1;
            }
        }

        return false;
    }

    static void print() {
        System.out.println(sb.toString());
    }

}

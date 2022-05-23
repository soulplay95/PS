package algorithm.binary_search.practice;

/**
 * @문제 숫자 카드 2 [S4]
 * @날짜 220523
 * @분류 자료 구조 / 정렬 / 이분 탐색
 * @조건
 * # 1 <= N, M <= 50만
 * @풀이
 * # 이분 탐색
 * - upperBound - lowerBound
 * - upperBound : x를 초과하는 수 중 제일 왼쪽의 인덱스
 * - lowerBound : x 이상의 수 중 제일 왼족 인덱스
 * @comments
 * # 정답의 최대치: Integer
 * # 시간 복잡도: O((N + M)logN)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_10816 {

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
            int upperBound = getUpperBound(A, 1, N, B[i]);
            int lowerBound = getLowerBound(A, 1, N, B[i]);
            sb.append(upperBound - lowerBound).append(" ");
        }
    }

    static int getUpperBound(int[] A, int L, int R, int target) {
        int result = R + 1;
        while (L <= R) {
            int M = (L + R) / 2;
            if (A[M] > target) {
                result = M;
                R = M - 1;
            } else {
                L = M + 1;
            }
        }

        return result;
    }

    static int getLowerBound(int[] A, int L, int R, int target) {
        int result = R + 1;
        while (L <= R) {
            int M = (L + R) / 2;
            if (A[M] >= target) {
                result = M;
                R = M - 1;
            } else {
                L = M + 1;
            }
        }

        return result;
    }

    static void print() {
        System.out.println(sb.toString());
    }

}

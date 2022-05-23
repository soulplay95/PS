package algorithm.binary_search.practice;

/**
 * @문제 듣보잡 [S4]
 * @날짜 220523
 * @분류 자료 구조 / 문자열 / 정렬 / 해시
 * @조건
 * # 1 <= N, M <= 50만
 * @풀이
 * # 이분 탐색
 * - 듣도 못한 사람 수열에서 보도 못한 사람 각각에 대해 존재하는지 이분 탐색으로 빠르게 찾는다.
 * @comments
 * # 정답의 최대치: Integer => max 50만
 * # 시간 복잡도: O(NlogN + MlogM + NlogM)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_1764 {

    static StringBuilder sb = new StringBuilder();

    static int N, M, ans;
    static String[] A, B;

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
        ans = 0;
        A = new String[N + 1];
        B = new String[M + 1];

        for (int i = 1; i <= N; i++) {
            A[i] = br.readLine();
        }
        for (int i = 1; i <= M; i++) {
            B[i] = br.readLine();
        }
    }

    static void solve() {
        Arrays.sort(A, 1, N + 1);
        Arrays.sort(B, 1, M + 1);

        for (int i = 1; i <= N; i++) {
            if (isIn(B, 1, M, A[i])) {
                ans++;
                sb.append(A[i]).append("\n");
            }
        }

        sb.insert(0, ans + "\n");
    }

    static boolean isIn(String[] A, int L, int R, String target) {
        while (L <= R) {
            int M = (L + R) / 2;
            int compareFlag = A[M].compareTo(target);
            if (compareFlag == 0) {
                return true;
            }

            if (compareFlag < 0) {
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

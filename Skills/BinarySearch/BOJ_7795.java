package algorithm.binary_search;

/**
 * @문제 먹을 것인가 먹힐 것인가 [S3]
 * @날짜 220523
 * @분류 정렬 / 이분 탐색 / 두 포인터
 * @조건
 * # 1 <= N, M <= 2만
 * @풀이
 * # 이분 탐색
 * - A의 각 원소마다 B에서 이분 탐색을 통해 미만의 수의 개수(미만의 수 중 제일 오른쪽에 있는 수의 인덱스)를 빠르게 찾는다.
 * @comments
 * # 정답의 최대치: Integer => max 4억(모든 쌍이 정답인 경우)
 * # 시간 복잡도: O((N + M)logM)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_7795 {

    static StringBuilder sb = new StringBuilder();

    static int N, M, ans;
    static int[] A, B;

    public static void main(String[] args) throws IOException {
        input();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            // init
            ans = 0;
            A = new int[N + 1];
            B = new int[M + 1];

            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 1; i <= N; i++) {
                A[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 1; i <= M; i++) {
                B[i] = Integer.parseInt(st.nextToken());
            }

            solve();

            sb.append(ans).append("\n");
        }

        System.out.println(sb.toString());
    }

    static void solve() {
        // 1. B를 오름차순 정렬
        Arrays.sort(B, 1, M + 1);

        // 2. A의 모든 원소에 대하여 B에서 이분 탐색으로 미만의 수의 개수를 찾는다.
        for (int i = 1; i <= N; i++) {
            ans += getLowerBound(A[i]);
        }
    }

    static int getLowerBound(int x) {
        int L = 1, R = M, result = 0;

        while (L <= R) {
            int M = (L + R) / 2;
            if (B[M] < x) {
                result = M;
                L = M + 1;
            } else {
                R = M - 1;
            }
        }

        return result;
    }
}

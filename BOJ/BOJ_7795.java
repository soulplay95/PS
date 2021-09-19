package algorithm.이분탐색;

/**
 * @문제 먹을 것인가 먹힐 것인가_S3
 * @날짜 210920
 * @분류 정렬, 이분 탐색, 두 포인터
 * @조건
 * # 1 <= A, B의 크기(N, N) <= 20000
 * @풀이
 * # A의 모든 원소에 대해 B에서 이분 탐색
 * @comment
 * # 이분 탐색 기본 문제
 */

import java.util.*;
import java.io.*;

public class BOJ_7795 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static int[] A, B;

    public static void main(String[] args) throws Exception {
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            // init
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
        }

        // print
        System.out.println(sb.toString());
    }

    static void solve() {
        // B 배열에 대해 이분 탐색을 할 것이므로 정렬한다.
        Arrays.sort(B);

        int ans = 0;
        for (int i = 1; i <= N; i++) {
            // A[i]를 선택했을 때, B에서는 A[i]보다 작은게 몇 개나 있는지 count
            ans += lower_bound(A[i]);
        }

        sb.append(ans).append("\n");
    }

    static int lower_bound(int x) {
        // B[L...R]에서 x 미만의 수 중 제일 오른쪽 인덱스를 return하는 함수
        int L = 1;
        int R = M;
        int result = 0;

        while (L <= R) {
            int mid = (L + R) / 2;
            if (B[mid] < x) {
                result = mid;
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }

        return result;
    }

}

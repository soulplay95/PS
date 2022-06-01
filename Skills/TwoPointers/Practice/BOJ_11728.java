package algorithm.two_pointers.practice;

/**
 * @문제 배열 합치기 [S5]
 * @날짜 220601
 * @분류 정렬 / 두 포인터
 * @조건
 * 1 <= N, M <= 100만
 * |Ai, Bi| <= 10억
 * @풀이
 * # 두 포인터
 * - 각 배열에 포인터를 하나씩 두고, 포인터에 해당하는 원소의 값을 비교 후 작은 것을 출력하고 포인터를 하나 옮긴다.
 * @comments
 * # 정답의 최대치: Integer => 10억(max 원소)
 * # 시간 복잡도: O(N)
 * # 공간 복잡도:
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11728 {

    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static int[] A, B;

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

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
    }

    static void solve() {
        int a = 1, b = 1;
        while (true) {
            if (a > N || b > M) {
                break;
            }

            if (A[a] <= B[b]) {
                sb.append(A[a]).append(" ");
                a++;
            } else {
                sb.append(B[b]).append(" ");
                b++;
            }
        }

        // 나머지 털기
        while (a <= N) {
            sb.append(A[a++]).append(" ");
        }
        while (b <= M) {
            sb.append(B[b++]).append(" ");
        }

        // print
        System.out.println(sb.toString());
    }

}

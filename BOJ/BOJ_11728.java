/**
 * @문제 배열 합치기_S5
 * @날짜 220302
 * @분류 정렬 / 두 포인터
 * @조건
 * # 1 <= 배열 크기 (N, M) <= 100만
 * # -10억 <= 배열에 들어있는 수 <= 10억
 * @풀이
 * # 두 포인터
 * - A, B 배열 각각을 정렬한다. => O(NlogN + MlogM)
 * - 각 배열의 첫번째 원소에 포인터를 두고 값을 비교하여 작은 값을 위치시키고 다음 포인터로 옮긴다. => O(N + M)
 * @comments
 * # 정답의 최대치: Integer
 * # 시간 복잡도: O(NlogN)
 * # 공간 복잡도: O(N + M)
 */

import java.io.*;
import java.util.*;

public class BOJ_11728 {

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
        StringTokenizer st;

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
        // A, B 배열을 오름차순 정렬
        Arrays.sort(A, 1, N + 1);
        Arrays.sort(B, 1, M + 1);

        int ap = 1, bp = 1;
        while (ap <= N && bp <= M) {
            if (A[ap] <= B[bp]) {
                sb.append(A[ap]).append(" ");
                ap++;
            } else {
                sb.append(B[bp]).append(" ");
                bp++;
            }
        }

        while (ap <= N) {
            sb.append(A[ap++]).append(" ");
        }
        while (bp <= M) {
            sb.append(B[bp++]).append(" ");
        }
    }

    static void print() {
        System.out.println(sb.toString());
    }

}

/**
 * @문제 숫자 카드_S4
 * @날짜 220301
 * @분류 정렬 / 이분 탐색
 * @조건
 * # 1 <= 숫자 카드 개수 (N), M <= 50만
 * # -천만 <= 각 숫자 <= 천만
 * @풀이
 * # 이분 탐색
 * @comments
 * # 정답의 최대치: Integer
 * # 시간 복잡도: O((N + M)logN)
 * - N개의 숫자 카드 오름차순 정렬 => O(NlogN)
 * - M개의 숫자 각각에 대해(M), N개의 숫자 중 이분 탐색(logN) => O(MlogN)
 * # 공간 복잡도: O(N)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_10815 {

    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static int[] A;

    public static void main(String[] args) throws IOException {
        input();
        print();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        // init
        A = new int[N + 1];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(A, 1, N + 1);

        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < M; i++) {
            int x = Integer.parseInt(st.nextToken());

            if (isHave(x)) {
                sb.append(1);
            } else {
                sb.append(0);
            }
            sb.append(" ");
        }
    }

    static boolean isHave(int x) {
        int L = 1, R = N;

        while (L <= R) {
            int mid = (L + R) / 2;
            if (A[mid] == x) {
                return true;
            } else if (A[mid] < x) {
                L = mid + 1;
            } else if (A[mid] > x) {
                R = mid - 1;
            }
        }

        return false;
    }

    static void print() {
        System.out.println(sb.toString());
    }

}

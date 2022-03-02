/**
 * @문제 수열_S3
 * @날짜 220302
 * @분류
 * @조건
 * # 2 <= 수열 길이 (N) <= 10만
 * # -100 <= 각 숫자 <= 100
 * @풀이
 * # 두 포인터
 * - 구간의 길이가 일정
 * - 한 싸이클에 L과 R을 하나씩 밀면서 기존 L값은 구간합에서 제외하고 새로운 R값을 더한다.
 * @comments
 * # 정답의 최대치: Integer
 * # 시간 복잡도: O(N)
 * # 공간 복잡도: O(N)
 */

import java.io.*;
import java.util.*;

public class BOJ_2559 {

    static StringBuilder sb = new StringBuilder();

    static int N, K, max;
    static int[] input;

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
        K = Integer.parseInt(st.nextToken());
        // init
        input = new int[N + 1];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        // init
        int sum = 0, L = 1, R = L + K - 1;
        for (int k = 1; k <= K; k++) {
            sum += input[k];
            max = sum;
        }

        while (R < N) {
            sum -= input[L++];
            sum += input[++R];
            max = Math.max(max, sum);
        }
    }

    static void print() {
        System.out.println(max);
    }

}

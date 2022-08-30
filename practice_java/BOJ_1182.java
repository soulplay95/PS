package practice.bruteforce;

/**
 * @문제 부분수열의 합 [S2]
 * @날짜 220825
 * @분류 완탐 / 백트래킹
 * @조건
 * # 1 <= N <= 20
 * # -1_000_000 <= S <= 1_000_000
 * @풀이
 * # 부분 수열
 * @복잡도
 * # 정답의 최대치: Integer
 * # 시간 복잡도: O(2^N)
 * # 공간 복잡도:
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1182 {

    static StringBuilder sb = new StringBuilder();

    static int N, S, answer;
    static int[] numbers;

    public static void main(String[] args) throws IOException {
        init();
        solve();
        print();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        numbers = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        answer = 0;
    }

    static void solve() {
        recursive(0, 0);

        if (S == 0 && answer > 0) {
            // 공집합 제외
            answer--;
        }
    }

    static void recursive(int depth, int sum) {
        if (depth == N) {
            if (sum == S) {
                answer++;
            }
        } else {
            recursive(depth + 1, sum + numbers[depth]);
            recursive(depth + 1, sum);
        }
    }

    static void print() {
        System.out.println(answer);
    }

}

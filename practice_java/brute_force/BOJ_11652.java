package practice.sort;

/**
 * @문제 카드 [S4]
 * @날짜 220828
 * @분류 자료 구조 / 정렬
 * @조건
 * 1 <= N <= 10만
 * @풀이
 * # 정렬
 * @복잡도
 * # 정답의 최대치: Long
 * # 시간 복잡도: O(NlogN)
 * # 공간 복잡도:
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11652 {

    static StringBuilder sb = new StringBuilder();

    static int N;
    static long answer;
    static long[] numbers;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        print();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        // init
        numbers = new long[N];

        for (int i = 0; i < N; i++) {
            numbers[i] = Long.parseLong(br.readLine());
        }
    }

    static void solve() {
        Arrays.sort(numbers);

        answer = numbers[0];
        int current = 1; // 현재 등장 횟수
        int max = 1; // 최대 등장 횟수

        for (int i = 1; i < N; i++) {
            if (numbers[i] == numbers[i - 1]) {
                current++;
            } else {
                current = 1;
            }

            if (current > max) {
                max = current;
                answer = numbers[i];
            }
        }
    }

    static void print() {
        System.out.println(answer);
    }

}

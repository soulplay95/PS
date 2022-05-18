package algorithm.sort;

/**
 * @문제 카드 [S4]
 * @날짜 220519
 * @분류 자료 구조 / 정렬 / 해시
 * @조건
 * # 1 <= N <= 10만
 * @풀이
 * # 정렬
 * - 정렬 후, 한번 쭉 훑으면서 등장 횟수를 고려하여 정답을 구한다.
 * @comments
 * # 정답의 최대치: Long => -2^62 ~ 2^62
 * # 시간 복잡도: O(NlogN)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

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
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        // init
        numbers = new long[N];

        for (int i = 0; i < N; i++) {
            numbers[i] = Long.parseLong(br.readLine());
        }
    }

    static void solve() {
        Arrays.sort(numbers, 0, N);

        answer = numbers[0];
        int currentAppearCounts = 1; // 현재 값의 등장 횟수
        int maxAppearCounts = 1; // 최대 등장 횟수

        // 두번째 원소부터 본다.
        for (int i = 1; i < N; i++) {
            if (numbers[i - 1] == numbers[i]) { // 같은 값이면
                currentAppearCounts++;
            } else {
                currentAppearCounts = 1;
            }

            if (currentAppearCounts > maxAppearCounts) {
                maxAppearCounts = currentAppearCounts;
                answer = numbers[i];
            }
        }
    }

    static void print() {
        System.out.println(answer);
    }

}

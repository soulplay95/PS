package study.w220307;

/**
 * @문제 단어 수학_G4
 * @날짜 220307
 * @분류
 * @조건
 * # 1 <= 단어 개수 (N) <= 10
 * # 1 <= 수의 길이 <= 8
 * @풀이
 * # 그리디
 * - 각 알파벳에 매핑되는 값을 소문자(a, b, ..., z)로 표현할 때
 * - ACDEB + GCF = (a * 10^4) + (c * 10^3) + ... + (b * 10^0) + (f * 10^0) 이므로
 * - 값으로 다시 묶었을 때 곱해지는 값이 큰 순으로 9부터 매핑한다.
 * @comments
 * # 정답의 최대치: Integer
 * - 99,999,999 * 10
 * # 시간 복잡도: O(N)
 * # 공간 복잡도: O(N)
 */

import java.io.*;
import java.util.*;

public class BOJ_1339_2 {

    static StringBuilder sb = new StringBuilder();

    static int N, ans;
    static int[] digitSum;

    static final int MAX_LENGTH = 8;

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
        digitSum = new int[26];
        ans = 0;

        for (int i = 0; i < N; i++) {
            char[] word = br.readLine().toCharArray();
            int pow = 0;
            int length = word.length;

            // 각 자릿수 값을 누적
            for (int digit = length - 1; digit >= 0; digit--) {
                char alphabet = word[digit];
                digitSum[alphabet - 'A'] += (int) Math.pow(10, pow++);
            }
        }
    }

    static void solve() {
        Arrays.sort(digitSum);

        int value = 9;
        for (int i = 25; i >= 16; i--) {
            ans += digitSum[i] * value--;
        }
    }

    static void print() {
        System.out.println(ans);
    }

}

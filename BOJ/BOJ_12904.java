package study.w220425;

/**
 * @문제 A와 B_G5
 * @날짜 220424
 * @분류 구현 / 문자열 / 그리디
 * @조건
 * # 1 <= S 길이 <= 999
 * # 2 <= T 길이 <= 1000
 * @풀이
 * # S -> T로 완탐: O(2^1000)
 * # T -> S: O(N)
 * - 1. T를 S와 길이가 같아질 때 까지 변환한다.
 * - 1-1. 현재 T 맨 뒤에 A가 붙어있으면 A를 제거
 * - 1-2. 현재 T 맨 뒤에 B가 붙어있으면 B를 제거하고 뒤집기
 * @comments
 * # 정답의 최대치:
 * # 시간 복잡도:
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_12904 {

    static String S;
    static StringBuilder T;

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        S = br.readLine();
        T = new StringBuilder(br.readLine());
    }

    static void solve() {
        while (T.length() != S.length()) {
            // T의 맨 뒤 문자에 따라 변환
            char tail = T.charAt(T.length() - 1);

            if (tail == 'A') {
                T.deleteCharAt(T.length() - 1);
            } else {
                T.deleteCharAt(T.length() - 1);
                T.reverse();
            }
        }

        if (S.equals(T.toString())) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

}

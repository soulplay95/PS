package algorithm.brute_force.practice;

/**
 * @문제 암호 만들기 [G5]
 * @날짜 220510
 * @분류 수학 / 완전 탐색 / 조합론 / 백트래킹
 * @조건
 * # 3 <= L <= C <= 15
 * @풀이
 * # 조합
 * - 1. 문자 사전순으로 정렬
 * - 2. 조합
 * - 2-1. 모음, 자음 개수 확인
 * @comments
 * # 정답의 최대치:
 * # 시간 복잡도: O(15C7)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_1759 {

    static StringBuilder sb = new StringBuilder();

    static int N, R;
    static char[] alphabets, selected;

    static final String VOWELS = "aeiou";

    public static void main(String[] args) throws IOException {
        input();
        solve();
        print();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        // init
        alphabets = new char[N + 1];
        selected = new char[R + 1];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            alphabets[i] = st.nextToken().charAt(0);
        }
    }

    static void recursive(int depth, int start, int voewlCounts, int consonantCounts) {
        if (depth == R + 1) {
            if (voewlCounts >= 1 && consonantCounts >= 2) {
                sb.append(String.valueOf(selected, 1, R));
                sb.append("\n");
            }
        } else {
            // start index 이후의 알파벳 부터 뽑는다.
            for (int candidateIndex = start; candidateIndex <= N; candidateIndex++) {
                selected[depth] = alphabets[candidateIndex];
                if (isVowel(alphabets[candidateIndex])) {
                    recursive(depth + 1, candidateIndex + 1, voewlCounts + 1, consonantCounts);
                } else {
                    recursive(depth + 1, candidateIndex + 1, voewlCounts, consonantCounts + 1);
                }
            }
        }
    }

    static boolean isVowel(char c) {
        if (VOWELS.contains(String.valueOf(c))) {
            return true;
        } else {
            return false;
        }
    }

    static void solve() {
        Arrays.sort(alphabets);
        recursive(1, 1, 0, 0);
    }

    static void print() {
        System.out.println(sb.toString());
    }

}

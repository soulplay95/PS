package algorithm.brute_force.practice;

/**
 * @문제 부등호 [S2]
 * @날짜 220517
 * @분류 완전 탐색 / 백트래킹
 * @조건
 * # 2 <= k <= 9
 * @풀이
 * # 순열
 * - 0 ~ 9를 주어진 부등호에 맞게 순서대로 나열한다.
 * @comments
 * # 정답의 최대치: Long => max 9876543210
 * # 시간 복잡도: O(N!) => max 10!
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_2529 {

    static StringBuilder sb = new StringBuilder();

    static int K;
    static char[] signs;
    static String min, max;
    static boolean[] used;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        print();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        K = Integer.parseInt(br.readLine());
        // init
        signs = new char[K];
        min = "9999999999";
        max = "0000000000";
        used = new boolean[10];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < K; i++) {
            signs[i] = st.nextToken().charAt(0);
        }
    }

    static void solve() {
        recursive(0, -1, "");
    }

    static void recursive(int depth, int before, String number) {
        if (depth == K + 1) {
            if (number.compareTo(max) > 0) {
                max = number;
            }
            if (number.compareTo(min) < 0) {
                min = number;
            }
            return;
        }

        for (int candidate = 0; candidate < 10; candidate++) {
            if (before == -1) { // 첫 자리 뽑는 경우
                used[candidate] = true;
                recursive(depth + 1, candidate, number + candidate);
                used[candidate] = false;
            } else {
                if (used[candidate]) continue;
                if (isValidate(before, signs[depth - 1], candidate)) { // 부등호 관계를 만족하면
                    used[candidate] = true;
                    recursive(depth + 1, candidate, number + candidate);
                    used[candidate] = false;
                }
            }
        }
    }

    static boolean isValidate(int operand1, char operator, int operand2) {
        return operator == '<' ? operand1 < operand2 : operand1 > operand2;
    }

    static void print() {
        System.out.println(max);
        System.out.println(min);
    }

}

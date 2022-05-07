package algorithm.brute_force.practice;

/**
 * @문제 연산자 끼워넣기 [S1]
 * @날짜 220508
 * @분류 완전 탐색 / 백트래킹
 * @조건
 * # 2 <= N <= 11
 * # 1 <= Ai <= 100
 * @풀이
 * # 순열
 * @comments
 * # 정답의 최대치: Integer
 * # 시간 복잡도: O(N!) => 10!
 * # 공간 복잡도:
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14888 {

    static StringBuilder sb = new StringBuilder();

    static int N, min, max;
    static int[] numbers, op;

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
        numbers = new int[N + 1];
        op = new int[5];
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= 4; i++) {
            op[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        recusiveFunc(1, numbers[1]);
    }

    static void recusiveFunc(int depth, int value) {
        if (depth == N) {
            max = Math.max(max, value);
            min = Math.min(min, value);
        } else {
            for (int i = 1; i <= 4; i++) {
                if (op[i] >= 1) {
                    op[i]--;
                    recusiveFunc(depth + 1, calculate(value, i, numbers[depth + 1]));
                    op[i]++;
                }
            }
        }
    }

    static int calculate(int operand1, int operator, int operand2) {
        if (operator == 1) {
            return operand1 + operand2;
        } else if (operator == 2) {
            return operand1 - operand2;
        } else if (operator == 3) {
            return operand1 * operand2;
        } else {
            return operand1 / operand2;
        }
    }

    static void print() {
        sb.append(max).append("\n");
        sb.append(min);
        System.out.println(sb.toString());
    }

}

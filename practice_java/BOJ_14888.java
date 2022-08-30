package practice.bruteforce;

/**
 * @문제 연산자 끼워넣기 [S1]
 * @날짜 220824
 * @분류 완전 탐색 / 백트래킹
 * @조건 2 <= N <= 11
 * 1 <= Ai <= 100
 * @풀이
 * @comments # 정답의 최대치: Integer
 * # 시간 복잡도:
 * # 공간 복잡도:
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14888 {

    static StringBuilder sb = new StringBuilder();

    static int N, min, max;
    static int[] A, op;

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
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
        A = new int[N];
        op = new int[4];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < 4; i++) {
            op[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        recursive(1, A[0]);
    }

    static void recursive(int depth, int value) {
        if (depth == N) {
            min = Math.min(min, value);
            max = Math.max(max, value);
        } else {
            for (int candidate = 0; candidate < 4; candidate++) {
                if (op[candidate] == 0) continue;

                op[candidate]--;
                recursive(depth + 1, calculate(value, candidate, A[depth]));
                op[candidate]++;
            }
        }
    }

    static int calculate(int operand1, int op, int operand2) {
        switch (op) {
            case 0:
                return operand1 + operand2;
            case 1:
                return operand1 - operand2;
            case 2:
                return operand1 * operand2;
            case 3:
                return operand1 / operand2;
        }
        return 0;
    }

    static void print() {
        // append
        sb.append(max);
        sb.append("\n");
        sb.append(min);

        System.out.println(sb.toString());
    }

}

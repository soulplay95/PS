package algorithm.brute_force.practice;

/**
 * @문제 N-Queen [G5]
 * @날짜 220508
 * @분류 완전 탐색 / 백트래킹
 * @조건
 * # 1 <= N < 15
 * @풀이
 * # 중복 순열
 * - 1번 행에 놓을 퀸의 열: 1 ~ N
 * - 2번 행에 놓을 퀸의 열: 1 ~ N
 * - ...
 * @comments
 * # 정답의 최대치: Integer => 경우의 수가 Integer 최대 범위를 넘으면 애초에 시간초과
 * # 시간 복잡도: O(N^N) => 14^14 > 10^16
 * # 공간 복잡도:
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_9663 {

    static StringBuilder sb = new StringBuilder();

    static int N, answer;
    static int[] column; // column[i] : i번 행에 놓은 퀸의 열

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
        column = new int[N];
        answer = 0;
    }

    static void solve() {
        dfs(0);
    }

    static void dfs(int row) {
        if (row == N) {
            answer++;
            return;
        }

        for (int col = 0; col < N; col++) {
            if (isValidate(row, col)) {
                column[row] = col;
                dfs(row + 1);
                column[row] = 0;
            }
        }
    }

    static boolean isValidate(int row, int col) {
        // 이전 행 전부를 순회하면서 수직, 대각선 조건을 따져본다.
        for (int r = 0; r < row; r++) {
            int c = column[r];
            if (c == col || Math.abs(c - col) == row - r) {
                return false;
            }
        }

        return true;
    }

    static void print() {
        sb.append(answer);
        System.out.println(sb.toString());
    }

}

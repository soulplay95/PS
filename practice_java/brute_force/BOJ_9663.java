package practice.bruteforce;

/**
 * @문제 N-Queen [G4]
 * @날짜 220824
 * @분류 완탐 / 백트래킹
 * @조건
 * 1 <= N < 15
 * @풀이
 *
 * @comments
 * # 정답의 최대치:
 * # 시간 복잡도:
 * # 공간 복잡도:
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_9663 {

    static StringBuilder sb = new StringBuilder();

    static int N, answer;
    static int[] column;

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
        column = new int[N + 1];
    }

    static void solve() {
        recursive(1);
    }

    static void recursive(int row) {
        if (row == N + 1) {
            answer++;
            return;
        }

        for (int candidateColumn = 1; candidateColumn <= N; candidateColumn++) {
            if (isValidate(candidateColumn, row)) {
                column[row] = candidateColumn;
                recursive(row + 1);
                column[row] = 0;
            }
        }
    }

    static boolean isValidate(int col, int row) {
        for (int r = row - 1; r >= 1; r--) {
            if (column[r] == col) return false; // 세로
            if (Math.abs(col - column[r]) == row - r) return false; // 대각선
        }

        return true;
    }

    static void print() {
        System.out.println(answer);
    }

}

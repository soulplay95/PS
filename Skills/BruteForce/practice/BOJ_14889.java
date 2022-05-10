package algorithm.brute_force.practice;

/**
 * @문제 스타트와 링크 [S2]
 * @날짜 220510
 * @분류 완전 탐색 / 백트래킹
 * @조건
 * # 4 <= N <= 20
 * # 1 <= Sij <= 100
 * @풀이
 * # 조합
 * - 1. 조합으로 두 팀으로 나눔
 * - 2. 각 팀의 능력치 차이를 계산
 * @comments
 * # 정답의 최대치: Integer
 * # 시간 복잡도:
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_14889 {

    static StringBuilder sb = new StringBuilder();

    static int N, R, minDiff;
    static int[] selected;
    static int[][] S;

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
        R = N / 2;
        minDiff = Integer.MAX_VALUE;
        selected = new int[R];
        S = new int[N][N];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int c = 0; c < N; c++) {
                S[r][c] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static int[] getOtherTeam() {
        int[] team = new int[R];
        int index = 0;
        int sIndex = 0;
        for (int i = 0; i < N; i++) {
            if (sIndex < R && i == selected[sIndex]) {
                sIndex++;
                continue;
            }

            team[index++] = i;
        }

        return team;
    }

    static int getSum(int[] team) {
        int sum = 0;

        for (int i : team) {
            for (int j : team) {
                if (i == j) continue;
                sum += S[i][j];
            }
        }

        return sum;
    }

    static int getDiff() {
        int[] otherTeam = getOtherTeam();

        int sum1 = getSum(selected);
        int sum2 = getSum(otherTeam);

        return Math.abs(sum1 - sum2);
    }

    static void recursive(int depth, int start) {
        if (depth == R) {
            minDiff = Math.min(minDiff, getDiff());
        } else {
            for (int candidate = start; candidate < N; candidate++) {
                selected[depth] = candidate;
                recursive(depth + 1, candidate + 1);
                selected[depth] = -1;
            }
        }
    }

    static void solve() {
        recursive(0, 0);
    }

    static void print() {
        System.out.println(minDiff);
    }

}

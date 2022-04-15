package study.w220418;

/**
 * @문제 주사위 쌓기_G4
 * @날짜 220416
 * @분류 구현 / 완전 탐색
 * @조건
 * # 주사위 개수 <= 1만
 * @풀이
 * # 완전 탐색
 * - 1층 주사위의 윗면을 1 ~ 6 으로 고정하고 모든 주사위를 쌓아본다.
 * - 한 층씩 쌓았을 때 옆면으로 올 수 있는 숫자 중 최대값을 더해나간다.
 * @comments
 * # 정답의 최대치: Integer
 * - 최대 6N := 6만
 * # 시간 복잡도: O(36N)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_2116 {

    static StringBuilder sb = new StringBuilder();

    static int N, max; // 주사위 개수, 정답
    static int[][] dice;
    static int[] pair; // pair[i]: 주사위 전개도(ABCDEF)에서 A를 0번이라고 했을 때, i번 주사위의 마주보는 면에 적힌 숫자의 인덱스

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
        dice = new int[N][6];
        pair = new int[6];
        max = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 6; j++) {
                dice[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    // 주사위의 1 ~ 6 숫자 중 top, bottom에 적힌 숫자를 제외한 수 중 제일 큰 수를 리턴한다.
    static int getMax(int top, int bottom) {
        for (int i = 6; i > 0; i--) {
            if (i == top || i == bottom) {
                continue;
            }
            return i;
        }
        return 6;
    }

    static void solve() {
        // init
        pair[0] = 5;
        pair[5] = 0;
        pair[1] = 3;
        pair[3] = 1;
        pair[2] = 4;
        pair[4] = 2;

        // 1층 주사위의 윗면을 1 ~ 6으로 고정하고 모든 주사위를 쌓아본다.
        for (int topIndex = 0; topIndex < 6; topIndex++) {
            int top = dice[0][topIndex]; // 1층 주사위의 윗면 숫자
            int bottom = dice[0][pair[topIndex]]; // 1층 주사위의 밑면 숫자
            int sum = getMax(top, bottom);

            // 1층을 제외한 N - 1개의 주사위를 쌓는다.
            for (int i = 1; i < N; i++) {
                // 밑면이 top과 일치하는 인덱스를 찾는다.
                for (int bottomIndex = 0; bottomIndex < 6; bottomIndex++) {
                    bottom = dice[i][bottomIndex];
                    if (bottom == top) {
                        top = dice[i][pair[bottomIndex]]; // top 갱신
                        sum += getMax(top, bottom);
                        break;
                    }
                }
            }

            // 최대값 갱신
            max = Math.max(max, sum);
        }
    }

    static void print() {
        System.out.println(max);
    }

}

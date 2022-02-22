/**
 * @문제 로또_S2
 * @날짜 220222
 * @분류 수학 / 조합론 / 백트래킹 / 재귀
 * @조건
 * # 6 < k < 13
 * @풀이
 * # 조합
 * @comments
 * # 시간 복잡도: O(nCr), max: 12C6
 * # 공간 복잡도:
 * # 정답의 최대치:
 */

import java.io.*;
import java.util.*;

public class BOJ_6603 {

    static StringBuilder sb = new StringBuilder();

    static int K;
    static int[] numbers, selected;

    public static void main(String[] args) throws IOException {
        input();
        print();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            String[] line = br.readLine().split(" ");
            K = Integer.parseInt(line[0]);
            if (K == 0) {
                break;
            }
            // init
            numbers = new int[K + 1];
            selected = new int[7];
            for (int i = 1; i <= K; i++) {
                numbers[i] = Integer.parseInt(line[i]);
            }
            solve();
            sb.append("\n");
        }
    }

    static void solve() {
        nCr(1, 1);
    }

    static void nCr(int depth, int start) {
        if (depth == 7) {
            for (int i = 1; i <= 6; i++) {
                sb.append(selected[i]).append(' ');
            }
            sb.append('\n');
        } else {
            for (int i = start; i <= K; i++) {
                selected[depth] = numbers[i];
                nCr(depth + 1, i + 1);
                selected[depth] = 0;
            }
        }
    }

    static void print() {
        System.out.println(sb.toString());
    }

}

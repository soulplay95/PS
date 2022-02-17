/**
 * @문제 N과 M (2)_S3
 * @날짜 220217
 * @분류 백트래킹
 * @조건
 * # 1 <= M <= N <= 8
 * @풀이
 * # 이전에 뽑은 수 이후의 수부터 뽑는다.
 * @comments
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_15650 {

    private static int N, M;
    private static int[] selected;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        sc.close();
        // init
        selected = new int[M];

        dfs(0, 1);

        // print
        System.out.print(sb.toString());
    }

    private static void dfs(int depth, int start) {
        if (depth == M) {
            for (int i : selected) {
                sb.append(i).append(" ");
            }
            sb.append("\n");
            return ;
        }

        for (int i = start; i <= N; i++) {
            selected[depth] = i;
            dfs(depth + 1, i + 1);
        }
    }

}

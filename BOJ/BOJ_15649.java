/**
 * @문제 N과 M (1)_S3
 * @날짜 220217
 * @분류 백트래킹
 * @조건
 * # 1 <= M <= N <= 8
 * @풀이
 * # 순열
 * - 백트래킹
 * @comments
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_15649 {

    private static int N, M;
    private static boolean[] visited;
    private static int[] selected;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        // init
        visited = new boolean[N + 1];
        selected = new int[M];

        permutation(0);

        // print
        System.out.print(sb.toString());
    }

    private static void permutation(int depth) {
        if (depth == M) {
            for (int i : selected) {
                sb.append(i).append(" ");
            }
            sb.append("\n");
            return ;
        }

        for (int n = 1; n <= N; n++) {
            if (!visited[n]) {
                visited[n] = true;
                selected[depth] = n;
                permutation(depth + 1);
                visited[n] = false;
            }
        }
    }

}

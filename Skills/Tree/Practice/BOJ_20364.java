package algorithm.tree.practice;

/**
 * @문제 부동산 다툼 [S2]
 * @날짜 220610
 * @분류 트리
 * @조건
 * 2 <= N < 2^20
 * 1 <= Q <= 20만
 * @풀이
 * - 부모 노드로 거슬러 올라가면서 (key / 2) 점유중이면 갱신한다.
 * @comments
 * # 정답의 최대치: Integer
 * # 시간 복잡도: O(Q)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_20364 {

    static StringBuilder sb = new StringBuilder();

    static int N, Q;
    static boolean[] occupied;

    public static void main(String[] args) throws IOException {
        input();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        // init
        occupied = new boolean[N + 1];

        while (Q-- > 0) {
            int node = Integer.parseInt(br.readLine());
            solve(node);
        }

        // print
        System.out.println(sb);
    }

    static void solve(int node) {
        int lastVisited = 0;
        int parent = node;
        while (parent > 0) {
            if (occupied[parent]) lastVisited = parent;
            parent /= 2;
        }
        occupied[node] = true;

        sb.append(lastVisited).append('\n');
    }

}

package algorithm.tree.practice;

/**
 * @문제 트리 순회 [S1]
 * @날짜 220609
 * @분류 트리 / 재귀
 * @조건
 * 1 <= N <= 26
 * @풀이
 * # 재귀
 * @comments
 * # 정답의 최대치:
 * # 시간 복잡도:
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_1991 {

    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[][] children; // children[0][0]: A 노드(인덱스 0)의 왼쪽 자식 노드, children[0][1]: A 노드의 오른쪽 자식 노드

    static final int MAX = 26;

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        // init
        children = new int[MAX][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int cur = (int) (st.nextToken().charAt(0) - 'A');
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);
            if (left == '.') {
                children[cur][0] = -1;
            } else {
                children[cur][0] = (int) (left - 'A');
            }
            if (right == '.') {
                children[cur][1] = -1;
            } else {
                children[cur][1] = (int) (right - 'A');
            }
        }
    }

    static void solve() {
        preorderTraversal(0);
        sb.append('\n');
        inorderTraversal(0);
        sb.append('\n');
        postorderTraversal(0);

        // print
        System.out.println(sb);
    }

    static void preorderTraversal(int x) {
        if (x == -1) {
            return;
        }
        sb.append((char) (x + 'A')); // root
        preorderTraversal(children[x][0]); // left
        preorderTraversal(children[x][1]); // right
    }

    static void inorderTraversal(int x) {
        if (x == -1) {
            return;
        }
        inorderTraversal(children[x][0]); // left
        sb.append((char) (x + 'A')); // root
        inorderTraversal(children[x][1]); // right
    }

    static void postorderTraversal(int x) {
        if (x == -1) {
            return;
        }
        postorderTraversal(children[x][0]); // left
        postorderTraversal(children[x][1]); // right
        sb.append((char) (x + 'A')); // root
    }

}

package algorithm.tree;

/**
 * @문제 트리 [G5]
 * @날짜 220608
 * @분류 그래프 탐색 / 트리
 * @조건
 * 1 <= N <= 50
 * @풀이
 * # DFS
 * - 루트 노드에서 DFS
 * @comments
 * # 정답의 최대치: Integer => <= max N
 * # 시간 복잡도: O(N)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_1068 {

    static StringBuilder sb = new StringBuilder();

    static int N, root, deleted;
    static ArrayList<Integer>[] children;
    static int[] leaf;

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        // init
        children = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            children[i] = new ArrayList<>();
        }
        leaf = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            int parent = Integer.parseInt(st.nextToken());
            if (parent == -1) {
                root = i;
            } else {
                children[parent].add(i);
            }
        }
        deleted = Integer.parseInt(br.readLine());
    }

    static void solve() {
        for (int parent = 0; parent < N; parent++) {
            if (children[parent].contains(deleted)) {
                children[parent].remove(children[parent].indexOf(deleted));
                break;
            }
        }

        if (root != deleted) {
            dfs(root, -1);
        }

        System.out.println(leaf[root]);
    }

    static void dfs(int cur, int parent) {
        if (children[cur].isEmpty()) {
            leaf[cur]++;
        } else {
            for (int child : children[cur]) {
                dfs(child, cur);
                leaf[cur] += leaf[child];
            }
        }
    }

}

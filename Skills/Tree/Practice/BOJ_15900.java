package algorithm.tree.practice;

/**
 * @문제 나무 탈출 [S1]
 * @날짜 220610
 * @분류 그래프 탐색 / 트리 / DFS
 * @조건
 * 2 <= N <= 50만
 * @풀이
 * - 루트 노드에서 DFS 탐색하여 리프 노드까지의 거리의 총 합이 홀수이면 이기고, 짝수이면 진다.
 * @comments
 * # 정답의 최대치: Long
 * # 시간 복잡도: O(N)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_15900 {

    static StringBuilder sb = new StringBuilder();

    static int N;
    static ArrayList<Integer>[] adjList;
    static long distanceSumOfLeafNodesFromRootNode;

    static final int ROOT = 1;

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        // init
        adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adjList[from].add(to);
            adjList[to].add(from);
        }
    }

    static void solve() {
        dfs(ROOT, -1, 0);

        // print
        if (distanceSumOfLeafNodesFromRootNode % 2 == 0) {
            System.out.println("No");
        } else {
            System.out.println("Yes");
        }
    }

    static void dfs(int cur, int parent, int depth) {
        if (cur != ROOT && adjList[cur].size() == 1) { // 단말 노드이면
            distanceSumOfLeafNodesFromRootNode += depth;
            return;
        }

        for (int adjVertex : adjList[cur]) {
            if (adjVertex == parent) continue;
            dfs(adjVertex, cur, depth + 1);
        }
    }
}

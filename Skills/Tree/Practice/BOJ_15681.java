package algorithm.tree.practice;

/**
 * @문제 트리와 쿼리 [G5]
 * @날짜 220612
 * @분류 DP / 그래프 탐색 / 트리 / DFS / 트리 DP
 * @조건
 * 2 <= N <= 10만
 * 1 <= Q <= 10만
 * @풀이
 * - 루트 노드에서 DFS를 통해 각 정점을 루트로 하는 서브트리 내 정점의 개수를 채운다.
 * @comments
 * # 정답의 최대치: Integer => max 10만 (max N)
 * # 시간 복잡도: O(N)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_15681 {

    static StringBuilder sb = new StringBuilder();

    static int N, R, Q;
    static ArrayList<Integer>[] adjList;
    static int[] nodeCounts;

    public static void main(String[] args) throws IOException {
        input();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        // init
        adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }
        nodeCounts = new int[N + 1];

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adjList[from].add(to);
            adjList[to].add(from);
        }

        dfs(R, -1);

        while (Q-- > 0) {
            int root = Integer.parseInt(br.readLine());
            // append
            sb.append(nodeCounts[root]).append('\n');
        }

        // print
        System.out.println(sb);
    }

    static void dfs(int cur, int parent) {
        nodeCounts[cur] = 1;

        for (int adjVertex : adjList[cur]) {
            if (adjVertex == parent) continue;
            dfs(adjVertex, cur);
            // 해당 자식 노드를 루트로 하는 서브 트리 내의 정점 개수가 count 된 상황
            nodeCounts[cur] += nodeCounts[adjVertex];
        }
    }

}

package algorithm.graph_search.practice;

/**
 * @문제 트리의 부모 찾기 [S2]
 * @날짜 220606
 * @분류 그래프 탐색 / 트리
 * @조건
 * 2 <= N <= 10만
 * @풀이
 * # DFS
 * - 1번 노드에서 DFS 탐색
 * @comments
 * # 정답의 최대치:
 * # 시간 복잡도: O(N)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_11725 {

    static StringBuilder sb = new StringBuilder();

    static int N;
    static ArrayList<Integer>[] adjList;
    static int[] parents;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        // init
        adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }
        parents = new int[N + 1];
        visited = new boolean[N + 1];

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adjList[from].add(to);
            adjList[to].add(from);
        }
    }

    static void solve() {
        dfs(1, 0);

        for (int i = 2; i <= N; i++) {
            sb.append(parents[i]).append('\n');
        }

        // print
        System.out.println(sb);
    }

    static void dfs(int cur, int from) {
        visited[cur] = true;
        parents[cur] = from;

        for (int adjVertex : adjList[cur]) {
            if (!visited[adjVertex]) {
                dfs(adjVertex, cur);
            }
        }
    }

}

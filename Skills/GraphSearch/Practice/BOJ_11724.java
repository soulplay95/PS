package algorithm.graph_search.practice;

/**
 * @문제 연결 요소의 개수 [S2]
 * @날짜 220605
 * @분류 그래프 탐색 / DFS / BFS
 * @조건
 * 1 <= N <= 1000
 * @풀이
 * # BFS
 * - 주어진 간선 정보로 그래프를 인접 리스트로 구성하고 아직 방문하지 않은(탐색하지 않은) 정점에서 BFS 탐색한다.
 * @comments
 * # 정답의 최대치: Integer => 1000 (max N)
 * # 시간 복잡도: O(N^2)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_11724 {

    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static ArrayList<Integer>[] adjList;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        // init
        adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }
        visited = new boolean[N + 1];

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            adjList[from].add(to);
            adjList[to].add(from);
        }
    }

    static void solve() {
        int groupCounts = 0;

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                bfs(i);
                groupCounts++;
            }
        }

        // print
        System.out.println(groupCounts);
    }

    static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int adjVertex : adjList[cur]) {
                if (!visited[adjVertex]) {
                    q.offer(adjVertex);
                    visited[adjVertex] = true;
                }
            }
        }
    }

}

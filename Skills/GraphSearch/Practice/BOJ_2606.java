package algorithm.graph_search.practice;

/**
 * @문제 바이러스 [S3]
 * @날짜 220606
 * @분류 그래프 탐색
 * @조건
 * 1 <= N <= 100
 * @풀이
 * # BFS
 * @comments
 * # 정답의 최대치: Integer => 99
 * # 시간 복잡도: O(N)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_2606 {

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

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        // init
        visited = new boolean[N + 1];
        adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            adjList[from].add(to);
            adjList[to].add(from);
        }
    }

    static void solve() {
        // print
        System.out.println(bfs());
    }

    static int bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.offer(1);
        visited[1] = true;
        int counts = 0;

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int adjVertex : adjList[cur]) {
                if (!visited[adjVertex]) {
                    visited[adjVertex] = true;
                    q.offer(adjVertex);
                    counts++;
                }
            }
        }

        return counts;
    }

}

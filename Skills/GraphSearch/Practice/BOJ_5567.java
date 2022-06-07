package algorithm.graph_search.practice;

/**
 * @문제 결혼식 [S2]
 * @날짜 220607
 * @분류 그래프 탐색
 * @조건
 * 2 <= n <= 500
 * 1 <= m <= 1만
 * @풀이
 * # depth 2까지 BFS
 * @comments
 * # 정답의 최대치: Integer => max N
 * # 시간 복잡도: O(N)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_5567 {

    static StringBuilder sb = new StringBuilder();

    static int N, M, ans;
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
        ans = 0;
        adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }
        visited = new boolean[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adjList[from].add(to);
            adjList[to].add(from);
        }
    }

    static void solve() {
        bfs(1);

        // print
        System.out.println(ans);
    }

    static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        visited[start] = true;
        int depth = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            if (depth >= 2) {
                return;
            }

            while (size-- > 0) {
                int cur = q.poll();

                for (int adjVertex : adjList[cur]) {
                    if (!visited[adjVertex]) {
                        visited[adjVertex] = true;
                        ans++;
                        q.offer(adjVertex);
                    }
                }
            }

            depth++;
        }
    }

}

package algorithm.graph_search;

/**
 * @문제 DFS와 BFS [S2]
 * @날짜 220602
 * @분류 그래프 탐색 / DFS / BFS
 * @조건
 * 1 <= N <= 1천
 * 1 <= M <= 1만
 * @풀이
 * # DFS, BFS
 * - 인접 리스트를 사용한다면, 인접한 정점 번호를 오름차순 정렬한다.
 * @comments
 * # 정답의 최대치:
 * # 시간 복잡도: O(ElogE)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_1260 {

    static StringBuilder sb = new StringBuilder();

    static int N, M, V;
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
        V = Integer.parseInt(st.nextToken());
        // init
        adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            adjList[x].add(y);
            adjList[y].add(x);
        }
    }

    static void solve() {
        // 인접 리스트 오름차순 정렬
        for (int i = 1; i <= N; i++) {
            Collections.sort(adjList[i]);
        }

        // DFS
        visited = new boolean[N + 1];
        dfs(V);

        sb.append('\n');

        // BFS
        visited = new boolean[N + 1];
        bfs(V);

        // print
        System.out.println(sb.toString());
    }

    static void dfs(int x) {
        // 방문 체크
        visited[x] = true;
        sb.append(x).append(' ');

        for (int y : adjList[x]) {
            if (visited[y]) continue;

            dfs(y);
        }
    }

    static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();
            sb.append(cur).append(' ');

            for (int adj : adjList[cur]) {
                if (visited[adj]) continue;

                q.offer(adj);
                visited[adj] = true;
            }
        }
    }

}

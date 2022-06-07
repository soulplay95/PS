package algorithm.graph_search.practice;

/**
 * @문제 촌수계산 [S2]
 * @날짜 220607
 * @분류 그래프 탐색
 * @조건
 * 1 <= n <= 100
 * @풀이
 * # BFS
 * @comments
 * # 정답의 최대치: Integer => <= 99
 * # 시간 복잡도: O(N)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_2644 {

    static StringBuilder sb = new StringBuilder();

    static int N, M, start, end;
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
        st = new StringTokenizer(br.readLine(), " ");
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(br.readLine());
        // init
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
        // print
        System.out.println(bfs(start));
    }

    static int bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        visited[start] = true;
        int distance = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                int cur = q.poll();

                if (cur == end) {
                    return distance;
                }

                for (int adjVertex : adjList[cur]) {
                    if (!visited[adjVertex]) {
                        q.offer(adjVertex);
                        visited[adjVertex] = true;
                    }
                }
            }

            distance++;
        }

        return -1;
    }

}

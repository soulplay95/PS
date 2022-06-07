package algorithm.graph_search.practice;

/**
 * @문제 케빈 베이컨의 6단계 법칙 [S1]
 * @날짜 220607
 * @분류 그래프 탐색 / 플로이드-와샬
 * @조건
 * 2 <= N <= 100
 * 1 <= M <= 5000
 * @풀이
 * # 모든 정점에서 BFS
 * @comments
 * # 정답의 최대치: Integer => < N^2
 * # 시간 복잡도: O(N^3)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_1389 {

    static StringBuilder sb = new StringBuilder();

    static int N, M, ans, min;
    static boolean[][] adjMatrix;
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
        ans = 1;
        min = Integer.MAX_VALUE;
        adjMatrix = new boolean[N + 1][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adjMatrix[from][to] = true;
            adjMatrix[to][from] = true;
        }
    }

    static void solve() {
        for (int candidate = 1; candidate <= N; candidate++) {
            int result = bfs(candidate);
            if (result < min) {
                ans = candidate;
                min = result;
            }
        }

        // print
        System.out.println(ans);
    }

    static int bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        visited = new boolean[N + 1];
        visited[start] = true;
        q.offer(start);
        int result = 0, dist = 1;

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                int cur = q.poll();

                for (int adjVertex = 1; adjVertex <= N; adjVertex++) {
                    if (adjMatrix[cur][adjVertex] && !visited[adjVertex]) {
                        result += dist;
                        visited[adjVertex] = true;
                        q.offer(adjVertex);
                    }
                }
            }

            dist++;
        }

        return result;
    }

}

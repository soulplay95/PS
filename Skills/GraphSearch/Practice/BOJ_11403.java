package algorithm.graph_search.practice;

/**
 * @문제 경로 찾기 [S1]
 * @날짜 220606
 * @분류 그래프 탐색 / 플로이드-와샬
 * @조건
 * 1 <= N <= 100
 * @풀이
 * # 모든 정점에서 DFS 탐색
 * @comments
 * # 정답의 최대치:
 * # 시간 복잡도: O(N^3)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_11403 {

    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[][] adjMatrix;
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
        adjMatrix = new int[N][N];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int c = 0; c < N; c++) {
                adjMatrix[r][c] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void solve() {
        // 모든 정점에서 DFS 탐색한다.
        for (int i = 0; i < N; i++) {
            visited = new boolean[N];
            dfs(i);
            for (int to = 0; to < N; to++) {
                if (visited[to]) {
                    sb.append(1).append(' ');
                } else {
                    sb.append(0).append(' ');
                }
            }
            sb.append('\n');
        }

        // print
        System.out.println(sb);
    }

    static void dfs(int i) {
        for (int adj = 0; adj < N; adj++) {
            if (adjMatrix[i][adj] == 1 && !visited[adj]) {
                visited[adj] = true;
                dfs(adj);
            }
        }
    }

}

/**
 * @문제 트리_G4
 * @날짜 220309
 * @분류 트리 / DFS / 분리 집합
 * @조건
 * # 정점 개수 (n) <= 500
 * @풀이
 * # 모든 정점에서 DFS 탐색하여, 사이클이 생기는지 판단한다.
 * @comments
 * # 정답의 최대치: Integer
 * - 간선이 0개일 때, 최대 n개의 트리가 생길 수 있다.
 * # 시간 복잡도: O(n^2)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_4803 {

    static StringBuilder sb = new StringBuilder();

    static int N, M, treeCounts;
    static ArrayList<Integer>[] adjList;
    static boolean[] visited;
    static boolean isCycle;

    public static void main(String[] args) throws IOException {
        input();
        print();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int TC = 1;
        while (true) {
            st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            // 종료 체크
            if (N == 0 && M == 0) {
                break;
            }

            // init
            adjList = new ArrayList[N + 1];
            for (int i = 1; i <= N; i++) {
                adjList[i] = new ArrayList<>();
            }
            visited = new boolean[N + 1];
            treeCounts = 0;

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                // 양방향 그래프 구성
                adjList[x].add(y);
                adjList[y].add(x);
            }

            solve();

            sb.append("Case " + TC + ": ");

            // append
            if (treeCounts == 0) {
                sb.append("No trees.");
            } else if (treeCounts == 1) {
                sb.append("There is one tree.");
            } else {
                sb.append("A forest of " + treeCounts + " trees.");
            }
            sb.append("\n");

            TC++;
        }
    }

    static void dfs(int cur, int parent) {
        visited[cur] = true;

        for (int adjVertex : adjList[cur]) {
            if (adjVertex == parent) {
                continue;
            }

            if (visited[adjVertex]) {
                isCycle = true;
                continue;
            }

            dfs(adjVertex, cur);
        }
    }

    static void solve() {
        // 모든 정점에 대하여 아직 방문하지 않았으면 DFS 탐색을 진행한다.
        for (int vertex = 1; vertex <= N; vertex++) {
            if (visited[vertex]) {
                continue;
            }

            isCycle = false;
            dfs(vertex, -1);
            if (!isCycle) {
                treeCounts++;
            }
        }
    }

    static void print() {
        System.out.println(sb.toString());
    }

}

package algorithm.tree.practice;

/**
 * @문제 트리 [G4]
 * @날짜 220609
 * @분류 자료 구조 / 그래프 탐색 / 트리 / 분리 집합
 * @조건
 * 1 <= n <= 500
 * @풀이
 * - 아직 방문하지 않은 정점에서 DFS 탐색한다.
 * - DFS 탐색 중에 사이클이 생기면 트리 개수에 포함시키지 않는다.
 * -- 사이클 판단: 현재 노드와 연결된 노드 중 부모를 제외한 다른 이미 방문한 노드가 잡히면 사이클이 있음.
 * @comments
 * # 정답의 최대치: Integer: <= max N
 * # 시간 복잡도: O(N^2)
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
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = 1;
        while (true) {
            st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            if (N == 0 && M == 0) {
                break;
            }

            // init
            treeCounts = 0;
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

            solve();

            // print
            sb.append("Case ").append(T++).append(": ");
            if (treeCounts == 0) {
                sb.append("No trees.").append('\n');
            } else if (treeCounts == 1) {
                sb.append("There is one tree.").append('\n');
            } else {
                sb.append("A forest of ").append(treeCounts).append(" trees.").append('\n');
            }
        }

        // print
        System.out.println(sb.toString());
    }

    static void solve() {
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                isCycle = false;
                dfs(i, -1);
                if (!isCycle) {
                    treeCounts++;
                }
            }
        }
    }

    static void dfs(int cur, int parent) {
        visited[cur] = true;

        for (int adjVertex : adjList[cur]) {
            if (adjVertex == parent) continue;
            if (visited[adjVertex]) {
                isCycle = true;
                continue;
            }
            dfs(adjVertex, cur);
        }
    }

}

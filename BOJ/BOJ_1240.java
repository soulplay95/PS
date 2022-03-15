/**
 * @문제 노드사이의 거리_G5
 * @날짜 220315
 * @분류 트리 / DFS / BFS
 * @조건
 * # 2 <= 노드 개수 (N) <= 1000
 * # 주어지는 노드 쌍 개수 (M) <= 1000
 * @풀이
 * # DFS
 * - DFS M번 수행
 * @comments
 * # 정답의 최대치: Integer
 * - 노드 A->B로 갈 때 모든 간선을 지나치고 각 간선의 거리가 최대일때, 999 * 10000
 * # 시간 복잡도: O(MN)
 * # 공간 복잡도: O(N)
 */

import java.io.*;
import java.util.*;

public class BOJ_1240 {

    static StringBuilder sb = new StringBuilder();

    static class Edge {
        int to, weight;

        Edge(int _to, int _weight) {
            to = _to;
            weight = _weight;
        }
    }

    static int N, M;
    static ArrayList<Edge>[] adjList;

    public static void main(String[] args) throws IOException {
        input();
        print();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        // init
        adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            adjList[x].add(new Edge(y, weight));
            adjList[y].add(new Edge(x, weight));
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            dfs(x, -1, y, 0);
        }
    }

    static void dfs(int cur, int parent, int target, int distance) {
        if (cur == target) {
            sb.append(distance).append("\n");
            return;
        }

        for (Edge adj : adjList[cur]) {
            if (adj.to == parent) {
                continue;
            }

            dfs(adj.to, cur, target, distance + adj.weight);
        }
    }

    static void print() {
        System.out.println(sb);
    }

}

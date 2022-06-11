package algorithm.tree.practice;

/**
 * @문제 노드사이의 거리 [G5]
 * @날짜 220611
 * @분류 그래프 탐색 / 트리
 * @조건
 * 2 <= N <= 1000
 * M <= 1000
 * 거리 <= 1만
 * @풀이
 * - 두 노드중 하나를 루트로 생각하고 dfs
 * @comments
 * # 정답의 최대치: Integer => max N * 10000
 * # 시간 복잡도: O(MN)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_1240 {

    static StringBuilder sb = new StringBuilder();

    static class Edge {
        int to, distance;

        Edge(int _to, int _distance) {
            to = _to;
            distance = _distance;
        }
    }

    static int N, M;
    static ArrayList<Edge>[] adjList;

    public static void main(String[] args) throws IOException {
        input();
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
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());
            adjList[from].add(new Edge(to, distance));
            adjList[to].add(new Edge(from, distance));
        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            dfs(x, -1, y, 0);
        }

        // print
        System.out.println(sb);
    }

    static void dfs(int cur, int parent, int goal, int dist) {
        if (cur == goal) {
            sb.append(dist).append('\n');
            return;
        }

        for (Edge adj : adjList[cur]) {
            if (adj.to == parent) continue;
            dfs(adj.to, cur, goal, dist + adj.distance);
        }
    }

}

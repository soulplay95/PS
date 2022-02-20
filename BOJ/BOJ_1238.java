/**
 * @문제 파티_G3
 * @날짜 220220
 * @분류 다익스트라
 * @조건
 * # 1 <= 정점 수 N <= 1000
 * # 1 <= 간선 수 M <= 1만
 * @풀이
 * # 모든 정점에서 각 정점으로 가는 최단 경로를 다익스트라 알고리즘을 정점 개수만큼 돌려서 얻는다.
 * # 오고 가는 길의 시간 = 각 마을 -> 타겟 마을의 최단 경로 + 타겟 마을 -> 각 마을의 최단 경로
 * @comments
 * # O(V(E + ElogE))
 * # 다익스트라 알고리즘을 모든 정점에 대하여 돌리는 문제
 */

import java.util.*;
import java.io.*;

public class BOJ_1238 {

    private static class Edge implements Comparable<Edge> {
        int vertex, weight;

        public Edge(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    private static int V, E, destination, max;
    private static ArrayList<Edge>[] adjList; // 인접 리스트
    private static int time[][]; // [from][to] : from 마을에서 to 마을로 가는 최단 경로(시간)

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        destination = Integer.parseInt(st.nextToken());
        // init
        max = 0;
        adjList = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            adjList[i] = new ArrayList<>();
        }
        time = new int[V + 1][V + 1];
        for (int r = 1; r <= V; r++) {
            Arrays.fill(time[r], Integer.MAX_VALUE);
        }
        for (int r = 1; r <= V; r++) {
            time[r][r] = 0;
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            adjList[from].add(new Edge(to, weight));
        }

        solve();

        // print
        System.out.println(max);
    }

    private static void solve() {
        // 모든 정점에 대하여 다익스트라 알고리즘 적용하여 time 뱨열을 채운다.
        for (int vertex = 1; vertex <= V; vertex++) {
            djkstra(vertex);
        }

        // time 배열을 순회하면서 오고 가는 시간을 계산해, 최대 값을 구한다.
        for (int v = 1; v <= V; v++) {
            int sum = time[v][destination] + time[destination][v];

            if (sum > max) {
                max = sum;
            }
        }
    }

    private static void djkstra(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge curEdge = pq.poll();
            int curVertex = curEdge.vertex;
            int curWeight = curEdge.weight;

            for (int i = 0; i < adjList[curVertex].size(); i++) {
                Edge adjacentEdge = adjList[curVertex].get(i);
                int adjacentVertex = adjacentEdge.vertex;
                int adjacentWeight = adjacentEdge.weight;

                if (curWeight + adjacentWeight < time[start][adjacentVertex]) {
                    time[start][adjacentVertex] = curWeight + adjacentWeight;
                    pq.offer(new Edge(adjacentVertex, time[start][adjacentVertex]));
                }
            }
        }
    }

}

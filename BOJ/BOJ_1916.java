/**
 * @문제 최소비용 구하기_G5
 * @날짜 220219
 * @분류 다익스트라
 * @조건
 * # 1 <= 도시 개수 N <= 1000
 * # 1 <= 버스 개수 M <= 10만
 * # 0 <= 버스 비용 < 10만
 * @풀이
 * # 다익스트라 - 우선순위 큐로 구현
 * @comments
 * # 버스 비용 합의 최대값 => 버스 개수(10만) * 버스 비용(10만) = 약 100억 => 비용 Long으로 관리
 */

import java.util.*;
import java.io.*;

public class BOJ_1916 {

    private static class Edge implements Comparable<Edge> {
        int toVertex; // 정점 번호
        Long weight; // 비용

        public Edge(int toVertex, Long weight) {
            this.toVertex = toVertex;
            this.weight = weight;
        }

        public int compareTo(Edge o) {
            return Long.compare(this.weight, o.weight);
        }
    }

    private static int V, E; // 도시 개수(N. 정점 개수), 버스 개수(M. 간선 개수)
    private static ArrayList<Edge>[] adjList; // 인접 리스트(그래프)
    private static Long[] weightSum; // 출발 정점에서 각 정점에 도착할 때 최소 비용

    public static void main(String[] args) throws IOException, NumberFormatException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        V = Integer.parseInt(br.readLine());
        E = Integer.parseInt(br.readLine());
        // init
        adjList = new ArrayList[V + 1]; // 정점 번호 1번부터
        for (int i = 1; i <= V; i++) {
            adjList[i] = new ArrayList<>();
        }
        weightSum = new Long[V + 1];
        for (int i = 1; i <= V; i++) {
            weightSum[i] = Long.MAX_VALUE;
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            Long weight = Long.parseLong(st.nextToken());

            adjList[from].add(new Edge(to, weight));
        }
        st = new StringTokenizer(br.readLine(), " ");
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        solve(start);

        // print
        System.out.println(weightSum[end]);
    }

    private static void solve(int start) {
        weightSum[start] = 0L;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, 0L));

        while (!pq.isEmpty()) {
            Edge curNode = pq.poll();
            int curVertex = curNode.toVertex;
            Long curWeight = curNode.weight;

            if (curWeight > weightSum[curVertex]) {
                continue;
            }

            // 인접한 노드 검사
            for (int i = 0; i < adjList[curVertex].size(); i++) {
                Edge adjacentNode = adjList[curVertex].get(i);
                int adjacentVertex = adjacentNode.toVertex;
                Long adjacentWeight = adjacentNode.weight;

                Long distance = curWeight + adjacentWeight;
                if (distance < weightSum[adjacentVertex]) {
                    weightSum[adjacentVertex] = distance;
                    pq.offer(new Edge(adjacentVertex, distance));
                }
            }
        }
    }

}

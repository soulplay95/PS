package programmers.level2;

import java.util.*;

class PROG_12978 {

    static class Edge implements Comparable<Edge> {
        int to, weight;

        Edge(int _to, int _weight) {
            to = _to;
            weight = _weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    static ArrayList<Edge>[] adjList;
    static int[] distance;

    static final int START = 1;

    // 주어진 간선 정보로 그래프를 구성한다.
    // 1번 마을로부터 최단 경로를 구한다. => 다익스트라
    // O(ElogV)
    public int solution(int N, int[][] road, int K) {
        // init
        int answer = 0;
        adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }
        distance = new int[N + 1];

        // 그래프 구성
        for (int i = 0; i < road.length; i++) {
            int[] edge = road[i];
            int from = edge[0];
            int to = edge[1];
            int weight = edge[2];

            adjList[from].add(new Edge(to, weight));
            adjList[to].add(new Edge(from, weight));
        }

        // 최단 경로 구하기
        dijkstra(N);

        // 정답 찾기
        for (int i = 1; i <= N; i++) {
            if (distance[i] <= K) {
                answer++;
            }
        }

        return answer;
    }

    static void dijkstra(int N) {
        // init
        PriorityQueue<Edge> pq = new PriorityQueue();
        pq.offer(new Edge(START, 0));
        for (int i = 1; i <= N; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        distance[START] = 0;

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            // 가치없는 정보는 폐기한다.
            if (cur.weight > distance[cur.to]) continue;

            // 현재 노드를 경유하여 갈 수 있는 새로운 최단 경로를 찾는다.
            for (Edge adj : adjList[cur.to]) {
                if (cur.weight + adj.weight < distance[adj.to]) {
                    distance[adj.to] = cur.weight + adj.weight;
                    pq.offer(new Edge(adj.to, distance[adj.to]));
                }
            }
        }
    }

}

/*
class Solution {
    static class Edge implements Comparable<Edge> {
        int vertex, weight; // 정점 번호, 비용

        public Edge(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    // 다익스트라
    public int solution(int N, int[][] road, int K) {
        // init
        int answer = 0;
        ArrayList<Edge>[] adjList = new ArrayList[N + 1]; // 인접 리스트
        int[] dist = new int[N + 1]; // 출발 정점에서 각 정점에 도착할 때 최소 비용
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
            dist[i] = Integer.MAX_VALUE;
        }

        // 인접 리스트 구성하기
        for (int i = 0; i < road.length; i++) {
            int[] edge = road[i];
            adjList[edge[0]].add(new Edge(edge[1], edge[2]));
            adjList[edge[1]].add(new Edge(edge[0], edge[2]));
        }

        // 다익스트라
        dist[1] = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(1, 0));
        while (!pq.isEmpty()) {
            Edge curNode = pq.poll();
            int curVertex = curNode.vertex;
            int curWeight = curNode.weight;

            // 가치있는 정보인지 체크
            if (curWeight > dist[curVertex]) {
                continue;
            }

            // 인접한 노드 검사
            for (Edge adjNode : adjList[curVertex]) {
                int distance = curWeight + adjNode.weight;
                if (distance < dist[adjNode.vertex]) {
                    dist[adjNode.vertex] = distance;
                    pq.offer(new Edge(adjNode.vertex, distance));
                }
            }
        }

        // 거리가 K 이하인 마을 개수 세기
        for (int i = 1; i <= N; i++) {
            if (dist[i] <= K) {
                answer++;
            }
        }

        return answer;
    }
}*/

package programmers; /**
 * @문제
 * @날짜 220429
 * @분류
 * @조건
 * #
 * @풀이
 * #
 * @comments
 * # 정답의 최대치:
 * # 시간 복잡도:
 * # 공간 복잡도:
 */

import java.util.*;

public class PROG_12978 {

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

}

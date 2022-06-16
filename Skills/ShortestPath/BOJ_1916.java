package algorithm.shortest_path;

/**
 * @문제 최소비용 구하기 [G5]
 * @날짜 220616
 * @분류 다익스트라
 * @조건
 * 1 <= N <= 1000
 * 1 <= M <= 10만
 * 0 <= weight < 10만
 * @풀이
 * # 다익스트라
 * @comments
 * # 정답의 최대치: Integer => max 1억(max N * max weight)
 * # 시간 복잡도: O(MlogN)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_1916 {

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
    static int[] distance;

    public static void main(String[] args) throws IOException {
        input();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        // init
        adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }
        distance = new int[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            adjList[from].add(new Edge(to, weight));
        }
        st = new StringTokenizer(br.readLine(), " ");
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        dijkstra(start, end);
    }

    static void dijkstra(int start, int end) {
        // 1. distance 배열, pq 초기화
        PriorityQueue<Edge> pq = new PriorityQueue<>(((o1, o2) -> Integer.compare(o1.weight, o2.weight)));
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;
        pq.offer(new Edge(start, 0));

        // 2. pq가 빌 때 까지 갱신
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            // 2-1. 가치 있는 정보인지 체크
            // 현재 start->to 까지의 거리 정보가 이미 저장되어 있는 start->to 까지의 최단 거리 정보보다 크면 쓸모 없는 정보
            if (cur.weight > distance[cur.to]) continue;
//            distance[cur.to] = cur.weight;

            // 2-2. to에 인접한 정점들(adj)에 대하여 start->adj의 최단거리가 저장된 것보다 작으면 갱신
            for (Edge adj : adjList[cur.to]) {
                if (distance[cur.to] + adj.weight < distance[adj.to]) {
                    distance[adj.to] = distance[cur.to] + adj.weight;
                    pq.offer(new Edge(adj.to, distance[adj.to]));
                }
            }
        }

        // print
        System.out.println(distance[end]);
    }

}

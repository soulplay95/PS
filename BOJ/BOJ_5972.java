package study.w220314;

/**
 * @문제 택배 배송_G5
 * @날짜 220312
 * @분류 다익스트라
 * @조건
 * # 1 <= 정점 수 (N) <= 5만
 * # 1 <= 간선 수 (M) <= 5만
 * # 0 <= 가중치 (C_i) <= 1000
 * @풀이
 * # 최단 경로 - 다익스트라
 * @comments
 * # 정답의 최대치: Integer
 * - 모든 간선(5만개)을 지나고 각 간선의 가중치가 최대(1000)일때, 50000 * 1000
 * # 시간 복잡도: O(ElogV)
 * # 공간 복잡도: O(E)
 */

import java.io.*;
import java.util.*;

public class BOJ_5972 {

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
        solve();
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
        distance = new int[N + 1];

        // 인접 리스트 구성
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            adjList[from].add(new Edge(to, weight));
            adjList[to].add(new Edge(from, weight));
        }
    }

    static void solve() {
        // distance 배열 초기화 - 출발점을 제외하고 MAX값으로 초기화
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[1] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<Edge>((o1, o2) -> o1.weight - o2.weight);
        pq.offer(new Edge(1, 0));

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            // 가치 없는 정보는 거른다.
            if (cur.weight > distance[cur.to]) {
                continue;
            }

            for (Edge adj : adjList[cur.to]) {
                if (distance[cur.to] + adj.weight < distance[adj.to]) { // 새로운 최단 경로를 찾으면
                    distance[adj.to] = distance[cur.to] + adj.weight;
                    pq.offer(new Edge(adj.to, distance[adj.to]));
                }
            }
        }
    }

    static void print() {
        System.out.println(distance[N]);
    }

}

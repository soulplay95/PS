package algorithm.shortest_path.practice;

/**
 * @문제 최단경로 [G4]
 * @날짜 220617
 * @분류 다익스트라
 * @조건
 * 1 <= V <= 2만
 * 1 <= E <= 30만
 * 1 <= 가중치 <= 10
 * @풀이
 * # 다익스트라
 * @comments
 * # 정답의 최대치: Integer => max 20만 (max 가중치 * max V)
 * # 시간 복잡도: O(ElogV)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_1753 {

    static StringBuilder sb = new StringBuilder();

    static class Edge {
        int to, weight;

        Edge(int _to, int _weight) {
            to = _to;
            weight = _weight;
        }
    }

    static int V, E, start;
    static int[] distance;
    static ArrayList<Edge>[] adjList;

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        // init
        distance = new int[V + 1];
        adjList = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            adjList[i] = new ArrayList<>();
        }

        start = Integer.parseInt(br.readLine());
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            adjList[from].add(new Edge(to, weight));
        }
    }

    static void solve() {
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.weight, o2.weight));
        pq.offer(new Edge(start, 0));
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            int to = cur.to;
            int dist = cur.weight;

            if (dist > distance[to]) continue; // 가치없는 정보는 폐기
            for (Edge adj : adjList[to]) {
                if (distance[to] + adj.weight < distance[adj.to]) {
                    distance[adj.to] = distance[to] + adj.weight;
                    pq.offer(new Edge(adj.to, distance[adj.to]));
                }
            }
        }

        // print
        for (int i = 1; i <= V; i++) {
            if (distance[i] == Integer.MAX_VALUE) {
                sb.append("INF");
            } else {
                sb.append(distance[i]);
            }
            sb.append('\n');
        }

        System.out.println(sb.toString());
    }

}

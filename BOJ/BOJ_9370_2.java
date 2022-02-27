package study.w220229;

/**
 * @문제 미확인 도착지_G2
 * @날짜 220227
 * @분류
 * @조건
 * # 2 <= 정점 개수 (n) <= 2000
 * # 1 <= 간선 개수 (m) <= 5만
 * # 1 <= 목적지 후보 개수 (t) <= 100
 * # 1 <= 각 간선의 최대 비용 <= 1000
 * @풀이
 * # 최단경로 역추적
 * - 다익스트라로 최단 경로를 구한다.
 * - 간선의 가중치에 g-h 간선은 홀수로 만들고 나머지는 짝수로 만든다.
 * @comments
 * # 시간 복잡도: O(ElogE)
 * # 공간 복잡도: O(n)
 * # 정답의 최대치: Integer
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_9370_2 {

    static StringBuilder sb = new StringBuilder();

    static class Edge implements Comparable<Edge> {
        int vertex, weight;

        public Edge(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    static int n, m, t, s, g, h;
    static ArrayList<Edge>[] adjList;
    static ArrayList<Integer> destinations;
    static int[] distance;

    public static void main(String[] args) throws IOException {
        input();
        print();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            t = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine(), " ");
            s = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            // init
            adjList = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) {
                adjList[i] = new ArrayList<>();
            }
            destinations = new ArrayList<>();
            distance = new int[n + 1];

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());

                if ((u == g && v == h) || (u == h && v == g)) {
                    weight = weight * 2 - 1;
                } else {
                    weight *= 2;
                }

                adjList[u].add(new Edge(v, weight));
                adjList[v].add(new Edge(u, weight));
            }
            for (int i = 0; i < t; i++) {
                int destination = Integer.parseInt(br.readLine());
                destinations.add(destination);
            }

            solve();
        }
    }

    static void solve() {
        init();
        dijkstra();
        getAnswer();
    }

    static void init() {
        // 목적지 후보들을 담은 리스트 오름차순 정렬
        Collections.sort(destinations);

        // distance 배열 초기화
        Arrays.fill(distance, 1, n + 1, Integer.MAX_VALUE);
        distance[s] = 0;
    }

    static void dijkstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(s, 0));

        while (!pq.isEmpty()) {
            Edge curEdge = pq.poll();
            int curVertex = curEdge.vertex;
            int curWeight = curEdge.weight;

            if (curWeight > distance[curVertex]) {
                continue;
            }

            for (Edge adjEdge : adjList[curVertex]) {
                int adjVertex = adjEdge.vertex;
                int adjWeight = adjEdge.weight;

                int newDistance = curWeight + adjWeight;
                if (newDistance < distance[adjVertex]) {
                    distance[adjVertex] = newDistance;
                    pq.offer(new Edge(adjVertex, newDistance));
                }
            }
        }
    }

    static void getAnswer() {
        for (int destination : destinations) {
            if (distance[destination] != Integer.MAX_VALUE && distance[destination] % 2 == 1) {
                sb.append(destination).append(" ");
            }
        }
        sb.append("\n");
    }

    static void print() {
        System.out.println(sb.toString());
    }

}

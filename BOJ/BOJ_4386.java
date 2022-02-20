package study.w220221;

/**
 * @문제 별자리 만들기_G4
 * @날짜 220220
 * @분류 최소 스패닝 트리
 * @조건
 * # 1 <= 정점 개수 <= 100
 * @풀이
 * # 최소 신장 트리 구하는 문제
 * - 모든 별들이 연결
 * - 비용이 양의 실수
 * # 크루스칼 알고리즘
 * 1. 모든 간선을 구한다. => n(n-1)/2
 * 2. 크루스칼
 * @comments
 * # O(ElogE)
 */

import java.util.*;
import java.io.*;

public class BOJ_4386 {

    // 각 정점의 좌표평면상 좌표를 나타내는 클래스
    private static class Pair {
        double r, c;

        public Pair(double r, double c) {
            this.r = r;
            this.c = c;
        }
    }

    // 간선 정보를 나타내는 클래스
    private static class Edge implements Comparable<Edge> {
        int u, v; // 연결된 두 정점의 인덱스 번호
        double weight; // 가중치(비용)

        public Edge(int u, int v, double weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }

        public int compareTo(Edge o) {
            return Double.compare(this.weight, o.weight);
        }
    }

    private static int V;
    private static double minSum; // 별자리를 만드는 최소 비용
    private static Pair[] nodes; // 모든 정점 정보
    private static Edge[] edges; // 모든 간선 정보
    private static int[] parent, rank;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        V = Integer.parseInt(br.readLine());
        // init
        nodes = new Pair[V];
        edges = new Edge[V * (V - 1) / 2]; // 정점이 V개일때, 최대 간선 개수는 V * (V - 1) / 2

        // 정점 정보 입력 받기
        for (int i = 0; i < V; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            double c = Double.parseDouble(st.nextToken());
            double r = Double.parseDouble(st.nextToken());
            nodes[i] = new Pair(r, c);
        }

        solve();

        // print
        System.out.printf("%.2f", minSum);
    }

    private static void makeSet() {
        // init
        parent = new int[V];
        rank = new int[V];

        for (int i = 0; i < V; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    private static int find(int x) {
        if (parent[x] == x) {
            return x;
        }

        return parent[x] = find(parent[x]); // path compression
    }

    private static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) {
            return ;
        }

        // union-by-rank
        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else {
            parent[rootY] = rootX;

            if (rank[rootX] == rank[rootY]) {
                rank[rootX]++;
            }
        }
    }

    private static void solve() {
        // init
        minSum = 0.0;
        makeSet();

        // 1. 모든 간선 정보 구하기
        int index = 0;
        for (int i = 0; i < V - 1; i++) {
            for (int j = i + 1; j < V; j++) {
                double absWidth = Math.abs(nodes[i].r - nodes[j].r);
                double absHeight = Math.abs(nodes[i].c - nodes[j].c);
                double distance = Math.sqrt(absWidth * absWidth + absHeight * absHeight); // 좌표평면에서 두 정점 사이의 거리
                edges[index++] = new Edge(i, j, distance);
            }
        }

        // 2. 크루스칼
        Arrays.sort(edges); // 비용 기준 오름차순 정렬
        for (int i = 0; i < edges.length; i++) {
            Edge curEdge = edges[i];

            // 해당 간선에 연결된 두 정점을 연결했을 때 사이클이 생기지 않으면 연결(선택)
            if (find(curEdge.u) != find(curEdge.v)) {
                union(curEdge.u, curEdge.v);
                minSum += curEdge.weight;
            }
        }
    }

}

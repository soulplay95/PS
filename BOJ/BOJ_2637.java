/**
 * @문제 장난감 조립_G2
 * @날짜 220228
 * @분류 DP / 위상 정렬
 * @조건
 * # 3 <= N, M <= 100
 * @풀이
 * # 위상 정렬
 * - 부품 간 관계를 그래프로 나타낸다.
 * - 기본 부품? 초기에 indegree가 0인 부품들
 * - 위상 정렬 순서로 진행하면서 각 중간 부품(완제품 포함)에 필요한 기본 부품들 개수를 누적한다.
 * @comments
 * # 시간 복잡도: O(NM)
 * # 공간 복잡도: O(N)
 * # 정답의 최대치: Integer
 */

import java.io.*;
import java.util.*;

public class BOJ_2637 {

    static StringBuilder sb = new StringBuilder();

    static class Edge {
        int vertex, weight;

        public Edge(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }

    static int N, M;
    static ArrayList<Edge>[] adjList;
    static int[] indegree;
    static int[][] counts;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        print();
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
        indegree = new int[N + 1];
        counts = new int[N + 1][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int to = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            adjList[from].add(new Edge(to, weight));
            indegree[to]++;
        }
    }

    static void solve() {
        Queue<Integer> q = new LinkedList<>();
        // init
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
                counts[i][i] = 1;
            }
        }

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (Edge adjEdge : adjList[cur]) {
                int adjVertex = adjEdge.vertex;
                int adjWeight = adjEdge.weight;

                indegree[adjVertex]--;
                for (int c = 1; c <= N; c++) {
                    if (counts[cur][c] == 0) {
                        continue;
                    }
                    counts[adjVertex][c] += counts[cur][c] * adjWeight;
                }

                if (indegree[adjVertex] == 0) {
                    q.offer(adjVertex);
                }
            }
        }
    }

    static void print() {
        for (int c = 1; c <= N; c++) {
            if (counts[N][c] > 0) {
                sb.append(c).append(" ").append(counts[N][c]).append("\n");
            }
        }

        System.out.println(sb.toString());
    }

}

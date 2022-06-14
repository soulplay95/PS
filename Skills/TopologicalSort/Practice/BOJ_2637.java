package algorithm.topological_sort.practice;

/**
 * @문제 장난감 조립 [G2]
 * @날짜 220615
 * @분류 DP / 위상 정렬
 * @조건
 * 3 <= N <= 100
 * 3 <= M <= 100
 * @풀이
 * - 위상 정렬 순서로 만들 수 있는 부품부터 만든다.
 * - counts[N번 부품][기본 부품] = N번 부품을 만드는데, 필요한 기본 부품 개수
 * @comments
 * # 정답의 최대치: Integer
 * # 시간 복잡도: O(N^2)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_2637 {

    static StringBuilder sb = new StringBuilder();

    static class Part {
        int from, counts;

        Part(int _from, int _counts) {
            from = _from;
            counts = _counts;
        }
    }

    static int N, M;
    static ArrayList<Part>[] adjList;
    static int[] indegree;
    static int[][] counts;

    public static void main(String[] args) throws IOException {
        input();
        solve();
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
            int target = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            int counts = Integer.parseInt(st.nextToken());
            adjList[from].add(new Part(target, counts));
            indegree[target]++;
        }
    }

    static void solve() {
        Queue<Integer> q = new LinkedList<>();

        // 기본 부품 후보들을 찾는다. => 초기 indegree가 0인 부품들
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
                counts[i][i] = 1; // (기본)부품 i를 만드는데 (기본)부품 i가 1개 든다.
            }
        }

        // 위상 정렬 순서로 만들 수 있는 부품부터 만들면서 필요한 기본 부품 개수를 누적한다.
        while (!q.isEmpty()) {
            int cur = q.poll();

            for (Part adj : adjList[cur]) {
                int to = adj.from;
                int needCounts = adj.counts;

                indegree[to]--;
                if (indegree[to] == 0) {
                    q.offer(to);
                }

                // 기본 부품 개수 누적
                for (int i = 1; i <= N; i++) {
                    counts[to][i] += counts[cur][i] * needCounts;
                }
            }
        }

        // print
        for (int i = 1; i <= N; i++) {
            if (counts[N][i] != 0) {
                System.out.println(i + " " + counts[N][i]);
            }
        }
    }

}

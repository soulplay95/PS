package algorithm.topological_sort.practice;

/**
 * @문제 게임 개발 [G3]
 * @날짜 220615
 * @분류 DP / 위상 정렬
 * @조건 1 <= N <= 500
 * 1 <= buildTime <= 10만
 * @풀이 - 위상 정렬 순서로 건물을 짓는다.
 * - 각 건물을 짓는데 걸리는 시간: max(기존 빌드 시간, 먼저 지어져야 하는 건물의 빌드 시간 + 현재 건물을 짓는데 걸리는 시간)
 * @comments # 정답의 최대치: Integer => 5천만(max N * max buildTime)
 * # 시간 복잡도: O(N)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_1516 {

    static StringBuilder sb = new StringBuilder();

    static int N;
    static ArrayList<Integer>[] adjList;
    static int[] indegree, buildTime, dp;

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        // init
        adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }
        indegree = new int[N + 1];
        buildTime = new int[N + 1];
        dp = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int time = Integer.parseInt(st.nextToken());
            buildTime[i] = time;
            while (true) {
                int from = Integer.parseInt(st.nextToken());
                if (from == -1) {
                    break;
                }
                adjList[from].add(i);
                indegree[i]++;
            }
        }
    }

    static void solve() {
        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
                dp[i] = buildTime[i];
            }
        }

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int adjVertex : adjList[cur]) {
                indegree[adjVertex]--;
                if (indegree[adjVertex] == 0) {
                    q.offer(adjVertex);
                }
                dp[adjVertex] = Math.max(dp[adjVertex], dp[cur] + buildTime[adjVertex]);
            }
        }

        // print
        for (int i = 1; i <= N; i++) {
            sb.append(dp[i]).append('\n');
        }
        System.out.println(sb.toString());
    }

}

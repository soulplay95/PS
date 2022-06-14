package algorithm.topological_sort.practice;

/**
 * @문제 작업 [G5]
 * @날짜 220615
 * @분류 DP / 위상 정렬
 * @조건
 * 3 <= N <= 1만
 * 1 <= time <= 100
 * @풀이
 * # 위상 정렬
 * @comments
 * # 정답의 최대치: Integer => 100만(max N * max time)
 * # 시간 복잡도: O(N)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_2056 {

    static StringBuilder sb = new StringBuilder();

    static int N;
    static ArrayList<Integer>[] adjList;
    static int[] indegree, pureBuildTime, completeBuildTime;

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
        indegree = new int[N + 1];
        pureBuildTime = new int[N + 1];
        completeBuildTime = new int[N + 1];

        for (int to = 1; to <= N; to++) {
            adjList[to] = new ArrayList<>();
            st = new StringTokenizer(br.readLine(), " ");
            int buildTime = Integer.parseInt(st.nextToken());
            pureBuildTime[to] = buildTime;
            int fromCounts = Integer.parseInt(st.nextToken());
            while (fromCounts-- > 0) {
                int from = Integer.parseInt(st.nextToken());
                adjList[from].add(to);
                indegree[to]++;
            }
        }
    }

    static void solve() {
        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
                completeBuildTime[i] = pureBuildTime[i];
            }
        }

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int adjVertex : adjList[cur]) {
                indegree[adjVertex]--;
                if (indegree[adjVertex] == 0) {
                    q.offer(adjVertex);
                }
                completeBuildTime[adjVertex] = Math.max(completeBuildTime[adjVertex], completeBuildTime[cur] + pureBuildTime[adjVertex]);
            }
        }

        int max = 0;
        for (int i = 1; i <= N; i++) {
            max = Math.max(max, completeBuildTime[i]);
        }

        // print
        System.out.println(max);
    }

}

/**
 * @문제 게임 개발_G3
 * @날짜 220225
 * @분류 DP / 위상 정렬
 * @조건
 * # 1 <= 건물 종류 수 (N) <= 500
 * # 1 <= 각 건물 건설 시간 <= 10만
 * @풀이
 * # 위상 정렬 순서대로 건물을 지으면서, 건물을 짓는 시간을 누적
 * @comments
 * # 시간 복잡도: O(V + E)
 * # 공간 복잡도: O(V)
 * # 정답의 최대치: Integer
 */

import java.io.*;
import java.util.*;

public class BOJ_1516 {

    static StringBuilder sb = new StringBuilder();

    static int N;
    static ArrayList<Integer>[] adjList;
    static int[] time, indegree, dp;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        print();
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
        time = new int[N + 1];
        indegree = new int[N + 1];
        dp = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int t = Integer.parseInt(st.nextToken());
            time[i] = t;
            dp[i] = t;
            while (st.hasMoreTokens()) {
                int adjVertex = Integer.parseInt(st.nextToken());
                if (adjVertex == -1) {
                    break;
                }

                adjList[adjVertex].add(i);
                indegree[i]++;
            }
        }
    }

    static void solve() {
        Queue<Integer> q = new LinkedList<>();
        // 초기화
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int adjVertex : adjList[cur]) {
                indegree[adjVertex]--;
                if (indegree[adjVertex] == 0) {
                    q.offer(adjVertex);
                }

                dp[adjVertex] = Math.max(dp[adjVertex], dp[cur] + time[adjVertex]);
            }
        }
    }

    static void print() {
        for (int i = 1; i <= N; i++) {
            sb.append(dp[i]).append("\n");
        }

        System.out.println(sb.toString());
    }

}

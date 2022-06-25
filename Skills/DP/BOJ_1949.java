package algorithm.dp;

/**
 * @문제 우수 마을 [G2]
 * @날짜 220625
 * @분류 트리 DP
 * @조건
 * 1 <= 마을 수 (N) <= 1만
 * 각 마을의 주민 수 <= 1만
 * @풀이
 * # DP
 * - 완탐: O(2^N)
 * - dp[i][0] := i를 root로 하는 subtree에서 root를 선택하지 않고서 가능한 최대 주민 수
 * - dp[i][1] := i를 root로 하는 subtree에서 root를 선택하고서 가능한 최대 주민 수
 * - 정답: 임의의 정점(1번 정점)을 루트로 했을 때(rooted tree로 변환) max(dp[1][0], dp[1][1])
 * - dp[R][1] = num[R] + sum(dp[child][0])
 * - dp[R][0] = sum(max(dp[child][0], dp[child][1]))
 * @comments
 * # 정답의 최대치: Integer
 * - 모든 마을에 최대 주민 수인 1만명씩 존재할 때, 9999개의 마을을 선택할 경우 약 1억
 * # 시간 복잡도: O(N)
 */

import java.io.*;
import java.util.*;

public class BOJ_1949 {

    static StringBuilder sb = new StringBuilder();

    static int N;
    static ArrayList<Integer>[] adjList;
    static int[] num;
    static int[][] dp;

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
        num = new int[N + 1];
        dp = new int[N + 1][2];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adjList[from].add(to);
            adjList[to].add(from);
        }
    }

    static void solve() {
        dfs(1, -1);

        // print
        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }

    static void dfs(int cur, int parent) {
        dp[cur][0] = 0;
        dp[cur][1] = num[cur];

        for (int adjVertex : adjList[cur]) {
            if (adjVertex == parent) continue;
            dfs(adjVertex, cur);
            dp[cur][0] += Math.max(dp[adjVertex][0], dp[adjVertex][1]);
            dp[cur][1] += dp[adjVertex][0];
        }
    }

}

package algorithm.dp;

/**
 * @문제 트리와 쿼리 [G5]
 * @날짜 220625
 * @분류 트리 DP
 * @조건
 * 2 <= 정점의 수 (N) <= 10만
 * 1 <= 쿼리의 수 (Q) <= 10만
 * @풀이
 * - dp[i] := i를 root로 하는 subtree의 정점 개수
 * - dp[i] = dp[i를 부모로 하는 자식노드들] + 1
 * @comments
 * # 정답의 최대치:
 * # 시간 복잡도: O(N)
 */

import java.io.*;
import java.util.*;

public class BOJ_15681 {

    static StringBuilder sb = new StringBuilder();

    static int N, R, Q;
    static ArrayList<Integer>[] adjList;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        // init
        adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }
        dp = new int[N + 1];

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adjList[from].add(to);
            adjList[to].add(from);
        }

        solve();

        for (int i = 0; i < Q; i++) {
            int q = Integer.parseInt(br.readLine());
            sb.append(dp[q]).append('\n');
        }

        // print
        System.out.println(sb.toString());
    }

    static void solve() {
        dfs(R, -1);
    }

    static void dfs(int cur, int parent) {
        dp[cur] = 1; // 자기 자신 포함

        for (int adjVertex : adjList[cur]) {
            if (adjVertex == parent) continue;
            dfs(adjVertex, cur);
            // 자식 노드에 대한 dp 값이 계산된 상태
            dp[cur] += dp[adjVertex];
        }
    }

}

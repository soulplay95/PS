/**
 * @문제 트리와 쿼리_G5
 * @날짜 220314
 * @분류 DP / DFS / 트리 DP
 * @조건
 * # 2 <= 정점 수 (N) <= 10만
 * # 1 <= 쿼리 수 (Q) <= 10만
 * @풀이
 * # DP - DFS
 * @comments
 * # 정답의 최대치: Integer
 * # 시간 복잡도: O(V + E)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_15681 {

    static StringBuilder sb = new StringBuilder();

    static int N, R, Q;
    static ArrayList<Integer>[] adjList;
    static int[] dp, query;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        print();
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
        query = new int[Q];

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            adjList[x].add(y);
            adjList[y].add(x);
        }

        for (int i = 0; i < Q; i++) {
            query[i] = Integer.parseInt(br.readLine());
        }
    }

    static void dfs(int cur, int parent) {
        dp[cur] = 1; // 자기 자신 포함

        // 부모를 제외한 자식 노드들에 대하여 dfs 호출하고, 호출이 끝나면 해당 자식 노드에 대한 dp 값을 누적해준다.
        for (int child : adjList[cur]) {
            if (child == parent) {
                continue;
            }

            dfs(child, cur);
            dp[cur] += dp[child];
        }
    }

    static void solve() {
        // DFS를 통해 동적 테이블을 구한다.
        dfs(R, -1);
    }

    static void print() {
        for (int root : query) {
            sb.append(dp[root]).append("\n");
        }

        System.out.println(sb.toString());
    }

}

package algorithm.tree.practice;

/**
 * @문제 가장 가까운 공통 조상 [G4]
 * @날짜 220610
 * @분류 그래프 탐색 / 트리 / DFS / 최소 공통 조상
 * @조건
 * 2 <= N <= 1만
 * @풀이
 * - 한 노드에서 루트 노드로 DFS 하여 자신 포함 부모 노드들을 방문표시한다.
 * - 다른 한 노드에서 DFS 하여 처음으로 방문 표시된 노드가 있으면 정답을 갱신한다.
 * @comments
 * # 정답의 최대치:
 * # 시간 복잡도: O(N)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_3584 {

    static StringBuilder sb = new StringBuilder();

    static int N, ans, A, B;
    static int[] parent;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int TC = Integer.parseInt(br.readLine());
        while (TC-- > 0) {
            N = Integer.parseInt(br.readLine());
            // init
            parent = new int[N + 1];
            visited = new boolean[N + 1];

            for (int i = 1; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int parents = Integer.parseInt(st.nextToken());
                int cur = Integer.parseInt(st.nextToken());
                parent[cur] = parents;
            }
            st = new StringTokenizer(br.readLine(), " ");
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            solve();
        }

        // print
        System.out.println(sb);
    }

    static void solve() {
        // Root 노드를 찾는다.
        for (int i = 1; i <= N; i++) {
            if (parent[i] == 0) {
                ans = i;
                break;
            }
        }

        // A 노드에서 루트 노드로 거슬러 올라가면서 방문 표시한다.
        dfs(A);

        // B 노드에서 최초로 방문 표시된 노드를 찾는다.
        while (B != 0) {
            if (visited[B]) {
                ans = B;
                break;
            }
            B = parent[B];
        }

        // print
        sb.append(ans).append('\n');
    }

    static void dfs(int cur) {
        visited[cur] = true;
        if (parent[cur] != 0) {
            dfs(parent[cur]);
        }
    }

}

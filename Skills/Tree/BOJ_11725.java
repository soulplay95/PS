package algorithm.tree;

/**
 * @문제 트리의 부모 찾기 [S2]
 * @날짜 220608
 * @분류 그래프 탐색 / 트리
 * @조건
 * 2 <= N <= 10만
 * @풀이
 * # DFS
 * - 그래프(트리)를 인접 리스트로 구성한다.
 * - 루트 노드에서 DFS 탐색한다.
 * @comments
 * # 정답의 최대치: Integer
 * # 시간 복잡도: O(N)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_11725 {

    static StringBuilder sb = new StringBuilder();

    static int N;
    static ArrayList<Integer>[] adjList;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        // init
        adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }
        parents = new int[N + 1];

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adjList[from].add(to);
            adjList[to].add(from);
        }
    }

    static void solve() {
        dfs(1, 0);

        // print
        for (int i = 2; i <= N; i++) {
            sb.append(parents[i]).append('\n');
        }
        System.out.println(sb.toString());
    }

    static void dfs(int curNode, int parentNode) {
        parents[curNode] = parentNode;

        for (int adjVertex : adjList[curNode]) {
            if (parents[adjVertex] == 0) { // 방문하지 않았으면
                dfs(adjVertex, curNode);
            }
        }
    }

}

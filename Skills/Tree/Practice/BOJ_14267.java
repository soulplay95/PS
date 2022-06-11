package algorithm.tree.practice;

/**
 * @문제 회사 문화 1 [G4]
 * @날짜 220612
 * @분류 DP / 그래프 탐색 / 트리 / DFS / 트리 DP
 * @조건
 * 2 <= n, m <= 10만
 * 1 <= w <= 1000
 * @풀이
 * - 루트 노드에서 DFS 타고 내려가면서 부모의 칭찬 수치를 자식의 칭찬 수치에 누적한다.
 * @comments
 * # 정답의 최대치: Integer => max 1억 (max w * max m)
 * # 시간 복잡도: O(N)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_14267 {

    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static ArrayList<Integer>[] children;
    static int[] praised;

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        // init
        children = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            children[i] = new ArrayList<>();
        }
        praised = new int[N + 1];

        st = new StringTokenizer(br.readLine(), " ");
        st.nextToken();
        for (int i = 2; i <= N; i++) {
            int parent = Integer.parseInt(st.nextToken());
            children[parent].add(i);
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int node = Integer.parseInt(st.nextToken());
            int initialPraisedValue = Integer.parseInt(st.nextToken());
            praised[node] += initialPraisedValue;
        }
    }

    static void solve() {
        dfs(1);

        // print
        for (int i = 1; i <= N; i++) {
            sb.append(praised[i]).append(' ');
        }
        System.out.println(sb);
    }

    static void dfs(int cur) {
        for (int child : children[cur]) {
            praised[child] += praised[cur];
            dfs(child);
        }
    }

}

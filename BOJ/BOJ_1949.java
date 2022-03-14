/**
 * @문제 우수 마을_G2
 * @날짜 220314
 * @분류 트리 DP
 * @조건
 * # 1 <= 정점 수 (N) <= 1만
 * # 1 <= 각 마을의 주민 수 <= 1만
 * @풀이
 * # DP
 * - 완탐의 경우 O(2^n)
 * - dt[i][0]: i를 root로 하는 서브 트리에서 root를 선택하지 않고서 가능한 최대 주민 수
 * - dt[i][1]: i를 root로 하는 서브 트리에서 root를 선택하고서 가능한 최대 주민 수
 * - 아무 정점(1번 정점)을 root로 하는 rooted tree로 만들어서 접근한다. => 정답: max(dt[root][0], dt[root][1])
 * - dt[root][1] = num[root] + Sigma(dt[child][0])
 * - dt[root][0] = Sigma(max(dt[child][0], dt[child][1]))
 * @comments
 * # 정답의 최대치: Integer
 * - 모든 마을에 1만명의 주민이 살고 있고, 1번 마을을 부모 노드로 2~10000번 마을이 자식 노드로 달려 있을 경우
 * - 약 10000 * 9999 < 1억
 * # 시간 복잡도: O(V + E) = O(N)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_1949 {

    static StringBuilder sb = new StringBuilder();

    static int N;
    static ArrayList<Integer>[] adjList;
    static int[] num;
    static int[][] dt;

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
        dt = new int[N + 1][2];
        num = new int[N + 1];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            adjList[x].add(y);
            adjList[y].add(x);
        }
    }

    static void dfs(int cur, int parent) {
        dt[cur][0] = 0;
        dt[cur][1] = num[cur];

        for (int child : adjList[cur]) {
            if (child == parent) {
                continue;
            }

            dfs(child, cur);
            dt[cur][0] += Math.max(dt[child][0], dt[child][1]);
            dt[cur][1] += dt[child][0];
        }
    }

    static void solve() {
        // DFS 탐색하여 동적 테이블을 채운다.
        dfs(1, -1); // 1번 정점을 root라고 가정
    }

    static void print() {
        System.out.println(Math.max(dt[1][0], dt[1][1]));
    }

}

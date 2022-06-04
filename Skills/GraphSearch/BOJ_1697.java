package algorithm.graph_search;

/**
 * @문제 숨바꼭질 [S1]
 * @날짜 220604
 * @분류 그래프 탐색 / BFS
 * @조건
 * 0 <= N, K <= 10만
 * @풀이
 * # BFS
 * @comments
 * # 정답의 최대치: Integer
 * # 시간 복잡도: O(10^5)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_1697 {

    static StringBuilder sb = new StringBuilder();

    static int N, K;
    static boolean[] visited;

    static final int MAX_POS = 100000;

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        // init
        visited = new boolean[MAX_POS + 1];
    }

    static void solve() {
        // print
        System.out.println(bfs());
    }

    static int bfs() {
        // init
        Queue<Integer> q = new LinkedList<>();
        q.offer(N);
        visited[N] = true;
        int time = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                int cur = q.poll();

                if (cur == K) {
                    return time;
                }

                if (cur - 1 >= 0 && !visited[cur - 1]) {
                    q.offer(cur - 1);
                    visited[cur - 1] = true;
                }
                if (cur + 1 <= MAX_POS && !visited[cur + 1]) {
                    q.offer(cur + 1);
                    visited[cur + 1] = true;
                }
                if (cur * 2 <= MAX_POS && !visited[cur * 2]) {
                    q.offer(cur * 2);
                    visited[cur * 2] = true;
                }
            }

            time++;
        }

        return time;
    }

}

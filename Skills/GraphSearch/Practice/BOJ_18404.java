package algorithm.graph_search.practice;

/**
 * @문제 현명한 나이트 [S1]
 * @날짜 220607
 * @분류 그래프 탐색
 * @조건 1 <= N <= 500
 * 1 <= M <= 1000
 * @풀이 # BFS
 * @comments # 정답의 최대치:
 * # 시간 복잡도: O(N^2)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_18404 {

    static StringBuilder sb = new StringBuilder();

    static class Pair {
        int r, c;

        Pair(int _r, int _c) {
            r = _r;
            c = _c;
        }
    }

    static int N, M;
    static Pair start;
    static Pair[] enemies;
    static int[][] dist;

    static int[] dr = {-2, -1, 1, 2, 2, 1, -1, -2}; // 1시부터 시계방향
    static int[] dc = {1, 2, 2, 1, -1, -2, -2, -1};

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");
        start = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        enemies = new Pair[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            enemies[i] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
    }

    static void solve() {
        bfs();

        for (int i = 0; i < M; i++) {
            Pair cur = enemies[i];
            sb.append(dist[cur.r][cur.c]).append(' ');
        }

        // print
        System.out.println(sb.toString());
    }

    static void bfs() {
        // init
        dist = new int[N + 1][N + 1];
        for (int r = 1; r <= N; r++) {
            Arrays.fill(dist[r], -1);
        }
        dist[start.r][start.c] = 0;
        Queue<Pair> q = new LinkedList<>();
        q.offer(start);

        while (!q.isEmpty()) {
            Pair cur = q.poll();

            for (int d = 0; d < 8; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];

                if (isOut(nr, nc)) continue;
                if (dist[nr][nc] != -1) continue;
                dist[nr][nc] = dist[cur.r][cur.c] + 1;
                q.offer(new Pair(nr, nc));
            }
        }
    }

    static boolean isOut(int r, int c) {
        return (r <= 0 || r > N || c <= 0 || c > N);
    }

}

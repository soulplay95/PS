/**
 * @문제 현명한 나이트_S1
 * @날짜 220308
 * @분류 BFS
 * @조건
 * # 1 <= 맵 가로, 세로 크기 (N) <= 500
 * # 1 <= 상대편 말의 수 (M) <= 1000
 * @풀이
 * # BFS
 * - 시작점(나이트 위치)에서 BFS 탐색을 시작하여 각 칸의 dist 값을 구한다.
 * @comments
 * # 정답의 최대치: Integer
 * - <= N
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
    static Pair[] targets;
    static int[][] dist;

    static int[] dr = {-2, -1, 1, 2, 2, 1, -1, -2}; // 1시 방향부터 시계방향
    static int[] dc = {1, 2, 2, 1, -1, -2, -2, -1};

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
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        // init
        start = new Pair(r, c);
        targets = new Pair[M];
        dist = new int[N + 1][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            targets[i] = new Pair(r, c);
        }
    }

    static boolean isOut(int r, int c) {
        return (r < 1 || r > N || c < 1 || c > N);
    }

    static void bfs() {
        // init
        for (int r = 1; r <= N; r++) {
            Arrays.fill(dist[r], -1);
        }

        Queue<Pair> q = new LinkedList<>();
        q.offer(start);
        dist[start.r][start.c] = 0;

        while (!q.isEmpty()) {
            Pair cur = q.poll();

            for (int d = 0; d < 8; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];

                if (isOut(nr, nc)) {
                    continue;
                }
                if (dist[nr][nc] != -1) {
                    continue;
                }

                dist[nr][nc] = dist[cur.r][cur.c] + 1;
                q.offer(new Pair(nr, nc));
            }
        }
    }

    static void solve() {
        bfs();
    }

    static void print() {
        for (int i = 0; i < M; i++) {
            int r = targets[i].r;
            int c = targets[i].c;

            sb.append(dist[r][c]).append(" ");
        }

        System.out.println(sb);
    }

}

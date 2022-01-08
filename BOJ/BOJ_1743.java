/**
 * @문제 음식물 피하기_S1
 * @날짜 220109
 * @분류 그래프 탐색
 * @조건
 * # 1 <= 맵 크기 (R, C) <= 100
 * @풀이
 * # 주어진 좌표를 맵에 표시하고 bfs
 * @comment
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_1743 {

    private static class Pair {
        int r, c;

        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    private static int R, C, K, max;
    private static boolean[][] map, visited;

    private static int[] dr = {-1, 0, 1, 0}; // 상우하좌 시계방향
    private static int[] dc = {0, 1, 0, -1}; // 상우하좌 시계방향

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        // init
        max = 1;
        map = new boolean[R][C];
        visited = new boolean[R][C];

        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            map[r][c] = true;
        }

        solve();

        // print
        System.out.println(max);
    }

    private static void solve() {
        // 음식물 칸에서 bfs 탐색하여 인접한 음식물의 개수를 구하고 최대값을 갱신한다.
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                // 탐색하지 않았고 음식물 칸이면
                if (!visited[r][c] && map[r][c]) {
                    max = Math.max(max, bfs(r, c));
                }
            }
        }
    }

    private static int bfs(int r, int c) {
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(r, c));
        visited[r][c] = true;

        int counts = 1;

        while (!q.isEmpty()) {
            Pair cur = q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];

                // 경계 체크
                if (isOut(nr, nc)) {
                    continue;
                }

                if (!visited[nr][nc] && map[nr][nc]) {
                    visited[nr][nc] = true;
                    counts++;
                    q.offer(new Pair(nr, nc));
                }
            }
        }

        return counts;
    }

    private static boolean isOut(int r, int c) {
        return (r < 0 || r >= R || c < 0 || c >= C);
    }

}

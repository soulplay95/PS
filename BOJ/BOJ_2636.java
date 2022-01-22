/**
 * @문제 치즈_G5
 * @날짜 220122
 * @분류 그래프 탐색
 * @조건
 * # 맵 가로, 세로 크기 <= 100
 * @풀이
 * # 무조건 공기인 가장자리 칸에서 bfs
 * @comment
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_2636 {

    private static class Pair {
        int r, c;

        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    private static int R, C, cheeseCounts, time, cheeseCountsBeforeOneHour;
    private static int[][] map;

    private static int[] dr = {-1, 0, 1, 0}; // 상우하좌 시계방향
    private static int[] dc = {0, 1, 0, -1}; // 상우하좌 시계방향

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        // init
        map = new int[R][C];
        cheeseCounts = 0;
        cheeseCountsBeforeOneHour = 0;
        time = 0;

        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int c = 0; c < C; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if (map[r][c] == 1) {
                    cheeseCounts++;
                }
            }
        }

        solve();

        // print
        System.out.println(time);
        System.out.println(cheeseCountsBeforeOneHour);
    }

    private static void solve() {
        while (cheeseCounts > 0) {
            cheeseCountsBeforeOneHour = cheeseCounts;
            time++;
            bfs();
        }
    }

    private static void bfs() {
        // 무조건 공기인 (0, 0) 칸에서 bfs
        Queue<Pair> q = new LinkedList<>();
        boolean[][] visited = new boolean[R][C];
        q.offer(new Pair(0, 0));
        visited[0][0] = true;

        while (!q.isEmpty()) {
            Pair cur = q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];

                if (isOut(nr, nc) || visited[nr][nc]) {
                    continue;
                }

                visited[nr][nc] = true;
                if (map[nr][nc] == 1) {
                    cheeseCounts--;
                    map[nr][nc] = 0;
                } else if (map[nr][nc] == 0) {
                    q.offer(new Pair(nr, nc));
                }
            }
        }
    }

    private static boolean isOut(int r, int c) {
        return (r < 0 || r >= R || c < 0 || c >= C);
    }

}

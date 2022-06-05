package algorithm.graph_search.practice;

/**
 * @문제 양 [S1]
 * @날짜 220605
 * @분류 그래프 탐색
 * @조건 3 <= R, C <= 250
 * @풀이 # BFS
 * - 아직 탐색하지 않은 양 또는 늑대의 위치에서 BFS 탐색
 * @comments # 정답의 최대치: Integer => < 250^2 (max N^2)
 * # 시간 복잡도: O(N^2)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_3184 {

    static StringBuilder sb = new StringBuilder();

    static class Pair {
        int r, c;

        Pair(int _r, int _c) {
            r = _r;
            c = _c;
        }
    }

    static int R, C, oCounts, vCounts;
    static char[][] map;
    static boolean[][] visited;

    static int[] dr = {-1, 0, 1, 0}; // 상우하좌 시계방향
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        // init
        map = new char[R][C];
        visited = new boolean[R][C];
        oCounts = 0;
        vCounts = 0;

        for (int r = 0; r < R; r++) {
            map[r] = br.readLine().toCharArray();
        }
    }

    static void solve() {
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (!visited[r][c] && (map[r][c] == 'o' || map[r][c] == 'v')) {
                    bfs(r, c);
                }
            }
        }

        // print
        System.out.println(oCounts + " " + vCounts);
    }

    static void bfs(int r, int c) {
        int oCountsInSection = 0, vCountsInSection = 0;
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(r, c));
        visited[r][c] = true;
        if (map[r][c] == 'o') {
            oCountsInSection++;
        } else if (map[r][c] == 'v') {
            vCountsInSection++;
        }

        while (!q.isEmpty()) {
            Pair cur = q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];

                if (isOut(nr, nc)) continue;
                if (map[nr][nc] == '#') continue;
                if (visited[nr][nc]) continue;
                if (map[nr][nc] == 'o') {
                    oCountsInSection++;
                } else if (map[nr][nc] == 'v') {
                    vCountsInSection++;
                }
                q.offer(new Pair(nr, nc));
                visited[nr][nc] = true;
            }
        }

        if (oCountsInSection > vCountsInSection) {
            oCounts += oCountsInSection;
        } else {
            vCounts += vCountsInSection;
        }
    }

    static boolean isOut(int r, int c) {
        return (r < 0 || r >= R || c < 0 || c >= C);
    }

}

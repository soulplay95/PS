/**
 * @문제 헌내기는 친구가 필요해_S2
 * @날짜 220110
 * @분류 그래프 탐색
 * @조건
 * # 1 <= 맵 크기 (N, M) <= 600
 * @풀이
 * # 도연이 위치에서 BFS 탐색
 * @comment
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_21736 {

    private static class Pair {
        int r, c;

        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    private static int R, C, ans;
    private static char[][] map;
    private static boolean[][] visited;
    private static Queue<Pair> q;

    private static int[] dr = {-1, 0, 1, 0}; // 상우하좌 시계방향
    private static int[] dc = {0, 1, 0, -1}; // 상우하좌 시계방향

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        // init
        map = new char[R][C];
        visited = new boolean[R][C];
        q = new LinkedList<>();
        ans = 0;

        for (int r = 0; r < R; r++) {
            String row = br.readLine();
            for (int c = 0; c < C; c++) {
                map[r][c] = row.charAt(c);
                if (map[r][c] == 'I') {
                    q.offer(new Pair(r, c));
                    visited[r][c] = true;
                }
            }
        }

        solve();

        // print
        System.out.println(ans == 0 ? "TT" : ans);
    }

    private static void solve() {
        while (!q.isEmpty()) {
            Pair cur = q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];

                // 경계 체크
                if (isOut(nr, nc)) {
                    continue;
                }

                // 방문 체크, 벽 체크
                if (!visited[nr][nc] && map[nr][nc] != 'X') {
                    // 사람이면 개수 카운팅
                    if (map[nr][nc] == 'P') {
                        ans++;
                    }
                    q.offer(new Pair(nr, nc));
                    visited[nr][nc] = true;
                }
            }
        }
    }

    private static boolean isOut(int r, int c) {
        return (r < 0 || r >= R || c < 0 || c >= C);
    }

}

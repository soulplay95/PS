package algorithm.graph_search.practice;

/**
 * @문제 토마토 [G5]
 * @날짜 220607
 * @분류 그래프 탐색
 * @조건
 * 2 <= M, N <= 100
 * 1 <= H <= 100
 * @풀이
 * # Multi-Source BFS
 * - 익은 토마토가 있는 칸에서 BFS 탐색하여 한 싸이클마다 아직 익지 않은 토마토 개수를 체크한다.
 * @comments
 * # 정답의 최대치:
 * # 시간 복잡도: O(N^2)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_7569 {

    static StringBuilder sb = new StringBuilder();

    static class Position {
        int r, c, h;

        Position(int _r, int _c, int _h) {
            r = _r;
            c = _c;
            h = _h;
        }
    }

    static int R, C, H, unripeTomatoCounts, ans;
    static int[][][] map;
    static Queue<Position> q;

    static int[] dr = {0, 0, -1, 0, 1, 0}; // 위, 아래, 상우하좌
    static int[] dc = {0, 0, 0, 1, 0, -1};
    static int[] dh = {-1, 1, 0, 0, 0, 0};

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine(), " ");
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        // init
        unripeTomatoCounts = 0;
        map = new int[H][R][C];
        q = new LinkedList<>();

        for (int h = 0; h < H; h++) {
            for (int r = 0; r < R; r++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int c = 0; c < C; c++) {
                    map[h][r][c] = Integer.parseInt(st.nextToken());
                    if (map[h][r][c] == 1) {
                        q.offer(new Position(r, c, h));
                    } else if (map[h][r][c] == 0) {
                        unripeTomatoCounts++;
                    }
                }
            }
        }

        if (unripeTomatoCounts == 0) {
            System.out.println(0);
            System.exit(0);
        }
    }

    static void solve() {
        bfs();

        if (unripeTomatoCounts > 0) {
            System.out.println(-1);
        } else {
            System.out.println(ans);
        }
    }

    static void bfs() {
        while (!q.isEmpty()) {
            int size = q.size();

            if (unripeTomatoCounts == 0) {
                return;
            }

            while (size-- > 0) {
                Position cur = q.poll();

                for (int d = 0; d < 6; d++) {
                    int nr = cur.r + dr[d];
                    int nc = cur.c + dc[d];
                    int nh = cur.h + dh[d];

                    if (isOut(nr, nc, nh)) continue;
                    if (map[nh][nr][nc] == 0) {
                        map[nh][nr][nc] = 2; // 방문 표시
                        q.offer(new Position(nr, nc, nh));
                        unripeTomatoCounts--;
                    }
                }
            }

            ans++;
        }
    }

    static boolean isOut(int r, int c, int h) {
        return (r < 0 || r >= R || c < 0 || c >= C || h < 0 || h >= H);
    }

}

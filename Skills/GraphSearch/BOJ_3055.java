package algorithm.graph_search;

/**
 * @문제 탈출 [G4]
 * @날짜 220604
 * @분류 그래프 탐색 / BFS
 * @조건
 * 1 <= R, C <= 50
 * @풀이
 * # BFS
 * @comments
 * # 정답의 최대치: Integer
 * # 시간 복잡도: O(N^2)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_3055 {

    static StringBuilder sb = new StringBuilder();

    static class Pair {
        int r, c;

        Pair(int _r, int _c) {
            r = _r;
            c = _c;
        }
    }

    static int R, C;
    static char[][] map;
    static int[][] distWater, distHedgehog;
    static boolean[][] visitedWater, visitedHedgehog;
    static Queue<Pair> wq, hq;

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
        distWater = new int[R][C];
        distHedgehog = new int[R][C];
        wq = new LinkedList<>();
        hq = new LinkedList<>();
        visitedWater = new boolean[R][C];
        visitedHedgehog = new boolean[R][C];

        for (int r = 0; r < R; r++) {
            String row = br.readLine();
            for (int c = 0; c < C; c++) {
                map[r][c] = row.charAt(c);
                distWater[r][c] = -1;
                distHedgehog[r][c] = -1;
                if (map[r][c] == 'S') {
                    hq.offer(new Pair(r, c));
                    visitedHedgehog[r][c] = true;
                    distHedgehog[r][c] = 0;
                } else if (map[r][c] == '*') {
                    wq.offer(new Pair(r, c));
                    visitedWater[r][c] = true;
                    distWater[r][c] = 0;
                }
            }
        }
    }

    static void solve() {
        bfsWater();
        bfsHedgehog();

        // print
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (map[r][c] == 'D') {
                    if (distHedgehog[r][c] == -1) {
                        System.out.println("KAKTUS");
                    } else {
                        System.out.println(distHedgehog[r][c]);
                    }
                }
            }
        }
    }

    static void bfsWater() {
        while (!wq.isEmpty()) {
            Pair cur = wq.poll();

            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];

                if (isOut(nr, nc)) continue;
                if (map[nr][nc] == '.' && !visitedWater[nr][nc]) {
                    visitedWater[nr][nc] = true;
                    wq.offer(new Pair(nr, nc));
                    distWater[nr][nc] = distWater[cur.r][cur.c] + 1;
                }
            }
        }
    }

    static void bfsHedgehog() {
        while (!hq.isEmpty()) {
            Pair cur = hq.poll();

            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];

                if (isOut(nr, nc)) continue;
                if (visitedHedgehog[nr][nc]) continue;
                if (map[nr][nc] == 'X') continue;
                if (map[nr][nc] == '*') continue;
                if (distWater[nr][nc] == -1 || distHedgehog[cur.r][cur.c] + 1 < distWater[nr][nc]) {
                    hq.offer(new Pair(nr, nc));
                    visitedHedgehog[nr][nc] = true;
                    distHedgehog[nr][nc] = distHedgehog[cur.r][cur.c] + 1;
                }
            }
        }
    }

    static boolean isOut(int r, int c) {
        return (r < 0 || r >= R || c < 0 || c >= C);
    }

}

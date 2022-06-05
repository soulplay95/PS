package algorithm.graph_search.practice;

/**
 * @문제 유기농 배추 [S2]
 * @날짜 220605
 * @분류 그래프 탐색 / DFS / BFS
 * @조건
 * 1 <= N, M <= 50
 * 1 <= K <= 50^2
 * @풀이
 * # DFS
 * - 배추가 있는 칸에서 DFS 탐색하여 방문 표시
 * @comments
 * # 정답의 최대치: Integer => < 50^2(max N^2)
 * # 시간 복잡도: O(N^2)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_1012 {

    static StringBuilder sb = new StringBuilder();

    static int R, C;
    static int[][] map;

    static int[] dr = {-1, 0, 1, 0}; // 상우하좌 시계방향
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        input();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int TC = Integer.parseInt(br.readLine());
        for (int t = 1; t <= TC; t++) {
            st = new StringTokenizer(br.readLine(), " ");
            C = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            // init
            map = new int[R][C];

            for (int k = 0; k < K; k++) {
                st = new StringTokenizer(br.readLine(), " ");
                int c = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                map[r][c] = 1;
            }

            solve();
        }

        // print
        System.out.println(sb.toString());
    }

    static void solve() {
        int sectionCounts = 0;

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (map[r][c] == 1) {
                    dfs(r, c);
                    sectionCounts++;
                }
            }
        }

        sb.append(sectionCounts).append('\n');
    }

    static void dfs(int r, int c) {
        map[r][c] = 2; // 방문 표시

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (isOut(nr, nc)) continue;
            if (map[nr][nc] == 1) {
                dfs(nr, nc);
            }
        }

    }

    static boolean isOut(int r, int c) {
        return (r < 0 || r >= R || c < 0 || c >= C);
    }

}

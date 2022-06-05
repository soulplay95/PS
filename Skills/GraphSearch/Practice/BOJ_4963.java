package algorithm.graph_search.practice;

/**
 * @문제 섬의 개수 [S2]
 * @날짜 220605
 * @분류 그래프 탐색
 * @조건
 * 1 <= w, h <= 50
 * @풀이
 * # DFS
 * - 아직 방문하지 않은 섬에서 DFS 탐색하여 방문 체크
 * @comments
 * # 정답의 최대치: Integer => < 50^2
 * # 시간 복잡도: O(N^2)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_4963 {

    static StringBuilder sb = new StringBuilder();

    static int R, C;
    static int[][] map;

    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1}; // 상부터 시계방향
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) throws IOException {
        input();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        while (true) {
            st = new StringTokenizer(br.readLine(), " ");
            C = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            if (R == 0 && C == 0) {
                break;
            }
            // init
            map = new int[R][C];

            for (int r = 0; r < R; r++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int c = 0; c < C; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            solve();
        }

        // print
        System.out.println(sb);
    }

    static void solve() {
        int groupCounts = 0;

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (map[r][c] == 1) {
                    dfs(r, c);
                    groupCounts++;
                }
            }
        }

        sb.append(groupCounts).append('\n');
    }

    static void dfs(int r, int c) {
        map[r][c] = 2;

        for (int d = 0; d < 8; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (isOut(nr ,nc)) continue;
            if (map[nr][nc] == 1) {
                dfs(nr, nc);
            }
        }
    }

    static boolean isOut(int r, int c) {
        return (r < 0 || r >= R || c < 0 || c >= C);
    }

}

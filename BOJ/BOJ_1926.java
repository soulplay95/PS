/**
 * @문제 그림_S1
 * @날짜 220118
 * @분류 그래프 탐색
 * @조건
 * # 1 <= 맵 가로, 세로 크기 <= 500
 * @풀이
 * # DFS 풀이
 * @comment
 * # 기본 BFS, DFS 문제
 */

import java.util.*;
import java.io.*;

public class BOJ_1926 {

    private static int R, C, paintCounts, maxPaintArea, tempArea;
    private static int[][] map;

    private static int[] dr = {-1, 0, 1, 0}; // 상우하좌 시계방향
    private static int[] dc = {0, 1, 0, -1}; // 상우하좌 시계방향

    public static void main(String[] args) throws IOException, NumberFormatException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        // init
        paintCounts = 0;
        maxPaintArea = 0;
        map = new int[R][C];

        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int c = 0; c < C; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        solve();

        // print
        System.out.println(paintCounts);
        System.out.println(maxPaintArea);
    }

    private static void solve() {
        // 모든 칸을 순회하며 1인 칸에서 dfs 탐색
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (map[r][c] == 1) {
                    paintCounts++; // 그림 개수 1 증가
                    tempArea = 0; // 그림 넓이 0으로 초기화
                    dfs(r, c);
                    maxPaintArea = Math.max(maxPaintArea, tempArea); // 그림 넓이 최댓값 갱신
                }
            }
        }
    }

    private static void dfs(int r, int c) {
        map[r][c] = 2; // 방문 표시
        tempArea++; // 넓이 1 증가

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (isOut(nr, nc)) { // 경계 체크
                continue;
            }

            if (map[nr][nc] == 1) {
                dfs(nr, nc);
            }
        }
    }

    private static boolean isOut(int r, int c) {
        return (r < 0 || r >= R || c < 0 || c >= C);
    }

}

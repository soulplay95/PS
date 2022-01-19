/**
 * @문제 양 한마리... 양 두마리..._S1
 * @날짜 220120
 * @분류 그래프 탐색
 * @조건
 * # 0 < 맵 가로, 세로 크기 <= 100
 * @풀이
 * # BFS
 * @comment
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_11123 {

    private static class Pair {
        int r, c;

        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    private static int R, C;
    private static char[][] map;
    private static StringBuilder sb = new StringBuilder();

    private static int[] dr = {-1, 0, 1, 0}; // 상우하좌 시계방향
    private static int[] dc = {0, 1, 0, -1}; // 상우하좌 시계방향

    public static void main(String[] args) throws IOException, NumberFormatException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine(), " ");
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            // init
            map = new char[R][C];

            for (int r = 0; r < R; r++) {
                map[r] = br.readLine().toCharArray();
            }

            sb.append(solve()).append("\n");
        }

        // print
        System.out.print(sb.toString());
    }

    private static int solve() {
        int sheepGroupCounts = 0;

        // #인 칸에서 BFS 돌면서 인접한 칸 방문 체크('v'로 표시)
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (map[r][c] == '#') {
//                    bfs(r, c);
                    dfs(r, c);
                    sheepGroupCounts++;
                }
            }
        }

        return sheepGroupCounts;
    }

//    private static void bfs(int r, int c) {
//        Queue<Pair> q = new LinkedList<>();
//        q.offer(new Pair(r, c));
//        map[r][c] = 'v';
//
//        while (!q.isEmpty()) {
//            Pair cur = q.poll();
//
//            for (int d = 0; d < 4; d++) {
//                int nr = cur.r + dr[d];
//                int nc = cur.c + dc[d];
//
//                if (isOut(nr, nc)) { // 경계 체크
//                    continue;
//                }
//                if (map[nr][nc] == '#') {
//                    map[nr][nc] = 'v'; // 방문 체크
//                    q.offer(new Pair(nr, nc));
//                }
//            }
//        }
//    }

    private static void dfs(int r, int c) {
        map[r][c] = 'v'; // 방문 체크

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (isOut(nr, nc)) {
                continue;
            }
            if (map[nr][nc] == '#') {
                dfs(nr, nc);
            }
        }
    }

    private static boolean isOut(int r, int c) {
        return (r < 0 || r >= R || c < 0 || c >= C);
    }

}

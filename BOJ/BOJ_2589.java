/**
 * @문제 보물섬_G5
 * @날짜 211122
 * @분류
 * @조건 # 1 <= 보물 지도 크기 <= 50
 * @풀이 # L인 모든 칸에서 BFS 돌려 최대 시간을 구한다.
 * @comment # 좀 더 최적화할 수 있는 방법 고민해보기
 */

import java.util.*;
import java.io.*;

public class BOJ_2589 {

    static class Pair {
        int r, c;

        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int R, C, max;
    static char[][] map;
    static boolean[][] visited;

    static int[] dr = {-1, 0, 1, 0}; // 상우하좌 시계방향
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        // init
        max = 0;
        map = new char[R][C];

        for (int r = 0; r < R; r++) {
            map[r] = br.readLine().toCharArray();
        } // input end

        solve();

        // print
        System.out.println(max);
    }

    static void solve() {
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (map[r][c] == 'L') {
                    max = Math.max(max, bfs(r, c));
                }
            }
        }
    }

    static int bfs(int r, int c) {
        int result = 0;
        Queue<Pair> q = new LinkedList<>();
        visited = new boolean[R][C];
        q.offer(new Pair(r, c));
        visited[r][c] = true;

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                Pair cur = q.poll();

                for (int d = 0; d < 4; d++) {
                    int nr = cur.r + dr[d];
                    int nc = cur.c + dc[d];

                    if (isOut(nr, nc) || visited[nr][nc]) {
                        continue;
                    }

                    if (map[nr][nc] == 'L') {
                        q.offer(new Pair(nr, nc));
                        visited[nr][nc] = true;
                    }
                }

            }

            result++; // 시간 증가
        }

        return result - 1;
    }

    static boolean isOut(int r, int c) {
        return (r < 0 || r >= R || c < 0 || c >= C);
    }

}

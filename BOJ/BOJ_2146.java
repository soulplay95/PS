package study.week36;

/**
 * @문제 다리 만들기_G3
 * @날짜 210917
 * @분류
 * @조건
 * # 지도 크기(N) <= 100
 * @풀이
 * # 각 섬을 구분한다. => 각 섬에서 BFS를 돌아 ID 마킹 + 섬의 테두리 좌표를 리스트에 저장
 * # 리스트에서 하나씩 꺼내면서 BFS를 돈다. 다른 섬에 도착하면 움직인 칸 수의 최소값 갱신
 * @comment
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_2146 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static class Pair {
        int r, c, num;

        public Pair(int r, int c, int num) {
            this.r = r;
            this.c = c;
            this.num = num;
        }
    }

    static int N, min;
    static int[][] map;
    static List<Pair> edgeList;
    static boolean[][] visited;

    static int[] dr = {-1, 0, 1, 0}; // 상우하좌 시계방향
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException, NumberFormatException {
        N = Integer.parseInt(br.readLine());
        // init
        min = 1000;
        map = new int[N][N];
        edgeList = new ArrayList<>();

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        } // input end

        solve();

        // print
        System.out.println(min);
    }

    static void solve() {
        // 1. 각 섬을 구분한다.
        init();

        // 2. 각 섬의 테두리에서 BFS 돌리기
        go();
    }

    static void init() {
        int i = 2; // 2번부터 마킹

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (map[r][c] == 1) {
                    bfs(r, c, i++);
                }
            }
        }
    }

    static void bfs(int r, int c, int num) {
        // (r, c)에서 bfs를 시작하여 num의 번호로 마킹 + 테두리 좌표 리스트에 담기
        Queue<Pair> q = new LinkedList<>();
        visited = new boolean[N][N];
        q.offer(new Pair(r, c, num));
        visited[r][c] = true;
        map[r][c] = num;

        while (!q.isEmpty()) {
            Pair cur = q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];

                // 경계 체크
                if (isOut(nr, nc)) {
                    continue;
                }

                // 방문 체크
                if (!visited[nr][nc]) {
                    visited[nr][nc] = true;
                    if (map[nr][nc] == 0) { // 테두리이면
                        edgeList.add(new Pair(nr, nc, num)); // 테두리 리스트에 넣기
                    } else if (map[nr][nc] == 1) { // 섬이면
                        map[nr][nc] = num;
                        q.offer(new Pair(nr, nc, num));
                    }
                }
            }
        }
    }

    static void go() {
        for (Pair cur : edgeList) {
            bfs2(cur);
        }
    }

    static void bfs2(Pair cur) {
        // 다른 섬에 도착했을 때 움직인 칸 수로 최소값 갱신
        int num = cur.num;
        Queue<Pair> q = new LinkedList<>();
        visited = new boolean[N][N];
        q.offer(cur);
        visited[cur.r][cur.c] = true;
        int moveCounts = 1;

        while (!q.isEmpty()) {
            int size = q.size();

            // 한 싸이클씩 돌기
            while (size-- > 0) {
                Pair p = q.poll();

                for (int d = 0; d < 4; d++) {
                    int nr = p.r + dr[d];
                    int nc = p.c + dc[d];

                    if (isOut(nr, nc)) {
                        continue;
                    }

                    if (!visited[nr][nc]) {
                        if (map[nr][nc] == 0) {
                            visited[nr][nc] = true;
                            q.offer(new Pair(nr, nc, num));
                        } else if (map[nr][nc] != num) { // 다른 섬에 도착
                            min = Math.min(min, moveCounts); // 최소값 갱신 후 종료
                            return;
                        }
                    }
                }
            }

            moveCounts++;
        }
    }

    static boolean isOut(int r, int c) {
        return (r < 0 || r >= N || c < 0 || c >= N);
    }

}

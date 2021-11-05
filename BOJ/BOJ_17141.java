/**
 * @문제 연구소 2_G5
 * @날짜 211105
 * @분류
 * @조건 # 5 <= 연구소의 크기 (N) <= 50
 * # 1 <= 바이러스의 개수 <= 10
 * @풀이 # 최대 10개의 바이러스를 놓을 수 있는 칸 중 M개를 뽑는 경우의 수 각각에 대하여 BFS로 시간을 체크한다.
 * # 시간 체크는 싸이클을 몇번 돌았는지로 체크하고
 * # 모든 칸에 바이러스가 퍼졌는지 체크하기 위해 바이러스가 퍼질때마다 칸의 개수를 센다.
 * @comment #
 */

import java.util.*;
import java.io.*;

public class BOJ_17141 {

    static class Pair {
        int r, c;

        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int N, M, ans, all, twoCounts;
    static int[][] map;
    static boolean[][] visited;
    static Pair[] virus, selectedVirus;

    static int[] dr = {-1, 0, 1, 0}; // 상우하좌 시계방향
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException, NumberFormatException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        // init
        ans = Integer.MAX_VALUE;
        map = new int[N][N];
        virus = new Pair[10]; // 최대 10개
        selectedVirus = new Pair[M];
        all = M; // 벽, 바이러스를 놓은 칸을 제외한 모든 칸 수

        int wallCounts = 0; // 벽 개수
        int index = 0; // 바이러스를 놓을 수 있는 칸을 배열에 담기 위한 인덱스
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if (map[r][c] == 2) {
                    virus[index++] = new Pair(r, c);
                } else if (map[r][c] == 1) {
                    wallCounts++;
                }
            }
        }
        all += wallCounts;
        all = N * N - all;
        twoCounts = index; // 2 개수

        solve();

        // print
        if (ans == Integer.MAX_VALUE) { // 모든 경우에도 모든 칸에 바이러스를 퍼뜨릴 수 없었던 경우
            System.out.println(-1);
        } else {
            System.out.println(ans);
        }
    }

    static void solve() {
        // 맵의 2 개수 중 M개를 뽑는다.
        nCr(0, 0);
    }

    static void nCr(int cnt, int start) {
        if (cnt == M) {
            if (bfs() != -1) {
                ans = Math.min(ans, bfs());
            }
            return;
        }

        for (int i = start; i < twoCounts; i++) {
            selectedVirus[cnt] = virus[i];
            nCr(cnt + 1, i + 1);
        }
    }

    static int bfs() {
        // init
        visited = new boolean[N][N];
        Queue<Pair> q = new LinkedList<>();

        // queue에 뽑은 M개의 바이러스 좌표를 넣는다.
        for (Pair p : selectedVirus) {
            q.offer(p);
            visited[p.r][p.c] = true;
        }

        int time = 0;
        int counts = 0; // 바이러스가 퍼진 칸의 개수
        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                Pair cur = q.poll();

                for (int d = 0; d < 4; d++) {
                    int nr = cur.r + dr[d];
                    int nc = cur.c + dc[d];

                    if (isOut(nr, nc)) continue; // 경계 체크
                    if (!visited[nr][nc] && map[nr][nc] != 1) { // 방문하지 않았고 벽이 아니면
                        q.offer(new Pair(nr, nc));
                        visited[nr][nc] = true;
                        counts++;
                    }
                }
            }
            time++;
        }

        // 모든 칸에 바이러스를 퍼뜨렸는지 체크
        if (all == counts) {
            return time - 1;
        }

        return -1;
    }

    static boolean isOut(int r, int c) {
        return (r < 0 || r >= N || c < 0 || c >= N);
    }

}

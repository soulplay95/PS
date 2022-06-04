package algorithm.graph_search;

/**
 * @문제 미로 탐색 [S1]
 * @날짜 220604
 * @분류 그래프 탐색 / BFS
 * @조건
 * 2 <= N, M <= 100
 * @풀이
 * # BFS
 * @comments
 * # 정답의 최대치: Integer => < max N^2
 * # 시간 복잡도: O(N^2)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_2178 {

    static StringBuilder sb = new StringBuilder();

    static class Pair {
        int r, c;

        Pair(int _r, int _c) {
            r = _r;
            c = _c;
        }
    }

    static int N, M;
    static char[][] map; // 방문 표시: 2

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
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        // init
        map = new char[N][M];

        for (int r = 0; r < N; r++) {
            map[r] = br.readLine().toCharArray();
        }
    }

    static void solve() {
        // print
        System.out.println(bfs());
    }

    static int bfs() {
        // init
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(0, 0));
        map[0][0] = '2';
        int moves = 1;

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                Pair cur = q.poll();

                // 정답 체크
                if (cur.r == N - 1 && cur.c == M - 1) {
                    return moves;
                }

                for (int d = 0; d < 4; d++) {
                    int nr = cur.r + dr[d];
                    int nc = cur.c + dc[d];

                    if (isOut(nr, nc)) continue;
                    if (map[nr][nc] == '1') {
                        q.offer(new Pair(nr, nc));
                        map[nr][nc] = '2';
                    }
                }
            }

            moves++;
        }

        return moves;
    }

    static boolean isOut(int r, int c) {
        return (r < 0 || r >= N || c < 0 || c >= M);
    }

}

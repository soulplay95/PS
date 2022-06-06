package algorithm.graph_search.practice;

/**
 * @문제 나이트의 이동 [S1]
 * @날짜 220606
 * @분류 그래프 탐색
 * @조건
 * 4 <= I <= 300
 * @풀이
 * # BFS
 * @comments
 * # 정답의 최대치: Integer => < max N^2
 * # 시간 복잡도: O(N)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_7562 {

    static StringBuilder sb = new StringBuilder();

    static class Pair {
        int r, c;

        Pair(int _r, int _c) {
            r = _r;
            c = _c;
        }
    }

    static int N;
    static Pair start, end;

    static int[] dr = {-2, -1, 1, 2, 2, 1, -1, -2}; // 1시 부터 시계방향
    static int[] dc = {1, 2, 2, 1, -1, -2, -2, -1};

    public static void main(String[] args) throws IOException {
        input();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int TC = Integer.parseInt(br.readLine());
        while (TC-- > 0) {
            N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine(), " ");
            start = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            st = new StringTokenizer(br.readLine(), " ");
            end = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

            sb.append(bfs()).append('\n');
        }

        // print
        System.out.println(sb.toString());
    }

    static int bfs() {
        Queue<Pair> q = new LinkedList<>();
        q.offer(start);
        boolean[][] visited = new boolean[N][N];
        visited[start.r][start.c] = true;
        int moves = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                Pair cur = q.poll();

                if (cur.r == end.r && cur.c == end.c) {
                    return moves;
                }

                for (int d = 0; d < 8; d++) {
                    int nr = cur.r + dr[d];
                    int nc = cur.c + dc[d];

                    if (isOut(nr, nc)) continue;
                    if (visited[nr][nc]) continue;

                    q.offer(new Pair(nr, nc));
                    visited[nr][nc] = true;
                }
            }

            moves++;
        }

        return moves;
    }

    static boolean isOut(int r, int c) {
        return (r < 0 || r >= N || c < 0 || c >= N);
    }

}

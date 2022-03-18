/**
 * @문제
 * @날짜 220318
 * @분류
 * @조건
 * #
 * @풀이
 * #
 * @comments
 * # 정답의 최대치:
 * # 시간 복잡도:
 * # 공간 복잡도:
 * https://programmers.co.kr/learn/courses/30/lessons/1829?language=java
 */

import java.util.*;

public class PROG_1829 {

    static class Pair {
        int r, c;

        Pair (int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static boolean[][] visited;
    static int[] dr = {-1, 0, 1, 0}; // 상우하좌 시계방향
    static int[] dc = {0, 1, 0, -1};

    static int bfs(int r, int c, int[][] picture, int m, int n) {
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(r, c));
        visited[r][c] = true;
        int color = picture[r][c];
        int size = 1;

        while (!q.isEmpty()) {
            Pair cur = q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];

                if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                    continue;
                }
                if (visited[nr][nc]) {
                    continue;
                }

                if (picture[nr][nc] == color) {
                    q.offer(new Pair(nr, nc));
                    size++;
                    visited[nr][nc] = true;
                }
            }
        }

        return size;
    }

    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        visited = new boolean[m][n];

        // 방문하지 않은 칸에서 BFS 탐색하여, 영역의 크기를 구한다.
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (!visited[r][c] && picture[r][c] > 0) {
                    maxSizeOfOneArea = Math.max(maxSizeOfOneArea, bfs(r, c, picture, m, n));
                    numberOfArea++;
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }

}

package programmers; /**
 * @문제
 * @날짜 220419
 * @분류
 * @조건
 * #
 * @풀이
 * #
 * @comments
 * # 정답의 최대치:
 * # 시간 복잡도:
 * # 공간 복잡도:
 */

import java.util.*;

public class PROG_1844 {

    static class Pair {
        int r, c;

        Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    // BFS
    public int solution(int[][] maps) {
        // init
        int R = maps.length;
        int C = maps[0].length;
        int[] dr = {-1, 0, 1, 0}; // 상우하좌 시계방향
        int[] dc = {0, 1, 0, -1};
        Queue<Pair> q = new LinkedList<>();
        int moves = 1;

        q.offer(new Pair(0, 0));
        maps[0][0] = 2; // 방문 표시

        while (!q.isEmpty()) {
            int size = q.size();

            // 한 싸이클씩 이동
            while (size-- > 0) {
                Pair cur = q.poll();

                // 종료 체크
                if (cur.r == R - 1 && cur.c == C - 1) {
                    return moves;
                }

                for (int d = 0; d < 4; d++) {
                    int nr = cur.r + dr[d];
                    int nc = cur.c + dc[d];

                    if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue; // 경계 체크
                    if (maps[nr][nc] == 1) {
                        q.offer(new Pair(nr, nc));
                        maps[nr][nc] = 2;
                    }
                }
            }

            moves++;
        }

        return -1;
    }
}

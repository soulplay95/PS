package study.w220502;

/**
 * @문제
 * @날짜 220426
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

public class PROG_67259 {

    static class Grid {
        int r, c, d; // d: 0 => 세로 방향, 1 => 가로 방향

        public Grid(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }

    static int[] dr = {-1, 0, 1, 0}; // 상우하좌. 시계방향
    static int[] dc = {0, 1, 0, -1};

    // BFS + DP
    // 코너인지 여부를 확인하기 위해 어느 방향으로부터 해당 좌표에 도달했는지 정보를 저장한다.
    // 이미 방문했던 좌표도 해당 좌표까지 도달하는데 비용이 저장된 비용보다 적다면 값을 갱신하고 탐색 진행
    public int solution(int[][] board) {
        // init
        int N = board.length;
        int[][][] dp = new int[N][N][2];
        Queue<Grid> q = new LinkedList<>();
        q.offer(new Grid(0, 0, 0));
        q.offer(new Grid(0, 0, 1));

        // BFS
        while (!q.isEmpty()) {
            Grid cur = q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];
                int nd = d % 2;
                int isVertical = (cur.d + d) % 2;

                if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue; // 경계 체크
                if (board[nr][nc] == 1) continue; // 벽 체크

                // 코너가 생기는지 여부를 판단하여 비용 계산
                int newCost = dp[cur.r][cur.c][cur.d] + (isVertical == 1 ? 600 : 100);

                // 최초 방문인지, 재방문이여도 저장된 값보다 작은지 체크
                if (dp[nr][nc][nd] == 0 || newCost < dp[nr][nc][nd]) {
                    dp[nr][nc][nd] = newCost;
                    q.offer(new Grid(nr, nc, nd));
                }
            }
        }

        int[] answer = dp[N - 1][N - 1];
        return Math.min(answer[0] == 0 ? Integer.MAX_VALUE : answer[0], answer[1] == 0 ? Integer.MAX_VALUE : answer[1]);
    }

}

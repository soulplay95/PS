package programmers; /**
 * @문제
 * @날짜 220418
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PROG_12905 {

    // dp[r][c] : (r, c)를 우측 하단으로 하여 정사각형을 그릴 때 가능한 한 변의 길이
    // dp[r][c] = min(dp[r - 1][c], dp[r][c - 1], dp[r - 1][c - 1])
    public int solution(int [][]board) {
        int R = board.length;
        int C = board[0].length;
        int max = board[0][0] == 0 ? 0 : 1;

        for (int r = 1; r < R; r++) {
            for (int c = 1; c < C; c++) {
                if (board[r][c] == 0) continue;
                board[r][c] = Math.min(Math.min(board[r - 1][c], board[r][c - 1]), board[r - 1][c - 1]) + 1;
                max = Math.max(max, board[r][c]);
            }
        }

        return max * max;
    }

}

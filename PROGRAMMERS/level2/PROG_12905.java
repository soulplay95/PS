package programmers.level2;

class PROG_12905 {

    // 완전 탐색: O(N^3) => 10억
    // DP
    // - dp[r][c] := (r, c)를 우측 하단으로 했을 때 그릴 수 있는 정사각형 한 변의 길이
    // dp[r][c] = min(dp[r - 1][c], dp[r][c - 1], dp[r - 1][c - 1]) + 1
    public int solution(int [][]board) {
        int R = board.length;
        int C = board[0].length;

        // R, C가 1일때 max값 초기화
        int max = board[0][0] == 1 ? 1 : 0;

        // 점화식을 토대로 dp 채우고 최대값 갱신
        for (int r = 1; r < R; r++) {
            for (int c = 1; c < C; c++) {
                if (board[r][c] == 0) continue;

                board[r][c] = Math.min(board[r - 1][c - 1], Math.min(board[r - 1][c], board[r][c - 1])) + 1;
                max = Math.max(max, board[r][c]);
            }
        }

        return max * max;
    }

}

/*
class Solution {
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
}*/

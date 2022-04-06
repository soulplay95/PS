/**
 * @문제
 * @날짜 220406
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

public class PROG_12913 {

    // dp[i][j] : dp[i - 1][!j] 중 최대 값
    int solution(int[][] land) {
        int R = land.length;

        int r = 1;
        for (; r < R; r++) {
            land[r][0] += Math.max(Math.max(land[r - 1][1], land[r - 1][2]), land[r - 1][3]);
            land[r][1] += Math.max(Math.max(land[r - 1][0], land[r - 1][2]), land[r - 1][3]);
            land[r][2] += Math.max(Math.max(land[r - 1][0], land[r - 1][1]), land[r - 1][3]);
            land[r][3] += Math.max(Math.max(land[r - 1][0], land[r - 1][1]), land[r - 1][2]);
        }
        r--;

        return Math.max(Math.max(land[r][0], land[r][1]), Math.max(land[r][2], land[r][3]));
    }

}

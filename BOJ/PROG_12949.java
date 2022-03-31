/**
 * @문제
 * @날짜 220331
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

public class PROG_12949 {

    // 정답의 최대치: 결과 행렬의 한 원소값의 최대치는 20*20*100 = 40000 <= Integer
    // 시간 복잡도: 행의 개수를 m, 열의 개수를 n이라 할 때 O(m^2 * n) => 최대 100^3
    public int[][] solution(int[][] arr1, int[][] arr2) {
        int R = arr1.length; // 행의 개수
        int C = arr2[0].length; // 열의 개수
        int[][] answer = new int[R][C];

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                int value = 0;

                // arr1의 r번째 행과 arr2의 c번째 열의 스칼라곱
                for (int i = 0; i < arr1[0].length; i++) {
                    value += arr1[r][i] * arr2[i][c];
                }

                answer[r][c] = value;
            }
        }

        return answer;
    }

}

/**
 * @문제
 * @날짜 220329
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

public class PROG_17681 {

    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];

        // OR 연산
        for (int r = 0; r < n; r++) {
            String overlap = Integer.toBinaryString(arr1[r] | arr2[r]);

            // 파싱
            int gap = n - overlap.length();
            String temp = "";
            for (int i = 0; i < gap; i++) {
                temp += "0";
            }

            overlap = temp + overlap;
            overlap = overlap.replace('0', ' ');
            overlap = overlap.replace('1', '#');

            answer[r] = overlap;
        }

        return answer;
    }

}

/**
 * @문제 OX퀴즈_B2
 * @날짜 220112
 * @분류 구현, 문자열
 * @조건
 * # 문자열 길이 < 80
 * @풀이
 * # O나오면 카운팅
 * @comment
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_8958 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            String input = br.readLine();

            // print
            System.out.println(solve(input));
        }
    }

    private static int solve(String s) {
        int sum = 0;
        int circleCounts = 0;

        for (int i = 0, end = s.length(); i < end; i++) {
            char c = s.charAt(i);

            if (c == 'X') {
                circleCounts = 0;
            } else {
                circleCounts++;

                sum += circleCounts;
            }
        }

        return sum;
    }

}

/**
 * @문제
 * @날짜 220324
 * @분류
 * @조건
 * #
 * @풀이
 * #
 * @comments
 * # 정답의 최대치:
 * # 시간 복잡도:
 * # 공간 복잡도:
 * https://programmers.co.kr/learn/courses/30/lessons/12940
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PROG_12940 {

    static int getGCD(int a, int b) {
        int r = a % b;

        if (r == 0) {
            return b;
        } else {
            return getGCD(b, r);
        }
    }

    public int[] solution(int n, int m) {
        int gcd;
        long lcm;

        if (n > m) {
            gcd = getGCD(n, m);
        } else {
            gcd = getGCD(m, n);
        }

        lcm = (long)n * (long)m / gcd;

        int[] answer = new int[2];
        answer[0] = gcd;
        answer[1] = (int)lcm;

        return answer;
    }

}

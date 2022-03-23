/**
 * @문제
 * @날짜 220323
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

public class PROG_12947 {

    public boolean solution(int x) {
        int digitSum = x % 10;
        int copyX = x / 10;

        while (copyX != 0) {
            digitSum += copyX % 10;
            copyX /= 10;
        }

        if (x % digitSum == 0) {
            return true;
        }

        return false;
    }
}

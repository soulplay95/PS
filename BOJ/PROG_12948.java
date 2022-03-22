/**
 * @문제
 * @날짜 220322
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

public class PROG_12948 {

    public String solution(String phone_number) {
        int end = phone_number.length() - 4;
        StringBuilder sb = new StringBuilder(phone_number);

        for (int i = 0; i < end; i++) {
            sb.setCharAt(i, '*');
        }

        return sb.toString();
    }

}

/**
 * @문제
 * @날짜 220326
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

public class PROG_12930 {

    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        String[] words = s.split("");
        int length = 0;

        for (int i = 0; i < words.length; i++) {
            String ns = words[i];

            if (" ".equals(ns)) {
                length = 0;
                sb.append(" ");
            } else {
                if (length % 2 == 0) {
                    sb.append(ns.toUpperCase());
                } else {
                    sb.append(ns.toLowerCase());
                }
                length++;
            }
        }

        return sb.toString();
    }

}

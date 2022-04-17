/**
 * @문제
 * @날짜 220417
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

public class PROG_12899 {

    // 10진법 -> 3진법
    // 0, 1, 2를 1, 2, 4로 매핑
    public String solution(int n) {
        String[] mappedNumbers = {"4", "1", "2"};
        StringBuilder sb = new StringBuilder();

        while (n > 0) {
            int mod = n % 3;
            sb.append(mappedNumbers[mod]);

            n /= 3;
            if (mod == 0) {
                n--;
            }
        }

        return sb.reverse().toString();
    }

}

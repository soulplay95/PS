/**
 * @문제 음양 더하기_Lv.1
 * @날짜 220317
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

public class PROG_76501 {
    public int solution(int[] absolutes, boolean[] signs) {
        int answer = 0;
        int length = absolutes.length;

        for (int i = 0; i < length; i++) {
            answer += signs[i] ? absolutes[i] : -absolutes[i];
        }

        return answer;
    }
}

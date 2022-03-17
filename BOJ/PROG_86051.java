/**
 * @문제 없는 숫자 더하기_Lv.1
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

public class PROG_86051 {

    public int solution(int[] numbers) {
        int answer = 0;
        boolean[] used = new boolean[10];

        // 찾을 수 없는 숫자 찾기
        for (int num : numbers) {
            used[num] = true;
        }

        // 사용되지 않은 숫자 더하기
        for (int i = 1; i <= 9; i++) {
            answer += used[i] ? 0 : i;
        }

        return answer;
    }

}

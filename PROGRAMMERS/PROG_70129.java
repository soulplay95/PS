package programmers; /**
 * @문제
 * @날짜 220504
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

import java.util.*;

public class PROG_70129 {

    // 1. 모든 0을 제거 => replaceAll()
    // 2. 2진법으로 표현한 문자열 => Integer.toBinaryString()
    public int[] solution(String s) {
        // init
        int[] answer = new int[2];
        String targetString = "1";

        while (!s.equals(targetString)) {
            String newZeroDeletedString = s.replaceAll("0", ""); // 모든 0을 제거
            answer[1] += s.length() - newZeroDeletedString.length(); // 제거된 0 개수 누적

            s = Integer.toBinaryString(newZeroDeletedString.length()); // 변환

            answer[0]++; // 변환 횟수 1증가
        }

        return answer;
    }

}

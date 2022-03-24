/**
 * @문제
 * @날짜 220325
 * @분류
 * @조건
 * #
 * @풀이
 * #
 * @comments
 * # 정답의 최대치:
 * # 시간 복잡도:
 * # 공간 복잡도:
 * https://programmers.co.kr/learn/courses/30/lessons/12917
 */

import java.util.*;

public class PROG_12917 {

    public String solution(String s) {
        char[] charArray = s.toCharArray();
        Arrays.sort(charArray); // 오름차순 정렬
        StringBuilder sb = new StringBuilder(String.valueOf(charArray));

        return sb.reverse().toString();
    }
}

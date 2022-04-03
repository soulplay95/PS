/**
 * @문제
 * @날짜 220403
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

public class PROG_12939 {

    // 오름차순 정렬하여 처음과 끝 원소를 리턴한다. => O(NlogN)
    public String solution(String s) {
        String[] split = s.split(" ");
        int[] numbers = new int[split.length];

        // 문자열을 파싱하여 숫자들을 int[] 배열에 넣는다.
        for (int i = 0; i < split.length; i++) {
            int num = Integer.parseInt(split[i]);
            numbers[i] = num;
        }

        // 오름차순 정렬
        Arrays.sort(numbers);

        // 정답 세팅
        StringBuilder sb = new StringBuilder();
        sb.append(numbers[0]);
        sb.append(" ");
        sb.append(numbers[numbers.length - 1]);

        return sb.toString();
    }

}

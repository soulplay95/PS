/**
 * @문제 가장 큰 수
 * @날짜 220130
 * @분류 정렬
 * @조건
 * # 1 <= 배열 길이 <= 10만
 * @풀이
 * # 사전순 내림차순 정렬
 * @comment
 * # https://programmers.co.kr/learn/courses/30/lessons/42746
 */

import java.util.*;

public class PROG_42746 {

    public String solution(int[] numbers) {
        String[] stringNumbers = new String[numbers.length];
        for (int i = 0, end = numbers.length; i < end; i++) {
            stringNumbers[i] = String.valueOf(numbers[i]);
        }

        Arrays.sort(stringNumbers, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o2 + o1).compareTo(o1 + o2);
            }
        });

        if (stringNumbers[0].equals("0")) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        for (String s : stringNumbers) {
            sb.append(s);
        }

        return sb.toString();
    }

}

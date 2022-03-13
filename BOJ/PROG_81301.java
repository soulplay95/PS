/**
 * @문제 숫자 문자열과 영단어_Lv.1
 * @날짜 220313
 * @분류
 * @조건
 * #
 * @풀이
 * #
 * @comments
 * # 정답의 최대치:
 * # 시간 복잡도:
 * # 공간 복잡도:
 * replaceAll(기존, 새로운)
 */

import java.io.*;
import java.util.*;

public class PROG_81301 {

    public static void main(String[] args) {
        System.out.println(solution("one4seveneight"));
    }

    public static int solution(String s) {
        HashMap<String, String> numbers = new HashMap<String, String>(); // key: 영단어, value: 숫자
        StringBuilder answer = new StringBuilder();
        StringBuilder temp = new StringBuilder();

        // init
        numbers.put("zero", "0");
        numbers.put("one", "1");
        numbers.put("two", "2");
        numbers.put("three", "3");
        numbers.put("four", "4");
        numbers.put("five", "5");
        numbers.put("six", "6");
        numbers.put("seven", "7");
        numbers.put("eight", "8");
        numbers.put("nine", "9");

        // 문자를 하나씩 보면서 '0' ~ '9' 범위에 없으면 이어지는 문자들을 하나씩 붙여보면서 매핑되는 숫자가 있는지 체크한다.
        int index = 0;
        while (index < s.length()) {
            char c = s.charAt(index);

            if (c >= '0' && c <= '9') { // 숫자이면
                answer.append(c);
            } else {
                while (true) {
                    temp.append(s.charAt(index));

                    // 매핑되는 영단어가 있는지 체크
                    if (numbers.containsKey(temp.toString())) {
                        answer.append(numbers.get(temp.toString()));
                        break;
                    }

                    index++;
                }
                temp.setLength(0);
            }

            index++;
        }

        return Integer.parseInt(answer.toString());
    }

}

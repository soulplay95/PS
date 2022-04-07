/**
 * @문제
 * @날짜 220407
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

public class PROG_12909 {

    // Stack
    boolean solution(String s) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                // stack에 넣는다.
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                if (stack.pop() == ')') { // 짝이 안맞으면
                    return false;
                }
            }
        }

        if (!stack.isEmpty()) { // '('가 남아있으면
            return false;
        }

        return true;
    }

}

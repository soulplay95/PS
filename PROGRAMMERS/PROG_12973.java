package programmers; /**
 * @문제
 * @날짜 220420
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

public class PROG_12973 {

    public int solution(String s)
    {
        Stack<Character> stack = new Stack<>();

        // O(N)
        for (char c : s.toCharArray()) {
            if (stack.isEmpty()) {
                stack.push(c);
            } else {
                char top = stack.peek();
                if (c == top) { // 짝이면
                    stack.pop();
                } else {
                    stack.push(c);
                }
            }
        }

        if (stack.isEmpty()) {
            return 1;
        }

        return 0;
    }

}

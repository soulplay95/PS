package programmers.level2;

import java.util.*;

class PROG_12909 {
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

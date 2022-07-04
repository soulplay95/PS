package programmers.level2;

import java.util.*;

class PROG_12973
{

    // O(N)
    public int solution(String s)
    {
        Stack<Character> stack = new Stack<>();

        // 문자열의 앞에서부터 stack에 넣고 빼는 과정을 통해 짝을 제거한다.
        for (char c : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek() == c) {
                stack.pop();
                continue;
            }
            stack.push(c);
        }

        if (stack.isEmpty()) {
            return 1;
        }

        return 0;
    }
}

/*class Solution
{
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
}*/

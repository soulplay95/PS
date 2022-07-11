package programmers.level2;

import java.util.*;

class PROG_76502 {

    // O(N^2)
    public int solution(String s) {
        // init
        int answer = 0;

        // s를 왼쪽으로 x칸 회전한다.
        for (int x = 0; x < s.length(); x++) {
            String rotatedString = s.substring(x) + s.substring(0, x);

            if (isValidate(rotatedString)) {
                answer++;
            }
        }

        return answer;
    }

    static boolean isValidate(String s) {
        // Stack을 활용해 올바른 괄호 문자열인지 판단한다.
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') { // 여는 괄호이면
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }

                char top = stack.peek();
                if (c == ')') {
                    if (top != '(') {
                        return false;
                    }
                } else if (c == '}') {
                    if (top != '{') {
                        return false;
                    }
                } else if (c == ']') {
                    if (top != '[') {
                        return false;
                    }
                }
                stack.pop();
            }
        }

        if (!stack.isEmpty()) {
            return false;
        }

        return true;
    }

}

/*
class Solution {
    static boolean isValidate(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') { // 여는 괄호이면
                stack.push(c);
            } else { // 닫는 괄호이면
                if (stack.isEmpty()) {
                    return false;
                }

                char top = stack.peek();
                if (c == ')') {
                    if (top != '(') {
                        return false;
                    }
                } else if (c == ']') {
                    if (top != '[') {
                        return false;
                    }
                } else if (c == '}') {
                    if (top != '{') {
                        return false;
                    }
                }
                stack.pop();
            }
        }

        if (!stack.isEmpty()) {
            return false;
        }

        return true;
    }

    // 회전 시킨 괄호 문자열에 대해 스택으로 올바른 괄호 문자열인지 여부를 체크한다.
    // O(N^2)
    public int solution(String s) {
        int answer = 0;
        int N = s.length();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            // 왼쪽으로 i번 회전시킨 괄호 문자열 만들기
            sb.append(s.substring(i));
            sb.append(s.substring(0, i));

            // 올바른 괄호 문자열인지 체크
            if (isValidate(sb.toString())) {
                answer++;
            }

            sb.setLength(0);
        }

        return answer;
    }
}*/

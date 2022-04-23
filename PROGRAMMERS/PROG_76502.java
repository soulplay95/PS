package programmers; /**
 * @문제
 * @날짜 220423
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

public class PROG_76502 {

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

}

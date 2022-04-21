package programmers; /**
 * @문제
 * @날짜 220421
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

public class PROG_60058 {

    static String solve(String w) {
        // 1. 빈 문자열인 경우 그대로 반환
        if ("".equals(w)) {
            return "";
        }

        // 2. w를 u, v로 분리
        // w의 앞에서부터 괄호 개수 짝이 맞게 u를 생성한다.
        Stack<Character> stack = new Stack<>();
        stack.push(w.charAt(0));
        int i = 1;
        boolean isValidate = true; // u가 올바른 문자열이면 true
        for (; i < w.length(); i++) {
            if (stack.isEmpty()) {
                break;
            }

            char top = stack.peek();
            char c = w.charAt(i);

            if (top != c) { // 다른 괄호이면
                stack.pop();
                if (top == ')') {
                    isValidate = false; // 올바른 배치가 아님
                }
            } else { // 같은 괄호이면
                stack.push(c);
            }
        }

        String u = w.substring(0, i);
        String v = w.substring(i);

        // 3. u가 올바른 괄호 문자열인지 여부에 따라 분기
        if (isValidate) {
            return u + solve(v);
        } else {
            // 4
            StringBuilder sb = new StringBuilder("(");
            sb.append(solve(v));
            sb.append(")");

            // u 문자열의 양 끝 문자를 제거하고 괄호 방향을 뒤집어서 붙임
            for (int j = 1; j < u.length() - 1; j++) {
                if (u.charAt(j) == '(') {
                    sb.append(')');
                } else {
                    sb.append('(');
                }
            }

            return sb.toString();
        }
    }

    public String solution(String p) {
        return solve(p);
    }

}

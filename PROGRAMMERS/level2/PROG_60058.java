package programmers.level2;

import java.util.*;

class PROG_60058 {
    public String solution(String p) {
        // 1. 빈 문자열인 경우 그대로 반환
        if (p == null || p.isEmpty()) {
            return p;
        }

        // 2. u, v로 분리
        int splitIndex = split(p);
        String u = p.substring(0, splitIndex);
        String v = p.substring(splitIndex);

        if (isValidate(u)) {
            // 3. v에 대해 재귀 호출하여 u에 이어 붙여 반환
            return u + solution(v);
        } else {
            // 4. 새로운 문자열 반환
            return "(" + solution(v) + ")" + reverse(u);
        }
    }

    static int split(String w) {
        int leftBracketCounts = 0;
        int rightBracketCounts = 0;
        int i = 0;

        for (; i < w.length(); i++) {
            if (leftBracketCounts != 0 && (leftBracketCounts == rightBracketCounts)) {
                return i;
            }

            if (w.charAt(i) == '(') {
                leftBracketCounts++;
            } else {
                rightBracketCounts++;
            }
        }

        return i;
    }

    static boolean isValidate(String u) {
        Stack<Character> stack = new Stack<>();

        for (char c : u.toCharArray()) {
            if (stack.isEmpty()) {
                stack.push(c);
            } else {
                char top = stack.peek();
                if (top == '(' && c == ')') {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            }
        }

        if (stack.isEmpty()) {
            return true;
        }

        return false;
    }

    static String reverse(String u) {
        StringBuilder sb = new StringBuilder();

        // 4-4. 처음과 끝 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집는다.
        for (int i = 1; i < u.length() - 1; i++) {
            char c = u.charAt(i);
            if (c == '(') {
                sb.append(')');
            } else {
                sb.append('(');
            }
        }

        return sb.toString();
    }
}

/*
class Solution {
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
}*/

/**
 * @문제 군형잡힌 세상_S4
 * @날짜 210906
 * @분류 자료 구조, 문자열, 스택
 * @조건
 *
 * @풀이
 * # 스택
 * @comment
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_4949 {
	
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	// -----------------------------------------------------------------------------

	public static void main(String[] args) throws IOException {
		while (true) {
			String input = br.readLine();
			if (".".equals(input)) {
				break;
			}

			if (solve(input)) {
				sb.append("yes").append("\n");
			} else {
				sb.append("no").append("\n");
			}
		}

		System.out.println(sb.toString());
	}
	
	static boolean solve(String input) {
		Stack<Character> stack = new Stack<>();

		for (int i = 0, end = input.length(); i < end; i++) {
			char c = input.charAt(i);

			if (c == '(' || c == '[') {
				stack.push(c);
			} else if (c == ')' || c == ']') {
				if (!stack.empty()) {
					char temp = stack.peek();
					if ((c == ')' && temp == '(') || (c == ']' && temp == '[')) {
						stack.pop();
					} else {
						return false;
					}
				} else {
					return false;
				}
			}
		}

		if (stack.empty()) {
			return true;
		}

		return false;
	}

}

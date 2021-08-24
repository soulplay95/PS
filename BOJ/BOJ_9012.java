/**
 * @문제 괄호_S4
 * @날짜 210824
 * @분류 
 * @조건
 * # 포함, 접합
 * # 2 <= 괄호 문자열 길이 <= 50
 * @풀이
 * # Stack
 * # ( => 스택에 push, ) => 스택에서 pop
 * @comment
 * # 문자열 길이 : String.length()
 * # Stack<T> stack = new Stack<>();
 * # push, pop, peek
 * # 스택이 비어있을 때 pop하는 것 조심
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_9012 {

	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			solve(br.readLine());
		}

		// print
		System.out.println(sb.toString());
	}

	static void solve(String str) {
		// 1. 스택 생성
		Stack<Character> stack = new Stack<>();

		// 2. 문자 하나씩 보면서 스택에 push or pop
		for (int i = 0, end = str.length(); i < end; i++) {
			char c = str.charAt(i);

			if (c == '(') {
				stack.push(c);
			} else {
				// pop 하려는데 스택이 비어있으면 NO 출력
				if (stack.empty()) {
					sb.append("NO").append("\n");
					return;
				}

				stack.pop();
			}
		}

		// 3. stack에 문자가 남아있으면 VPS가 아님.
		if (stack.empty()) {
			sb.append("YES").append("\n");
		} else {
			sb.append("NO").append("\n");
		}
	}

}

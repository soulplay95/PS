/**
 * @문제 좋은 단어_S4
 * @날짜 210902
 * @분류 자료 구조, 문자열, 스택
 * @조건
 * # 2 <= 단어 길이 <= 10만
 * @풀이
 * # 스택의 top이 현재 문자와 일치하지 않으면 push, 일치하면 pop
 * @comment
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_3986 {
	
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	// -----------------------------------------------------------------------------

	static int N, ans;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		// init
		ans = 0;
		for (int n = 0; n < N; n++) {
			char[] str = br.readLine().toCharArray();

			ans += solve(str);
		}
		
		// print
		System.out.println(ans);
	}
	
	static int solve(char[] str) {
		Stack<Character> stack = new Stack<>();

		for (int i = 0, end = str.length; i < end; i++) {
			char c = str[i];

			if (stack.isEmpty()) {
				stack.push(c);
			} else {
				if (stack.peek() == c) { // 일치하면 pop
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

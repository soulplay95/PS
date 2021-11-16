/**
 * @문제 계산기2_D4
 * @날짜 211116
 * @분류 
 * @조건
 * #
 * @풀이
 * #
 * @comment
 * #
 */

import java.util.*;
import java.io.*;


public class SWEA_1223 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = 10;
		for (int t = 1; t <= T; t++) {
			int n = Integer.parseInt(br.readLine());
			String s = br.readLine();
			char[] c = s.toCharArray();

			// logic
			int result = 0;
			// 후위표기식으로 바꾸기
			// ascii : * -> 52, + -> 53
			Stack<Character> stack = new Stack<>();
			Queue<Character> queue = new LinkedList<Character>();
			for (int i = 0; i < n; i++) {
				if (c[i] == '+' || c[i] == '*') {
					while(true) {
						if (stack.isEmpty()) {
							stack.push(c[i]);
							break;
						} else if (!stack.isEmpty() && stack.peek() <= c[i]) {
							queue.offer(stack.pop());
						} else if (!stack.isEmpty() && stack.peek() > c[i]) {
							stack.push(c[i]);
							break;
						}
					}
				} else {
					queue.offer(c[i]);
				}
			}
			int size = stack.size();
			for (int i = 0; i < size; i++) {
				queue.offer(stack.pop());
			}
			// 계산
			Stack<Integer> calStack = new Stack<>();
			for (int i = 0; i < n; i++) {
				char temp = queue.poll();
				if (temp == '*' || temp == '+') {
					int a = 0, b = 0;
					if (!calStack.isEmpty()) {
						b = calStack.pop();
					}
					if (!calStack.isEmpty()) {
						a = calStack.pop();
					}
					calStack.push((temp == '*') ? (a * b) : (a + b));
				} else {
					calStack.push(temp - '0');
				}
			}
			result = calStack.pop();

			// print
			System.out.println("#" + t + " " + result);
		}
	}

}

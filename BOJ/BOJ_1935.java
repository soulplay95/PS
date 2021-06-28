/**
 * @문제 후위 표기식2_S3
 * @날짜 210629
 * @분류 
 * @조건
 * 
 * @풀이
 * 
 * @comment
 * String.format
 */

import java.util.*;
import java.io.*;

public class BOJ_1935 {
	
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	// -----------------------------------------------------------------------------
	
	static int N;
	static String input; // 후위 표기식
	static int[] operands; // 피연산자
	static double ans;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		input = br.readLine();
		operands = new int[26];
		for (int n = 0; n < N; n++) {
			int i = Integer.parseInt(br.readLine());
			operands[n] = i;
		} // input end
		
		// print
		System.out.println(String.format("%.2f", solve()));
	}
	
	static double solve() {
		// init
		ans = 0.0;
		
		Stack<Double> stack = new Stack<>();
		for (int i = 0, end = input.length(); i < end; i++) {
			int n = input.charAt(i);
			
			// 피연산자이면
			if (n >= 'A' && n <= 'Z') {
				stack.push((double)operands[n - 'A']);
			} else { // 연산자이면
				switch (n) {
				case '+':
					stack.push(stack.pop() + stack.pop());
					break;
				case '-':
					stack.push(-stack.pop() + stack.pop());
					break;
				case '*':
					stack.push(stack.pop() * stack.pop());
					break;
				case '/':
					double a = stack.pop();
					double b = stack.pop();
					stack.push(b / a);
					break;
				}
			}
		}
		
		return stack.pop();
	}

}
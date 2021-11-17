/**
 * @문제 쇠막대기 자르기_D4
 * @날짜 211117
 * @분류 
 * @조건
 * #
 * @풀이
 * # 스택
 * @comment
 * #
 */

import java.io.*;
import java.util.*;

public class SWEA_5432 {

	private static int T;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			Stack<Character> stack = new Stack<>();
			char[] c = br.readLine().toCharArray();
			int pieces = 0;
			for (int i = 0; i < c.length; i++) {
				if (c[i] == '(') {
					stack.push(c[i]);
				} else if (c[i] == ')') {
					if (!stack.isEmpty() && c[i - 1] == '(') {
						stack.pop();
						pieces += stack.size();
					} else if (c[i - 1] == ')') {
						pieces += 1;
						stack.pop();
					}
				}
			}

			// print
			System.out.println("#" + t + " " + pieces);
		}
	}

}

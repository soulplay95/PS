/**
 * @문제 스택 수열_S3
 * @날짜 210830
 * @분류 자료 구조, 스택
 * @조건
 * # 1 <= N <= 10만
 * @풀이
 * # 수열의 주어진 수를 꺼내기 위해 스택을 조작
 * @comment
 * 
 */

import java.util.*;
import java.io.*;

public class BOJ_1874 {

	static int N, index;
	static Stack<Integer> stack;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		// init
		stack = new Stack<>();
		index = 1;
		sb = new StringBuilder();
		for (int n = 0; n < N; n++) {
			int num = Integer.parseInt(br.readLine());
			if (num >= index) {
				while (index <= num) {
					sb.append("+").append("\n");
					stack.push(index++);
				}

				sb.append("-").append("\n");
				stack.pop();
			} else {
				while (true) {
					if (stack.isEmpty()) {
						System.out.println("NO");
						System.exit(0);
					}

					int temp = stack.pop();
					sb.append("-").append("\n");

					if (temp == num) {
						break;
					}
				}
			}
		}

		// print
		System.out.println(sb.toString());
	}

}

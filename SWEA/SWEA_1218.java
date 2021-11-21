/**
 * @문제 [S/W 문제해결 기본] 4일차 - 괄호 짝짓기_D4
 * @날짜 211121
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

public class SWEA_1218 {

	static int T = 10;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int t = 1; t <= T; t++) {
			int n = Integer.parseInt(br.readLine()); // 테스트 케이스 길이
			char[] str = br.readLine().toCharArray();
			Stack<Character> stack = new Stack<>();
			HashMap<Character, Character> map = new HashMap<>();
			map.put('(', ')');
			map.put('{', '}');
			map.put('[', ']');
			map.put('<', '>');

			// logic
			boolean possible = true;
			for (int i = 0; i < n; i++) {
				if (map.containsKey(str[i])) { // 여는거면
					stack.push(str[i]);
				} else { // 닫는거면
					if (map.get(stack.pop()) != str[i]) {
						possible = false;
						break;
					}
				}
			}

			// print
			System.out.println("#" + t + " " + (possible ? 1 : 0));
		}
	}

}

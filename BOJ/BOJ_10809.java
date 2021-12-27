/**
 * @문제 알파벳 찾기_B2
 * @날짜 211227
 * @분류 구현, 문자열
 * @조건
 * # 단어 길이 <= 100
 * @풀이
 * # String.indexOf
 * @comment
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_10809 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String input = br.readLine();

		for (int i = 0; i < 26; i++) {
			char c = (char) ('a' + i);
			sb.append(input.indexOf(c)).append(" ");
		}

		// print
		System.out.println(sb.toString());
	}

}

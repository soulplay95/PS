/**
 * @문제 소트인사이드_S5
 * @날짜 211223
 * @분류 문자열, 정렬
 * @조건
 * # 주어진 수 (N) <= 10억
 * @풀이
 * # 자리수로 끊어서 배열에 넣고 내림차순 정렬
 * @comment
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_1427 {

	private static String input;
	private static Integer[] digits;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input = br.readLine();
		// init
		digits = new Integer[input.length()];

		split();

		// print
		System.out.println(sb.toString());
	}

	private static void split() {
		// 각 자리수를 쪼개서 배열에 넣는다.
		for (int i = 0, end = input.length(); i < end; i++) {
			digits[i] = input.charAt(i) - '0';
		}

		// 내림차순 정렬
		Arrays.sort(digits, Collections.reverseOrder());

		// append
		for (Integer i : digits) {
			sb.append(i);
		}
	}

}

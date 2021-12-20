/**
 * @문제 단어의 개수_B2
 * @날짜 211220
 * @분류 구현, 문자열
 * @조건
 * # 문자열의 길이 <= 100만
 * @풀이
 * # 단어 개수 : 공백의 개수 + 1
 * # 시작과 끝이 공백이면 제외
 * @comment
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_1152 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();

		// print
		System.out.println(solve(input));
	}

	private static int solve(String input) {
		// 공백 하나만 있는 경우
		if (input.length() == 1 && input.charAt(0) == ' ') {
			return 0;
		}

		int blackCounts = 0; // 공백 개수
		int bothEndsBlankCounts = 0; // 양쪽 끝(시작과 끝)의 공백 개수
		if (input.charAt(0) == ' ') { // 시작이 공백인 경우
			bothEndsBlankCounts++;
		}
		if (input.length() != 1 && input.charAt(input.length() - 1) == ' ') { // 끝이 공백인 경우
			bothEndsBlankCounts++;
		}

		blackCounts = input.length() - input.replace(String.valueOf(" "), "").length();

		return blackCounts + 1 - bothEndsBlankCounts;
	}

}

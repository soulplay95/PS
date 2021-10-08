/**
 * @문제 모음의 개수_B2
 * @날짜 211008
 * @분류 구현, 문자열
 * @조건
 * # 각 테스트케이스의 입력 글자 <= 255
 * @풀이
 * # 정규표현식
 * @comment
 * #
 */

import java.util.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BOJ_1264 {

	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	// -----------------------------------------------------------------------------

	public static void main(String[] args) throws IOException {
		Pattern vowel = Pattern.compile("[aeiou]");
		String input = "";

		while (!"#".equals(input = br.readLine())) {
			input = input.toLowerCase();
			Matcher matcher = vowel.matcher(input);
			int counts = 0;

			while (matcher.find()) {
				counts++;
			}

			System.out.println(counts);
		}
	}

}

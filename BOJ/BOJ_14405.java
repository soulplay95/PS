/**
 * @문제 피카츄_S5
 * @날짜 211013
 * @분류 문자열
 * @조건
 * # 문자열 길이 <= 5000
 * @풀이
 * #
 * @comment
 * #
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BOJ_14405 {
	
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	// -----------------------------------------------------------------------------

	public static void main(String[] args) throws IOException {
		String input = br.readLine();

		Pattern pattern = Pattern.compile("(pi|ka|chu)*");
		Matcher matcher = pattern.matcher(input);

		// print
		System.out.println(matcher.matches() ? "YES" : "NO");
	}

}

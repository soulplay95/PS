package study.algo210802;

/**
 * @문제 회문은 회문아니야!!_G5
 * @날짜 210731
 * @분류 문자열, 애드 혹
 * @조건
 * 1 <= 입력 문자열 <= 50만
 * @풀이
 * # palindrome이면 문자 하나씩 잘라서 계속 판단
 * # 입력 문자열 길이가 2이상이면서 palindrome이면 맨 앞 또는 맨 뒤 문자를 하나 자른 문자열은 무조건 palindrome이 아니다.
 * => 한 번만 판단
 * @comment
 * # Object 값을 String으로 변환
 * # 1. o.toString() : Object가 Null일 경우 NPE를 발생 + Object에 담긴 값이 String이 아니여도 출력
 * # 2. String.valueOf(o) : "null"이라는 문자열로 처리
 * # 문자열에서 특정 문자 개수 세기
 * # 1. Stream 이용 : str.chars().filter(c -> c == ch).count()
 * # 2. replace() 이용 : str.length() - str.replace(String.valueOf(ch), "").length()
 * # => 특정 문자를 ""으로 날린 후 길이를 센다. 이를 원본 문자열 길이에서 빼서 구함
 * # 문자열 뒤집기 reverse는 StringBuilder, StringBuffer만 가능
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15927 {
	
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	// -----------------------------------------------------------------------------
	
	public static void main(String[] args) throws IOException {
		sb.append(br.readLine());
		char startChar = sb.charAt(0); // 문자열의 첫 문자

		// 모두 같은 문자가 입력되었는지 체크
		if (sb.length() == countChar(startChar)) {
			System.out.println(-1);
			return;
		}
		
		while (isPalindrome()) {
			sb.setLength(sb.length() - 1); // 마지막 문자 하나 자르기
		}

		// print
		System.out.println(sb.length());
	}

	static long countChar(char ch) {
		return sb.chars().filter(c -> c == ch).count();
	}

	static boolean isPalindrome() {
		StringBuilder a, b;
		a = new StringBuilder();
		b = new StringBuilder();
		a.append(sb.substring(0, sb.length() / 2));

		if (sb.length() % 2 == 0) { // 짝수길이이면
			b.append(sb.substring(sb.length() / 2));
		} else {
			b.append(sb.substring(sb.length() / 2 + 1));
		}

		// 대칭이면
		if (a.toString().equals(b.reverse().toString())) {
			return true;
		}

		return false;
	}

}

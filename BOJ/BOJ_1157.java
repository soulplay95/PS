/**
 * @문제 단어 공부_B1
 * @날짜 211225
 * @분류 구현, 문자열
 * @조건
 * # 단어의 길이 <= 100만
 * @풀이
 * # 단어 개수 세기
 * @comment
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_1157 {

	private static String input;
	private static int[] counts; // 알파벳 등장 횟수

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input = br.readLine().toLowerCase(); // 소문자로 입력 받기
		// init
		counts = new int[26];

		// 단어 개수 세기
		for (int i = 0, end = input.length(); i < end; i++) {
			char alphabet = input.charAt(i);
			counts[alphabet - 'a']++;
		}

		// max값 찾기
		int max = 0;
		int maxIndex = 0;
		for (int i = 0; i < 26; i++) {
			if (counts[i] > max) {
				maxIndex = i;
				max = counts[i];
			}
		}

		// max값 여러개인지 체크
		for (int i = 0; i < 26; i++) {
			if (i != maxIndex && counts[i] == max) {
				maxIndex = -1;
				break;
			}
		}

		char ans = maxIndex == -1 ? '?' : (char) ('A' + maxIndex);
		// print
		System.out.println(ans);
	}

}

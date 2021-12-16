/**
 * @문제 그룹 단어 체커_S5
 * @날짜 211216
 * @분류 구현, 문자열
 * @조건
 * # 단어 개수 (N) <= 100
 * @풀이
 * # 전 단어가 다르고 used이면 그룹 단어가 아님
 * @comment
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_1316 {

	private static int N, ans;
	private static boolean[] used;
	private static char beforeChar;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		// init
		ans = 0;

		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			ans += isGroupWord(input);
		}

		// print
		System.out.println(ans);
	}

	private static int isGroupWord(String input) {
		// init
		used = new boolean[26];
		beforeChar = input.charAt(0);

		for (int i = 0, end = input.length(); i < end; i++) {
			char c = input.charAt(i);

			if (beforeChar != c && used[c - 'a']) {
				return 0;
			}

			beforeChar = c;
			used[c - 'a'] = true;
		}

		return 1;
	}

}

/**
 * @문제 암호 만들기_G5
 * @날짜 211110
 * @분류 수학, 완전 탐색, 조합론, 백트래킹
 * @조건
 * # 3 <= 암호 길이(L), 문자 종류(C) <= 15
 * @풀이
 * # 15P15 := 1조
 * # 순열 + 백트래킹
 * @comment
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_1759 {

	static int L, C;
	static char[] input, password;
	static boolean[] isSelected;
	static List<String> ans;
	static int vowelCounts, consonantCounts;

	static final String VOWELS = "aeiou";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		// init
		input = new char[C];
		isSelected = new boolean[C];
		password = new char[L];
		ans = new ArrayList<>();
		vowelCounts = 0;
		consonantCounts = 0;

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < C; i++) {
			input[i] = st.nextToken().charAt(0);
		}

		nPr(0);

		Collections.sort(ans);
		for (String s : ans) {
			System.out.println(s);
		}
	}

	static void nPr(int cnt) {
		if (cnt == L) {
			if (vowelCounts < 1 || consonantCounts < 2) {
				return;
			}

			ans.add(String.valueOf(password));
			return;
		}

		for (int i = 0; i < C; i++) {
			if (isSelected[i]) continue;
			// 백트래킹
			if (cnt >= 1 && password[cnt - 1] > input[i]) {
				continue;
			}

			boolean vowelFlag = false;
			if (VOWELS.indexOf(input[i]) != -1) { // 모음이면
				vowelCounts++;
				vowelFlag = true;
			} else {
				consonantCounts++;
			}
			password[cnt] = input[i];
			isSelected[i] = true;
			nPr(cnt + 1);
			isSelected[i] = false;
			if (vowelFlag) {
				vowelCounts--;
			} else {
				consonantCounts--;
			}
		}
	}

}

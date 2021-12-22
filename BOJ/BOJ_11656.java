/**
 * @문제 접미사 배열_S4
 * @날짜 211222
 * @분류 문자열, 정렬
 * @조건
 * # 문자열 길이 <= 1000
 * @풀이
 * # 문자열 사전순 정렬
 * @comment
 * # java 문자열 사전순 정렬 => Arrays.sort(), Collections.sort()
 */

import java.util.*;
import java.io.*;

public class BOJ_11656 {

	private static String input;
	private static ArrayList<String> suffixList;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input = br.readLine();
		// init
		suffixList = new ArrayList<>();

		solve();

		// print
		System.out.print(sb.toString());
	}

	private static void solve() {
		// 접미사를 구한다.
		for (int i = 0, end = input.length(); i < end; i++) {
			suffixList.add(input.substring(i));
		}

		// 접미사 리스트를 사전순 정렬
		Collections.sort(suffixList);

		// StringBuilder에 append
		for (String s : suffixList) {
			sb.append(s).append("\n");
		}
	}

}

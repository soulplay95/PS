/**
 * @문제 단어 정렬_S5
 * @날짜 211224
 * @분류 문자열, 정렬
 * @조건
 * # 1 <= 단어 개수 (N) <= 2만
 * # 문자열 길이 <= 50
 * @풀이
 * # Comparable
 * @comment
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_1181 {

	private static int N;
	private static ArrayList<String> result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		// init
		result = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			String input = br.readLine();

			// 같은 단어가 여러번 입력된 경우는 제외
			if (!result.contains(input)) {
				result.add(input);
			}
		}

		// 조건에 따라 정렬
		Collections.sort(result, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				if (o1.length() == o2.length()) { // 길이가 같으면
					// 사전순 정렬
					for (int i = 0, end = o1.length(); i < end; i++) {
						char c1 = o1.charAt(i);
						char c2 = o2.charAt(i);

						if (c1 == c2) {
							continue;
						} else {
							return c1 - c2;
						}
					}
				}

				return o1.length() - o2.length();
			}
		});

		// print
		for (String s : result) {
			System.out.println(s);
		}
	}

}

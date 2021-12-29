/**
 * @문제 나는야 포켓몬 마스터 이다솜_S4
 * @날짜 211229
 * @분류 자료 구조, 해시
 * @조건
 * # 1 <= 도감에 있는 포켓몬 개수 (N), 맞춰야 하는 문제 개수 (M) <= 10만
 * # 포켓몬 이름 영어. 첫글자만 대문자. 일부는 마지막 문자만 대문자
 * @풀이
 * # 해시맵 사용하여 검색
 * @comment
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_1620 {

	private static int N, M;
	private static HashMap<String, String> itosBook, stoiBook; // Integer to String, String to Integer 도감
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException, NumberFormatException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// init
		itosBook = new HashMap<>();
		stoiBook = new HashMap<>();

		for (int i = 1; i <= N; i++) {
			String name = br.readLine();

			// 두 가지 타입의 도감에 저장
			itosBook.put("" + i, name);
			stoiBook.put(name, "" + i);
		}

		for (int i = 1; i <= M; i++) {
			String item = br.readLine();

			if (itosBook.get(item) == null) {
				sb.append(stoiBook.get(item)).append("\n");
			} else {
				sb.append(itosBook.get(item)).append("\n");
			}
		}

		// print
		System.out.println(sb.toString());
	}

}

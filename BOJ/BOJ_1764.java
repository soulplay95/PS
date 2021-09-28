/**
 * @문제 듣보잡_S4
 * @날짜 210928
 * @분류 자료 구조, 문자열, 정렬, 해시를 사용한 집합과 맵
 * @조건
 * # 1 <= 이름 길이 <= 20
 * # 1 <= N, M <= 50만
 * @풀이
 * # 완탐 : 50만 * 50만
 * # 이분탐색
 * # 문자열 사전순 정렬
 * @comment
 * # java 문자열 사전순 비교 메소드 : int compareTo(String anotherString)
 * # 두 문자열이 사전순으로 같다면(문자열이 같으면) 0을 반환, 대상 문자열이 매개변수로 받은 문자열보다 사전순으로 앞서면 음수 반환, 뒤면 양수 반환
 */

import java.util.*;
import java.io.*;

public class BOJ_1764 {

	static int N, M;
	static String[] input;
	static List<String> answer;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();
		// init
		input = new String[N];
		answer = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			input[i] = sc.next();
		}
		Arrays.sort(input);

		for (int i = 0; i < M; i++) {
			String cur = sc.next();
			if (isIn(cur)) {
				answer.add(cur);
			}
		}

		// print
		System.out.println(answer.size());
		Collections.sort(answer);
		for (String s : answer) {
			System.out.println(s);
		}

		sc.close();
	}

	static boolean isIn(String cur) {
		int L = 0, R = N - 1;

		while (L <= R) {
			int mid = (L + R) / 2;

			if (input[mid].equals(cur)) {
				return true;
			}

			if (cur.compareTo(input[mid]) > 0) { // cur이 사전순으로 뒤에 있으면
				L = mid + 1;
			} else {
				R = mid - 1;
			}
		}

		return false;
	}

}

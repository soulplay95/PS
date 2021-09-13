/**
 * @문제 AC_G5
 * @날짜 210914
 * @분류 구현, 자료 구조, 문자열, 파싱, 덱
 * @조건
 * # 1 <= 함수 <= 10만
 * # 0 <= n <= 10만
 * @풀이
 * # 덱으로 배열 관리
 * @comment
 * # 방향 바꿔주는 것만으로 해결 가능
 */

import java.util.*;
import java.io.*;

public class BOJ_5430 {

	static Deque<String> dq;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			String func = br.readLine(); // 수행할 함수
			int n = Integer.parseInt(br.readLine()); // 배열 길이
			String arrString = br.readLine();

			parse(arrString); // parsing string to Deque
			solve(func); // 함수 수행
		}

		// print
		System.out.println(sb.toString());
	}

	static void parse(String arrString) {
		// init
		dq = new LinkedList<>();

		// 1. string을 덱으로 파싱한다.
		if ("[]".equals(arrString)) {
			return;
		}
		arrString = arrString.substring(1, arrString.length() - 1);
		String[] numbers = arrString.split(",");

		for (String str : numbers) {
			dq.offerLast(str);
		}
	}

	static void solve(String func) {
		// 2. 함수 순서대로 실행
		int d = -1; // D명령 방향. -1: left, 1: right

		for (int i = 0, end = func.length(); i < end; i++) {
			char command = func.charAt(i);

			if (command == 'R') {
				d = -d;

			} else if (command == 'D') {
				if (dq.isEmpty()) {
					sb.append("error").append("\n");
					return;
				}

				if (d == -1) {
					dq.pollFirst();
				} else {
					dq.pollLast();
				}
			}
		}

		sb.append("[");

		boolean flag = false;
		while (!dq.isEmpty()) {
			flag = true;
			if (d == -1) {
				sb.append(dq.pollFirst());
			} else {
				sb.append(dq.pollLast());
			}
			sb.append(",");
		}

		if (flag) {
			sb.setLength(sb.length() - 1);
		}

		sb.append("]");
		sb.append("\n");
	}

}

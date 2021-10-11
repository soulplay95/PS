/**
 * @문제 이진수 연산_B2
 * @날짜 211011
 * @분류 문자열
 * @조건
 * # 10만 비트
 * @풀이
 * # 문자열로 받아 각 비트연산을 수행
 * @comment
 * # 문자열 concat 연산대신 += 쓰면 시간초과
 */

import java.util.*;
import java.io.*;

public class BOJ_12813 {

	static String A, B;
	static StringBuilder[] answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		A = br.readLine();
		B = br.readLine();
		// init
		answer = new StringBuilder[5];
		answer[0] = new StringBuilder("");
		answer[1] = new StringBuilder("");
		answer[2] = new StringBuilder("");
		answer[3] = new StringBuilder("");
		answer[4] = new StringBuilder("");

		solve();

		// print
		for (int i = 0; i < 5; i++) {
			System.out.println(answer[i]);
		}
	}

	static void solve() {
		for (int i = 0; i < A.length(); i++) {
			char a = A.charAt(i);
			char b = B.charAt(i);

			if (a == '1') {
				if (b == '0') {
					answer[0].append("0");
					answer[1].append("1");
					answer[2].append("1");
					answer[4].append("1");
				} else {
					answer[0].append("1");
					answer[1].append("1");
					answer[2].append("0");
					answer[4].append("0");
				}
				answer[3].append("0");
			} else if (a == '0') {
				if (b == '0') {
					answer[0].append("0");
					answer[1].append("0");
					answer[2].append("0");
					answer[4].append("1");
				} else {
					answer[0].append("0");
					answer[1].append("1");
					answer[2].append("1");
					answer[4].append("0");
				}
				answer[3].append("1");
			}
		}
	}

}

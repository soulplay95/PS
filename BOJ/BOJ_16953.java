/**
 * @문제 A -> B_S1
 * @날짜 211210
 * @분류 
 * @조건
 * # 1 <= A < B <= 10억
 * @풀이
 * # BFS => visited를 Collections.contains로 체크?
 * # 그리디 => 임의의 수 A가 B가 되기 위해서는 B는 짝수이거나(* 2), 일의 자리 수가 1이여야만 한다.
 * B가 짝수이면 전 단계에서 2를 곱한 것이 최선의 선택이고, 일의 자리 수가 1이면 전 단계에서 1을 추가하는 것이 최선의 선택이다.
 * @comment
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_16953 {

	private static int A, B;

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		A = sc.nextInt();
		B = sc.nextInt();

		// print
		System.out.println(solve());

		sc.close();
	}

	private static int solve() {
		int counts = 0;

		while (true) {
			if (A == B) { // A와 B가 같아지면 종료
				return counts + 1;
			}
			if (A > B) { // B가 더 작아지면 만들 수 없는 경우이므로 종료
				return -1;
			}
			if (B % 2 == 0) { // B의 현재 값이 짝수이면 전 단계에서 2를 곱한 것이 최선의 선택이므로 B를 2로 나눔
				B /= 2;
			} else if (B % 10 == 1) { // B의 현재 값의 일의자리 수가 1이면 전 단계에서 1을 추가한 것이 최선의 선택
				B /= 10;
			} else { // B의 현재 값이 위의 2가지 케이스가 아니면 A가 어떤 값이라도 B와 같아질 수 없음
				return -1;
			}

			counts++;
		}
	}

}

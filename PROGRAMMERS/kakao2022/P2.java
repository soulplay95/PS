/**
 * @문제
 * @날짜 210911
 * @분류
 * @조건
 * # 1 <= n <= 100만
 * # 3 <= k <= 10
 * @풀이
 * # 1. n을 k진수로 바꾼다.
 * # 2. k를 0으로 split하여 각 부분의 수가 소수인지 판별한다.
 * @comment
 *
 */

package kakao2022;

import java.io.*;
import java.util.*;

public class P2 {

	public static void main(String[] args) throws IOException {
		System.out.println(solution(1, 3));
	}

	public static int solution(int n, int k) {
		int answer = 0;

		// 1. n을 k진수로 변환
		String result = change(n, k);

		// 2. '0' 기준으로 잘라서 각 부분 숫자가 소수인지 판별
		String[] sub = result.split("0");
		for (String s : sub) {
			if ("".equals(s) || "1".equals(s)) {
				continue;
			}

			Long num = Long.parseLong(s);
			if (isPrime(num)) {
				answer++;
			}
		}

		return answer;
	}

	public static String change(int n, int k) {
		// 10진수 n을 k진수 String 형태로 변환
		StringBuilder sb = new StringBuilder("");

		// k로 나눈 나머지를 순서대로 붙이고 reverse
		while (n > 0) {
			sb.append(n % k); // k로 나눈 나머지를 append
			n /= k;
		}

		return sb.reverse().toString();
	}

	public static boolean isPrime(Long num) {
		for (long i = 2L; i * i <= num; i++) {
			if (num % i == 0) {
				return false;
			}
		}

		return true;
	}

}

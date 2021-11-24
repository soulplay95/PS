/**
 * @문제 책 페이지
 * @날짜 211124
 * @분류 수학, 구간합
 * @풀이
 *
 * @comment
 * 구간 합 : A부터 B까지 각 숫자가 모두 몇 번 나오는지 구함
 * A는 일의 자리를 0으로 만들고, B는 일의 자리를 9로 만든다. => 그 후 반복되는 패턴 이용
 * A = 1350, B = 8739일때, A 부터 B 까지 일의 자리에 0~9는 총 (873-135+1)번 등장함.
 * 그 다음 십의자리에 0~9가 몇번 등장했는지 계산...
 */

import java.util.*;
import java.io.*;

public class BOJ_1019 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static long[] ans;

	public static void main(String[] args) throws IOException {
		long n = Long.parseLong(br.readLine());

		ans = new long[10];
		go(1, n, 1);

		for (int i = 0; i < 10; i++) {
			sb.append(ans[i]).append(" ");
		}
//		sb.append("\n");

		// print
		System.out.println(sb.toString());
	}

	// n의 각 자릿수 숫자의 개수를 ans에 누적시킴. ten : 자릿수
	static void calc(long n, long ten) {
		while (n > 0) {
			ans[(int)n % 10] += ten;
			n = n / 10;
		}
	}

	static void go(long start, long end, long ten) {
		// start의 일의 자리를 0까지 맞춰줌 ++
		while (start % 10 != 0 && start <= end) { // start의 일의 자리가 0이 아닐때까지 반복
			calc(start, ten); // start를 1씩 증가시키면서 그 때의 각 자리수 숫자의 개수를 누적
			start++;
		}

		// 제일 앞자리 연산 시 종료 조건
		if (start > end) return;

		// end의 일의 자리를 9로 맞춰줌 --
		while (end % 10 != 9 && start <= end) {
			calc(end, ten);
			end--;
		}

		// 각 자릿수 별로 0 ~ 9까지 수의 총 개수를 누적
		long cnt = (end / 10 - start / 10 + 1);
		for (int i = 0; i < 10; i++) {
			ans[i] += cnt * ten;
		}

		go(start / 10, end / 10, ten * 10L); // 자릿수 하나 올라가서 계산
	}

}
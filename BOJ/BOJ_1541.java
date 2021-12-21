/**
 * @문제 잃어버린 괄호_S2
 * @날짜 211221
 * @분류 수학, 문자열, 그리디, 파싱
 * @조건
 * # 식의 길이 <= 50
 * @풀이
 * # - 연산자 뒤의 숫자가 최대한 커지도록 괄호를 친다. => 덧셈을 먼저 계산한다.
 * @comment
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_1541 {

	private static String input;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input = br.readLine();

		// print
		System.out.println(solve());
	}

	private static int solve() {
		int result = 0;
		Queue<Integer> numbers = new LinkedList<>();

		// - 기준으로 문자열 분리
		StringTokenizer tokensSplitByMinus = new StringTokenizer(input, "-");

		while (tokensSplitByMinus.hasMoreTokens()) {
			// + 기준으로 문자열 분리
			StringTokenizer tokensSplitByPlus = new StringTokenizer(tokensSplitByMinus.nextToken(), "+");
			int sum = 0;

			while (tokensSplitByPlus.hasMoreTokens()) {
				sum += Integer.parseInt(tokensSplitByPlus.nextToken());
			}

			numbers.offer(sum);
		}

		result = numbers.poll();

		while (!numbers.isEmpty()) {
			result -= numbers.poll();
		}

		return result;
	}

}

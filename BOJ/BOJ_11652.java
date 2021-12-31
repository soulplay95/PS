/**
 * @문제 카드_S4
 * @날짜 211231
 * @분류 자료 구조, 정렬, 해시
 * @조건
 * # 카드 개수 (N) <= 10만
 * @풀이
 * # HashMap<Long, Integer> => 숫자, 개수
 * @comment
 * # Java8 이후 Map.forEach로 맵 순회 가능
 */

import java.util.*;
import java.io.*;

public class BOJ_11652 {

	private static int N;
	private static HashMap<Long, Integer> cardCounts;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		// init
		cardCounts = new HashMap<>();
		for (int i = 0; i < N; i++) {
			Long number = Long.parseLong(br.readLine());

			if (cardCounts.containsKey(number)) { // Map에 1개 이상 존재하면 개수 1 증가
				cardCounts.put(number, cardCounts.get(number) + 1);
			} else {
				cardCounts.put(number, 1);
			}
		}

		// print
		System.out.println(solve());
	}

	private static Long solve() {
		int maxCounts = 0;
		Long result = Long.MAX_VALUE;

		for (Map.Entry<Long, Integer> entrySet : cardCounts.entrySet()) {
			if (entrySet.getValue().intValue() == maxCounts) {
				if (entrySet.getKey().longValue() < result) {
					result = entrySet.getKey().longValue();
				}
			} else if (entrySet.getValue().intValue() > maxCounts) {
				result = entrySet.getKey().longValue();
				maxCounts = entrySet.getValue().intValue();
			}
		}

		return result;
	}

}
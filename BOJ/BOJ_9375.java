/**
 * @문제 패션왕 신해빈_S3
 * @날짜 211230
 * @분류 수학, 자료 구조, 해시
 * @조건
 * # 0 <= 의상 수 (n) <= 30
 * @풀이
 * # 각 의상의 종류별 개수에 그 종류의 의상을 입지 않은 경우의 수를 더해 곱해준다.
 * 여기에 모든 종류의 의상을 입지 않은 경우가 포함되므로 -1
 * ex) (2 + 1) * (1 + 1) - 1
 *     (headgear 개수 + 1) * (eyewear 개수 + 1) - 1
 * @comment
 * # HashMap.containsKey(key) => 해당 key값이 해시맵에 있으면 true 리턴
 * # HashMap.values() => 모든 key값에 대한 값 Collection 리턴
 */

import java.util.*;
import java.io.*;

public class BOJ_9375 {

	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int n = Integer.parseInt(br.readLine());
			// init
			HashMap<String, Integer> countsByCategory = new HashMap<>(); // 의상 종류 별 가짓 수

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				String name = st.nextToken();
				String category = st.nextToken();

				if (!countsByCategory.containsKey(category)) { // 해당 key(의상 종류)에 값이 없는 경우
					countsByCategory.put(category, 1); // 값을 넣어준다.
				} else { // 값이 있는 경우
					countsByCategory.put(category, countsByCategory.get(category) + 1); // 기존 값에 +1
				}
			}

			// 경우의 수 구하기
			int result = 1;
			for (int counts : countsByCategory.values()) {
				result *= counts + 1;
			}

			// append
			sb.append(result - 1).append("\n");
		}

		// print
		System.out.println(sb.toString());
	}

}

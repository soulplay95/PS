/**
 * @문제
 * @날짜 210911
 * @분류
 * @조건
 * # 2 <= 유저 수 <= 1000
 * @풀이
 * # 유저 ID로 구성된 2차원 배열을 만든다.
 * # map[i][j] : i가 j를 신고하면 true
 * # map[i][j]가 false 일때만(여러번 신고 시 한번만 적용되므로) 신고 횟수 누적
 * # 마지막에 신고횟수를 카운팅하여 k번 이상인 유저를 map[?][x]로 잡고 true인 개수를 센다.
 * @comment
 *
 */

package kakao2022;

import java.io.*;
import java.util.*;

public class P1 {

	public static void main(String[] args) throws IOException {

	}

	// -------------------

	static boolean[][] map; // map[i][j] : i가 j유저를 신고했으면 true
	static int[] reportCounts; // 각 유저별 신고 당한 횟수
	static int N;
	static Map<String, Integer> hashMap;

	public static int[] solution(String[] id_list, String[] report, int k) {
		// init
		N = id_list.length; // 유저 수
		map = new boolean[N][N];
		reportCounts = new int[N];
		hashMap = new HashMap<>(); // 유저 아이디, 유저 인덱스
		int[] answer = new int[N];

		// 1. make hashmap
		for (int i = 0, end = id_list.length; i < end; i++) {
			hashMap.put(id_list[i], i);
		}

		// 2. report parsing
		for (int i = 0, end = report.length; i < end; i++) {
			String[] s = report[i].split(" ");
			int from = hashMap.get(s[0]);
			int to = hashMap.get(s[1]);

			// 여러번 신고 시 한번만 적용
			if (!map[from][to]) {
				reportCounts[to]++; // 신고 횟수 누적
				map[from][to] = true; // 신고 체크
			}
		}

		// 3. 신고 횟수 카운팅하여 k번 이상인 유저 찾기
		List<Integer> list = new ArrayList<>(); // 정지당한 유저의 인덱스 리스트
		for (int i = 0; i < N; i++) {
			if (reportCounts[i] >= k) {
				list.add(i);
			}
		}

		// 4. 각 유저 별 정지당한 유저가 몇명 포함되어 있는지 확인
		for (int i = 0; i < N; i++) {
			int counts = 0;

			for (int cur : list) {
				if (map[i][cur]) { // 신고했으면
					counts++;
				}
			}

			answer[i] = counts;
		}

		return answer;
	}

}

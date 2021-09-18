/**
 * @문제
 * @날짜 210911
 * @분류
 * @조건
 * @풀이
 * # 입/출차 기록의 HH:MM을 분으로 변경(H * 60 + M)
 * # 차량 번호 순으로 정렬하여 HashMap 생성
 * # 차량 번호 파싱
 * @comment
 *
 */

package kakao2022;

import java.io.*;
import java.util.*;

public class P3 {

	public static void main(String[] args) {
		solution(
				new int[] {120, 0, 60, 591},
				new String[] {"16:00 3961 IN","16:00 0202 IN","18:00 3961 OUT","18:00 0202 OUT","23:58 3961 IN"}
		);
	}

	public static int[] solution(int[] fees, String[] records) {
		Set<Integer> set = new HashSet<>();

		// 1. records를 차량 번호 순으로 정렬
		Arrays.sort(records, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				String[] info1 = o1.split(" ");
				String[] info2 = o2.split(" ");
				// 차량 번호
				int num1 = Integer.parseInt(info1[1]);
				int num2 = Integer.parseInt(info2[1]);

				return num1 - num2;
			}
		});

		// 1-2. 차량 번호 파싱
		for (String record : records) {
			int num = Integer.parseInt(record.split(" ")[1]);
			set.add(num);
		}

		// 1-3. set to array
		Integer[] list = set.toArray(new Integer[0]);
		Arrays.sort(list);
		Map<Integer, Integer> map = new HashMap<>();
		int index = 0;
		for (int cur : list) {
			map.put(cur, index++);
		}

		int N = set.size(); // 차량 수
		int[] time = new int[N];
		int[] answer = new int[N];

		String[] firstInfo = records[0].split(" ");
		int firstNum = Integer.parseInt(firstInfo[1]); // 차량 번호
		String ftype = firstInfo[2];

		// HH:MM -> 분으로 바꾸기
		String[] firstHm = firstInfo[0].split(":");
		int fh = Integer.parseInt(firstHm[0]) * 60; // 시간(분)
		int fm = Integer.parseInt(firstHm[1]); // 분

		if (records.length == 1) {
			time[0] = 1439 - (fh + fm);
		} else {
			int beforeNum = firstNum;
			int beforeTime = fh + fm;
			String beforeType = ftype;
			for (int i = 1, end = records.length; i < end; i++) {
				String[] info = records[i].split(" ");
				int curNum = Integer.parseInt(info[1]); // 차량 번호
				String type = info[2];

				// HH:MM -> 분으로 바꾸기
				String[] hm = info[0].split(":");
				int h = Integer.parseInt(hm[0]) * 60; // 시간(분)
				int m = Integer.parseInt(hm[1]); // 분

				// 차량 번호가 같은지 다른지에 따라 분류(같으면 현재가 OUT정보, 다르면 다른 차량의 IN 정보)
				if (beforeNum == curNum) { // 같으면
					if ("IN".equals(beforeType) && "OUT".equals(type)) {
						time[map.get(curNum)] += (h + m) - beforeTime;
					} else if ("OUT".equals(beforeType) && "IN".equals(type)) {
						// 마지막인지
						if (i == end - 1) {
							time[map.get(curNum)] += 1439 - (h + m);
						}
					}
					beforeNum = curNum;
					beforeTime = h + m;
					beforeType = type;
				} else {
					// OUT이 없다
					if ("IN".equals(beforeType)) {
						time[map.get(beforeNum)] += 1439 - beforeTime;
					}
					// 업데이트
					beforeNum = curNum;
					beforeTime = h + m;
					beforeType = type;

					// 마지막인지
					if (i == end - 1) {
						time[map.get(curNum)] += 1439 - (h + m);
					}
				}
			}
		}

		// 3. 주차 요금 계산
		for (int i = 0; i < N; i++) {
			if (time[i] <= fees[0]) { // 기본 시간 이하이면
				answer[i] = fees[1];
				continue;
			}

			answer[i] = fees[1] + ((int)(Math.ceil((double)(time[i] - fees[0]) / (double)(fees[2]))) * fees[3]);
		}

		return answer;
	}

}

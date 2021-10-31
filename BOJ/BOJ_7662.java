package study.week41;

/**
 * @문제 이중 우선순위 큐_G5
 * @날짜 211031
 * @분류 
 * @조건
 * # 연산의 개수 k <= 100만
 * @풀이
 * # 최대, 최소힙 2가지로 동기화?
 * # 리스트로 최대, 최소값 관리
 * # TreeMap 사용. key: n, value: 저장된 개수
 * @comment
 * # JAVA map - getOrDefault : 찾는 키가 존재한다면 찾는 키의 값을 반환, 없으면 기본 값을 반환
 * getOrDefault(key, 없는 경우 반환되는 기본값)
 * map.put(n, map.getOrDefault(n, 0) + 1) => 자주 쓰이는 형태
 * # JAVA TreeMap : 이진 트리 기반의 Map. TreeMap에 객체를 저장하면 자동 정렬. key값 기준 오름차순 정렬
 * 정렬된 상태로 Map을 유지하거나 정렬된 데이터를 조회해야 하는 범위 검색이 필요한 경우 TreeMap 사용
 * firstKey() : 최소 Key
 * lastKey() : 최대 Key
 */

import java.util.*;
import java.io.*;

public class BOJ_7662 {

	static int K;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			K = Integer.parseInt(br.readLine());
			// init
			TreeMap<Integer, Integer> map = new TreeMap<>();

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				String command = st.nextToken();
				int n = Integer.parseInt(st.nextToken());

				if ("I".equals(command)) {
					map.put(n, map.getOrDefault(n, 0) + 1);
				} else if ("D".equals(command)) {
					if (map.size() != 0) {
						int key = n == 1 ? map.lastKey() : map.firstKey();
						
						// map에 해당 key의 -1한 value로 업데이트
						int counts = map.get(key);
						if (counts == 1) {
							map.remove(key);
						} else {
							map.put(key, counts - 1);
						}
					}
				}
			}

			if (map.size() == 0) {
				sb.append("EMPTY").append("\n");
			} else {
				sb.append(map.lastKey()).append(" ").append(map.firstKey()).append("\n");
			}
		}

		// print
		System.out.println(sb.toString());
	}

}

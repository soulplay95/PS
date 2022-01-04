package study.week50;

/**
 * @문제 친구 네트워크_G2
 * @날짜 220105
 * @분류 자료 구조, 분리 집합, 해시
 * @조건
 * # 친구 관계 수 (F) <= 10만
 * @풀이
 * # 서로소 집합
 * - 각 노드의 구분을 숫자로 해야 한다. parents 배열의 인덱스로 관리해야 하므로 => map<친구이름(String), 인덱스(Integer)>을 이용
 * - 각 집합 내 노드의 수를 관리하기 위한 size 배열을 이용한다.
 * @comment
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_4195 {

	private static int F;
	private static int[] parents, rank, size;
	private static HashMap<String, Integer> nameIndexMap = new HashMap<>();
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			F = Integer.parseInt(br.readLine());
			// init
			make();
			nameIndexMap.clear(); // 해시맵 초기화
			int index = 1; // 인덱스 1번부터 부여

			for (int i = 0; i < F; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				String a = st.nextToken();
				String b = st.nextToken();

				// 문자열로 주어진 사용자 아이디에 대하여 처음 등장한 아이디인지(인덱스가 부여되지 않은) 확인하고 인덱스 번호 부여
				if (!nameIndexMap.containsKey(a)) {
					nameIndexMap.put(a, index++);
				}
				if (!nameIndexMap.containsKey(b)) {
					nameIndexMap.put(b, index++);
				}

				int aIndex = nameIndexMap.get(a);
				int bIndex = nameIndexMap.get(b);
				sb.append(union(aIndex, bIndex)).append("\n");
			}
		}

		// print
		System.out.println(sb.toString());
	}

	private static void make() {
		parents = new int[F * 2 + 1]; // 1번 인덱스부터 부여. F값이 최대 10만이므로 사용자 아이디는 최대 20만개까지 나올 수 있다.
		rank = new int[F * 2 + 1];
		size = new int[F * 2 + 1];
		for (int i = 1, end = F * 2 + 1; i < end; i++) {
			parents[i] = i;
			size[i] = 1; // 각 집합의 사이즈(집합 내 친구관계 수)는 1로 초기화
		}
	}

	private static int find(int x) {
		if (parents[x] == x) {
			return x;
		}

		return parents[x] = find(parents[x]); // path compression
	}

	private static int union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		if (aRoot == bRoot) { // 이미 같은 집합이면
			return size[aRoot]; // size 합치기 없이 원래 사이즈 바로 리턴
		}

		// rank가 큰쪽에 rank가 작은 집합을 포함시킨다.
		if (rank[aRoot] < rank[bRoot]) { // a집합을 b집합에 포함
			parents[aRoot] = bRoot;
			size[bRoot] += size[aRoot]; // 합쳐진 후 대표 노드의 size를 조정

			return size[bRoot];
		} else if (rank[aRoot] == rank[bRoot]) {
			rank[aRoot]++;
		}

		parents[bRoot] = aRoot; // b집합을 a집합에 포함
		size[aRoot] += size[bRoot];

		return size[aRoot];
	}

}

package study.week42;

/**
 * @문제 사이클 게임_G4
 * @날짜 211107
 * @분류 자료 구조, 분리 집합
 * @조건
 * # 3 <= 점의 개수(n) <= 50만
 * # 3 <= 진행된 차례 수(m) <= 100만
 * @풀이
 * # Union-Find
 * # O(N + MlogN)
 * @comment
 * # 서로소 집합(Disjoint-Set) : 분리 집합. 상호배타 집합. 서로 중복 포함된 원소가 없는 집합들 => 교집합이 없다.
 * # 집합에 속한 하나의 특정 멤버를 통해 각 집합들을 구분한다. => 대표자(representative)
 * # 서로소 집합을 표현하는 방법 : 연결리스트, 트리
 * # 서로소 집합 연산
 * Make-Set(x) : 다 쪼개어진 단위 집합을 만드는 연산. 유일한 멤버 x를 포함하는 새로운 집합을 생성하는 연산
 * Find-Set(x) : x가 속한 집합을 찾아내는 연산
 * Union(x, y) : x, y가 속한 두 집합을 합치는 연산. 내부적으로 find 연산이 동반된다.
 * # 문제점 : union하는 과정에서 연결리스트 형태로 줄줄이 소세지 모양으로 연결되면 find 할 때 트리의 depth만큼 O(depth)
 * 의 시간복잡도가 소요된다.
 * # 해결
 * # Rank를 이용한 Union : 각 노드는 자신을 루트로하는 subtree의 높이를 rank 라는 이름으로 저장한다.
 * 두 집합을 합칠 때 rank가 낮은 집합을 rank가 높은 집합에 붙인다. => 트리의 높이(depth)가 높아지는 상황을 방지할 수 있음
 * # Path compression : find를 하는 과정에서 타고 올라가는 모든 노드들이 직접 root를 가리키도록 포인터를 바꾸어준다.
 */

import java.util.*;
import java.io.*;

public class BOJ_20040 {

	static int N, M;
	static int[] parents, rank;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		make();

		for (int m = 1; m <= M; m++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			if (!union(x, y)) { // 사이클 발생
				System.out.println(m);
				System.exit(0);
			}
		}

		System.out.println(0);
	}

	static void make() {
		parents = new int[N];
		rank = new int[N];

		for (int i = 0; i < N; i++) {
			parents[i] = i;
			rank[i] = 0;
		}
	}

	static int find(int x) {
		if (parents[x] == x) {
			return x;
		}

		return parents[x] = find(parents[x]); // path compression
	}

	static boolean union(int x, int y) {
		int xRoot = find(x);
		int yRoot = find(y);

		if (xRoot == yRoot) {
			return false;
		}

		// y가 속한 집합을 x가 속한 집합에 합친다.
		if (rank[xRoot] < rank[yRoot]) { // y가 속한 집합의 rank가 더 높으면 반대로
			parents[xRoot] = yRoot;

			return true;
		} else if (rank[xRoot] == rank[yRoot]) { // 같은 경우 rank 1 증가
			rank[xRoot]++;
		}

		parents[yRoot] = xRoot;
		
		return true;
	}

}

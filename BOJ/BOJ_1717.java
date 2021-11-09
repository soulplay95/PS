/**
 * @문제 집합의 표현_G4
 * @날짜 211109
 * @분류 자료 구조, 분리 집합
 * @조건
 * # 1 <= 집합 개수(n) <= 100만
 * # 1 <= 연산의 개수(m) <= 10만
 * @풀이
 * # Union-Find
 * @comment
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_1717 {

	static int N, M;
	static int[] parents, rank;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// init
		parents = new int[N + 1];
		rank = new int[N + 1];
		sb = new StringBuilder();

		make();

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine(), " ");
			int command = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if (command == 0) { // 합집합
				union(a, b);
			} else if (command == 1) {
				sb.append(find(a) == find(b) ? "YES" : "NO").append("\n");
			}
		}

		// print
		System.out.println(sb.toString());
	}

	static void make() {
		for (int i = 0; i <= N; i++) {
			parents[i] = i;
		}
	}

	static int find(int x) {
		if (x == parents[x]) {
			return x;
		}

		return parents[x] = find(parents[x]); // path compression
	}

	static void union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		if (aRoot == bRoot) {
			return;
		}

		if (rank[aRoot] < rank[bRoot]) {
			parents[aRoot] = bRoot;
			return;
		} else if (rank[aRoot] == rank[bRoot]) {
			rank[aRoot]++;
		}

		parents[bRoot] = aRoot;

		return;
	}

}

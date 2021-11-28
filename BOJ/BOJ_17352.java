package study.week44;

/**
 * @문제 여러분의 다리가 되어 드리겠습니다!_G5
 * @날짜 211128
 * @분류 그래프 탐색, 분리 집합
 * @조건
 * # 2 <= 섬 개수 (N) <= 30만
 * @풀이
 * # union-find
 * # parent가 다른 두 노드를 연결한다.
 * @comment
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_17352 {

	private static int N;
	private static int[] parents, rank;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());

		make();

		for (int i = 0, end = N - 2; i < end; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			union(x, y);
		}

		// parents가 다른 두 노드 출력
		int x = 1;
		int y = 0;
		int xRoot = find(x);
		for (int i = 2; i <= N; i++) {
			if (find(i) != xRoot) {
				y = i;
				break;
			}
		}

		// print
		System.out.println(x + " " + y);
	}

	private static void make() {
		// init
		parents = new int[N + 1];
		rank = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}

	private static int find(int x) {
		if (parents[x] == x) {
			return x;
		}

		return parents[x] = find(parents[x]); // path compression
	}

	private static boolean union(int x, int y) {
		int xRoot = find(x);
		int yRoot = find(y);

		if (xRoot == yRoot) {
			return false;
		}

		// y가 속한 집합을 x가 속한 집합에 합친다.
		if (rank[xRoot] < rank[yRoot]) {
			parents[xRoot] = yRoot;

			return true;
		} else if (rank[xRoot] == rank[yRoot]) {
			rank[xRoot]++;
		}

		parents[yRoot] = xRoot;

		return true;
	}

}

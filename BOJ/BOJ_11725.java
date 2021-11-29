/**
 * @문제 트리의 부모 찾기_S2
 * @날짜 211129
 * @분류 그래프 탐색, 트리, BFS, DFS
 * @조건
 * # 2 <= 노드의 개수 <= 10만
 * @풀이
 * # 인접 리스트로 그래프를 구성하고 BFS 또는 DFS를 통해 루트 노드(1번)부터 탐색을 시작한다.
 * # 특정 노드를 탐색할 때 직전에 탐색했던 노드(부모 노드) 정보를 넘긴다.
 * @comment
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_11725 {

	private static int N;
	private static ArrayList<Integer>[] adjList;
	private static int[] parents; // 각 노드의 부모 노드 번호

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		// init
		parents = new int[N + 1];
		adjList = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}

		for (int i = 0, end = N - 1; i < end; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			adjList[x].add(y);
			adjList[y].add(x);
		}

		dfs(1, 1); // 루트 노드(1번)부터 탐색 시작

		for (int i = 2; i <= N; i++) {
			sb.append(parents[i]).append("\n");
		}

		// print
		System.out.print(sb.toString());
	}

	private static void dfs(int parent, int cur) {
		// 부모 노드 마킹
		parents[cur] = parent;

		// 방문하지 않은 인접한 노드 탐색
		for (int v : adjList[cur]) {
			if (parents[v] == 0) { // 방문하지 않았으면
				dfs(cur, v);
			}
		}
	}

}

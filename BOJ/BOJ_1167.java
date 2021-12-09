/**
 * @문제 트리의 지름_G3
 * @날짜 211209
 * @분류 
 * @조건
 * # 2 <= 정점 개수 (V) <= 10만
 * @풀이
 * # 임의의 노드에서 제일 멀리 있는 노드까지 dfs 탐색 후, 그 노드에서 다시 제일 먼 노드까지 dfs 탐색 했을 때 거리를 구한다.
 * @comment
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_1167 {

	private static class Node {
		int index, weight;

		public Node(int index, int weight) {
			this.index = index;
			this.weight = weight;
		}
	}

	private static int N, startIndex, max;
	private static ArrayList<Node>[] adjList;
	private static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		// init
		adjList = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int target = Integer.parseInt(st.nextToken());

			while (true) {
				int index = Integer.parseInt(st.nextToken());
				if (index == -1) {
					break;
				}
				int weight = Integer.parseInt(st.nextToken());

				adjList[target].add(new Node(index, weight));
			}
		}

		solve();

		// print
		System.out.println(max);
	}

	private static void solve() {
		// 1. 임의의 노드로부터 가장 먼 노드의 번호를 찾는다.
		visited = new boolean[N + 1];
		max = 0;
		startIndex = 1;
		dfs(1, 0);

		// 2. 찾은 노드 번호로부터 다시 dfs
		visited = new boolean[N + 1];
		max = 0;
		dfs(startIndex, 0);
	}

	private static void dfs(int index, int weightSum) {
		visited[index] = true;

		for (Node node : adjList[index]) {
			if (!visited[node.index]) {
				dfs(node.index, weightSum + node.weight);
			}
		}

		if (weightSum > max) {
			startIndex = index;
			max = weightSum;
		}
	}

}

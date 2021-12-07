/**
 * @문제 트리의 지름_G4
 * @날짜 211207
 * @분류 
 * @조건
 * # 1 <= 노드의 개수 (n) <= 1만
 *
 * @풀이
 * # 트리의 지름 구하기
 * 1. 아무 점이나 잡고(루트), 이 점에서 가장 거리가 먼 점 t를 잡는다.
 * 2. t에서 가장 거리가 먼 점 u를 찾는다.
 * 3. t ~ u가 트리의 지름
 * # 인접리스트로 구현
 * # dfs 2번 돌림
 * @comment
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_1967 {

	// Node 클래스
	private static class Node {
		int index, weight; // 노드 번호, 가중치

		public Node(int index, int weight) {
			this.index = index;
			this.weight = weight;
		}
	}

	private static int N, max, indexFarFromRoot;
	private static ArrayList<Node>[] adjList;
	private static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		// init
		max = 0;
		indexFarFromRoot = 1; // 루트 노드로부터 가장 멀리 있는 노드의 번호
		adjList = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}

		// 트리의 노드가 V개이면 간선 개수는 V-1개이다.
		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int parent = Integer.parseInt(st.nextToken());
			int child = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			adjList[parent].add(new Node(child, weight));
			adjList[child].add(new Node(parent, weight));
		}

		solve();

		// print
		System.out.println(max);
	}

	static void solve() {
		visited = new boolean[N + 1];
		dfs(1, 0); // 루트 노드로부터 제일 먼 노드의 번호 찾기

		max = 0;
		visited = new boolean[N + 1];
		dfs(indexFarFromRoot, 0);
	}

	static void dfs(int index, int weightSum) {
		visited[index] = true; // 방문 체크

		for (Node v : adjList[index]) {
			if (!visited[v.index]) {
				dfs(v.index, weightSum + v.weight);
			}
		}

		// 최대값 갱신
		if (weightSum > max) {
			indexFarFromRoot = index;
			max = weightSum;
		}
	}

}

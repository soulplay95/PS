package study.week35;

/**
 * @문제 두 로봇_G5
 * @날짜 210913
 * @분류 그래프 이론, 그래프 탐색, BFS, DFS
 * @조건
 * # 1 <= N <= 10만
 * @풀이
 * # 두 로봇을 연결하는 유일한 경로의 가중치 합에서 max 가중치를 빼준다.
 * @comment
 * # N개의 노드, N - 1개의 간선 => 트리
 * # 임의의 두 노드 간의 경로는 유일하다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_15971 {
	
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	// -----------------------------------------------------------------------------

	static class Node {
		int v, w;

		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}

	static int N, A, B, max;
	static List<Node>[] adjList;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());

		// init
		adjList = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}
		visited = new boolean[N + 1];
		max = 0;

		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adjList[from].add(new Node(to, weight));
			adjList[to].add(new Node(from, weight));
		} // input end

		solve();
	}
	
	static void solve() {
		visited[A] = true;
		dfs(A, 0);
	}

	static void dfs(int v, int sum) {
		// 기저 조건
		if (v == B) {
			System.out.println(sum - max);
			return;
		}

		for (Node n : adjList[v]) {
			if (!visited[n.v]) {
				max = Math.max(max, n.w); // 가중치 최대값 갱신
				visited[n.v] = true; // 방문체크
				dfs(n.v, sum + n.w);
			}
		}
	}

}

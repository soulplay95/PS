/**
 * @문제 이분 그래프_G4
 * @날짜 211126
 * @분류 BFS, DFS
 * @조건
 * # 1 <= 정점 개수 (V) <= 2만
 * # 1 <= 간선 개수 (E) <= 20만
 * @풀이
 * # BFS 또는 DFS를 통해 인접한 정점으로 이동할 때마다 색깔을 달리 칠한다.
 * # 모든 간선에 대해 다른 색깔 두가지를 포함하는지 체크한다.
 * @comment
 * # 이분 그래프 판정 : 정점을 두 집합으로 나눴을 때 (red, blue) 모든 간선이 red, blue 정점을 연결시키는 형태가 나와야 함
 */

import java.util.*;
import java.io.*;

public class BOJ_1707 {

	private static int V, E;
	private static ArrayList<Integer>[] adjList;
	private static int[] visited; // -1 : red, 1 : blue
	private static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			// init
			adjList = new ArrayList[V + 1];
			for (int i = 1; i <= V; i++) {
				adjList[i] = new ArrayList<>();
			}
			visited = new int[V + 1];

			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());

				// 양방향 그래프
				adjList[x].add(y);
				adjList[y].add(x);
			} // input end

			// 모든 정점에 대하여 red 또는 blue 마킹 처리(두 집합으로 나눔)
			for (int i = 1; i <= V; i++) {
				if (visited[i] == 0) {
					dfs(i, 1); // 1(blue)의 컬러로 시작
				}
			}

			if (isBipartiteGraph()) {
				sb.append("YES").append("\n");
			} else {
				sb.append("NO").append("\n");
			}
		}

		// print
		System.out.println(sb.toString());
	}

	private static void dfs(int v, int color) {
		// 컬러 마킹
		visited[v] = color;

		// 아직 마킹하지 않은 인접한 정점들에 대하여 dfs 탐색
		for (int i : adjList[v]) {
			if (visited[i] == 0) {
				dfs(i, -color); // 반대 진영의 색깔로 마킹해야 하므로
			}
		}
	}

	private static boolean isBipartiteGraph() {
		// 간선을 구성하는 서로 인접한 노드끼리 같은 색깔(진영)을 가지면 이분그래프가 될 수 없다.
		for (int v = 1; v <= V; v++) {
			for (int i : adjList[v]) {
				if (visited[v] == visited[i]) {
					return false;
				}
			}
		}

		return true;
	}

}

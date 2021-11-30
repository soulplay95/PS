/**
 * @문제 케빈 베이컨의 6단계 법칙_S1
 * @날짜 211130
 * @분류 그래프 탐색, BFS, 플로이드-와샬
 * @조건
 * # 2 <= 유저 수 (N) <= 100
 * # 1 <= 간선 수 (M) <= 5000
 * @풀이
 * # 모든 노드에서 BFS 돌려서 케빈 베이컨 수를 구한다.
 * @comment
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_1389 {

	private static int N, M;
	private static boolean[][] adjMatrix;
	private static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// init
		adjMatrix = new boolean[N + 1][N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			adjMatrix[x][y] = true;
			adjMatrix[y][x] = true;
		}

		// print
		System.out.println(solve());
	}

	private static int solve() {
		int min = Integer.MAX_VALUE;
		int minNode = 0;

		// 모든 노드에서 BFS 돌려서 케빈 베이컨 수를 구한다.
		for (int i = N; i > 0; i--) {
			int result = bfs(i);

			if (result <= min) {
				min = result;
				minNode = i;
			}
		}

		return minNode;
	}

	private static int bfs(int start) {
		int result = 0;
		visited = new boolean[N + 1];
		Queue<Integer> q = new LinkedList<>();
		visited[start] = true;
		q.offer(start);

		int distance = 1; // 노드간의 거리
		while (!q.isEmpty()) {
			int size = q.size();

			while (size-- > 0) {
				int cur = q.poll();

				// 인접한 노드 중에 방문하지 않은 노드 탐색
				for (int i = 1; i <= N; i++) {
					if (adjMatrix[cur][i] && !visited[i]) {
						visited[i] = true;
						q.offer(i);
						result += distance; // 케빈 베이컨 수 누적
					}
				}
			}

			distance++;
		}

		return result;
	}

}

package study.week34;

/**
 * @문제 중량제한_G4
 * @날짜 210906
 * @분류 그래프, 자료 구조, 이분 탐색, BFS
 * @조건
 * # 2 <= 노드 수 N <= 1만
 * # 1 <= 간선 수 M <= 10만
 * # 1 <= 중량 C <= 10억
 * # 두 노드 사이에 여러 간선이 있을 수 있음
 * @풀이
 * # 이분탐색으로 중량을 찾아 나간다.
 * # 이분탐색으로 찍은 중량보다 크거나 같은 간선만 타면서 bfs 돌려서 도착하면, 탐색 범위를 더 큰 중량으로
 * # 도착 못하면 작은 범위에서 계속 탐색
 * @comment
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1939 {
	
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	// -----------------------------------------------------------------------------

	static class Node {
		int n, w;

		public Node(int n, int w) {
			this.n = n;
			this.w = w;
		}
	}

	static int N, M, max, from, to;
	static List<Node> adjList[];

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// init
		adjList = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}
		max = 0;

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken()); // 가중치
			max = Math.max(max, w); // 가중치 중 최대값 구하기
			adjList[from].add(new Node(to, w));
			adjList[to].add(new Node(from, w));
		}
		st = new StringTokenizer(br.readLine(), " ");
		from = Integer.parseInt(st.nextToken());
		to = Integer.parseInt(st.nextToken());

		// print
		System.out.println(solve());
	}
	
	static int solve() {
		// 이분 탐색
		int l, r, m, ret;
		l = 1;
		r = max;
		ret = 1;

		while (l <= r) {
			m = (l + r) / 2;

			if (bfs(m)) { // 한 공장에서 다른 공장으로 도착 가능하면 더 큰 범위에서 찾아본다.
				ret = m;
				l = m + 1;
			} else {
				r = m - 1;
			}
		}

		return ret;
	}

	static boolean bfs(int m) {
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[N + 1];
		q.offer(from);
		visited[from] = true;

		while (!q.isEmpty()) {
			int cur = q.poll();

			if (cur == to) { // 도착하면
				return true;
			}

			// 현재 노드와 연결된 간선들 구하기
			int size = adjList[cur].size();
			for (int i = 0; i < size; i++) {
				// 모든 간선 꺼내서 가중치 비교
				Node node = adjList[cur].get(i);
				int w = node.w; // 가중치
				int c = node.n; // 노드 번호

				if (visited[c]) { // 방문체크
					continue;
				}

				if (w >= m) { // 기준보다 크거나 같은 가중치일때만
					q.offer(c);
					visited[c] = true;
				}
			}
		}

		return false;
	}

}

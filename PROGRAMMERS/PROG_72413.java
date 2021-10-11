package study.week38;

/**
 * @문제 합승 택시 요금
 * @날짜 211008
 * @분류 
 * @조건
 * #
 * @풀이
 * #
 * @comment
 * #
 */

// 다익스트라

import java.util.*;
import java.io.*;

public class PROG_72413 {

	static class Node implements Comparable<Node> {
		int vertex;
		int totalDistance;

		public Node(int vertex, int totalDistance) {
			this.vertex = vertex;
			this.totalDistance = totalDistance;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.totalDistance, o.totalDistance);
		}
	}

	static int[][] adjMatrix;
	static int[] distanceFromStart, distanceFromA, distanceFromB; // s, a, b를 각 출발지점으로 각 정점으로 가는 최소비용
	static boolean[] visited;

	public static void main(String[] args) {
		int[][] fares = {
				{4, 1, 10},
				{3, 5, 24},
				{5, 6, 2},
				{3, 1, 41},
				{5, 1, 24},
				{4, 6, 50},
				{2, 4, 66},
				{2, 3, 22},
				{1, 6, 25}
		};
		System.out.println(solution(6, 4, 6, 2, fares));
	}

	public static int solution(int n, int s, int a, int b, int[][] fares) {
		// init
		adjMatrix = new int[n + 1][n + 1];
		distanceFromStart = new int[n + 1];
		distanceFromA = new int[n + 1];
		distanceFromB = new int[n + 1];

		// 인접 행렬 구성
		for (int r = 0; r < fares.length; r++) {
			int from = fares[r][0];
			int to = fares[r][1];
			int d = fares[r][2];

			adjMatrix[from][to] = d;
			adjMatrix[to][from] = d;
		}

		// distance 배열 구하기
		distanceFromStart = dijkstra(n, s, distanceFromStart);
		distanceFromA = dijkstra(n, a, distanceFromA);
		distanceFromB = dijkstra(n, b, distanceFromB);

		// 출발지 s를 제외한 임의의 정점 x에 대하여 최소 요금 찾기
		int min = Integer.MAX_VALUE;
		for (int x = 1; x <= n; x++) {
			min = Math.min(min, distanceFromStart[x] + distanceFromA[x] + distanceFromB[x]);
		}

		return min;
	}

	static int[] dijkstra(int n, int s, int[] distance) {
		visited = new boolean[n + 1];

		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[s] = 0;

		PriorityQueue<Node> queue = new PriorityQueue<>();
		queue.offer(new Node(s, distance[s]));

		Node current = null;
		while(!queue.isEmpty()) {
			// 1. 방문하지 않은 정점들 중 최소가중치의 정점 선택
			current = queue.poll();
			if (visited[current.vertex]) continue;
			visited[current.vertex] = true; // 선택 정점 방문 처리

			// current 정점을 경유지로 하여 갈수 있는 다른 방문하지 않은 정점들에 대한 처리
			for (int c = 1; c <= n; c++) {
				if (!visited[c] && adjMatrix[current.vertex][c] != 0
						&& distance[c] > current.totalDistance + adjMatrix[current.vertex][c]){
					distance[c] = current.totalDistance + adjMatrix[current.vertex][c];
					queue.offer(new Node(c, distance[c]));
				}
			}
		}

		return distance;
	}

}
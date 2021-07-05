/**
 * @문제 최소 스패닝 트리_G4
 * @날짜 210706
 * @분류 MST
 * @조건
 * 
 * @풀이
 * 크루스칼
 * @comment
 * 크루스칼 => sparse, 프림 => dense한 그래프에서 성능이 좋음
 * 둘 다 음수 가중치 허용
 */

import java.util.*;
import java.io.*;

public class BOJ_1197 {
	
	// 간선을 나타내는 클래스
	static class Edge implements Comparable<Edge> {
		int from, to, weight;
		
		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	static int V, E;
	static int[] parents;
	static Edge[] edgeList; // 간선 리스트
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		parents = new int[V + 1];
		edgeList = new Edge[E];
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edgeList[i] = new Edge(from, to, weight);
		} // input end
		
		// print
		System.out.println(solve());
	}
	
	static long solve() {
		long result = 0L;
		int count = 0;
		
		make();
		
		Arrays.sort(edgeList); // 간선 리스트 가중치 기준으로 오름차순 정렬
		
		// 간선 리스트 순회
		for (Edge e : edgeList) {
			if (union(e.from, e.to)) { // 서로 다른 집합이면 => 싸이클이 발생하지 않으면
				// 간선 선택
				result += e.weight; // 가중치 누적
				if (++count == V - 1) {
					break;
				}
			}
		}
		
		return result;
	}
	
	static void make() {
		for (int i = 1; i <= V; i++) {
			parents[i] = i;
		}
	}
	
	static int findSet(int a) {
		if (parents[a] == a) return a;
		return parents[a] = findSet(parents[a]); // path compression
	}
	
	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if (aRoot == bRoot) return false;
		
		parents[bRoot] = aRoot;
		
		return true;
	}

}

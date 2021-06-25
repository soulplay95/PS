package week15;
/**
 * @문제 MooTube (Silver)_G5
 * @날짜 210515
 * @분류 
 * @조건
 * N, Q <= 5000
 * @풀이
 * dfs
 * k보다 작은 유사도를 가진 경로는 탐색x
 * @comment
 * 
 */

import java.util.*;
import java.io.*;

public class BOJ_15591 {
	
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	// -----------------------------------------------------------------------------

	static int N, Q;
	static List<int[]>[] adjList;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		adjList = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}
		for (int n = 1; n < N; n++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken()); // 가중치(유사도)
			
			adjList[from].add(new int[] {to, w});
			adjList[to].add(new int[] {from, w});
		}
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int k = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			visited = new boolean[N + 1]; // 매 질문마다 visited 초기화
			sb.append(dfs(k, v) - 1).append("\n"); // v번 노드를 시작점으로 dfs
		}
	
		// print
		System.out.println(sb.toString());
	}
	
	static int dfs(int k, int v) {
		int result = 1;

		visited[v] = true;
		
		// 인접한 노드들을 들여다 보면서 유사도가 k보다 크거나 같으면 dfs
		for (int i = 0, end = adjList[v].size(); i < end; i++) {
			int[] node = adjList[v].get(i); // node[0] : 인접한 노드의 번호, node[1] : 가중치
			if (!visited[node[0]] && node[1] >= k) {
				result += dfs(k, node[0]);
			}
		}
		
		return result;
	}

}
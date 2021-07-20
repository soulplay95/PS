/**
 * @문제 [Professional] 키 순서 D4
 * @날짜 210720
 * @분류 
 * @풀이
 * 자신의 키가 몇번째인지 정확히 알 수 있는 경우 => 자신보다 작은 노드의 개수 + 자신보다 큰 노드의 개수 = N - 1인 경우
 * 자신보다 큰 노드의 개수는 한 노드에서 dfs 타고 들어가면서 방문한 점들의 개수
 * 자신보다 작은 노드의 개수는 dfs 타고 들어가는 과정에서 방문한 노드들의 count를 1증가시켜주면서 누적하면 구해짐.
 * 인접 리스트 이용
 * @comment
 * 
 */

import java.util.*;
import java.io.*;

public class SWEA_5643 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static class Node {
		int vertexNum; // 노드 번호
		Node next;

		public Node(int vertexNum, Node next) {
			this.vertexNum = vertexNum;
			this.next = next;
		}
	}
	
	static int N; // <= 500
	static int M; // 비교 횟수. 간선 수
	static int[] counts; // 자신보다 작은 + 큰 노드의 개수를 담는 배열
	static boolean[] visited; // dfs 탐색 시 방문 체크를 위한 배열
	static Node[] adjList; // 연결 리스트로 구현한 인접 리스트
	static int upCounts; // dfs 과정에서 방문한(자신보다 큰 노드의 개수) 노드 개수
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			adjList = new Node[N + 1];
			counts = new int[N + 1];
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				adjList[from] = new Node(to, adjList[from]); // head쪽으로 연결
			}
			
			// 모든 노드에 대하여 dfs
			for (int i = 1; i <= N; i++) {
				visited = new boolean[N + 1];
				upCounts = 0;
				dfs(i);
				counts[i] += upCounts; // 자신보다 큰 노드 개수 누적
			}
			
			// counts 값이 N - 1인 노드의 개수 세기.
			int ans = 0;
			for (int i = 1; i <= N; i++) {
				if (counts[i] == N - 1) {
					ans++;
				}
			}
			
			// TC append
			sb.append('#').append(t).append(' ').append(ans).append('\n');
		}
		
		// print
		System.out.println(sb.toString());
	}
	
	static void dfs(int cur) {
		visited[cur] = true;
		
		for (Node temp = adjList[cur]; temp != null; temp = temp.next) {
			if (visited[temp.vertexNum]) {
				continue;
			}
			
			counts[temp.vertexNum]++; // 방문한 노드 체크
			upCounts++; // 방문한 노드 개수 증가
			dfs(temp.vertexNum);
		}
	}

}
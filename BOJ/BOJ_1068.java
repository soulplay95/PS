/**
 * @문제 트리_G5
 * @날짜 211213
 * @분류 그래프 탐색, 트리, DFS
 * @조건
 * # 1 <= 노드 개수 (N) <= 50
 * @풀이
 * # 인접 리스트로 노드 구성
 * # 삭제할 노드에서 dfs 탐색하여 해당 노드 포함 모든 자식 노드에 삭제 표시
 * # 삭제할 노드의 부모 노드를 찾아서 size 1 감소
 * # 리프 노드 개수 : 인접 리스트에서 삭제 표시가 안된 노드 중 size가 0인 노드의 개수 
 * @comment
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_1068 {

	private static int N;
	private static boolean[] deleted;
	private static ArrayList<Integer>[] adjList;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		// init
		deleted = new boolean[N];
		adjList = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			adjList[i] = new ArrayList<>();
		}

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			int parent = Integer.parseInt(st.nextToken());
			if (parent == -1) continue;

			adjList[parent].add(i);
		}
		int deleteNode = Integer.parseInt(br.readLine()); // 삭제할 노드 번호

		dfs(deleteNode); // 해당 노드 포함 모든 자식 노드들 삭제
		findParentNodeFromDeletedNode(deleteNode); // 삭제할 노드의 부모 노드 번호를 찾아서 인접 리스트 사이즈 1 감소

		// print
		System.out.println(getLeafNodeCounts());
	}

	private static void dfs(int index) {
		deleted[index] = true; // 삭제 표시

		for (Integer child : adjList[index]) {
			dfs(child);
		}
	}

	private static void findParentNodeFromDeletedNode(int target) {
		// 모든 노드의 자식 노드들을 탐색
		for (int i = 0; i < N; i++) {
			for (Integer child : adjList[i]) {
				if (child == target) { // 타겟 노드의 부모를 찾았으면
					adjList[i].remove(child); // 인접 리스트에서 삭제
					return;
				}
			}
		}
	}

	private static int getLeafNodeCounts() {
		// 모든 노드를 탐색해서 인접 리스트 사이즈가 0인 노드의 개수를 센다.
		int counts = 0;

		for (int i = 0; i < N; i++) {
			if (!deleted[i] && adjList[i].size() == 0) {
				counts++;
			}
		}

		return counts;
	}

}

/**
 * @문제 최단경로_G5
 * @날짜 210729
 * @분류
 * @조건
 *
 * @풀이
 * 방향 그래프
 * 정점 번호 1부터
 * 서로 다른 두 정점 사이에 여러 개의 간선이 존재할 수도 있음
 * 출력 : 시작점 자신은 0으로 출력. 경로가 존재하지 않으면 INF 출력
 * # 인접 행렬 -> 메모리 초과? 인접 리스트로
 * @comment
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1753 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int V = Integer.parseInt(st.nextToken()); // 정점 개수
		int E = Integer.parseInt(st.nextToken()); // 간선 개수
		int start = Integer.parseInt(br.readLine()); // 시작 정점 번호
		int[][] adjMatrix = new int[V + 1][V + 1]; // 인접 행렬. 정점 번호는 1부터
		// 인접 행렬 비용 최대값(11~)로 초기화
		for (int i = 1; i <= V; i++) {
			for (int j = 1; j <= V; j++) {
				adjMatrix[i][j] = 11;
			}
		}
		// 간선 정보 입력 받아 인접 행렬 채우기
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adjMatrix[from][to] = weight;
		}
		List<Integer> selectedVertex = new ArrayList<>(); // 선택된 정점 집합
		
		int[] minDistance = new int[V + 1]; // 시작정점에서의 최단 거리
		dijkstra(start, adjMatrix, minDistance, V, selectedVertex);
		
	}
	
	static void dijkstra(int start, int[][] adjMatrix, int[] minDistance, int V, List<Integer> selectedVertex) {
		selectedVertex.add(start);
		
		// minDistance 배열 초기화(시작 정점으로부터 인접한 정점의 비용으로)
		for (int i = 1; i <= V; i++) {
			minDistance[i] = adjMatrix[start][i];
		}
		
		// 모든 정점이 선택되기 전까지 반복
		while (selectedVertex.size() < V) {
			
		}
	}

}

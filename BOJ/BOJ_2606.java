/**
 * @문제 바이러스
 * @날짜 210620
 * @분류 
 * @조건
 * 
 * @풀이
 * 그래프 정보 나타내고
 * BFS
 * @comment
 * 간선 정보가 주어질 때 그래프 그리기 익숙해지기 => 인접 행렬, 인접 리스트?
 */

import java.util.*;
import java.io.*;

public class BOJ_2606 {
	
	static int N;
	static boolean[][] adjMatrix;
	static boolean[] visited;
	static int ans;
	
	public static void main(String[] agrs) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		adjMatrix = new boolean[N + 1][N + 1];
		int e = Integer.parseInt(br.readLine());
		for (int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			// 양방향 연결
			adjMatrix[a][b] = true;
			adjMatrix[b][a] = true;
		} // input end
		
		solve();
		
		// print
		System.out.println(ans);
	}
	
	static void solve() {
		bfs();
	}

	static void bfs() {
		visited = new boolean[N + 1];
		
		Queue<Integer> q = new LinkedList<>();
		q.offer(1); // 1번부터 시작
		visited[1] = true;
		
		while (!q.isEmpty()) {
			int cur = q.poll();
			
			// 모든 열 검사
			for (int i = 1; i <= N; i++) {
				if (!visited[i] && adjMatrix[cur][i]) {
					ans++;
					q.offer(i);
					visited[i] = true;
				}
			}
		}
		
		return;
	}
}
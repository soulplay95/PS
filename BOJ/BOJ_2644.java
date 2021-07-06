/**
 * @문제 촌수계산_S2
 * @날짜 210706
 * @분류 DFS
 * @조건
 * 
 * @풀이
 * 인접행렬로 그래프 나타내고 dfs
 * @comment
 * 
 */

import java.util.*;
import java.io.*;

public class BOJ_2644 {
	
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	// -----------------------------------------------------------------------------
	
	static int N, M;
	static boolean[][] adjMatrix;
	static boolean[] visited;
	static int start, end; // 촌수를 구하려는 두 사람 번호

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(br.readLine());
		adjMatrix = new boolean[N + 1][N + 1];
		visited = new boolean[N + 1];
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adjMatrix[a][b] = true;
			adjMatrix[b][a] = true;
		} // input end
		
		solve();
		
		// print
		System.out.println(-1);
	}
	
	static void solve() {
		dfs(start, 0);
	}

	static void dfs(int i, int depth) {
		visited[i] = true; // 방문 체크
		
		// 바닥조건
		if (i == end) {
			System.out.println(depth);
			System.exit(0);
		}
		
		for (int j = 1; j <= N; j++) {
			if (!visited[j] && adjMatrix[i][j]) { // 방문하지 않았고 1촌으로 인접해있으면
				dfs(j, depth + 1);
			}
		}
	}
}

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JUNGOL_1681 {
	static int N;
	static int[][] adjMatrix;
	static boolean[] visited;
	static int minCost;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		adjMatrix = new int[N][N];
		visited = new boolean[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				adjMatrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		minCost = 500;
		dfs(0, 0, 0);
		
		// print
		System.out.println(minCost);
	}
	
	static void dfs(int from, int depth, int cost) {
		if (cost > minCost) return; // 가지치기
		// 한바퀴 돌았으면 돌아가야 함
		if (depth == N - 1) {
			if (adjMatrix[from][0] == 0) return; // 못가는 길
			minCost = Math.min(minCost, cost + adjMatrix[from][0]); // 한번씩 돌고 마지막으로 돌아오는 비용 중 최소값
		}
		visited[from] = true;
		for (int i = 1; i < N; i++) {
			if (!visited[i] && adjMatrix[from][i] != 0) {
				dfs(i, depth + 1, cost + adjMatrix[from][i]);
				visited[i] = false;
			}
		}
	}

}

/*
 * 해밀턴 순환회로
 * SSAFY_210322HW
 */

/**
* 1 <= N <= 12
* 0 <= 비용 < 100
* 단방향 그래프
* 최소 신장 트리 => 인접 행렬이 주어졌으므로 Prim 알고리즘 사용 (x)
* dfs 가지치기
*/
/**
 * @문제 연결 요소의 개수_S2
 * @날짜 210916
 * @분류 
 * @조건
 * # 1 <= 정점 개수(N) <= 1000
 * @풀이
 * # 주어진 간선 정보로 인접 행렬을 구성하고, 1번 노드부터 dfs를 돌아 visited 체크한다.
 * @comment
 * # union-find로 풀이 가능
 */

import java.util.*;
import java.io.*;

public class BOJ_11724 {

	static int N, M;
	static boolean[][] adjMatrix;
	static boolean[] visited;
	static int ans;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		// init
		adjMatrix = new boolean[N + 1][N + 1];
		visited = new boolean[N + 1];
		ans = 0;

		for (int m = 0; m < M; m++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			adjMatrix[a][b] = true;
			adjMatrix[b][a] = true;
		} // input end

		solve();

		// print
		System.out.println(ans);

		sc.close();
	}

	static void solve() {
		for (int i = 1; i <= N; i++) {
			if (!visited[i]) {
				dfs(i);
				ans++;
			}
		}
	}

	static void dfs(int n) {
		if (visited[n]) {
			return;
		}

		visited[n] = true;
		for (int i = 1; i <= N; i++) {
			if (adjMatrix[n][i]) {
				dfs(i);
			}
		}
	}

}

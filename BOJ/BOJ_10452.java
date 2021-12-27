/**
 * @문제 순열 사이클_S2
 * @날짜 210914
 * @분류 
 * @조건
 * # 2 <= 순열의 크기(N) <= 1000
 * @풀이
 * # 순열의 모든 수에 대해 방문하지 않았으면 dfs를 돌아 방문체크 하면서 싸이클을 찾는다.
 * @comment
 * 
 */

import java.util.*;
import java.io.*;

public class BOJ_10452 {

	static int N;
	static int[] input;
	static boolean[] visited;
	static int ans;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			N = sc.nextInt();

			// init
			input = new int[N + 1];
			visited = new boolean[N + 1];
			ans = 0;

			for (int n = 1; n <= N; n++) {
				input[n] = sc.nextInt();
			} // input end

			solve();

			// print
			System.out.println(ans);
		}

		sc.close();
	}

	static void solve() {
		// 1. 모든 순열의 수에 대해 방문하지 않았으면 dfs 탐색 시작
		for (int n = 1; n <= N; n++) {
			if (!visited[n]) {
				dfs(n);
			}
		}
	}

	static void dfs(int n) {
		// base condition
		if (visited[n]) {
			// 방문했으면 사이클 개수 증가시키고 return
			ans++;

			return;
		}

		visited[n] = true; // 방문 체크
		dfs(input[n]); // 다음 노드
	}

}

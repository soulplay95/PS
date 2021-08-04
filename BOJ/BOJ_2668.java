/**
 * @문제 숫자고르기_G5
 * @날짜 210804
 * @분류 
 * @조건
 * 
 * @풀이
 * input을 Set에 넣고 없는 숫자는 제외시킨다.
 * 순회하면서 dfs 돌린다.
 * @comment
 * 
 */

import java.util.*;
import java.io.*;

public class BOJ_2668 {
	
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	// -----------------------------------------------------------------------------
	
	static int N;
	static int[] input;
	static boolean[] visited, stop;
	static Set<Integer> ans;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		input = new int[N + 1];
		stop = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			input[i] = Integer.parseInt(br.readLine());
			stop[input[i]] = true;
		} // input end
		
		solve();
		
		// print
		System.out.println(sb.toString());
	}
	
	static void solve() {
		// init
		ans = new HashSet<>();
		
		for (int i = 1; i <= N; i++) {
			if (stop[i]) {
				visited = new boolean[N + 1];
				dfs(i, 0, 0);
			}
		}
		
		// ans append
		sb.append(ans.size()).append("\n");
		for (int cur : ans) {
			sb.append(cur).append("\n");
		}
	}

	static void dfs(int i, int a, int b) {
		if (i == input[i]) {
			ans.add(i);
			
			return;
		}
		
		visited[i] = true;
		
		if (visited[input[i]]) { // 싸이클 발생
			ans.add(i);
		} else {
			dfs(input[i], a + 1, b + 1);
		}
		
	}
}
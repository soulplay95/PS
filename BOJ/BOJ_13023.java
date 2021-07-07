/**
 * @문제 ABCDE_G5
 * @날짜 210708
 * @분류 
 * @조건
 * N, M <= 2000
 * @풀이
 * 입력을 그래프로 나타내고, dfs를 돌아 depth가 4까지 들어가면 1을 리턴한다.
 * @comment
 * 그래프를 인접 리스트로 나타내보기
 * 자바에서 리스트의 배열을 생성할 때에는 new ArrayList[N]으로 선언한다.
 * DFS 재귀 타면서 방문체크, 방문체크 해제 해주는거 익숙해지기
 * LinkedList로 하면 시간초과 => why?
 * Node[]로 푸는 방식
 */

import java.util.*;
import java.io.*;

public class BOJ_13023 {
	
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	// -----------------------------------------------------------------------------
	
	static int N, M;
	static boolean[] visited;
	static List<Integer>[] adjList; // 그래프를 나타내기 위한 인접 리스트
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		visited = new boolean[N];
		adjList = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			adjList[i] = new ArrayList<>();
		}
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adjList[a].add(b);
			adjList[b].add(a);
		} // input end
		
		// print
		System.out.println(solve());
	}
	
	static int solve() {
		// 모든 번호의 사람을 dfs 돌린다.
		for (int i = 0; i < N; i++) {
			dfs(i, 0);
		}
		
		return 0;
	}
	
	static void dfs(int n, int depth) {
		// 종료 조건
		if (depth == 4) {
			System.out.println(1);
			System.exit(0);
		}
		
		visited[n] = true; // 방문 체크
		
		for (int i : adjList[n]) {
			int next = i;
			if (!visited[next]) {
				dfs(next, depth + 1);
			}
		}
		
		visited[n] = false; // 방문체크 해제
	}

}

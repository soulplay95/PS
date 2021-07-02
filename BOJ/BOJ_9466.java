package week21;
/**
 * @문제 텀 프로젝트_G4
 * @날짜 210702
 * @분류 DFS
 * @조건
 * 2 <= n <= 10만
 * @풀이
 * # 싸이클이 발생하는 경우 한 팀이 된다.
 * # 모든 학생의 선택에 대한 dfs의 끝은 싸이클이 발생한 경우이다.
 * # 싸이클이 발생하면(지나온 길을 재방문한 경우) 길을 되돌아가면서 (재귀를 return 하면서) 싸이클이 발생한 지점까지의 학생들은 팀이 확정이고
 * 그 이후의 학생들은 반드시 팀에 속할 수 없다.(시작과 끝이 본인인 싸이클을 만들 수 없다는게 정해졌으므로)
 * # 전체 학생을 순회하면서 팀에 이미 속한 or 팀에 속하지 않는 학생을 제외하고 dfs탐색 진행
 * @comment
 * 1 try : 문제 잘못 이해 => sr이 s1을 선택하는 경우에만 한 팀임
 * 2 try : 시간초과 반례 : 2 3 4 .... 10만 10만 => O(n^2)으로 풀 수 없다.
 */

import java.util.*;
import java.io.*;

public class BOJ_9466 {
	
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	// -----------------------------------------------------------------------------
	
	static int N; // 총 학생수
	static boolean[] done; // 고려한애들
	static boolean[] visited; // 방문체크
	static int[] input; // input[i] : i번 학생이 선택한 학생 번호
	static int count; // 팀을 이룬 학생 수
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			done = new boolean[N + 1];
			visited = new boolean[N + 1];
			input = new int[N + 1];
			st = new StringTokenizer(br.readLine(), " ");
			for (int n = 1; n <= N; n++) {
				input[n] = Integer.parseInt(st.nextToken());
			}
			
			solve();
			
			// TC append
			sb.append(N - count).append('\n');
		}
		
		// print
		System.out.println(sb.toString());
	}
	
	static void solve() {
		count = 0;
		
		// 전체 학생을 순회하여 팀에 속할 학생인지 판단한다
		for (int i = 1; i <= N; i++) {
			if (!visited[i]) {
				dfs(i);
			}
		}
	}
	
	static void dfs(int i) {
		visited[i] = true; // 방문 체크
		
		if (!visited[input[i]]) { // 방문 하지 않은 경우에만 dfs
			dfs(input[i]);
		}
		
		// 재방문이 일어난 시점
		// base condition - 싸이클이 생성
		if (!done[input[i]]) { // 고려안된 학생이면
			// 싸이클 한바퀴 돌려서 팀원 수 세기
			for (int j = input[i]; j != i; j = input[j]) {
				count++;
			}
			count++;
		}
		
		done[i] = true; // 고려되었음을 체크
	}

}

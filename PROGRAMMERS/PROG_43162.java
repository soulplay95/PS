/**
 * @문제 네트워크
 * @날짜 210907
 * @분류 DFS, BFS
 * @조건
 * # 1 <= n <= 200
 * @풀이
 * # BFS로 visited 체크해가면서 네트워크 개수 구하기
 * @comment
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class PROG_43162 {
	
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	// -----------------------------------------------------------------------------

	public static void main(String[] args) throws IOException {
		// print
		System.out.println(solution(3, new int[][] {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}}));
	}

	static boolean[] visited;

	public static int solution(int n, int[][] computers) {
		int answer = 0;
		// init
		visited = new boolean[n];

		for (int r = 0; r < n; r++) {
			if (!visited[r]) {
				answer += bfs(r, n, computers);
			}
		}

		return answer;
	}

	public static int bfs(int r, int n, int[][] computers) {
		// bfs 타고 들어가면서 같은 네트워크의 노드들 visited 체크
		Queue<Integer> q = new LinkedList<>();
		visited[r] = true;
		q.offer(r);

		while (!q.isEmpty()) {
			int cur = q.poll();

			// 다른 모든 노드에 대해 검사
			for (int c = 0; c < n; c++) {
				// 방문 체크
				if (visited[c]) continue;

				// 연결 체크
				if (computers[cur][c] == 1) {
					visited[c] = true;
					q.offer(c);
				}
			}
		}

		return 1;
	}

}

/**
 * @문제 숨바꼭질 4_G4
 * @날짜 211020
 * @분류 그래프 탐색, BFS
 * @조건
 * # 0 <= 수빈, 동생 위치 (N, K) <= 10만
 * @풀이
 * # BFS
 * @comment
 * # 경로 추적 => 해당 위치에 도착 하기 전 이전 위치를 저장한다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_13913 {

	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	// -----------------------------------------------------------------------------

	static class User {
		int pos, time;

		public User(int pos, int time) {
			this.pos = pos;
			this.time = time;
		}
	}

	static int N, K, ans;
	static boolean[] visited;
	static int[] parents;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		// init
		visited = new boolean[100001];
		parents = new int[100001];
		ans = solve();

		// print
		System.out.println(ans);

		Stack<Integer> stack = new Stack<>();
		int i = K;
		while (i != N) {
			stack.push(i);
			i = parents[i];
		}
		stack.push(i);

		while (!stack.isEmpty()) {
			System.out.print(stack.pop() + " ");
		}
	}

	static int solve() {
		Queue<User> q = new LinkedList<>();
		q.offer(new User(N, 0));
		visited[N] = true;

		while (!q.isEmpty()) {
			User cur = q.poll();
			int now = cur.pos;
			int time = cur.time;

			// 종료 조건
			if (now == K) {
				return time;
			}

			if (now - 1 >= 0 && !visited[now - 1]) {
				visited[now - 1] = true;
				parents[now - 1] = now;
				q.offer(new User(now - 1, time + 1));
			}
			if (now + 1 <= 100000 && !visited[now + 1]) {
				visited[now + 1] = true;
				parents[now + 1] = now;
				q.offer(new User(now + 1, time + 1));
			}
			if (now * 2 >= 0 && now * 2 <= 100000 && !visited[now * 2]) {
				visited[now * 2] = true;
				parents[now * 2] = now;
				q.offer(new User(now * 2, time + 1));
			}
		}

		return 0;
	}

}

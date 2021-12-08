/**
 * @문제 숨바꼭질 3_G5
 * @날짜 211208
 * @분류 그래프 탐색, BFS, 다익스트라, 0-1 BFS
 * @조건
 * # 0 <= 수빈이, 동생 위치 (N, K) <= 10만
 * @풀이
 * # BFS
 * 순간이동을 먼저 처리한다 => offerFirst
 * @comment
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_13549 {

	private static class User {
		int pos, time;

		public User(int pos, int time) {
			this.pos = pos;
			this.time = time;
		}
	}

	private static int N, K;
	private static boolean[] visited;
	private static final int MAX = 100001;

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		// init
		visited = new boolean[MAX];

		// print
		System.out.println(bfs());

		sc.close();
	}

	private static int bfs() {
		Deque<User> q = new LinkedList<>();
		q.offer(new User(N, 0));
		visited[N] = true;

		while (!q.isEmpty()) {
			User cur = q.poll();

			// 도착 여부 체크
			if (cur.pos == K) {
				return cur.time;
			}

			if (cur.pos * 2 < MAX && !visited[cur.pos * 2]) {
				visited[cur.pos * 2] = true;
				q.offerFirst(new User(cur.pos * 2, cur.time));
			}
			if (cur.pos < MAX - 1 && !visited[cur.pos + 1]) {
				visited[cur.pos + 1] = true;
				q.offerLast(new User(cur.pos + 1, cur.time + 1));
			}
			if (cur.pos > 0 && !visited[cur.pos - 1]) {
				visited[cur.pos - 1] = true;
				q.offerLast(new User(cur.pos - 1, cur.time + 1));
			}
		}

		return -1;
	}

}

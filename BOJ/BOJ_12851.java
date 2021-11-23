/**
 * @문제 숨바꼭질 2_G5
 * @날짜 211123
 * @분류 BFS
 * @조건
 * # 0 <= 위치 (N, K) <= 10만
 * @풀이
 * # BFS로 도착하면 시간을 측정하고 개수를 카운트한다.
 * @comment
 */

import java.util.*;
import java.io.*;

public class BOJ_12851 {

	static int N, K;
	static int[] time, counts;
	static boolean[] visited;

	static int[] dr = {-1, 1, 2};

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		// init
		time = new int[100001];
		counts = new int[100001];
		visited = new boolean[100001];

		solve();

		// print
		System.out.println(time[K]);
		System.out.println(counts[K]);

		sc.close();
	}

	static void solve() {
		Queue<Integer> q = new LinkedList<>();
		counts[N] = 1;
		visited[N] = true;
		q.offer(N);

		while (!q.isEmpty()) {
			int cur = q.poll();

			// 3가지 이동 유형
			for (int d = 0; d < 3; d++) {
				int newPos = cur + dr[d];
				if (d == 2) {
					newPos = cur * dr[d];
				}

				// 경계 체크
				if (newPos < 0 || newPos > 100000) {
					continue;
				}

				if (!visited[newPos]) {
					q.offer(newPos);
					visited[newPos] = true;

					counts[newPos] = counts[cur];
					time[newPos] = time[cur] + 1;
				} else if (time[newPos] == time[cur] + 1) {
					counts[newPos] += counts[cur];
				}
			}
		}
	}

}

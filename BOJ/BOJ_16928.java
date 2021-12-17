package study.week47;

/**
 * @문제 뱀과 사다리 게임_S1
 * @날짜 211216
 * @분류 
 * @조건
 * # 1 <= 사다리의 수, 뱀의 수 (N, M) <= 15
 * @풀이
 * # BFS
 * @comment
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_16928 {

	private static int[] movePoints; // 사다리, 뱀에 의해 이동하게 되는 칸

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		// init
		movePoints = new int[101];

		for (int i = 0, end = N + M; i < end; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			movePoints[from] = to;
		}

		// print
		System.out.println(bfs());
	}

	private static int bfs() {
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[101];
		visited[1] = true;
		q.offer(1);
		int moves = 0;

		while (!q.isEmpty()) {
			int size = q.size();

			while (size-- > 0) {
				Integer cur = q.poll();

				if (cur == 100) {
					return moves;
				}

				for (int i = 1; i <= 6; i++) {
					if (cur + i > 100) continue;
					int nextPoint = (movePoints[cur + i] == 0 ? cur + i : movePoints[cur + i]);

					if (!visited[nextPoint]) {
						visited[cur + i] = true;
						visited[nextPoint] = true;
						q.offer(nextPoint);
					}
				}
			}

			moves++;
		}

		return moves;
	}

}

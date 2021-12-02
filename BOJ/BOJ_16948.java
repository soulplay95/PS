/**
 * @문제 데스 나이트_S1
 * @날짜 211202
 * @분류 BFS
 * @조건
 * # 5 <= 맵 크기 (N) <= 200
 * @풀이
 * # 기본적인 BFS 문제
 * @comment
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_16948 {

	private static class Pair {
		int r, c;

		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	private static int N;
	private static boolean[][] visited;
	private static Pair p1, p2;

	private static int[] dr = {-2, -2, 0, 0, 2, 2};
	private static int[] dc = {-1, 1, -2, 2, -1, 1};

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		// init
		visited = new boolean[N][N];
		p1 = new Pair(sc.nextInt(), sc.nextInt());
		p2 = new Pair(sc.nextInt(), sc.nextInt());

		// print
		System.out.println(bfs());

		sc.close();
	}

	private static int bfs() {
		Queue<Pair> q = new LinkedList<>();
		q.offer(p1);
		visited[p1.r][p1.c] = true;
		int move = 0;

		while (!q.isEmpty()) {
			int size = q.size();

			while (size-- > 0) {
				Pair cur = q.poll();

				// 도착 여부 체크
				if (cur.r == p2.r && cur.c == p2.c) {
					return move;
				}

				for (int d = 0; d < 6; d++) {
					int nr = cur.r + dr[d];
					int nc = cur.c + dc[d];

					if (isOut(nr, nc)) {
						continue;
					}

					if (!visited[nr][nc]) {
						visited[nr][nc] = true;
						q.offer(new Pair(nr, nc));
					}
				}
			}

			move++;
		}

		return -1;
	}

	private static boolean isOut(int r, int c) {
		return (r < 0 || r >= N || c < 0 || c >= N);
	}

}

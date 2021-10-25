/**
 * @문제 나이트의 이동_S2
 * @날짜 211025
 * @분류 그래프 탐색, BFS
 * @조건
 * # 4 <= 맵 크기 N <= 300
 * # 좌표 0부터
 * @풀이
 * # delta 조건에 맞게 설정
 * # BFS
 * @comment
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_7562 {

	static class Pair {
		int r, c;

		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int N;
	static boolean[][] map;
	static Pair start, end;

	static int[] dr = {-1, -2, -2, -1, 1, 2, 2, 1};
	static int[] dc = {-2, -1, 1, 2, 2, 1, -1, -2};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			// init
			map = new boolean[N][N];
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			start = new Pair(r, c);
			map[r][c] = true;
			st = new StringTokenizer(br.readLine(), " ");
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			end = new Pair(r, c);

			sb.append(bfs()).append("\n");
		}

		// print
		System.out.print(sb.toString());
	}

	static int bfs() {
		int moves = 0;
		Queue<Pair> q = new LinkedList<>();
		q.offer(start);

		while (!q.isEmpty()) {
			int size = q.size();

			while (size-- > 0) {
				Pair cur = q.poll();

				// 도착 체크
				if (cur.r == end.r && cur.c == end.c) {
					return moves;
				}

				for (int d = 0; d < 8; d++) {
					int nr = cur.r + dr[d];
					int nc = cur.c + dc[d];

					// 경계 체크
					if (isOut(nr, nc)) {
						continue;
					}

					// 방문 체크
					if (!map[nr][nc]) {
						map[nr][nc] = true;
						q.offer(new Pair(nr, nc));
					}
				}
			}

			moves++;
		}

		return moves;
	}

	static boolean isOut(int r, int c) {
		return (r < 0 || r >= N || c < 0 || c >= N);
	}

}

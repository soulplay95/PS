/**
 * @문제 불_G4
 * @날짜 220101
 * @분류 구현, BFS
 * @조건
 * # 맵 크기 <= 1000
 * @풀이
 * # BFS
 * @comment
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_5427 {

	private static class Pair {
		int r, c;

		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	private static int R, C;
	private static char[][] map;
	private static Queue<Pair> fire, user;
	private static StringBuilder sb = new StringBuilder();

	private static int[] dr = {-1, 0, 1, 0}; // 상우하좌 시계방향
	private static int[] dc = {0, 1, 0, -1}; // 상우하좌 시계방향

	private static final String FAIL_MESSAGE = "IMPOSSIBLE";

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			C = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			// init
			map = new char[R][C];
			fire = new LinkedList<>();
			user = new LinkedList<>();

			for (int r = 0; r < R; r++) {
				String row = br.readLine();
				for (int c = 0; c < C; c++) {
					map[r][c] = row.charAt(c);
					if (map[r][c] == '*') {
						fire.add(new Pair(r, c));
					} else if (map[r][c] == '@') {
						user.add(new Pair(r, c));
					}
				}
			} // input end

			int result = bfs();

			sb.append(result == -1 ? FAIL_MESSAGE : result).append("\n");
		}

		// print
		System.out.print(sb.toString());
	}

	private static int bfs() {
		int time = 0;

		while (!(fire.isEmpty() && user.isEmpty())) {
			// 불 먼저 확장
			int size = fire.size();
			while (size-- > 0) {
				Pair cur = fire.poll();

				for (int d = 0; d < 4; d++) {
					int nr = cur.r + dr[d];
					int nc = cur.c + dc[d];

					// 경계 체크
					if (isOut(nr, nc)) {
						continue;
					}
					if (map[nr][nc] == '.' || map[nr][nc] == '@') { // 벽이 아닐때, 현재 싸이클에서 이미 방문한(불이 확장된) 지점 아닐때 확장
						map[nr][nc] = '*';
						fire.add(new Pair(nr, nc));
					}
				}
			}

			// 상근이 이동
			size = user.size();
			while (size-- > 0) {
				Pair cur = user.poll();

				for (int d = 0; d < 4; d++) {
					int nr = cur.r + dr[d];
					int nc = cur.c + dc[d];

					// 도착 체크
					if (isOut(nr, nc)) {
						return time + 1;
					}
					if (map[nr][nc] == '.') {
						map[nr][nc] = '@';
						user.add(new Pair(nr, nc));
					}
				}
			}

			time++; // 1초 증가
		}

		return -1;
	}

	private static boolean isOut(int r, int c) {
		return (r < 0 || r >= R || c < 0 || c >= C);
	}

}

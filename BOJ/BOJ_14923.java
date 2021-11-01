/**
 * @문제 미로 탈출_G4
 * @날짜 211101
 * @분류 그래프 탐색, BFS
 * @조건
 * # # 2 <= 맵 크기 N, M <= 1000
 * # 주어진 좌표 1부터
 * @풀이
 * # visited의 차원을 하나 더 두고 BFS
 * @comment
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_14923 {

	static class Pair {
		int r, c, t;
		boolean isUsed;

		public Pair(int r, int c, int t, boolean isUsed) {
			this.r = r;
			this.c = c;
			this.t = t;
			this.isUsed = isUsed;
		}
	}

	static int N, M;
	static Pair start, end;
	static int[][] map;
	static boolean[][][] visited;

	static int[] dr = {-1, 0, 1, 0}; // 상우하좌 시계방향
	static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");
		int startR = Integer.parseInt(st.nextToken()) - 1;
		int startC = Integer.parseInt(st.nextToken()) - 1;
		st = new StringTokenizer(br.readLine(), " ");
		int endR = Integer.parseInt(st.nextToken()) - 1;
		int endC = Integer.parseInt(st.nextToken()) - 1;
		// init
		start = new Pair(startR, startC, 0, false);
		end = new Pair(endR, endC, 0, false);
		map = new int[N][M];
		visited = new boolean[N][M][2];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// print
		System.out.println(solve());
	}

	static int solve() {
		Queue<Pair> q = new LinkedList<>();
		visited[start.r][start.c][0] = true;
		q.offer(new Pair(start.r, start.c, 0, false));

		while (!q.isEmpty()) {
			Pair cur = q.poll();

			// 도착 체크
			if (cur.r == end.r && cur.c == end.c) {
				return cur.t;
			}

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				if (isOut(nr, nc)) continue;
				if (map[nr][nc] == 0) {
					if (cur.isUsed && !visited[nr][nc][1]) {
						visited[nr][nc][1] = true;
						q.offer(new Pair(nr, nc, cur.t + 1, true));
					} else if (!cur.isUsed && !visited[nr][nc][0]){
						visited[nr][nc][0] = true;
						q.offer(new Pair(nr, nc, cur.t + 1, false));
					}
				}
				if (!cur.isUsed && map[nr][nc] == 1 && !visited[nr][nc][1]) {
					visited[nr][nc][1] = true;
					q.offer(new Pair(nr, nc, cur.t + 1, true));
				}
			}
		}

		return -1;
	}

	static boolean isOut(int r, int c) {
		return (r < 0 || r >= N || c < 0 || c >= M);
	}

}

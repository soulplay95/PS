/**
 * @문제 치즈_G5
 * @날짜 211215
 * @분류 
 * @조건
 * # 맵 크기 <= 100
 * @풀이
 * # 가장자리로 부터 BFS를 돌려 공기가 있는 구멍을 체크한다.
 * # 치즈 위치에서 BFS를 돌려 4방에 공기가 있는 구멍이 하나라도 있으면 제거한다.
 * # BFS 사이클마다 제거한 개수를 카운트하여 남은 치즈가 0개가 되면 전 단계의 남은 치즈 개수를 센다.
 * @comment
 */

import java.util.*;
import java.io.*;

public class BOJ_2636 {

	private static class Pair {
		int r, c;

		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	private static int R, C, time, cheeseCounts, cheeseCountsBeforeOneHour; // 치즈 개수, 1시간 전 치즈 개수
	private static int[][] map; // 0 : 닫힌 구멍, 1 : 치즈, 2 : 열린 구멍
	private static StringBuilder sb = new StringBuilder();

	private static int[] dr = {-1, 0, 1, 0}; // 상우하좌 시계방향
	private static int[] dc = {0, 1, 0, -1}; // 상우하좌 시계방향

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		// init
		map = new int[R][C];
		cheeseCounts = 0;
		time = 0;

		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 1) {
					cheeseCounts++;
				}
			}
		}

		solve();

		// print
		System.out.println(sb.toString());
	}

	private static void solve() {
		openHoleMark(); // 열린 구멍을 map에서 2로 마킹한다.
		cheeseCountsBeforeOneHour = cheeseCounts;

		while (true) {
			if (cheeseCounts == 0) {
				sb.append(time).append("\n");
				sb.append(cheeseCountsBeforeOneHour).append("\n");
				return;
			}

			if (time > 0) {
				markClosedHoleToOpenHole(); // 기존의 닫힌 구멍이었던 곳을 열린 구멍으로 마킹한다.
			}

			cheeseCountsBeforeOneHour = cheeseCounts; // 녹이기 전 치즈 개수 저장

			melt(); // 치즈를 녹인다.

			time++; // 1시간 흐름
		}
	}

	private static void melt() {
		// 치즈 녹이기
		Queue<Pair> q = new LinkedList<>();

		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (map[r][c] == 1) {
					for (int d = 0; d < 4; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];

						if (map[nr][nc] == 2) {
							q.offer(new Pair(r, c));
							break;
						}
					}
				}
			}
		}

		cheeseCounts -= q.size();
		for (Pair p : q) {
			map[p.r][p.c] = 2;
		}
	}

	private static void openHoleMark() {
		// 가장자리 중 한 칸 (0, 0)에서 BFS를 돌아 열린 칸을 2로 채운다.
		Queue<Pair> q = new LinkedList<>();
		boolean[][] visited = new boolean[R][C];
		q.offer(new Pair(0, 0));
		visited[0][0] = true;
		map[0][0] = 2;

		while (!q.isEmpty()) {
			Pair cur = q.poll();

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				if (isOut(nr, nc)) continue;
				if (!visited[nr][nc] && map[nr][nc] == 0) {
					visited[nr][nc] = true;
					map[nr][nc] = 2;
					q.offer(new Pair(nr, nc));
				}
			}
		}
	}

	private static void markClosedHoleToOpenHole() {
		// 닫힌 구멍을 찾아 열리게 되었는지 판단
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (map[r][c] == 0) {
					if (isOpen(r, c)) { // 열리게 되었으면 2로 마킹
						markOpen(r, c);
					}
					return;
				}
			}
		}
	}

	private static boolean isOpen(int r, int c) {
		// (r, c)에서 BFS를 돌아 4방에 2인 칸이 하나라도 있으면 true 리턴
		Queue<Pair> q = new LinkedList<>();
		q.offer(new Pair(r, c));
		boolean[][] visited = new boolean[R][C];
		visited[r][c] = true;

		while (!q.isEmpty()) {
			Pair cur = q.poll();

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				if (isOut(nr, nc)) continue;
				if (map[nr][nc] == 2) {
					return true;
				}
				if (!visited[nr][nc] && map[nr][nc] == 0) {
					q.offer(new Pair(nr, nc));
					visited[nr][nc] = true;
				}
			}
		}

		return false;
	}

	private static void markOpen(int r, int c) {
		// 인접한 부분 2로 마킹
		Queue<Pair> q = new LinkedList<>();
		q.offer(new Pair(r, c));
		boolean[][] visited = new boolean[R][C];
		visited[r][c] = true;
		map[r][c] = 2;

		while (!q.isEmpty()) {
			Pair cur = q.poll();

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				if (isOut(nr, nc)) continue;
				if (!visited[nr][nc] && map[nr][nc] == 0) {
					q.offer(new Pair(nr, nc));
					visited[nr][nc] = true;
					map[nr][nc] = 2;
				}
			}
		}
	}

	private static boolean isOut(int r, int c) {
		return (r < 0 || r >= R || c < 0 || c >= C);
	}

}

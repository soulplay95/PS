/**
 * @문제 통나무 옮기기_G2
 * @날짜 211203
 * @분류 구현, BFS
 * @조건
 * # 4 <= 맵 크기 (N) <= 50
 * @풀이
 * # 중심점과 타입(수직 or 수평)으로 통나무를 관리한다.
 * # BFS를 돌릴 때 5가지 기본 동작 중 가능한 동작으로 탐색한다.
 * # 방문 체크는 visited[type][r][c]로 관리 => (r, c)를 중심점으로 수직, 수평 모양의 막대인지
 * @comment
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_1938 {

	// 통나무 클래스
	private static class Log {
		int centerR, centerC, type; // 중심점, 타입(수직, 수평)

		public Log(int centerR, int centerC, int type) {
			this.centerR = centerR;
			this.centerC = centerC;
			this.type = type;
		}
	}

	private static int N;
	private static char[][] map;
	private static boolean[][][] visited;
	private static Log start, end; // 시작, 도착 통나무 정보
	private static Queue<Log> q = new LinkedList<>(); // BFS에 필요한 큐

	private static final int HORIZONTAL = 0;
	private static final int VERTICAL = 1;
	private static int[] dr = {-1, 0, 1, 0}; // 상우하좌 시계방향
	private static int[] dc = {0, 1, 0, -1}; // 상우하좌 시계방향
	private static int[] dhr = {-1, -1, -1, 1, 1, 1}; // 수평 타입일때
	private static int[] dhc = {-1, 0, 1, -1, 0, 1}; // 수평 타입일때
	private static int[] dvr = {-1, -1, 0, 0, 1, 1}; // 수직 타입일때
	private static int[] dvc = {-1, 1, -1, 1, -1, 1}; // 수직 타입일때

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		// init
		map = new char[N][N];
		visited = new boolean[2][N][N];

		for (int r = 0; r < N; r++) {
			map[r] = br.readLine().toCharArray();
		}

		getInitialLogInfo(); // 시작, 도착 통나무 정보 파싱

		// print
		System.out.println(bfs());
	}

	// 시작, 도착 통나무 정보를 파싱한다.
	private static void getInitialLogInfo() {
		// 각 통나무 정보를 찾았는지 여부
		boolean isFindStartLog = false;
		boolean isFindEndLog = false;

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				// 시작, 도착 통나무 정보를 모두 찾았으면 종료
				if (isFindStartLog && isFindEndLog) {
					return;
				}

				if (!isFindStartLog && map[r][c] == 'B') {
					// 시작 통나무를 찾았으면 수평인지 수직 형태인지 검사
					if (r + 1 < N && map[r + 1][c] == 'B') { // 수직인지
						start = new Log(r + 1, c, VERTICAL);
					} else if (c + 1 < N && map[r][c + 1] == 'B') { // 수평인지
						start = new Log(r, c + 1, HORIZONTAL);
					}
					isFindStartLog = true;
				} else if (!isFindEndLog && map[r][c] == 'E') {
					if (r + 1 < N && map[r + 1][c] == 'E') { // 수직인지
						end = new Log(r + 1, c, VERTICAL);
					} else if (c + 1 < N && map[r][c + 1] == 'E') { // 수평인지
						end = new Log(r, c + 1, HORIZONTAL);
					}
					isFindEndLog = true;
				}
			}
		}
	}

	private static int bfs() {
		q.offer(start);
		visited[start.type][start.centerR][start.centerC] = true;
		int moveCounts = 0;

		while (!q.isEmpty()) {
			int size = q.size();

			while (size-- > 0) {
				Log cur = q.poll();

				// 도착 체크
				if (cur.type == end.type && cur.centerR == end.centerR && cur.centerC == end.centerC) {
					return moveCounts;
				}

				// U, D, L, R 동작 수행
				for (int d = 0; d < 4; d++) {
					// 이동한 중심점 좌표
					int newCenterR = cur.centerR + dr[d];
					int newCenterC = cur.centerC + dc[d];

					// 이동한 중심점 유효성 체크
					if (isOut(newCenterR, newCenterC) || map[newCenterR][newCenterC] == '1') {
						continue;
					}

					// 타입에 따라 분기
					// 세 좌표 모두 경계를 벗어나거나 나무에 걸리는지 체크
					if (cur.type == HORIZONTAL) {
						// 양 옆의 좌표까지 유효성 체크
						if (!isOut(newCenterR, newCenterC - 1) && map[newCenterR][newCenterC - 1] != '1'
						&& !isOut(newCenterR, newCenterC + 1) && map[newCenterR][newCenterC + 1] != '1') { // 이동 가능하면
							// 방문 체크 후 큐에 집어넣는다.
							if (!visited[HORIZONTAL][newCenterR][newCenterC]) {
								visited[HORIZONTAL][newCenterR][newCenterC] = true;
								q.offer(new Log(newCenterR, newCenterC, cur.type));
							}
						}
					} else if (cur.type == VERTICAL) {
						// 위아래 좌표까지 유효성 체크
						if (!isOut(newCenterR - 1, newCenterC) && map[newCenterR - 1][newCenterC] != '1'
								&& !isOut(newCenterR + 1, newCenterC) && map[newCenterR + 1][newCenterC] != '1') { // 이동 가능하면
							if (!visited[VERTICAL][newCenterR][newCenterC]) {
								visited[VERTICAL][newCenterR][newCenterC] = true;
								q.offer(new Log(newCenterR, newCenterC, cur.type));
							}
						}
					}
				}
				// T 동작 수행
				rotate(cur);
			}
			moveCounts++;
		}

		return 0;
	}

	private static boolean isOut(int r, int c) {
		return (r < 0 || r >= N || c < 0 || c >= N);
	}

	private static void rotate(Log cur) {
		if (cur.type == HORIZONTAL) {
			// 위아래 3칸씩 체크
			for (int d = 0; d < 6; d++) {
				int nr = cur.centerR + dhr[d];
				int nc = cur.centerC + dhc[d];

				if (isOut(nr, nc)) return;
				if (map[nr][nc] == '1') return;
			}
			// 방문 체크
			if (!visited[VERTICAL][cur.centerR][cur.centerC]) {
				visited[VERTICAL][cur.centerR][cur.centerC] = true;
				q.offer(new Log(cur.centerR, cur.centerC, VERTICAL));
			}
		} else if (cur.type == VERTICAL) {
			// 양 옆 3칸씩 체크
			for (int d = 0; d < 6; d++) {
				int nr = cur.centerR + dvr[d];
				int nc = cur.centerC + dvc[d];

				if (isOut(nr, nc)) return;
				if (map[nr][nc] == '1') return;
			}
			// 방문 체크
			if (!visited[HORIZONTAL][cur.centerR][cur.centerC]) {
				visited[HORIZONTAL][cur.centerR][cur.centerC] = true;
				q.offer(new Log(cur.centerR, cur.centerC, HORIZONTAL));
			}
		}
	}

}

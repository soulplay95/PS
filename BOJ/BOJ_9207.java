/**
 * @문제 양_S2
 * @날짜 210819
 * @분류 그래프, BFS, DFS
 * @조건
 * # 3 <= R, C <= 250
 * @풀이
 * # 각 영역별로 BFS 돌려서 양과 늑대의 수를 세고, 초기 마리수에서 감소
 * @comment
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_9207 {

	static class Pair {
		int r, c;

		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int R, C, counts[];
	static char[][] map;

	static int[] dr = {-1, 0, 1, 0}; // 상우하좌 시계방향
	static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException, NumberFormatException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		// init
		map = new char[R][C];
		counts = new int[2];
		for (int r = 0; r < R; r++) {
			String row = br.readLine();
			for (int c = 0; c < C; c++) {
				map[r][c] = row.charAt(c);

				// 양과 늑대의 초기 수를 센다
				if (map[r][c] == 'o') {
					counts[0]++;
				} else if (map[r][c] == 'v') {
					counts[1]++;
				}
			}
		} // input end

		solve();

		// print
		System.out.println(counts[0] + " " + counts[1]);
	}

	static void solve() {
		// 1. map을 탐색하면서 visited & 울타리(#)이 아닌 곳에서 BFS 시작하여 영역 내 양과 늑대 수를 비교
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (map[r][c] != '#') {
					bfs(r, c);
				}
			}
		}
	}

	static void bfs(int r, int c) {
		int sheepCounts = 0;
		int wolfCounts = 0;
		Queue<Pair> q = new LinkedList<>();
		q.offer(new Pair(r, c));
		if (map[r][c] == 'o') {
			sheepCounts++;
		} else if (map[r][c] == 'v') {
			wolfCounts++;
		}
		map[r][c] = '#'; // visited 표시

		while (!q.isEmpty()) {
			Pair cur = q.poll();

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				// 경계 체크 & 방문 체크
				if (isOut(nr, nc) || map[nr][nc] == '#') {
					continue;
				}

				// 양과 늑대 수 체크
				if (map[nr][nc] == 'o') {
					sheepCounts++;
				} else if (map[nr][nc] == 'v') {
					wolfCounts++;
				}

				q.offer(new Pair(nr, nc));
				map[nr][nc] = '#'; // visited 표시
			}
		}

		// 양과 늑대 수 비교
		if (sheepCounts == 0 || wolfCounts == 0) {
			return;
		}

		if (sheepCounts > wolfCounts) {
			counts[1] -= wolfCounts;
		} else {
			counts[0] -= sheepCounts;
		}
	}

	static boolean isOut(int r, int c) {
		return (r < 0 || r >= R || c < 0 || c >= C);
	}

}

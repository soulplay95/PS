/**
 * @문제 양치기 꿍_S2
 * @날짜 210909
 * @분류 DFS, BFS
 * @조건
 * # 3 <= R, C <= 250
 * @풀이
 * # BFS로 한 공간 안에 양과 늑대의 수 세고 잡아먹기
 * @comment
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3187 {
	
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	// -----------------------------------------------------------------------------

	static class Pair {
		int r, c;

		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int R, C;
	static char[][] map;
	static int[] ans;

	static int[] dr = {-1, 0, 1, 0}; // 상우하좌 시계방향
	static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		// init
		map = new char[R][C];
		ans = new int[2];

		for (int r = 0; r < R; r++) {
			String row = br.readLine();
			for (int c = 0; c < C; c++) {
				map[r][c] = row.charAt(c);
				if (map[r][c] == 'v') {
					ans[1]++;
				} else if (map[r][c] == 'k') {
					ans[0]++;
				}
			}
		} // input end

		solve();

		// print
		System.out.println(ans[0] + " " + ans[1]);
	}
	
	static void solve() {
		// map에서 울타리가 아닌 곳에서 bfs 시작하고 visited 체크를 울타리로 만들어버리기
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
		if (map[r][c] == 'k') {
			sheepCounts++;
		} else if (map[r][c] == 'v') {
			wolfCounts++;
		}
		map[r][c] = '#';

		while (!q.isEmpty()) {
			Pair cur = q.poll();

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				// 경계체크
				if (isOut(nr, nc) || map[nr][nc] == '#') {
					continue;
				}

				if (map[nr][nc] == 'v') {
					wolfCounts++;
				} else if (map[nr][nc] == 'k') {
					sheepCounts++;
				}

				map[nr][nc] = '#';
				q.offer(new Pair(nr, nc));
			}
		}

		if (sheepCounts > wolfCounts) {
			ans[1] -= wolfCounts;
		} else {
			ans[0] -= sheepCounts;
		}
	}

	static boolean isOut(int r, int c) {
		return (r < 0 || r >= R || c < 0 || c >= C);
	}

}

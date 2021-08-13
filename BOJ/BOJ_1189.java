/**
 * @문제 컴백홈_S1
 * @날짜 210813
 * @분류 브루트포스, 백트래킹
 * @조건
 * # 1 <= R, C <= 5
 * @풀이
 * # 시작 -> 도착 까지 dfs로 완전탐색. depth(거리)가 K이상인데 도착하지 않았으면 백트래킹
 * @comment
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1189 {
	
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	// -----------------------------------------------------------------------------

	static int R, C, K, ans;
	static char[][] map;
	static boolean[][] visited;

	static int[] dr = {-1, 0, 1, 0}; // 상우하좌 시계방향
	static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		// init
		ans = 0;
		map = new char[R][C];
		visited = new boolean[R][C];
		for (int r = 0; r < R; r++) {
			map[r] = br.readLine().toCharArray();
		} // input end

		solve();

		// print
		System.out.println(ans);
	}
	
	static void solve() {
		visited[R - 1][0] = true;
		dfs(R - 1, 0, 1);
	}

	static void dfs(int r, int c, int depth) {
		// 백트래킹
		if (depth > K) {
			return;
		}

		// 도착
		if (r == 0 && c == C - 1 && depth == K) {
			ans++;
			return;
		}

		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			// 경계체크
			if (isOut(nr, nc)) {
				continue;
			}

			if (!visited[nr][nc] && map[nr][nc] == '.') {
				visited[nr][nc] = true;
				dfs(nr, nc, depth + 1);
				visited[nr][nc] = false;
			}
		}
	}

	static boolean isOut(int r, int c) {
		return (r < 0 || r >= R || c < 0 || c >= C);
	}

}

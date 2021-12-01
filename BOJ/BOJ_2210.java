/**
 * @문제 숫자판 점프_S2
 * @날짜 211201
 * @분류 완전 탐색, DFS
 * @조건
 * #
 * @풀이
 * # 모든 칸(25칸)에서 dfs를 돌려 set에 넣는다.
 * @comment
 * # set보다는 visited[1000001] 만들어서 체크하는게 살짝 빠름
 */

import java.util.*;
import java.io.*;

public class BOJ_2210 {

	private static final int N = 5;
	private static String[][] map;
	private static Set<String> set;

	private static int[] dr = {-1, 0, 1, 0}; // 상우하좌 시계방향
	private static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		// init
		map = new String[N][N];
 		set = new HashSet<>();

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < N; c++) {
				map[r][c] = st.nextToken();
			}
		}

		// print
		System.out.println(solve());
	}

	private static int solve() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				dfs(r, c, 1, map[r][c]);
			}
		}

		return set.size();
	}

	private static void dfs(int r, int c, int depth, String num) {
		if (depth == 6) {
			set.add(num);
			return;
		}

		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (isOut(nr, nc)) {
				continue;
			}

			dfs(nr, nc, depth + 1, num.concat(map[nr][nc]));
		}
	}

	private static boolean isOut(int r, int c) {
		return (r < 0 || r >= N || c < 0 || c >= N);
	}

}

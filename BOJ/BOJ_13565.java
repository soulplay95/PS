/**
 * @문제 침투_S2
 * @날짜 210701
 * @분류 DFS, BFS
 * @조건
 * 
 * @풀이
 * 
 * @comment
 * 
 */

import java.util.*;
import java.io.*;

public class BOJ_13565 {
	
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	// -----------------------------------------------------------------------------
	
	static int R, C;
	static char[][] map;

	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		for (int r = 0; r < R; r++) {
			map[r] = br.readLine().toCharArray();
		} // input end

		// print
		System.out.println(solve() ? "YES" : "NO");
		
	}
	
	static boolean solve() {
		for (int c = 0; c < C; c++) {
			if (map[0][c] == '0') {
				dfs(0, c);
			}
		}
		
		return false;
	}
	
	static void dfs(int r, int c) {
		// 바닥 조건
		if (r == R - 1) {
			System.out.println("YES");
			System.exit(0);
		}
		
		map[r][c] = '2'; // visited 체크
		
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if (isOut(nr, nc)) continue;
			if (map[nr][nc] == '0') {
				dfs(nr, nc);
			}
		}
	}

	static boolean isOut(int r, int c) {
		return (r < 0 || r >= R || c < 0 || c >= C);
	}
	
}
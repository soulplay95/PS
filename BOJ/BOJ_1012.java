/**
 * @문제 유기농 배추_S2
 * @날짜 210630
 * @분류 DFS, BFS
 * @조건
 * R, C <= 50
 * @풀이
 * 배추면 dfs 타고 들어가면서 visited 체크
 * @comment
 * DFS, BFS 기본 문제
 */

import java.util.*;
import java.io.*;

public class BOJ_1012 {
	
	static int T, R, C, K;
	static boolean[][] map, visited;
	
	static int[] dr = {-1, 0, 1, 0}; // 상우하좌 시계방향
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new boolean[R][C];
			visited = new boolean[R][C];
			for (int k = 0; k < K; k++) {
				st = new StringTokenizer(br.readLine(), " ");
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				map[r][c] = true;
			} // input end
			
			sb.append(solve()).append("\n");
		}
		
		// print
		System.out.println(sb.toString());
	}
	
	static int solve() {
		int ret = 0;
		
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (!visited[r][c] && map[r][c]) {
					dfs(r, c);
					ret++;
				}
			}
		}
		
		return ret;
	}
	
	static void dfs(int r, int c) {
		visited[r][c] = true;
		
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if (isOut(nr, nc)) continue; // 경계 체크
			if (!visited[nr][nc] && map[nr][nc]) { // 아직 방문하지 않았고 배추가 심어진 칸이면
				dfs(nr, nc);
			}
		}
	}
	
	static boolean isOut(int r, int c) {
		return (r < 0 || r >= R || c < 0 || c >= C);
	}
	
}
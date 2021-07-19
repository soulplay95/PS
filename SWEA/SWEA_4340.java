/**
 * @문제 [연습문제] 파이프 연결
 * @날짜 210719
 * @분류 
 * @조건
 * 
 * @풀이
 * 
 * @comment
 * 
 */

import java.util.*;
import java.io.*;

public class SWEA_4340 {
	
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int[][][] dp = new int[51][51][6];
	
	static int[] dr = {-1, 0, 1, 0}; // 상우하좌 시계방향
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException, NumberFormatException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			visited = new boolean[N][N];
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			} // input end
			
			solve(0, 0, 0);
			
			// TC append
			
		}
	}
	
	static void solve(int r, int c, int depth) {
		visited[r][c] = true; // 방문 체크
		
		// 바닥 조건
		if (r == N - 1 && c == N - 1) {
			return;
		}
		
		// 4방탐색 파이프 있는 곳 찾기
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if (isOut(nr, nc)) continue; // 경계 체크
			if (map[nr][nc] != 0) { // 파이프이면
				solve(nr, nc, depth + 1);
			}
		}
	}
	
	static boolean isOut(int r, int c) {
		return (r < 0 || r >= N || c < 0 || c >= N);
	}

}
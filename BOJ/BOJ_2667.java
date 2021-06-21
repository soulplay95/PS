/**
 * @문제 단지번호붙이기
 * @날짜 210621
 * @분류 BFS, DFS
 * @조건
 * 
 * @풀이
 * visited 따로 안만들고 map에 바로 마킹
 * @comment
 * dfs가 좀 더 빠름
 */

import java.util.*;
import java.io.*;

public class BOJ_2667 {
	
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
	
	static int N;
	static char[][] map;
	static int ans;
	static List<Integer> house;

	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		for (int r = 0; r < N; r++) {
			map[r] = br.readLine().toCharArray();
		} // input end
		
		solve();
		
		// print
		System.out.println(house.size());
		Collections.sort(house);
		for (int cur : house) {
			System.out.println(cur);
		}
	}
	
	static void solve() {
		// init
		house = new LinkedList<>();
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c] == '1') {
					house.add(bfs(r, c));
				}
			}
		}
	}
	
	static int bfs(int r, int c) {
		Queue<Pair> q = new LinkedList<>();
		q.offer(new Pair(r, c));
		map[r][c] = '2'; // 방문표시
		int ret = 1;
		
		while (!q.isEmpty()) {
			Pair cur = q.poll();
			
			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				
				if (isOut(nr, nc)) continue; // 경계체크
				if (map[nr][nc] == '1') {
					q.offer(new Pair(nr, nc));
					map[nr][nc] = '2';
					ret++;
				}
			}
		}
		
		return ret;
	}
	
	static boolean isOut(int r, int c) {
		return (r < 0 || r >= N || c < 0 || c>= N);
	}

}
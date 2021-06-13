/**
 * @문제 미로 탐색_S1
 * @날짜 210613
 * @분류 
 * @조건
 * 
 * @풀이
 * 
 * @comment
 * 기본적인 BFS 문제
 */

import java.util.*;
import java.io.*;

public class BOJ_2178 {
	
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
	
	static int N, M;
	static char[][] map;
	static boolean[][] visited;
	static int ans;

	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[N][M];
		for (int r = 0; r < N; r++) {
			map[r] = br.readLine().toCharArray();
		} // input end
		
		solve();
		
		// print
		System.out.println(ans);
	}
	
	static void solve() {
		// Init
		ans = 1; // 시작 위치 포함
		
		bfs();
	}

	static void bfs() {
		Queue<Pair> q = new LinkedList<>();
		q.offer(new Pair(0, 0));
		visited[0][0] = true;
		
		while (!q.isEmpty()) {
			int cycle = q.size();
			
			// 한 싸이클 당 이동 횟수를 증가시키기 위해
			while (cycle-- > 0) {
				Pair cur = q.poll();
				
				// 도착 여부 체크
				if (cur.r == N - 1 && cur.c == M - 1) {
					return;
				}
				
				// 4방 탐색
				for (int d = 0; d < 4; d++) {
					int nr = cur.r + dr[d];
					int nc = cur.c + dc[d];
					
					if (isOut(nr, nc)) continue;
					// 방문하지 않았고, 1인 경우만 이동
					if (!visited[nr][nc] && map[nr][nc] == '1') {
						visited[nr][nc] = true;
						q.offer(new Pair(nr, nc));
					}
				}
			}
			
			ans++;
		}
	}
	
	static boolean isOut(int r, int c) {
		return (r < 0 || r >= N || c < 0 || c >= M);
	}
	
}
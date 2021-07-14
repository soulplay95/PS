/**
 * @문제 벽 부수고 이동하기 2_G3
 * @날짜 210714
 * @분류 BFS
 * @조건
 * 1 <= R, C <= 1000
 * 1 <= K <= 10
 * @풀이
 * # K차원을 늘려 visited를 관리한다.
 * @Comments
 * # https://www.acmicpc.net/source/29423683
 * # visited를 int로 잡고 벽이 아니면 -1로 초기화, 벽이면 0으로 초기화
 * Pair의 k를 부술 수 있는 벽의 개수(기회)로 나타내고  visited를 이 지점까지 이동했을 때 남은 기회로 표현한다.
 * visited[nr][nc] < cur.k
 */

import java.util.*;
import java.io.*;

public class BOJ_14442 {
	
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb;

	static class Pair {
		int r, c, moves, k;

		public Pair(int r, int c, int moves, int k) {
			this.r = r;
			this.c = c;
			this.moves = moves;
			this.k = k;
		}
	}
	
	static int R, C, K;
	static char[][] map;
	static boolean[][][] visited;
	
	static int[] dr = {-1, 0, 1, 0}; // 상우하좌 시계방향
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		// init
		map = new char[R][C];
		visited = new boolean[R][C][K + 1];
		for (int r = 0; r < R; r++) {
			map[r] = br.readLine().toCharArray();
		} // input end
		
		// print
		System.out.println(solve());
	}
	
	static int solve() {
		Queue<Pair> q = new LinkedList<>();
		q.offer(new Pair(0, 0, 1, 0)); // 시작점 이동 포함
		visited[0][0][0] = true;
		int nr = 0, nc = 0;
		
		while (!q.isEmpty()) {
			Pair cur = q.poll();
			
			// 종료조건
			if (cur.r == R - 1 && cur.c == C - 1) return cur.moves;
			
			// 벽을 부술 수 있는 기회가 남아 있으면 벽을 부순다.
			if (cur.k < K) {
				for (int d = 0; d < 4; d++) {
					nr = cur.r + dr[d];
					nc = cur.c + dc[d];
					
					if (isOut(nr, nc)) continue; // 경계체크
					if (!visited[nr][nc][cur.k + 1] && map[nr][nc] == '1') {
						q.offer(new Pair(nr, nc, cur.moves + 1, cur.k + 1));
						visited[nr][nc][cur.k + 1] = true;
					}
				}
			}
			
			// 벽을 안부수고 이동
			for (int d = 0; d < 4; d++) {
				nr = cur.r + dr[d];
				nc = cur.c + dc[d];
				
				if (isOut(nr, nc)) continue;
				if (!visited[nr][nc][cur.k] && map[nr][nc] == '0') {
					q.offer(new Pair(nr, nc, cur.moves + 1, cur.k));
					visited[nr][nc][cur.k] = true;
				}
			}
		}
		
		return -1;
	}
	
	static boolean isOut(int r, int c) {
		return (r < 0 || r >= R || c < 0 || c >= C);
	}

}

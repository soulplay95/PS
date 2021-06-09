/**
 * @문제 로봇 청소기_G5
 * @날짜 210609
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

public class BOJ_14503 {

	static class Pair {
		int r, c, d;

		public Pair(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}
	
	static int N, M;
	static int[][] map;
	static Pair cur; // 로봇 현재 상태(좌표, 방향)
	static int cleanCounts; // 청소하는 칸 개수
	
	static int[] dr = {-1, 0, 1, 0}; // 상우하좌 시계방향
	static int[] dc = {0, 1, 0, -1}; // 상우하좌 시계방향
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		st = new StringTokenizer(br.readLine(), " ");
		cur = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		} // input end
		
		solve();
		
		// print
		System.out.println(cleanCounts);
	}
	
	static void solve() {
		// 초기화
		cleanCounts = 0;
		
		while (search()) {
			
		}
	}
	
	static boolean search() {
		// 1. 현재 위치를 청소. map에 2로 마킹
		map[cur.r][cur.c] = 2;
		cleanCounts++;
		
		int dCount = 0; // 4방향 탐색 횟수
		while (true) {
			// 왼쪽 방향부터 탐색
			int nd = (cur.d - 1 + 4) % 4; // 현재 방향 기준 왼쪽 방향
			int nr = cur.r + dr[nd];
			int nc = cur.c + dc[nd];
			
			// 빈칸이면 이동하고 1번부터 진행
			if (map[nr][nc] == 0) {
				set(nr, nc, nd);
				return true;
			} else if (dCount < 4) { // 청소할 공간이 없고 탐색할 방향이 남았으면
				cur.d = nd;
				dCount++;
			} else { // 4방향 모두 이동할 수 없으면
				int nnd = (cur.d - 2 + 4) % 4;
				int nnr = cur.r + dr[nnd];
				int nnc = cur.c + dc[nnd];
				
				// 뒤쪽 방향이 벽이면 종료
				if (map[nnr][nnc] == 1) {
					return false;
				} else {
					dCount = 0;
					set(nnr, nnc, cur.d); // 방향 유지한채로 한칸 후진
				}
			}
		}
	}
	
	static void set(int r, int c, int d) {
		cur.r = r;
		cur.c = c;
		cur.d = d;
		
		return;
	}
	
}
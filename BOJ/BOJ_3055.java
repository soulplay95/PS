/**
 * @문제 탈출_G5
 * @날짜 210511
 * @분류 그래프 탐색, BFS
 * @조건
 * R, C <= 50
 * @풀이
 * BFS
 * 물 먼저 확산
 * @comment
 * 
 */

import java.util.*;
import java.io.*;

public class BOJ_3055 {
	
	// input, output handler
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
	static int time; // 걸린 시간
	static Queue<Pair> user; // 고슴도치 이동 좌표를 담는 큐
	static Queue<Pair> water; // 물 확산 좌표를 담는 큐
	
	static int[] dr = {-1, 0, 1, 0}; // 상우하좌 시계방향
	static int[] dc = {0, 1, 0, -1}; // 상우하좌 시계방향
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		user = new LinkedList<>();
		water = new LinkedList<>();
		for (int r = 0; r < R; r++) {
			String row = br.readLine();
			for (int c = 0; c < C; c++) {
				map[r][c] = row.charAt(c);
				if (map[r][c] == 'S') {
					user.add(new Pair(r, c));
				} else if (map[r][c] == '*') {
					water.add(new Pair(r, c));
				}
			}
		} // input end
		
		time = 0;
		
		// print
		System.out.println(bfs() ? time : "KAKTUS");
	}
	
	
	static boolean bfs() {
		while (!user.isEmpty()) { // 고슴도치가 계속 이동할 좌표가 있을때 동안만 반복
			// 한 사이클(1분)마다 큐의 사이즈를 체크해서 큐의 사이즈만큼만 이동, 확산
			int userSize = user.size();
			int waterSize = water.size();
			
			// 물 먼저 확산
			while (waterSize-- > 0) {
				Pair cur = water.poll();
				
				for (int d = 0; d < 4; d++) {
					int nr = cur.r + dr[d];
					int nc = cur.c + dc[d];
					
					if (isOut(nr, nc)) continue;
					if (map[nr][nc] == '.' || map[nr][nc] == 'S') { // 빈칸이나 고슴도치칸에만 확산
						map[nr][nc] = '*';
						water.offer(new Pair(nr, nc));
					}
				}
			}
			
			// 고슴도치 이동
			while (userSize-- > 0) {
				Pair cur = user.poll();
				
				for (int d = 0; d < 4; d++) {
					int nr = cur.r + dr[d];
					int nc = cur.c + dc[d];
					
					if (isOut(nr, nc)) continue;
					if (map[nr][nc] == 'D') { // 비버굴에 도착하면
						time++;
						return true;
					} else if (map[nr][nc] == '.') { // 빈칸일때만 이동
						map[nr][nc] = 'S';
						user.offer(new Pair(nr, nc));
					}
				}
			}
			
			time++; // 시간 증가
		}
		
		return false;
	}
	
	static boolean isOut(int r, int c) {
		return (r < 0 || r >= R || c < 0 || c >= C);
	}

}
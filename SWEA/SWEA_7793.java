/**
 * @문제 오! 나의 여신님_D5
 * @날짜 210509
 * @분류 
 * @조건
 * N, M <= 50
 * @풀이
 * BFS
 * 악마 먼저 확장
 * visited 안쓰고 풀이
 * @comment
 * 사이클(움직일때마다 1초 증가 같은)이 있는 BFS는 큐의 사이즈만큼만 돌고 시간을 증가시킨다.
 * 악마를 먼저 확장시킨다.
 * visited 체크 안하는 대신 continue 사용 시 주의!
 */

import java.util.*;
import java.io.*;

public class SWEA_7793 {
	
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
	
	static int N, M;
	static char[][] map;
	static Queue<Pair> user; // 수연이의 이동 좌표를 담는 큐
	static Queue<Pair> devils; // 악마의 확장 좌표를 담는 큐
	static int time; // 전체 시간
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new char[N][M];
			user = new LinkedList<>();
			devils = new LinkedList<>();
			for (int r = 0; r < N; r++) {
				String row = br.readLine();
				for (int c = 0; c < M; c++) {
					map[r][c] = row.charAt(c);
					if (map[r][c] == 'S') { // 수연이 초기 위치 찾기
						user.add(new Pair(r, c));
					} else if (map[r][c] == '*') { // 악마 초기 위치 찾기
						devils.add(new Pair(r, c));
					}
				}
			}
			
			time = 0;
			
			// TC append
			sb.append('#').append(t).append(' ').append(bfs() ? time : "GAME OVER").append('\n');
		}
		
		// print
		System.out.println(sb.toString());
	}
	
	static int[] dr = {-1, 0, 1, 0}; // 상우하좌 시계방향
	static int[] dc = {0, 1, 0, -1}; // 상우하좌 시계방향
	
	static boolean bfs() {
		// 수연이가 이동할 좌표가 있을 동안 반복
		while (!user.isEmpty()) {
			// 한 사이클(1초)당 큐의 사이즈를 구해서 각각 큐의 사이즈만큼만 반복
			int userQSize = user.size();
			int devilQSize = devils.size();
			
			// 악마 확장
			while (devilQSize-- > 0) {
				Pair cur = devils.poll();
				
				for (int d = 0; d < 4; d++) {
					int nr = cur.r + dr[d];
					int nc = cur.c + dc[d];
					
					if (isOut(nr, nc)) continue; // 경계체크
					if (map[nr][nc] == '.' || map[nr][nc] == 'S') { // 빈칸 또는 수연이의 위치에만 확장
						map[nr][nc] = '*'; // 마킹
						devils.add(new Pair(nr, nc)); // 큐에 추가
					}
				}
			}
			
			// 수연이 이동
			while (userQSize-- > 0) {
				Pair cur = user.poll();
				
				for (int d = 0; d < 4; d++) {
					int nr = cur.r + dr[d];
					int nc = cur.c + dc[d];
					
					if (isOut(nr, nc)) continue; // 경계체크
					if (map[nr][nc] == 'D') { // 도착하면
						time++;
						return true;
					} else if (map[nr][nc] == '.') { // 빈칸에만 이동
						map[nr][nc] = 'S'; // 마킹
						user.add(new Pair(nr, nc)); // 큐에 추가
					}
				}
			}
			
			time++; // 한 사이클 당 시간 증가
		}
		
		return false;
	}
	
	static boolean isOut(int r, int c) {
		return (r < 0 || r >= N || c < 0 || c >= M);
	}

}
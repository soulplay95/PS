/**
 * @문제 미세먼지 안녕!_G5
 * @날짜 210730
 * @분류 
 * @풀이
 * 구현, 시뮬레이션
 * @comment
 */

import java.io.*;
import java.util.*;

public class BOJ_17144 {
	
	static int R, C, T;
	static int[][] map;
	static int cleanerR; // 공기청정기의 행 좌표
	static int[] dr = {0, -1, 0, 1}; // 우상좌하
	static int[] dc = {1, 0, -1, 0}; // 우상좌하
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == -1) {
					cleanerR = i;
				}
			}
		}
		// -- input
		
		for (int t = 0; t < T; t++) {
			spreadDust(); // 확산
			clean(); // 공기청정기 작동
		}
		
		int totalDustAmount = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				totalDustAmount += map[i][j];
			}
		}
		
		// print
		System.out.println(totalDustAmount + 2);
	}
	
	static void spreadDust() {
		// 임시 2차원 배열 만들고 깊은 복사
		int[][] temp_map = new int[R][C];
		for (int i = 0; i < R; i++) {
			temp_map[i] = map[i].clone();
		}
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] > 0) {
					int spreadCount = 0; // 확산된 칸의 개수 <= 4
					int spreadAmount = map[i][j] / 5; // 확산되는 미세먼지 양
					if (spreadAmount == 0) continue;
					for (int d = 0; d < 4; d++) {
						int nr = i + dr[d];
						int nc = j + dc[d];
						if (!isIn(nr, nc)) continue;
						if (map[nr][nc] == -1) continue;
						temp_map[nr][nc] += spreadAmount; // 미세먼지 양 누적
						spreadCount++;
					}
					temp_map[i][j] -= spreadCount * spreadAmount; // 미세먼지 확산 후 기존 미세먼지 양 업데이트
				}
			}
		}
		
		// 맵 업데이트
		map = temp_map;
	}
	
	static void clean() {
		// 임시 2차원 배열 만들고 깊은 복사
		int[][] temp_map = new int[R][C];
		for (int i = 0; i < R; i++) {
			temp_map[i] = map[i].clone();
		}
		
		// 공기청정기 상단 오른쪽부터 시작
		int r = cleanerR - 1;
		int c = 1;
		int d = 0; // 방향
		temp_map[r][c] = 0;
		// 반시계 회전
		while (true) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (!isIn(nr, nc)) {
				d = (d + 1) % 4; // 방향 전환
				continue;
			}
			if (map[nr][nc] == -1) break; // 공기청정기로 돌아오면 끝
			temp_map[nr][nc] = map[r][c];
			r = nr;
			c = nc;
		}
		
		r = cleanerR;
		c = 1;
		d = 0;
		temp_map[r][c] = 0;
		// 시계 회전
		while (true) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (!isIn(nr, nc)) {
				d = (4 + (d - 1)) % 4; // 방향 전환
				continue;
			}
			if (map[nr][nc] == -1) break; // 공기청정기로 돌아오면 끝
			temp_map[nr][nc] = map[r][c];
			r = nr;
			c = nc;
		}
		
		// 맵 업데이트
		map = temp_map;
	}

	static boolean isIn(int r, int c) {
		return (r >= 0 && r < R && c >= 0 && c < C);
	}
}

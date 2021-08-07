/**
 * @문제 감시_G5
 * @날짜 210807
 * @분류 시뮬레이션, 구현
 * @풀이
 * 5번 1가지, 2번 2가지, 나머지 4가지 경우의 수를 가짐.
 * cctv 조합에 따라 나올 수 있는 모든 경우의 수에 대하여 시뮬레이션 후 최소값 구함
 * 중간 과정에서 재사용 되는 변형된 map을 얼마나 재사용 하느냐가 관건
 * @comment
 * 
 */

import java.util.*;
import java.io.*;

public class BOJ_15683 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static class CCTV {
		int r, c; // 좌표
		int type; // 타입

		public CCTV(int r, int c, int type) {
			this.r = r;
			this.c = c;
			this.type = type;
		}
	}
	
	static int N, M; // <= 8
	static int[][] originalMap;
	static List<CCTV> cctvs; // cctv 정보를 담는 배열
	static int ans; // 사각 지대(cctv 가동 후에도 빈칸으로 남아있는 칸) 최소 개수
	static int[] dr = {-1, 0, 1, 0}; // 상우하좌 - 시계방향
	static int[] dc = {0, 1, 0, -1}; // 상우하좌 - 시계방향
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		originalMap = new int[N][M];
		cctvs = new ArrayList<>();
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < M; c++) {
				originalMap[r][c] = Integer.parseInt(st.nextToken());
				if (originalMap[r][c] >= 1 && originalMap[r][c] <= 5) { // cctv이면
					cctvs.add(new CCTV(r, c, originalMap[r][c])); // 리스트에 추가
				}
			}
		}
		// -- input
		
		ans = 65; // 가능한 max값으로 초기화
		go(originalMap, 0); // dfs느낌으로 모든 cctv에 대해 map 변형
		sb.append(ans).append('\n');
		
		// print
		System.out.println(sb.toString());
	}
	
	static void go(int[][] map, int depth) {
		if (depth == cctvs.size()) { // 모든 cctv가 작동 한 상태
			ans = Math.min(ans, getBlindSpotCount(map)); // 사각지대 개수 최소값 갱신
			return;
		}
		
		CCTV cur = cctvs.get(depth); // 현재 다루는 cctv
		int type = cur.type; // cctv 타입
		int[][] newMap = new int[N][M]; // 현재 단계의 cctv 작동으로 새롭게 변경될 map
		// map copy
		for (int r = 0; r < N; r++) {
			newMap[r] = map[r].clone();
		}
		
		if (type == 5) { // 1가지 경우 뿐
			go(work(newMap, cur, type, 0), depth + 1); // 색칠된 맵 들고 다음 단계로
		} else if (type == 2) { // 2가지 경우 뿐
			for (int j = 0; j < 2; j++) {
				go(work(newMap, cur, type, j), depth + 1); // 색칠된 맵 들고 다음 단계로
			}
		} else {
			for (int j = 0; j < 4; j++) {
				go(work(newMap, cur, type, j), depth + 1); // 색칠된 맵 들고 다음 단계로
			}
		}
	}
	
	static int getBlindSpotCount(int[][] map) {
		int count = 0;
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (map[r][c] == 0) {
					count++;
				}
			}
		}
		
		return count;
	}
	
	// map을 색칠해서 반환
	static int[][] work(int[][] newMap, CCTV cur, int type, int index) {
		int d, nr, nc;
		int[][] map = new int[N][M]; // 현재 단계의 cctv 작동으로 새롭게 변경될 map
		// map copy
		for (int r = 0; r < N; r++) {
			map[r] = newMap[r].clone();
		}
		
		switch (type) {
		case 1:
			d = index;
			nr = cur.r + dr[d];
			nc = cur.c + dc[d];
			while (true) {
				if (!isIn(nr, nc)) break;
				if (map[nr][nc] == 6) break;
				if (map[nr][nc] == 0) {
					map[nr][nc] = -1;
				}
				
				nr += dr[d];
				nc += dc[d];
			}
			break;
		case 2:
			for (d = index; d < 4; d = d + 2) {
				nr = cur.r + dr[d];
				nc = cur.c + dc[d];
				while (true) {
					if (!isIn(nr, nc)) break;
					if (map[nr][nc] == 6) break;
					if (map[nr][nc] == 0) {
						map[nr][nc] = -1;
					}
					
					nr += dr[d];
					nc += dc[d];
				}
			}
			break;
		case 3:
			d = index;
			for (int i = 1; i <= 2; i++) {
				nr = cur.r + dr[d];
				nc = cur.c + dc[d];
				while (true) {
					if (!isIn(nr, nc)) break;
					if (map[nr][nc] == 6) break;
					if (map[nr][nc] == 0) {
						map[nr][nc] = -1;
					}
					
					nr += dr[d];
					nc += dc[d];
				}
				d = (d + i) % 4;
			}
			break;
		case 4:
			d = index;
			for (int i = 1; i <= 3; i++) {
				nr = cur.r + dr[d];
				nc = cur.c + dc[d];
				while (true) {
					if (!isIn(nr, nc)) break;
					if (map[nr][nc] == 6) break;
					if (map[nr][nc] == 0) {
						map[nr][nc] = -1;
					}
					
					nr += dr[d];
					nc += dc[d];
				}
				d = (d + i) % 4;
			}
			break;
		case 5:
			for (d = 0; d < 4; d++) {
				nr = cur.r + dr[d];
				nc = cur.c + dc[d];
				while (true) {
					if (!isIn(nr, nc)) break;
					if (map[nr][nc] == 6) break;
					if (map[nr][nc] == 0) {
						map[nr][nc] = -1;
					}
					
					nr += dr[d];
					nc += dc[d];
				}
			}
			break;
		}
		
		return map;
	}
	
	static boolean isIn(int r, int c) {
		return (r >= 0 && r < N && c >= 0 && c < M);
	}

}

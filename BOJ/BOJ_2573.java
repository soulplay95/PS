/**
 * @문제 빙산_G4
 * @날짜 210608
 * @분류 
 * @조건
 * 
 * @풀이
 * 1. 빙산 위치 4방 탐색 녹이기
 * 2. 덩어리 체크
 * @comment
 * 
 */

import java.util.*;
import java.io.*;

public class BOJ_2573 {
	
	static int N, M;
	static int[][] map;
	static int year;
	static int iceCount; // 빙산 개수
	static boolean[][] visited;
	
	static int[] dr = {-1, 0, 1, 0}; // 상우하좌 시계방향
	static int[] dc = {0, 1, 0, -1}; // 상우하좌 시계방향
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		} // input end
		
		solve();
		
		// print
		System.out.println(year);
	}
	
	static void solve() {
		year = 0; // 년도 초기화
		
		while (true) {
			// 1. 빙산 녹이기
			if (melt()) {
				year++; // 1년 지남
			} else {
				year = 0;
				break;
			}
			
			// 2. 덩어리 체크
			if (isSplit()) { // 2덩이 이상으로 나뉘었으면
				break; // 종료
			}
		}
	}
	
	static boolean melt() {
		// 일괄 처리를 위해 map 깊은 복사
		int[][] cmap = new int[N][M];
		for (int r = 1; r < N - 1; r++) {
			cmap[r] = map[r].clone();
		}
		
		// 빙산 개수 초기화
		iceCount = 0;
		
		// 빙산 기준 4방탐색 하여 바다 개수만큼 높이 감소
		for (int r = 1; r < N - 1; r++) {
			for (int c = 1; c < M - 1; c++) {
				// 빙산이면
				if (map[r][c] > 0) {
					iceCount++; // 빙산 개수 증가
					int seaCount = 0; // 주변 바다 개수
					// 4방 탐색
					for (int d = 0; d < 4; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						
						if (map[nr][nc] == 0) {
							seaCount++;
						}
					}
					
					// 복사된 map에 변경된 빙산 높이 적용
					int mh = cmap[r][c] - seaCount; // modified height
					// 완전히 녹아 바다가 된 빙산 체크
					if (mh <= 0) {
						mh = 0;
						iceCount--; // 빙산 개수 감소
					}
					cmap[r][c] = mh;
				}
			}
		}
		
		if (iceCount == 0) {
			// 빙산 개수가 0개이면
			return false;
		}
		
		// map 복원
		map = cmap;
		
		return true;
	}
	
	static boolean isSplit() {
		visited = new boolean[N][M];
		
		for (int r = 1; r < N - 1; r++) {
			for (int c = 1; c < M - 1; c++) {
				// 빙산이면
				if (map[r][c] > 0) {
					// bfs 리턴값(한 덩어리로 이어진 빙산 개수)이 map의 전체 빙산 개수와 다르면 종료
					if (bfs(r, c) != iceCount) {
						return true;
					} else {
						return false;
					}
				}
			}
		}
		
		return false;
	}
	
	static int bfs(int r, int c) {
		Queue<int[]> q = new LinkedList<>(); // 한 덩어리에 포함된 빙산을 담는 큐
		q.offer(new int[] {r, c});
		visited[r][c] = true;
		int cnt = 1; // 한 덩어리 내 빙산 개수
		
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			
			for (int d = 0; d < 4; d++) {
				int nr = cur[0] + dr[d];
				int nc = cur[1] + dc[d];
				
				// 방문하지 않았고 빙산일때만 큐에 넣음
				if (!visited[nr][nc] && map[nr][nc] > 0) {
					visited[nr][nc] = true;
					q.offer(new int[] {nr, nc});
					cnt++; // 빙산 개수 증가
				}
			}
		}
		
		return cnt;
	}

}
/**
 * @문제 안전 영역_S1
 * @날짜 210610
 * @분류 
 * @조건
 * 
 * @풀이
 * 
 * @comment
 * 모두 1일때 같은 조건 생각해보기 => 답은 1. 경계조건?
 */

import java.util.*;
import java.io.*;

public class BOJ_2468 {
	
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static boolean[][] cvisited;
	static int min, max;
	static int maxSafeArea;
	
	static int[] dr = {-1, 0, 1, 0}; // 상우하좌 시계방향
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		min = 101;
		max = 0;
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] > max) {
					max = map[r][c];
				}
				if (map[r][c] < min) {
					min = map[r][c];
				}
			}
		} // input end
		
		// init
		maxSafeArea = 1;
		
		solve();
		
		// print
		System.out.println(maxSafeArea);
	}
	
	static void solve() {
		// 최소 높이 ~ 최대 높이까지 하나씩 비의 양을 증가시키면서 잠기는 지역을 만들고 그때의 안전한 영역의 개수를 센다.
		for (int i = min; i <= max; i++) {
			// 1. 물에 잠기는 지점 갱신
			flood(i);
			
			// 2. 안전한 영역 개수 세기
			int count = getSafeAreaCounts();
			
			// 최대값 갱신
			if (maxSafeArea < count) {
				maxSafeArea = count;
			}
		}
	}
	
	static void flood(int rain) { // rain : 비의 양
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (!visited[r][c]) { // 물에 아직 잠기지 않은 영역에 대해서만
					if (map[r][c] <= rain) {
						visited[r][c] = true; // 잠겼음을 표시
					}
				}
			}
		}
	}
	
	static int getSafeAreaCounts() {
		int ret = 0;
		
		// visited copy
		cvisited = new boolean[N][N];
		for (int r = 0; r < N; r++) {
			cvisited[r] = visited[r].clone();
		}
		
		// 아직 잠기지 않은 영역으로부터 dfs 돌림
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (!cvisited[r][c]) {
					dfs(r, c);
					ret++;
				}
			}
		}
		
		return ret;
	}
	
	static void dfs(int r, int c) {
		cvisited[r][c] = true;
		
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if (isOut(nr, nc)) continue;
			if (!cvisited[nr][nc]) {
				dfs(nr, nc);
			}
		}
		
		return;
	}
	
	static boolean isOut(int r, int c) {
		return (r < 0 || r >= N || c < 0 || c >= N);
	}

}
/**
 * @문제 Two Dots_G4
 * @날짜 210714
 * @분류 
 * @조건
 * 같은 색으로 이루어진 사이클 찾기
 * 연결된 점 개수 k >= 4
 * 시작과 끝이 인접해야 함
 * 4방 인접
 * N, M <= 50
 * # 사이클이 존재하는지 아닌지 구하기
 * @풀이
 * 점의 종류는 알파벳 대문자 개수 26가지
 * map 입력 받을 때 개수 세고 3개 이하면 버리기
 * dfs 시작점이 4방 인접 개수가 2개 이상일때만 dfs시작
 * @comment
 * 
 */

import java.util.*;
import java.io.*;

public class BOJ_16929_X {
	
	// input, output handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	// -----------------------------------------------------------------------------
	
	static int ans;

	static int N, M;
	static char[][] map;
	static boolean[][] visited;
	static int[] dots; // 각 점의 색깔 당 개수
	
	static int[] dr = {-1, 0, 1, 0}; // 상우하좌 시계방향
	static int[] dc = {0, 1, 0, -1}; // 상우하좌 시계방향
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[N][M];
		dots = new int[26];
		for (int r = 0; r < N; r++) {
			String row = br.readLine();
			for (int c = 0; c < M; c++) {
				map[r][c] = row.charAt(c);
				dots[map[r][c] - 'A']++; // 개수 누적
			}
		}
		
		solve();
		
		// print
		System.out.println("No");
	}
	
	static void solve() {
		// map 전체 순회하면서 dfs 시작점 찾고 dfs 시작
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (visited[r][c]) {
					continue;
				} else if (dots[map[r][c] - 'A'] < 4) { // 사이클 구성 점 개수가 4개 미만인 점은 건너뜀
					visited[r][c] = true;
					continue;
				} else if (getClosedCounts(r, c) < 2) { // 인접 변이 2개 미만인 점은 건너뜀
					visited[r][c] = true;
					continue;
				} else {
					dfs(r, c, r, c, map[r][c], 0);
				}
			}
		}
	}
	
	static void dfs(int r, int c, int sr, int sc, char color, int depth) {
		visited[r][c] = true;
		
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if (isOut(nr, nc)) continue;
			if (map[nr][nc] == color) {
				// 사이클 여부 체크
				if (nr == sr && nc == sc && depth >= 3) {
					System.out.println("Yes");
					System.exit(0);
				} else if (visited[nr][nc]) {
					continue;
				} else {
					dfs(nr, nc, sr, sc, color, depth + 1);
				}
			}
		}
	}
	
	static int getClosedCounts(int r, int c) {
		int counts = 0;
		
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if (isOut(nr, nc)) continue;
			if (map[r][c] == map[nr][nc]) counts++;
			if (counts >= 2) break; // 2개 이상은 셀 필요 없으므로 break
		}
		
		return counts;
	}
	
	static boolean isOut(int r, int c) {
		return (r < 0 || r >= N || c < 0 || c >= M);
	}

}
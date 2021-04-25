/**
 * @문제 영역 구하기_S1
 * @날짜 210425
 * @분류 그래프, bfs, dfs
 * @조건
 * M, N, K <= 100
 * @풀이
 * dfs 풀이
 * @comment
 * visited 안쓰고 map 자체를 visited로 사용 가능
 * 문제에서 가중치 이런게 딱히 없으므로
 */

import java.util.*;
import java.io.*;

public class BOJ_2583_v2 {
	
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
	
	static int ans;

	static int N, M, K;
	static boolean[][] map;
	static List<Integer> areas; // 영역 넓이를 담는 리스트
	static int area;
	
	static int[] dr = {-1, 0, 1, 0}; // 상우하좌 시계방향
	static int[] dc = {0, 1, 0, -1}; // 상우하좌 시계방향
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new boolean[M][N];
		areas = new ArrayList<Integer>();
		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			// map 칠하기
			for (int r = y1; r < y2; r++) {
				for (int c = x1; c < x2; c++) {
					map[r][c] = true;
				}
			}
		} // input end
		
		search();
		
		// areas 오름차순 정렬
		Collections.sort(areas);
		
		ans = areas.size();
		sb.append(ans).append('\n');
		
		for (int i = 0; i < ans; i++) {
			sb.append(areas.get(i)).append(' ');
		}
		
		// print
		System.out.println(sb.toString());
	}
	
	static void search() {
		for (int r = 0; r < M; r++) {
			for (int c = 0; c < N; c++) {
				// 직사각형 외부이면
				if (!map[r][c]) {
					area = 0;
					dfs(r, c); // dfs 시작
					areas.add(area); // 리스트에 영역 넓이 담기
				}
			}
		}
	}
	
	static void dfs(int r, int c) {
		map[r][c] = true;
		area++;
		
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if (!isIn(nr, nc)) continue;
			if (map[nr][nc]) continue;
			dfs(nr, nc);
		}
	}
	
	static boolean isIn(int r, int c) {
		return (r >= 0 && r < M && c >= 0 && c < N);
	}

}
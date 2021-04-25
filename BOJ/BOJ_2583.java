/**
 * @문제 영역 구하기_S1
 * @날짜 210425
 * @분류 그래프, bfs, dfs
 * @조건
 * M, N, K <= 100
 * @풀이
 * bfs 풀이
 * 1. map에 주어진 K개의 직사각형대로 칠하고
 * 2. 이중 for문 돌면서 안칠해진 부분에서 bfs 시작. => 카운트 증가(분리된 영역 개수)
 * 3. bfs 돌면서 queue에 추가할 때마다 카운트 증가 => 영역의 넓이
 * 4. visited 또는 map을 칠해간다.
 * @comment
 * 문제에 주어진대로 M, N 굳이 맞출 필요 없음. 내 맘대로 이름 바꿔서 사용 => 문제에서 다시 볼 때 인식할때만 조심하기
 */

import java.util.*;
import java.io.*;

public class BOJ_2583 {
	
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
					bfs(r, c); // bfs 시작
				}
			}
		}
	}
	
	static void bfs(int r, int c) {
		int area = 1; // 영역 넓이
		Queue<Pair> q = new LinkedList<>();
		q.offer(new Pair(r, c));
		map[r][c] = true;
		
		while (!q.isEmpty()) {
			Pair cur = q.poll();
			
			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				
				if (!isIn(nr, nc)) continue;
				if (map[nr][nc]) continue;
				map[nr][nc] = true;
				area++;
				q.offer(new Pair(nr, nc));
			}
		}
		
		areas.add(area); // 리스트에 영역 넓이 담기
	}
	
	static boolean isIn(int r, int c) {
		return (r >= 0 && r < M && c >= 0 && c < N);
	}

}
/**
 * @문제 화염에서의탈출(SLIKAR)
 * @날짜 210428
 * @분류 
 * @조건
 * 매 분마다 4방이동 가능
 * 불은 매 분마다 4방 확산
 * 최소 탈출 시간 구하기
 * R, C <= 50
 * 불 여러개 가능
 * @풀이
 * BFS
 * 불 먼저 확산
 * @comment
 * visited 체크 조심
 * 이동했으면 빈칸으로 비워주기!
 */

import java.util.*;
import java.io.*;

public class JUNGOL_1082 {
	
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
	static int minTime;
	static Queue<Pair> user;
	static Queue<Pair> fires;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		user = new LinkedList<>();
		fires = new LinkedList<>();
		for (int r = 0; r < R; r++) {
			map[r] = br.readLine().toCharArray();
			for (int c = 0; c < C; c++) {
				if (map[r][c] == '*') {
					fires.offer(new Pair(r, c));
				} else if (map[r][c] == 'S') {
					user.offer(new Pair(r, c));
				}
			}
		} // input end
		
		// print
		System.out.println(bfs() ? minTime : "impossible");
	}

	static int[] dr = {-1, 0, 1, 0}; // 상우하좌 시계방향
	static int[] dc = {0, 1, 0, -1};
	static boolean bfs() {
		boolean[][] visited = new boolean[R][C];
		visited[user.peek().r][user.peek().c] = true;
		
		while (!user.isEmpty()) {
			minTime++;

			// 불 확산
			int fqSize = fires.size(); // 불 확산 좌표를 담은 큐의 사이즈
			while (fqSize-- > 0) {
				Pair cur = fires.poll();
				for (int d = 0; d < 4; d++) {
					int nr = cur.r + dr[d];
					int nc = cur.c + dc[d];
					
					if (!isIn(nr, nc)) continue;
					if (map[nr][nc] == '.') {
						map[nr][nc] = '*';
						fires.offer(new Pair(nr, nc));
					}
				}
			}
			
			// 재우 이동
			int qSize = user.size();
			while (qSize-- > 0) {
				Pair cur = user.poll();
				for (int d = 0; d < 4; d++) {
					int nr = cur.r + dr[d];
					int nc = cur.c + dc[d];
					
					if (!isIn(nr, nc) || visited[nr][nc]) continue;
					if (map[nr][nc] == 'D') {
						return true;
					}
					if (map[nr][nc] == '.') {
						map[nr][nc] = 'S';
						visited[nr][nc] = true;
						user.offer(new Pair(nr, nc));
					}
				}
				map[cur.r][cur.c] = '.'; // 이동했으면 빈칸으로 만들어줌 
			}
		}
		
		return false;
	}
	
	static boolean isIn(int r, int c) {
		return (r >= 0 && r < R && c >= 0 && c < C);
	}

}
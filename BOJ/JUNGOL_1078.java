/**
 * @문제 저글링 방사능 오염
 * @날짜 210809
 * @분류 BFS
 * @조건
 * 
 * @풀이
 * # 큐의 사이즈만큼 bfs 돌때마다 시간 체크
 * @comment
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class JUNGOL_1078 {
	
	// IO Handler
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
	static boolean[][] visited;
	static int time, remain;

	static int[] dr = {-1, 0, 1, 0}; // 상우하좌 시계방향
	static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		// init
		map = new char[R][C];
		visited = new boolean[R][C];
		time = 3;
		remain = 0;
		for (int r = 0; r < R; r++) {
			map[r] = br.readLine().toCharArray();
		}
		st = new StringTokenizer(br.readLine(), " ");
		int c = Integer.parseInt(st.nextToken()) - 1;
		int r = Integer.parseInt(st.nextToken()) - 1;

		bfs(r, c);
		getRemain();

		// print
		System.out.println(time - 1);
		System.out.println(remain);
	}
	
	static void bfs(int r, int c) {
		Queue<Pair> q = new LinkedList<>();
		q.offer(new Pair(r, c));
		map[r][c] = '0';
		visited[r][c] = true;

		while (!q.isEmpty()) {
			int size = q.size();

			while (size-- > 0) {
				Pair cur = q.poll();

				for (int d = 0; d < 4; d++) {
					int nr = cur.r + dr[d];
					int nc = cur.c + dc[d];

					if (isOut(nr, nc)) {
						continue;
					}

					if (!visited[nr][nc] && map[nr][nc] == '1') {
						visited[nr][nc] = true;
						map[nr][nc] = '0';
						q.offer(new Pair(nr, nc));
					}
				}
			}

			time++;
		}
	}

	static boolean isOut(int r, int c) {
		return (r < 0 || r >= R || c < 0 || c >= C);
	}

	static void getRemain() {
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (map[r][c] == '1') {
					remain++;
				}
			}
		}
	}

}

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_17472 {

	// 좌표 클래스
	static class Pair {
		int r, c;

		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int N, M; // 1 <= N, M <= 10
	static int[][] map;
	static int[] dr = {-1, 1, 0, 0}; // 상하좌우
	static int[] dc = {0, 0, -1, 1}; // 상하좌우
	static int islandCount; // 섬 개수
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		islandCount = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		// map에 섬 넘버링
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// bfs 돌면서 섬에 번호 부여
				if (map[i][j] == 1) {
					++islandCount; // 섬 개수 증가(2부터 넘버링). 최종 개수는 -1한 값
					bfs(i, j);
				}
			}
		}
		
		// 인접 행렬 만들기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 땅이면
				if (map[i][j] != 0) {
					dfs(i, j, 0);
				}
			}
		}
		
		
		sc.close();
	}
	
	static void bfs(int r, int c) {
		Queue<Pair> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		queue.offer(new Pair(r, c));
		visited[r][c] = true;
		map[r][c] = islandCount;
		while (!queue.isEmpty()) {
			Pair cur = queue.poll();
			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				if (!isIn(nr, nc)) continue;
				if (visited[nr][nc]) continue;
				if (map[nr][nc] == 1) {
					queue.offer(new Pair(nr, nc));
					visited[nr][nc] = true;
					map[nr][nc] = islandCount; // 넘버링
				}
			}
		}
	}
	
	static void dfs(int r, int c, int depth) { // depth : 길이
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if (!isIn(nr, nc)) continue;
			// 다른 섬을 만나면
			if (map[nr][nc] != 0 && map[r][c] != map[nr][nc]) {
				// TODO
				if (depth < 2) continue; // 다리 길이가 2미만이면
			}
			if (map[r][c] == map[nr][nc]) return;
		}
	}
	
	static boolean isIn(int r, int c) {
		return (r >= 0 && r < N && c >= 0 && c < M);
	}
	
}

/**
 * 다리 만들기2_G2
 * 
 * MST
 * 인접 행렬을 어떻게 만들까?
 * bfs로 섬을 넘버링해주고 섬의 테두리를 구성하는 모든 좌표에서 한 방향으로 dfs 탐색해서 마주치는 첫 섬까지의 최소 거리로 인접행렬 채운다.
 */
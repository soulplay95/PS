// 1. 장기 포 게임

import java.util.*;
import java.io.*;

public class Solution {
	
	static class Pair {
		int r, c;
		
		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int N, ans;
	static int[][] map;
	static boolean[][] catched;
	static Queue<Pair> q;
	
	static int[] dr = {-1, 0, 1, 0}; // 상우하좌 시계방향
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			// init
			ans = 0;
			q = new LinkedList<Pair>();
			map = new int[N][N];
			catched = new boolean[N][N];
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					
					if (map[r][c] == 2) {
						q.offer(new Pair(r, c));
						map[r][c] = 0;
					}
				}
			}
			
			solve(0, q.poll());
			
			// print
			System.out.println("#" + t + " " + ans);
		}
	}
	
	static void solve(int depth, Pair cur) {
		if (depth == 3) return;
		
		// 4방향으로 탐색을 진행하여 포가 이동할 수 있는 위치를 큐에 넣는다.
		// 이 때 아직 잡지 않은 알이 있다면 ans 1 증가
		for (int d = 0; d < 4; d++) {
			int r = cur.r;
			int c = cur.c;
			boolean firstNode = false; // 첫 번째 알을 지나면 true
			
			while (true) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				// 경계 체크
				if (isOut(nr, nc)) break;
				// 알의 위치인지 체크
				if (map[nr][nc] == 1) {
					if (!firstNode) { // 첫번째 알이면 flag true로 설정 
						firstNode = true;
					} else { // 첫 번째 알 이후의 알이면 
						// 아직 안잡은 알이면 알을 잡는다.
						if (!catched[nr][nc]) {
							catched[nr][nc] = true;
							ans++;
						}
						int temp = map[nr][nc];
						map[nr][nc] = 0;
						solve(depth + 1, new Pair(nr, nc));
						map[nr][nc] = temp;
						break;
					}
				}
				
				// 첫번째 알 이후이면서 알의 위치가 아니면
				if (firstNode && map[nr][nc] == 0) {
					int temp = map[nr][nc];
					map[nr][nc] = 0;
					solve(depth + 1, new Pair(nr, nc));
					map[nr][nc] = temp;
				}
				
				r = nr;
				c = nc;
			}
		}
	}
	
//	static void solve() {
//		int moves = 0;
//		
//		while (moves++ < 3 && !q.isEmpty()) {
//			int size = q.size();
//			
//			while (size-- > 0) {
//				Pair cur = q.poll(); // 포의 현재 위치
//				int temp = map[cur.r][cur.c];
//				map[cur.r][cur.c] = 0;
//				
//				// 4방향으로 탐색을 진행하여 포가 이동할 수 있는 위치를 큐에 넣는다.
//				// 이 때 아직 잡지 않은 알이 있다면 ans 1 증가
//				for (int d = 0; d < 4; d++) {
//					int r = cur.r;
//					int c = cur.c;
//					boolean firstNode = false; // 첫 번째 알을 지나면 true
//					
//					while (true) {
//						int nr = r + dr[d];
//						int nc = c + dc[d];
//						
//						// 경계 체크
//						if (isOut(nr, nc)) break;
//						// 알의 위치인지 체크
//						if (map[nr][nc] == 1) {
//							if (!firstNode) { // 첫번째 알이면 flag true로 설정 
//								firstNode = true;
//							} else { // 첫 번째 알 이후의 알이면 
//								// 아직 안잡은 알이면 알을 잡는다.
//								if (!catched[nr][nc]) {
//									catched[nr][nc] = true;
//									ans++;
//								}
//								q.offer(new Pair(nr, nc)); // 포의 새로운 위치에 포함
//								break;
//							}
//						}
//						
//						// 첫번째 알 이후이면서 알의 위치가 아니면
//						if (firstNode && map[nr][nc] == 0) {
//							q.offer(new Pair(nr, nc));
//						}
//						
//						r = nr;
//						c = nc;
//					}
//				}
//				
//				map[cur.r][cur.c] = temp;
//			}
//		}
//	}
	
	static boolean isOut(int r, int c) {
		return (r < 0 || c < 0 || r >= N || c >= N);
	}
	
}
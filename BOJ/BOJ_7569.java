/**
 * @문제 토마토
 * @날짜 210622
 * @분류 BFS
 * @조건
 * 
 * @풀이
 * # BFS의 새로운 탐색점이 되는(안익은 -> 익은 토마토로 변한) 좌표를 큐에 넣고
 * 한 싸이클(1 day) 동안 큐의 사이즈만큼만 돌아서 3차원 map의 좌표를 업데이트(2로 visited 체크)하고 안익은 토마토 수를 감소시킨다.
 * # 익히기 전 후의 남은 안익은 토마토 수를 비교해서 같다면(더 이상 익을 수 없음) 반복을 종료한다. & 종료 조건 : 안익은 토마토 수가 0일때
 * @comment
 * 대표적인 BFS + 구현? 문제
 */

import java.util.*;
import java.io.*;

public class BOJ_7569 {
	
	static class Pair {
		int h, r, c;
		
		public Pair(int h, int r, int c) {
			this.h = h;
			this.r = r;
			this.c = c;
		}
	}
	
	static int R, C, H;
	static int[][][] map;
	static Queue<Pair> ripe; // "인접 토마토들을 탐색해야 하는" 익은 토마토들을 담는 큐
	static int unripeCounts; // 덜 익은 토마토 수
	static int days; // 경과 일

	static int[] dh = {-1, 1, 0, 0, 0, 0};
	static int[] dr = {0, 0, -1, 0, 1, 0};
	static int[] dc = {0, 0, 0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H][R][C];
		ripe = new LinkedList<>();
		for (int h = 0; h < H; h++) {
			for (int r = 0; r < R; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < C; c++) {
					map[h][r][c] = Integer.parseInt(st.nextToken());
					if (map[h][r][c] == 1) { // 익은 토마토 큐에 넣기
						ripe.offer(new Pair(h, r, c));
						map[h][r][c] = 2; // 방문 체크
					} else if (map[h][r][c] == 0) { // 익지 않은 토마토 수 카운팅
						unripeCounts++;
					}
				}
			}
		} // input end
		
		// init
		days = 0;
		
		if (unripeCounts != 0) { // 안 익은 토마토가 있으면
			solve();
		}
		
		// print
		System.out.println(days);
	}
	
	static void solve() {
		// 익은 토마토 큐에서 하나씩 꺼내어 인접한 안익은 토마토들을 익힌다. => 한 싸이클(1 day)
		while (true) {
			int before = unripeCounts; // 익히기 전에 안익은 토마토 수를 저장해두고 비교

			days++;
			
			// 익히기
			ripen();
			
			// 모두 익었으면 종료
			if (unripeCounts == 0) {
				break;
			}
			
			// 익히기 전 후 안익은 토마토 수 비교해서 같다면(더 이상 토마토가 모두 익지 못하는 상황)
			if (before == unripeCounts) {
				days = -1;
				break;
			}
		}
	}
	
	static void ripen() {
		int size = ripe.size();
		
		// 큐의 사이즈 만큼만 돈다.
		while (size-- > 0) {
			Pair cur = ripe.poll();
			
			for (int d = 0; d < 6; d++) {
				int nh = cur.h + dh[d];
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				
				if (isOut(nh, nr, nc)) continue; // 경계 체크
				if (map[nh][nr][nc] == 0) {
					unripeCounts--; // 안익은 토마토 수 감소
					map[nh][nr][nc] = 2; // 방문 체크
					ripe.offer(new Pair(nh, nr, nc)); // 큐에 넣기
				}
			}
		}
	}
	
	static boolean isOut(int h, int r, int c) {
		return (h < 0 || h >= H || r < 0 || r >= R || c < 0 || c >= C);
	}

}
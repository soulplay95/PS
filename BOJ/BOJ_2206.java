package week22;
/**
 * @문제 벽 부수고 이동하기_G4
 * @날짜 210710
 * @분류 BFS
 * @조건
 * # 시작하는 칸, 끝나는 칸도 포함해서 센다.
 * # 벽 한개까지 부수기 가능
 * # 1 <= N, M <= 1000
 * # 도착 불가능하면 -1 출력
 * @풀이
 * # 벽을 부수고 이동했는지 상태 체크를 한다.
 * # 방문 체크를 부순 상태로 온 놈, 아직 부수지 않은 상태로 온 놈 2가지에 대해 체크한다.
 * @comment
 * # 1 try : 11% FAIL
 * # https://www.acmicpc.net/board/view/67446
 * # BFS, DFS에서 visited state에 관리하는 문제 연습하기
 * # 항상 경계 체크 잊지 말기
 */

import java.util.*;
import java.io.*;

public class BOJ_2206 {
	
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	// -----------------------------------------------------------------------------
	
	static class Pair {
		int r, c, moves; // moves : 이동 횟수
		boolean isBreak; // 벽을 한 개 부순 상태인지

		public Pair(int r, int c, int moves, boolean isBreak) {
			this.r = r;
			this.c = c;
			this.moves = moves;
			this.isBreak = isBreak;
		}
	}
	
	static int N, M;
	static char[][] map;
	static boolean[][][] visited;
	
	static int[] dr = {-1, 0, 1, 0}; // 상우하좌 시계방향
	static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// init
		map = new char[N][M];
		visited = new boolean[2][N][M];
		
		for (int r = 0; r < N; r++) {
			map[r] = br.readLine().toCharArray();
		} // input end
		
		// print
		System.out.println(solve());
	}
	
	static int solve() {
		// 0, 0에서부터 BFS 시작
		Queue<Pair> q = new LinkedList<>();
		q.offer(new Pair(0, 0, 1, false));
		visited[0][0][0] = true;
		
		while (!q.isEmpty()) {
			Pair cur = q.poll();
			
			// 도착 여부 체크
			/*
			 * 1 1
			 * 0
			 * => 1 
			 */
			if (cur.r == N - 1 && cur.c == M - 1) {
				return cur.moves;
			}
			
			// 4방 탐색
			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				
				if (isOut(nr, nc)) continue; // 경계체크
				if (cur.isBreak) { // 벽을 이미 부순 상태면
					// 벽이 아닌 공간만 이동할 수 있다.
					if (!visited[1][nr][nc] && map[nr][nc] == '0') {
						q.offer(new Pair(nr, nc, cur.moves + 1, true));
						visited[1][nr][nc] = true;
					}
				} else { // 벽을 부술 기회가 남아있으면 부수고 이동하거나 그냥 이동한다.
					if (!visited[0][nr][nc]) {
						if (map[nr][nc] == '1') {
							q.offer(new Pair(nr, nc, cur.moves + 1, true));
							visited[1][nr][nc] = true;
						} else {
							q.offer(new Pair(nr, nc, cur.moves + 1, false));
							visited[0][nr][nc] = true;
						}
					}
				}
			}
		}
		
		return -1;
	}
	
	static boolean isOut(int r, int c) {
		return (r < 0 || r >= N || c < 0 || c >= M);
	}

}

/*
13 13
0100011011000
0001001010000
0000100001000
1100010101011
1111101111000
1011010001001
0100001001001
0100111010001
0101010000100
0001110100000
0000001000100
1010001000111
1001000100000

27
*/
/**
 * @문제 드래곤 커브_G4
 * @날짜 210615
 * @분류 구현, 시뮬레이션
 * @조건
 * 시계방향으로 90도 회전
 * @풀이
 * 
 * @comment
 * 
 */

import java.util.*;
import java.io.*;

public class BOJ_15685 {
	
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	// -----------------------------------------------------------------------------
	
	static class Pair {
		int r, c, d; // d : 이전 점으로부터 어느 방향으로 뻗어나온 건지

		public Pair(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}


	static int N;
	static boolean[][] map;
	static int ans;

	static int[] dr = {0, -1, 0, 1}; // 우상좌하 반시계
	static int[] dc = {1, 0, -1, 0};
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		map = new boolean[101][101];
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine(), " ");
			int c = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			
			// 주어진 드래곤 커브의 정보에 따라 map에 마킹
			mark(r, c, d, g);
		}

		// print
		System.out.println(count());
	}
	
	static void mark(int r, int c, int d, int g) {
		// 0세대 초기화
		List<Pair> curve = new ArrayList<>(); // 커브를 구성하는 모든 점 리스트
		map[r][c] = true; // 시작점 마킹
		int nr = r + dr[d];
		int nc = c + dc[d];
		int nd = d;
		curve.add(new Pair(nr, nc, nd)); // 두번째 점 리스트에 추가
		map[nr][nc] = true; // 두번째 점 마킹
		
		// 세대 수에 따라 마킹
		while (g-- > 0) {
			int size = curve.size(); // 리스트 사이즈
			
			// 시작 좌표 초기화 => 끝 점
			nr = curve.get(size - 1).r;
			nc = curve.get(size - 1).c;
			
			// 리스트 사이즈만큼 반복하면서 새롭게 연결되는 커브 구성 점 추가
			while (size-- > 0) {
				Pair cur = curve.get(size); // 마지막에 연결된 점부터 참조하여 그려나가기
				nd = (cur.d + 1) % 4; // 새로운 방향
				nr += dr[nd];
				nc += dc[nd];
				
				// 마킹
				map[nr][nc] = true;
				// 리스트에 추가
				curve.add(new Pair(nr, nc, nd));
			}
		}
	}
	
	static int count() {
		int ret = 0;
		
		// map을 순회하면서 네 꼭지점이 전부 마킹된 정사각형 개수를 센다
		for (int r = 0; r < 100; r++) {
			for (int c = 0; c < 100; c++) {
				// 판단 시작점이 마킹된 부분만 체크
				if (map[r][c]) {
					// 하나라도 중간에 마킹 안되면 pass
					if (!map[r][c + 1]) continue;
					if (!map[r + 1][c + 1]) continue;
					if (!map[r + 1][c]) continue;
					
					// 네 꼭지점 모두 마킹되어 있으면 카운트 증가
					ret++;
				}
			}
		}
		
		return ret;
	}
		
}
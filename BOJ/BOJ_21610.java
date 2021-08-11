/**
 * @문제 마법사 상어와 비바라기_G5
 * @날짜 210811
 * @분류 
 * @조건
 * 이동할때 경계 넘어가도 연결
 * @풀이
 * 구름의 좌표를 리스트로 관리 => 4번 물복사까지 하고 -연산 => 물의 양이 2이상인 칸을 찾을 때 안걸리게끔
 * 5번 연산 후에 다시 -연산
 * 대각선 인덱스 2, 4, 6, 8 
 * @comment
 * list.get(i)를 Pair cur로 받아서 cur의 좌표를 수정해도 값이 바뀜
 * 경계 넘어가는 좌표 연산할때 조심 => 범위 안에 들어올때까지 N을 더하거나 뺴줘야 함 => (n % N + N) % N
 */

import java.util.*;
import java.io.*;

public class BOJ_21610 {
	
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
	
	static int N, M;
	static int[][] map;
	static List<Pair> clouds;
	
	static int[] dr = {0, 0, -1, -1, -1, 0, 1, 1, 1}; // di는 1부터
	static int[] dc = {0, -1, -1, 0, 1, 1, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 초기 구름 위치 리스트에 넣기
		clouds = new ArrayList<>();
		clouds.add(new Pair(N - 1, 0));
		clouds.add(new Pair(N - 1, 1));
		clouds.add(new Pair(N - 2, 0));
		clouds.add(new Pair(N - 2, 1));
		
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine(), " ");
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			move(d, s);
		}
		
		int ans = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				ans += map[r][c];
			}
		}
		
		// print
		System.out.println(ans);
	}
	
	static void move(int d, int s) {
		// 1~3. 모든 구름 이동 + 비 내리기
		rain(d, s);
		
		// 4. 물복사
		copy();
		
		// 5. 구름 갱신
		update();
	}
	
	static void update() {
		// 구름이 사라진 칸 구분
		for (int i = 0, end = clouds.size(); i < end; i++) {
			Pair cur = clouds.get(i);
			map[cur.r][cur.c] = -map[cur.r][cur.c]; 
		}
		
		// 구름 리스트 초기화
		clouds.clear();
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c] > 1) { // 물의 양이 2이상이면
					clouds.add(new Pair(r, c)); // 구름 리스트에 추가
					map[r][c] -= 2; // 물의 양 2감소
				} else if (map[r][c] < 0) { // 구름이 사라진 칸 되돌리기
					map[r][c] = -map[r][c];
				}
			}
		}
	}
	
	static void copy() {
		for (int i = 0, end = clouds.size(); i < end; i++) {
			Pair cur = clouds.get(i);
			int r = cur.r;
			int c = cur.c;
			// 4방 대각선 체크
			for (int d = 2; d <= 8; d += 2) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if (isOut(nr, nc)) continue; // 경계체크
				if (map[nr][nc] > 0) { // 물이 있으면
					map[r][c]++;
				}
			}
		}
	}

	static void rain(int d, int s) {
		// clouds 리스트 전체를 순회하면서 좌표 이동 + map의 물양 1증가
		for (int i = 0, end = clouds.size(); i < end; i++) {
			Pair cur = clouds.get(i);
			cur.r = check(cur.r + dr[d] * s);
			cur.c = check(cur.c + dc[d] * s);
			map[cur.r][cur.c]++;
		}
	}
	
	// 경계를 넘어갔을때 좌표 보정 메소드
	static int check(int n) {
//		while (true) {
//			if (n < 0) n += N;
//			else if (n >= N) n -= N;
//			else return n;
//		}
		return ((n % N + N) % N);
	}
	
	static boolean isOut(int r, int c) {
		return (r < 0 || r >= N || c < 0 || c >= N);
	}
	
}
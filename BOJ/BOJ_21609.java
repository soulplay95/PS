package week13;
/**
 * @문제 상어 중학교_G2
 * @날짜 210501
 * @분류 시뮬레이션
 * @조건
 * 블록 종류 2+M가지
 * 4방 인접
 * N <= 20
 * M <= 5
 * 블록 그룹 조건
 * 1. 일반 블록이 적어도 하나 있어야 한다.
 * 2. 일반 블록의 색은 모두 같아야 한다.
 * 3. 검은색 블록은 포함되면 안된다.
 * 기준 블록 : 일반 블록 중 작은 행, 작은 열
 * 블록 그룹이 존재하는 동안 계속해서 반복
 * @풀이
 * 1. 블록 그룹 찾고 크기가 가장 큰 블록 그룹 찾기 => (0, 0)부터 탐색하면서 BFS
 * BFS 조건 : 검은색x, 기준 블록(BFS 시작점)과 같은 색, 무지개는 상관x
 * 블록 그룹 크기는 큐의 사이즈로 측정. 포함된 무지개 블록 수
 * 2. 점수 획득
 * 3. 중력
 * 4. 90도 반시계 회전
 * 5. 중력
 * @comment
 * 
 */

import java.util.*;
import java.io.*;

public class BOJ_21609 {
	
	// input, output handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	// -----------------------------------------------------------------------------
	
	// 블록 좌표 정보를 나타내는 클래스
	static class Pair {
		int r, c;

		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int score;
	
	static int N, M;
	static int[][] map;
	static int maxGroupSize; // 최대 블록 그룹 크기
	static int beforeRainbowBlockCount;
	
	static int[] dr = {-1, 0, 1, 0}; // 상우하좌 시계방향
	static int[] dc = {0, 1, 0, -1}; // 상우하좌 시계방향

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
		} // input end
		
		score = 0;
		play();
		
		// print
		System.out.print(score);
	}
	
	static void play() {
		Queue<Pair> bigGroup = null; // 가장 큰 크기의 블록 그룹
		
		// 블록 그룹이 존재하는 동안만 play
		while ((bigGroup = findGroup()) != null) {
			score += getScoreAndDeleteBlock(bigGroup); // 점수 획득 & 블록 제거
			drop(); // 중력 작용
			map = rotate(); // 90도 반시계 회전
			drop(); // 중력 작용
		}
	}
	
	static void drop() {
		// 각 열에 대해 밑에서 부터 위로 올라가면서 중력 작용 
		for (int c = 0; c < N; c++) {
			int dropPoint = 0; // 떨어질 행 좌표
			for (int r = N - 1; r >= 0; r--) {
				if (dropPoint == 0 && map[r][c] == -2) { // 떨어질 거리가 정해지지 않았고, 빈칸이면 
					dropPoint = r; // dropPoint 한 번 갱신
				} else if (map[r][c] == -1) { // 검은 블록이면
					dropPoint = 0; // dropPoint 초기화
				} else if (dropPoint != 0 && map[r][c] >= 0) { // 떨어질 위치가 정해졌고, 색 블록이면
					int temp = map[r][c];
					map[r][c] = -2; // 빈칸으로 만들고
					map[dropPoint][c] = temp; // drop
					dropPoint--;
				}
			}
		}
	}
	
	static int[][] rotate() {
		int[][] newMap = new int[N][N];
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				newMap[r][c] = map[c][N - 1 - r];
			}
		}
		
		return newMap;
	}
	
	static int getScoreAndDeleteBlock(Queue<Pair> group) {
		int blockCount = 0;
		
		for (Pair p : group) {
			blockCount++;
			map[p.r][p.c] = -2; // 빈칸으로 만들기
		}
		
		return blockCount * blockCount;
	}
	
	static Queue<Pair> findGroup() {
		Queue<Pair> biggestGroup = null;
		boolean[][] visited = new boolean[N][N];
		maxGroupSize = 2;
		beforeRainbowBlockCount = 0;
		
		// map을 탐색하면서 가장 큰 블록 그룹을 찾음
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (!visited[r][c] && map[r][c] >= 1) { // 방문(이미 다른 그룹에 속한 블록)하지 않았고, 일반 블록이면
					Queue<Pair> temp = bfs(r, c, visited);
					// null이 리턴되면(기존 블록 그룹보다 작은 크기의 블록을 찾은 경우) 갱신 안함
					biggestGroup = (temp == null) ? biggestGroup : temp; // bfs로 가장 큰 블록 그룹 찾기
				}
			}
		}
		
		return biggestGroup;
	}
	
	static Queue<Pair> bfs(int r, int c, boolean[][] visited) {
		Queue<Pair> q = new LinkedList<>();
		Queue<Pair> group = new LinkedList<>();
		q.offer(new Pair(r, c));
		group.offer(new Pair(r, c));
		visited[r][c] = true;
		int color = map[r][c]; // 기준 블록의 색깔
		int rainbowBlockCount = 0; // 무지개 블록 개수
		Queue<Pair> rainbowBlocks = new LinkedList<>();
		
		while (!q.isEmpty()) {
			Pair cur = q.poll();
			
			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				
				if (isOut(nr, nc)) continue; // 경계 체크
				if (visited[nr][nc]) continue; // 방문 체크
				if (map[nr][nc] == color || map[nr][nc] == 0) { // 같은 색의 블록이거나 무지개 블록이면
					q.offer(new Pair(nr, nc));
					visited[nr][nc] = true;
					group.offer(new Pair(nr, nc));
					if (map[nr][nc] == 0) {
						rainbowBlocks.offer(new Pair(nr, nc));
						rainbowBlockCount++;
					}
				}
			}
		}
		
		// 무지개 블록 visited 초기화
		for (Pair p : rainbowBlocks) {
			visited[p.r][p.c] = false;
		}
		
		// 기존에 존재하는 블록 그룹 중 최대 크기보다 큰 블록 그룹이 형성되었으면
		if (group.size() >= maxGroupSize) {
			// 블록 크기가 같은데 무지개 블록 수가 적으면
			if (group.size() == maxGroupSize && beforeRainbowBlockCount > rainbowBlockCount) {
				return null;
			}
			maxGroupSize = group.size(); // 최대 블록 크기 갱신
			beforeRainbowBlockCount = rainbowBlockCount;
			return group;
		}
		
		return null;
	}
	
	static boolean isOut(int r, int c) {
		return (r < 0 || r >= N || c < 0 || c >= N);
	}

}
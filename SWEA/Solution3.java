// 1. 비상구 탈출
// 각 탈출구별로 모든 사람의 도착 시간을 구해 완탐?
// 각 탈출구별 큐를 두어서 1초마다 모든 사람에 대해 BFS 진행. 탈출구 도착 시 큐에 넣고 스케줄링

import java.util.*;
import java.io.*;

public class Solution3 {
	
	// 좌표를 나타내는 클래스
	static class Pair {
		int r, c;
		
		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int N, M, ans;
	static int[][] map;
	static boolean[] escaped; // 해당 인덱스 번호의 사람이 탈출했는지 여부
	static List<Pair> pos;
	static List<Pair> exitPos;
	static Queue<Integer> exitQ, exitQ2;
	
	static int[] dr = {-1, 0, 1, 0}; // 상우하좌 시계방향
	static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			// init
			map = new int[N][N];
			ans = 0;
			pos = new ArrayList<Pair>();
			exitPos = new ArrayList<Pair>();
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					if (map[r][c] == 1) { // 사람이면
						pos.add(new Pair(r, c));
					} else if (map[r][c] == 2) { // 탈출구이면
						exitPos.add(new Pair(r, c));
					}
				}
			} // input end
			
			M = pos.size(); // 사람 수
			escaped = new boolean[M];
			
			// print
			System.out.println("#" + t + " " + solve());
		}
	}
	
	static int solve() {
		Pair exit1 = exitPos.get(0);
		Pair exit2 = exitPos.get(1);
		exitQ = new LinkedList<Integer>();
		exitQ2 = new LinkedList<Integer>();
		int time = 0; // 현재 시간
		
		// 각 사람의 위치에서 1초 단위로 이동한다.
		boolean[][][] visited = new boolean[M][N][N];
		Queue<Pair>[] queues = new Queue[M];
		for (int i = 0; i < M; i++) {
			queues[i] = new LinkedList<Pair>(); // 사람 명수만큼 큐 만들기
			Pair initPos = pos.get(i); // 각 사람의 초기 위치
			queues[i].add(initPos); // 각 사람의 초기 위치 큐에 셋팅
			visited[i][initPos.r][initPos.c] = true; // 방문 체크
		}
		
		// 전부 탈출하기 전까지 반복
		while (!isAllEscaped()) {
			// 각 탈출구 큐를 먼저 체크하여 pop하기
			int num1 = -1;
			int num2 = -1;
			if (!exitQ.isEmpty()) {
				num1 = exitQ.poll();
				if (escaped[num1] && !exitQ.isEmpty()) {
					escaped[exitQ.poll()] = true;
				}
				escaped[num1] = true;
			}
			if (!exitQ2.isEmpty()) {
				num2 = exitQ2.poll();
				if (escaped[num2] && !exitQ2.isEmpty()) {
					escaped[exitQ2.poll()] = true;
				}
				escaped[num2] = true;
			}
			for (int i = 0; i < M; i++) {
				if (escaped[i]) {
					if (!exitQ.isEmpty()) {
						if (exitQ.contains(i)) {
							exitQ.remove(i);
						}
					}
					if (!exitQ2.isEmpty()) {
						if (exitQ2.contains(i)) {
							exitQ2.remove(i);
						}
					}
				}
			}

			
			// 아직 탈출 못한 모든 사람에 대해 이동
			for (int i = 0; i < M; i++) {
				if (!escaped[i]) {
					int size = queues[i].size();
					
					// 한 칸만 이동
					while (size-- > 0) {
						Pair cur = queues[i].poll();
						
						for (int d = 0; d < 4; d++) {
							int nr = cur.r + dr[d];
							int nc = cur.c + dc[d];
							
							// 경계 체크
							if (isOut(nr, nc)) continue;
							// 방문 체크
							if (visited[i][nr][nc]) continue;
							// 도착 체크
							if (nr == exit1.r && nc == exit1.c) {
								exitQ.add(i);
							} else if (nr == exit2.r && nc == exit2.c) {
								exitQ2.add(i);
							}
							visited[i][nr][nc] = true;
							queues[i].add(new Pair(nr, nc));
						}
					}
				}
			}
			
			time++;
		}
		
		return time;
	}
	
	static boolean isAllEscaped() {
		// 전부 다 탈출했으면 true
		for (int i = 0; i < M; i++) {
			if (!escaped[i]) return false;
		}
		
		return true;
	}
	
	static boolean isOut(int r, int c) {
		return (r < 0 || r >= N || c < 0 || c >= N);
	}
	
}

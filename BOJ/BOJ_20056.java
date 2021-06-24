/**
 * @문제 마법사 상어와 파이어볼_G5
 * @날짜 210624
 * @분류 구현, 시뮬레이션
 * @조건
 * # 행과 열의 가장자리가 연결되어 있음
 * # 방향은 8방
 * # 4 <= N <= 50
 * # 1 <= K <= 1000
 * @풀이
 * # 가장자리 벗어남 => mod 연산으로 좌표값 보정.
 * # Queue<Ball> [][]으로 맵을 만들어 시뮬레이션
 * @comment
 * # 모두 홀수이거나 짝수 판단 => 합이 짝수이면 모두 홀수이거나, 모두 짝수이다 => 0이 포함되면 이야기기 달라짐!!! 반례 : (0, 1, 3, 2)
 * # map의 행과 열 끝이 각각 연결되어 있을 때 좌표 계산 => 움직이는 칸 수(속력) % N을 원래 좌표에 더하고 결과가 음수면 +N / 양수면 -N
 * # 자바 큐(LinkedList) 복사 안되나??
 */

/*
 * 반례
4 9 5
3 2 8 5 2
3 3 19 3 4
3 1 7 1 1
4 4 6 4 0
2 1 6 2 5
4 3 9 4 3
2 2 16 1 2
4 2 17 5 3
3 4 3 5 7
=> 33
 */

import java.util.*;
import java.io.*;

public class BOJ_20056 {
	
	static class Ball {
		int m, s, d;

		public Ball(int m, int s, int d) {
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}
	
	static int N, M, K;
	static Queue<Ball>[][] map;
	
	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1}; // 상부터 시계방향
	static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		// 리스트 초기화
		map = new LinkedList[N][N];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				map[r][c] = new LinkedList<>();
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			map[r][c].offer(new Ball(m, s, d)); // List map에 초기 Ball 상태 넣기
		} // input end
		
		solve();
		
		// print
		System.out.println(getAns());
	}
	
	static void solve() {
		// K번 반복
		while (K-- > 0) {
			move();
			
			divide4();
		}
	
		return;
	}
	
	static void move() {
		// copy map 생성
		Queue<Ball>[][] cmap = new LinkedList[N][N];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				cmap[r][c] = new LinkedList<>();
			}
		}
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				// 파이어볼이 있는 칸에서
				if (map[r][c].size() > 0) {
					// 모든 파이어볼을 꺼내 이동
					while (!map[r][c].isEmpty()) {
						Ball cur = map[r][c].poll();
						
						// 좌표값 보정
						int nr = getN(r + dr[cur.d] * (cur.s % N)); 
						int nc = getN(r + dc[cur.d] * (cur.s % N));
						
						// cmap에 추가
						cmap[nr][nc].offer(new Ball(cur.m, cur.s, cur.d));
					}
				}
			}
		}
		
		map = cmap;
	}
	
	static void divide4() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				// 파이어볼이 2개 이상 있는 칸에서
				if (map[r][c].size() > 1) {
					int size = map[r][c].size();
					int mSum = 0;
					int sSum = 0;
					boolean evenFlag = true;
					boolean oddFlag = true;
					
					// 모든 파이어볼을 꺼냄
					while (!map[r][c].isEmpty()) {
						Ball cur = map[r][c].poll();
						mSum += cur.m;
						sSum += cur.s;
						if (cur.d % 2 == 0) {
							oddFlag = false;
						} else {
							evenFlag = false;
						}
					}
					
					mSum = mSum / 5;
					sSum = sSum / size;
					// 합쳐지는 파이어볼의 방향이 모두 홀수이거나 짝수인 경우
					if (mSum > 0) {
						if (evenFlag || oddFlag) {
							// 방향은 0, 2, 4, 6
							for (int d = 0; d < 4; d++) {
								map[r][c].offer(new Ball(mSum, sSum, d * 2));
							}
						} else {
							for (int d = 0; d < 4; d++) {
								map[r][c].offer(new Ball(mSum, sSum, d * 2 + 1));
							}
						}
					}
				}
			}
		}
	}
	
	static int getN(int n) {
		if (n < 0) {
			return n + N;
		} else if (n >= N) {
			return n - N;
		}
		
		return n;
	}
	
	static int getAns() {
		int ret = 0;
		
		// 남아있는 파이어볼 질량의 합 구하기
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				// 파이어볼이 있는 칸에서
				if (map[r][c].size() > 0) {
					// 모든 파이어볼을 꺼내 질량의 합을 누적
					while (!map[r][c].isEmpty()) {
						ret += map[r][c].poll().m;
					}
				}
			}
		}
		
		return ret;
	}

}
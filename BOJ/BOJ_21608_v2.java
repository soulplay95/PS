package week13;
/**
 * @문제 상어 초등학교_S1
 * @날짜 210430
 * @분류 시뮬레이션
 * @조건
 * 3 <= N <= 20
 * @풀이
 * 
 * @comment
 * 경계체크 더 빠르게?
 */

import java.util.*;
import java.io.*;

public class BOJ_21608_v2 {
	
	// input, output handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	// -----------------------------------------------------------------------------
	
	static int N;
	static int[][] map; // 인접 여부를 좌표상에서 관리하기 위한 map 
	static boolean[][] loveInfo; // 각 학생별 좋아하는 학생의 정보를 나타내는 map. adjMatrix[i][j] : i번 학생이 j번 학생을 좋아하면 true
	
	static int[] dr = {-1, 0, 1, 0}; // 상우하좌 시계방향
	static int[] dc = {0, 1, 0, -1}; // 상우하좌 시계방향

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		loveInfo = new boolean[N * N + 1][N * N + 1]; // 0번 학생 없음
		
		for (int i = 0, end = N * N; i < end; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken()); // 학생 번호
			
			// loveInfo 맵 채우기
			for (int j = 0; j < 4; j++) {
				loveInfo[num][Integer.parseInt(st.nextToken())] = true;
			}
			
			// 앉기
			sit(num);
		}
		
		// print
		System.out.println(getScore());
	}
	
	static void sit(int num) {
		// 규칙 1, 2에서 최대값을 기준
		int maxLoveSeatCount = -1; // 최대 (인접 칸 중 좋아하는 학생이 앉아있는 자리 수)
		int maxEmptySeatCount = -1; // 최대 (비어있는 자리 수)
		// 최종 좌석 좌표
		int rr = 0;
		int cc = 0;
		
		// map 전체를 탐색하면서 모든 빈칸에 대하여 조건 체크
		// --->
		// --->
		// ...
		// 순으로 탐색하기 때문에 규칙3은 자동으로 만족 
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				// 비어있는 칸에 대해
				if (map[r][c] == 0) {
					int loveSeatCount = 0;
					int emptySeatCount = 0;
					
					// 인접한 칸에 대해 조건1, 2를 따져보기 위해 좋아하는 학생 칸 수, 비어있는 칸 수 2가지를 구한다.
					for (int d = 0; d < 4; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						
						if (!isIn(nr, nc)) continue; // 경계 체크
						
						// 비어있는 칸의 4방 인접 칸(학생의 번호)이 "현재 자리를 잡으려는 학생이 좋아하는 학생"에 포함되면
						if (loveInfo[num][map[nr][nc]]) {
							loveSeatCount++;
						}
						
						// 비어있는 칸 수 세기
						if (map[nr][nc] == 0) {
							emptySeatCount++;
						}
					}
					
					// 규칙1, 2
					if (loveSeatCount > maxLoveSeatCount || 
							(loveSeatCount == maxLoveSeatCount) && (emptySeatCount > maxEmptySeatCount)) {
						maxLoveSeatCount = loveSeatCount;
						maxEmptySeatCount = emptySeatCount;
						rr = r;
						cc = c;
					}
				}
			}
		}
		
		// 앉히기
		map[rr][cc] = num;
	}
	
	static int getScore() {
		int sum = 0;
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				int cnt = 0; // 인접 칸 중 좋아하는 학생 수
				for (int d = 0; d < 4; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					if (!isIn(nr, nc)) continue;
					// 좋아하는 학생이면
					if (loveInfo[map[r][c]][map[nr][nc]]) cnt++;
				}
				
				sum += (int)Math.pow(10, cnt - 1); // 점수 누적
			}
		}
		
		return sum;
	}
	
	static boolean isIn(int r, int c) {
		return (r >= 0 && r < N && c >= 0 && c < N);
	}

}
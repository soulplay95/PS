/**
 * @문제 배열 돌리기 1_S3
 * @날짜 210802
 * @분류 구현
 * @조건
 * 
 * @풀이
 * # min(R, C) / 2로 반복 횟수 구하고, delta 이용하여 반시계 회전
 * @comment
 * # 반시계 회전 : delta 방향을 시계방향으로 잡고 map[r][c] = map[nr][nc]로 덮어쓰기
 */

import java.util.*;
import java.io.*;

public class BOJ_16926 {
	
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	// -----------------------------------------------------------------------------

	static int[][] map;
	static int R, C, K;

	static int[] dr = {0, 1, 0, -1}; // 우하좌상 시계방향
	static int[] dc = {1, 0, -1, 0};

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		// init
		map = new int[R][C];
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		} // input end

		solve();

		// print
		System.out.println(sb.toString());
	}
	
	static void solve() {
		while (K-- > 0) {
			rotate();
		}

		// make output
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				sb.append(map[r][c]).append(" ");
			}
			sb.append("\n");
		}
	}

	static void rotate() {
		int iterCounts = Math.min(R, C) / 2; // 반복 횟수

		for (int i = 0; i < iterCounts; i++) {
			int temp = map[i][i]; // 시작 좌표값 저장
			int r = i;
			int c = i;

			// 4방향 돌리기
			for (int d = 0; d < 4; d++) {
				while (true) {
					int nr = r + dr[d];
					int nc = c + dc[d];

					if (isOut(i, nr, nc)) break; // 범위 나가면 break해서 방향 바꾸기
					map[r][c] = map[nr][nc];
					r = nr;
					c = nc;
				}
			}
			// 마지막 값 덮어쓰기
			map[i + 1][i] = temp;
		}
	}

	static boolean isOut(int i, int r, int c) {
		return (r < i || r >= R - i || c < i || c >= C - i);
	}

}

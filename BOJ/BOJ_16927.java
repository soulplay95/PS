/**
 * @문제 배열 돌리기 2_S1
 * @날짜 210804
 * @분류 구현
 * @조건
 * 1 <= R <= 10억
 * @풀이
 * # 껍데기별로 R % 껍데기 총 길이 만큼만 회전
 * @comment
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16927 {
	
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	// -----------------------------------------------------------------------------

	static int R, C, K;
	static int[][] map;

	static int[] dr = {0, 1, 0, -1}; // 우하좌상. 시계방향
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
		print();

		// print
		System.out.println(sb.toString());
	}
	
	static void solve() {
		int count = Math.min(R, C) / 2; // 총 반복 횟수(껍데기 수)

		// 각 껍데기별로 회전 수를 구하여 회전
		for (int i = 0; i < count; i++) {
			int k = K % ((R + C - 2 * (1 + 2 * i)) * 2); // 회전 수

			while (k-- > 0) {
				rotate(i);
			}
		}
	}

	static void rotate(int i) {
		int r = i;
		int c = i;
		int temp = map[i][i];

		for (int d = 0; d < 4; d++) {
			while (true) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (isOut(i, nr, nc)) break; // 경계(껍데기) 나가면 방향 바꾸기

				map[r][c] = map[nr][nc];
				r = nr;
				c = nc;
			}
		}
		map[i + 1][i] = temp;
	}

	static boolean isOut(int i, int r, int c) {
		return (r < i || r >= R - i || c < i || c >= C - i);
	}

	static void print() {
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				sb.append(map[r][c]).append(" ");
			}
			sb.append("\n");
		}
	}

}

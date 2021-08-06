/**
 * @문제 배열 돌리기 4_G4
 * @날짜 210806
 * @분류 구현, 브루트포스
 * @조건
 * # 1 <= K <= 6
 * @풀이
 * # 최대 6! 만큼의 순열을 구해 모든 경우의 수에 대해 최소값을 찾는다.
 * @comment
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_17406 {

	static class Pair {
		int r, c;

		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int R, C, K, map[][], cmap[][], min, result[];
	static Pair[][] operations;

	static int[] dr = {1, 0, -1, 0}; // 하우상좌 반시계
	static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		// init
		operations = new Pair[K][2];
		map = new int[R][C];
		cmap = new int[R][C];
		min = 5001;
		result = new int[K];
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			operations[k][0] = new Pair(r - s - 1, c - s - 1);
			operations[k][1] = new Pair(r + s - 1, c + s - 1);
		} // input end

		solve();

		// print
		System.out.println(min);
	}

	static void solve() {
		nPr(0, 0);
	}

	static void nPr(int cnt, int flag) {
		// K개 다 뽑았으면
		if (cnt == K) {
			// result에 담긴 순서대로 연산 수행 후 최소값 갱신
			min = Math.min(min, getValue());

			return;
		}
		for (int i = 0; i < K; i++) {
			if ((flag & 1 << i) != 0) continue;

			result[cnt] = i;
			nPr(cnt + 1, flag | 1 << i);
		}
	}

	static int getValue() {
		int ret = 5001;
		// map copy
		for (int r = 0; r < R; r++) {
			cmap[r] = Arrays.copyOf(map[r], C);
		}

		// 순서대로 회전
		for (int k = 0; k < K; k++) {
			rotate(cmap, operations[result[k]][0], operations[result[k]][1]);
		}

		// 최소값 구하기
		for (int r = 0; r < R; r++) {
			int sum = 0;
			for (int c = 0; c < C; c++) {
				sum += cmap[r][c];

			}
			ret = Math.min(ret, sum);
		}

		return ret;
	}

	static void rotate(int[][] cmap, Pair start, Pair end) {
		int rSize = end.r - start.r + 1;
		int cSize = end.c - start.c + 1;
		int count = Math.min(rSize, cSize) / 2; // 반복 횟수
 		int r = start.r;
		int c = start.c;

		for (int i = 0; i < count; i++) {
			int sr = r + i;
			int sc = c + i;
			int temp = cmap[sr][sc];

			for (int d = 0; d < 4; d++) {
				while (true) {
					int nr = sr + dr[d];
					int nc = sc + dc[d];

					if (isOut(start, end, i, nr, nc)) break;

					cmap[sr][sc] = cmap[nr][nc];
					sr = nr;
					sc = nc;
				}
			}

			cmap[sr][sc + 1] = temp;
		}
	}

	static boolean isOut(Pair start, Pair end, int i, int r, int c) {
		int minR = start.r + i;
		int minC = start.c + i;
		int maxR = end.r - i;
		int maxC = end.c - i;

		return (r < minR || r > maxR || c < minC || c > maxC);
	}

}

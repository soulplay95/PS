package study;
/**
 * @문제 꽃길_S2
 * @날짜 210723
 * @분류 
 * @조건
 * # 6 <= N <= 10
 * # 0 <= G <= 200
 * @풀이
 * # 하나 심고 다음 꽃을 심었을 때 안죽는 칸을 visited로 계산한다음 거기서 또 심고..
 * # 64C1 * ?C1 * ?C1
 * @comment
 * # 마킹 해제할때 그 전에 심었던 씨앗에서 마킹된 부분을 해제하지 않게 조심
 */

import java.util.*;
import java.io.*;

public class BOJ_14620 {

	static int N, map[][], visited[][], min; // visited : depth로 마킹

	static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1, -2, 0, 2, 0}; // 마름모 꼴 => 8방 + 거리가 2칸인 4방
	static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1, 0, 2, 0, -2};

	public static void main(String[] args) throws IOException, NumberFormatException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		// init
		map = new int[N][N];
		visited = new int[N][N];
		min = 200 * 5 * 3 + 1;
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		} // input end

		solve();

		// print
		System.out.println(min);
	}

	static void solve() {
		plant(1, 0); // 첫 씨앗 심기
	}

	static void plant(int depth, int totalCost) {
		// base condition
		if (depth == 4) { // 씨앗 3개 다 심었으면
			// 최소값 갱신
			min = Math.min(min, totalCost);

			return;
		}

		// 경계를 제외한 안쪽 칸중에서
		for (int r = 1; r < N - 1; r++) {
			for (int c = 1; c < N - 1; c++) {
				if (visited[r][c] == 0) { // 씨앗을 심어도 안죽는 칸이면 심는다.
					mark(r, c, depth); // 마름모 범위 마킹
					plant(depth + 1, totalCost + getCost(r, c)); // 다음 씨앗 심기
					unmark(r, c, depth); // 마킹 해제
				}
			}
		}
	}

	static void mark(int r, int c, int type) {
		// (r, c)를 중심으로 대각선 길이가 2인 마름모 형태의 칸에 type값으로 마킹
		visited[r][c] = type;
		for (int d = 0; d < 12; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (isOut(nr, nc)) continue;
			if (visited[nr][nc] == 0) { // 다른 type(depth)으로 마킹된 곳을 덮어쓰지 않게
				visited[nr][nc] = type;
			}
		}
	}

	static void unmark(int r, int c, int type) {
		visited[r][c] = 0;
		for (int d = 0; d < 12; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];

			if (isOut(nr, nc)) continue;
			if (visited[nr][nc] == type) { // 다른 type이 마킹한 곳을 지우면 안된다.
				visited[nr][nc] = 0;
			}
		}
	}

	static int getCost(int r, int c) {
		// 5칸의 비용 계산
		int sum = map[r][c];

		// delta 0, 2, 4, 6 인덱스
		for (int d = 0; d <= 6; d += 2) {
			sum += map[r + dr[d]][c + dc[d]];
		}

		return sum;
	}

	static boolean isOut(int r, int c) {
		// 경계 포함
		return (r < 1 || r >= N - 1 || c < 1 || c >= N - 1);
	}

}

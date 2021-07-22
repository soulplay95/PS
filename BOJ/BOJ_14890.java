/**
 * @문제 경사로_G3
 * @날짜 210722
 * @분류 구현
 * @조건
 * # 2 <= N <= 100
 * # 1 <= L <= N
 * # 1 <= 각 칸의 높이 <= 10
 * @풀이
 * # 높이가 1낮아지는 경우, 같은 경우, 1높아지는 경우에 대해 따져본다.
 * @comment
 * # 디버깅 놀이
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14890 {
	
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	// -----------------------------------------------------------------------------

	static int N, L, map[][];

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		// init
		map = new int[N][N];
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		} // input end

		// print
		System.out.println(solve());
	}
	
	static int solve() {
		int ret = 0;

		// 2N가지 경우에 대해 따져본다.
		// 가로
		for (int r = 0; r < N; r++) {
			ret += isPossible(map[r]);
		}

		// 90도 회전
		int[][] rotated_map = new int[N][N];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				rotated_map[N - 1 - c][r] = map[r][c];
			}
		}

		// 세로
		for (int r = 0; r < N; r++) {
			ret += isPossible(rotated_map[r]);
		}

		return ret;
	}

	static int isPossible(int[] arr) {
		int before = arr[0]; // 첫번째 원소
		int count = 1; // 높이가 연속적으로 같은 칸 수

		for (int i = 1; i < N; i++) {
			int diff = arr[i] - before; // 전 값과의 높이 차

			if (diff == 0) { // 전 값과 높이가 같으면
				count++;
			} else if (diff == 1) { // 한 칸 높으면
				if (count >= L) { // L이상이라 커버 가능하면
					before = arr[i];
					count = 1; // count 1로 초기화하고 계속 진행
				} else {
					return 0;
				}
			} else if (diff == -1) { // 한 칸 낮으면
				int underCount = 1;
				int initNum = arr[i];
				int index = i + 1;
				// count가 L + 1 이상일때까지 진행해서 같은 count를 센다.
				while (index < N) {
					if (arr[index] == initNum) { // 같으면
						underCount++;
					} else {
						break;
					}

					index++;
				}

				if (index == N && underCount == L) { // 끝지점이면
					return 1; // 성공
				} else if (underCount < L) {
					// count가 L보다 작으면 실패
					return 0;
				}

				// i 갱신하고 계속 진행
				count = 0;
				i = i + L - 1;
				before = arr[i];
			} else { // 높이차가 2이상이면 길 만들기 실패
				return 0;
			}
		}

		return 1;
	}

}

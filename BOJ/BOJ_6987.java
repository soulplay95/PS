package study.week37;

/**
 * @문제 월드컵_G5
 * @날짜 211004
 * @분류 브루트포스 알고리즘, 백트래킹
 * @조건
 * #
 * @풀이
 * # 완전 탐색
 * # 경기 양상 총 15가지 => 각 경기마다 3가지의 경우의 수
 * # O(3^15)
 * @comment
 * # 기저 조건이랑 백트래킹 조건 위치 조심
 */

import java.util.*;
import java.io.*;

public class BOJ_6987 {

	static final int TC = 4;
	static int[][] input;
	static int[][] rounds; // 15가지 게임 양상
	static int ans;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		rounds = new int[15][2];
		int row = 0;
		for (int r = 0; r < 5; r++) {
			for (int c = r + 1; c < 6; c++) {
				rounds[row][0] = r;
				rounds[row][1] = c;
				row++;
			}
		}

		for (int t = 1; t <= TC; t++) {
			// init
			input = new int[6][3];

			for (int r = 0; r < 6; r++) {
				for (int c = 0; c < 3; c++) {
					input[r][c] = sc.nextInt();
				}
			}

			if (!isCorrectInput()) { // 6이 등장하거나, 승무패의 합이 5가 아닌 경우
				sb.append(0).append(" ");
				continue;
			}

			ans = 0;
			dfs(0);

			sb.append(ans).append(" ");
		}

		// print
		System.out.println(sb.toString());

		sc.close();
	}

	static boolean isCorrectInput() {
		for (int r = 0; r < 6; r++) {
			int sum = 0;
			for (int c = 0; c < 3; c++) {
				int cur = input[r][c];
				if (cur == 6) {
					return false;
				}

				sum += cur;
			}

			if (sum != 5) {
				return false;
			}
		}

		return true;
	}

	static void dfs(int depth) {
		// 백트래킹
		if (depth >= 1) {
			int beforeA = rounds[depth - 1][0];
			int beforeB = rounds[depth - 1][1];

			// 전 경기의 결과가 입력과 일치하지 않으면
			for (int i = 0; i < 3; i++) {
				if (input[beforeA][i] < 0 || input[beforeB][i] < 0) {
					return;
				}
			}
		}

		// 기저 조건
		if (depth == 15) {
			// 입력된 경기 결과가 가능
			ans = 1;
			return;
		}

		// 경기하는 두 팀의 인덱스
		int a = rounds[depth][0];
		int b = rounds[depth][1];

		// 1. a가 이기는 경우
		input[a][0]--;
		input[b][2]--;
		dfs(depth + 1);
		input[a][0]++;
		input[b][2]++;

		// 2. 비기는 경우
		input[a][1]--;
		input[b][1]--;
		dfs(depth + 1);
		input[a][1]++;
		input[b][1]++;

		// 3. b가 이기는 경우
		input[a][2]--;
		input[b][0]--;
		dfs(depth + 1);
		input[a][2]++;
		input[b][0]++;
	}

}

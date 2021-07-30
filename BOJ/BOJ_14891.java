package study.algo210802;

/**
 * @문제 톱니바퀴_G5
 * @날짜 210731
 * @분류 구현, 시뮬레이션
 * @조건
 * 1 <= 회전 횟수 <= 100
 * @풀이
 * # 톱니바퀴 회전 => (index + 1) % 8
 * # 맞닿은 극 판단 => 12시 방향부터 [0 ~ 7] 기준 index 2, 6
 * @comment
 * # 시계, 반시계 회전하는거 익숙해질때까지 연습. 행렬 지지고 볶기 시리즈
 */

import java.util.*;
import java.io.*;

public class BOJ_14891 {

	static char[][] gearWheels = new char[4][8]; // 톱니바퀴 N, S극 상태
	static int[] directions = new int[4]; // 톱니바퀴 별 회전 방향(0 : 회전x, 1 : 시계, -1 : 반시계)

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		for (int n = 0; n < 4; n++) {
			gearWheels[n] = br.readLine().toCharArray();
		}
		int K = Integer.parseInt(br.readLine());
		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine(), " ");
			int num = Integer.parseInt(st.nextToken()) - 1; // 톱니바퀴 번호
			int direction = Integer.parseInt(st.nextToken()); // 회전 방향(시계 or 반시계)

			select(num, direction); // 회전시킬 톱니바퀴 고른 후 회전
		}

		// print
		System.out.println(getScore());
	}

	static void select(int n, int d) {
		// 회전을 시작하는 톱니바퀴(n) 기준으로 맞닿은 극이 같은지 다른지 판단하여 회전시킬 톱니바퀴를 선정한다.
		directions[n] = d; // 회전 방향 설정

		int i = n; // 기준 톱니바퀴 번호
		// 오른쪽 톱니바퀴 중 고르기
		while (++i < 4) {
			// 맞닿은 톱니바퀴가 회전하게되고, 다른 극이면
			if (directions[i - 1] != 0 && gearWheels[i - 1][2] != gearWheels[i][6]) {
				directions[i] = -directions[i - 1]; // 반대 방향으로 회전
			} else {
				directions[i] = 0; // 회전 안함 상태로 초기화
			}
		}

		i = n;
		// 왼쪽 톱니바퀴 중 고르기
		while (--i >= 0) {
			// 맞닿은 톱니바퀴가 회전하게되고, 다른 극이면
			if (directions[i + 1] != 0 && gearWheels[i + 1][6] != gearWheels[i][2]) {
				directions[i] = -directions[i + 1]; // 반대 방향으로 회전
			} else {
				directions[i] = 0; // 회전 안함 상태로 초기화
			}
		}

		// 회전하게 될 톱니바퀴가 선정되었으면 회전
		for (int j = 0; j < 4; j++) {
			if (directions[j] != 0) {
				rotate(j, directions[j]);
			}
		}
	}

	static void rotate(int n, int d) {
		char temp;

		if (d == 1) { // 시계방향 회전
			temp = gearWheels[n][7];

			for (int i = 6; i >= 0; i--) {
				gearWheels[n][i + 1] = gearWheels[n][i];
			}
			gearWheels[n][0] = temp;
		} else { // 반시계방향 회전
			temp = gearWheels[n][0];

			for (int i = 1; i < 8; i++) {
				gearWheels[n][i - 1] = gearWheels[n][i];
			}
			gearWheels[n][7] = temp;
		}
	}

	static int getScore() {
		int sum = 0;

		for (int i = 0; i < 4; i++) {
			if (gearWheels[i][0] == '1') { // 12시 방향이 S극이면
				sum += (int)Math.pow(2, i);
			}
		}

		return sum;
	}

}
/**
 * @문제 오목
 * @날짜 211125
 * @분류
 * @조건
 * @풀이
 * @comment
 */

import java.util.*;
import java.io.*;

public class JUNGOL_1733 {

	static int[][] map = new int[21][21]; // 패딩
	static int[] dx = {1, 0, 1, -1}; // 아래, 오른쪽, 대각선 우측 아래, 대각선 우측 위
	static int[] dy = {0, 1, 1, 1};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for (int i = 1; i < 20; i++) {
			for (int j = 1; j < 20; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		sc.close(); // 스캐너 다 썼으면 닫아버림

		// logic
		for (int i = 1; i < 20; i++) {
			for (int j = 1; j < 20; j++) {
				int currVal = map[i][j];
				if (currVal == 0) continue; // 오목알이 아니면 관심 x
				// 흰돌 또는 검은돌 나왔음
				// 4가지 방향에 대해
				for (int k = 0; k < 4; k++) {
					int count = 1; // 오목 카운트
					int nx = dx[k]; // 방향값 int nx = dx[k] * count와 같은 코드
					int ny = dy[k];

					// 진행하려는 방향과 반대 방향에 이미 같은 색의 돌이 있다면 앞에서 체크된 상태
					if (currVal == map[i + dx[k] * -1][j + dy[k] * -1]) {
						continue; // 더 이상 이 방향은 체크할 필요 없다.
					}

					// 같은 값이 아닐때까지 반복
					while (currVal == map[i + nx][j + ny]) {
						count++;
						nx = dx[k] * count;
						ny = dy[k] * count;
					}

					// count는 같은 값을 가진 수를 체크
					if (count == 5) {
						System.out.println(currVal);
						System.out.println(i + " " + j);
						System.exit(0);
					}
				}
			}
		}
		System.out.println(0);
	}

}

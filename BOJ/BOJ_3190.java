/**
 * @문제 뱀_G5
 * @날짜 210817
 * @분류 구현, 자료구조, 시뮬레이션, 덱, 큐
 * @조건
 * # 2 <= N <= 100
 * @풀이
 * # 뱀의 정보(구성하는 좌표들)를 Dequeue로 관리한다.
 * # 사과가 있는 칸으로 이동하면 offerFront(), 없는 칸으로 이동하면 offerFront() + pollLast()
 * @comment
 * 
 */

import java.io.IOException;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class BOJ_3190 {

	static class Pair {
		int r, c, d;

		public Pair(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}

	static int N, K, L, time;
	static int[][] map; // 사과: -1, 뱀: 1
	static char[] rotateInfo; // 방향 변환 시간
	static Deque<Pair> snake;

	static int[] dr = {-1, 0, 1, 0}; // 상우하좌 시계방향
	static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		// init
		map = new int[N][N];
		rotateInfo = new char[10001];
		time = 0;
		for (int k = 0; k < K; k++) {
			int r = sc.nextInt() - 1;
			int c = sc.nextInt() - 1;
			map[r][c] = -1; // 사과 좌표 마킹
		}
		L = sc.nextInt();
		for (int l = 0; l < L; l++) {
			int x = sc.nextInt();
			char d = sc.next().charAt(0);
			rotateInfo[x] = d;
		} // input end
		sc.close();

		solve();

		// print
		System.out.println(time);
	}
	
	static void solve() {
		// 초기 뱀 정보 셋팅
		snake = new LinkedList<>();
		snake.offer(new Pair(0, 0, 1));
		map[0][0] = 1;

		while (move()) {
			time++;
		}
	}

	static boolean move() {
		// 0. 방향 변환 여부 체크
		if (rotateInfo[time] > 0) {
			int nd = 0;

			if (rotateInfo[time] == 'L') {
				nd = (4 + snake.peekFirst().d - 1) % 4;
			} else {
				nd = (snake.peekFirst().d + 1) % 4;
			}

			snake.peekFirst().d = nd;
		}

		// 스네이크 머리 정보
		Pair head = snake.peekFirst();
		int r = head.r;
		int c = head.c;
		int d = head.d;

		// 1. 이동하게 될 좌표의 경계 체크
		int nr = r + dr[d];
		int nc = c + dc[d];

		if (isOut(nr, nc)) {
			time++;
			return false;
		}

		// 2. 충돌 체크
		if (map[nr][nc] == 1) {
			time++;
			return false;
		}

		// 3. 사과 체크 + 이동
		if (map[nr][nc] == -1) {
			snake.offerFirst(new Pair(nr, nc, d));
			map[nr][nc] = 1;
		} else {
			snake.offerFirst(new Pair(nr, nc, d));
			Pair tail = snake.pollLast();
			map[tail.r][tail.c] = 0;
			map[nr][nc] = 1;
		}

		return true;
	}

	static boolean isOut(int r, int c) {
		return (r < 0 || r >= N || c < 0 || c >= N);
	}

}

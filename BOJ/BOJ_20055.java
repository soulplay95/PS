/**
 * @문제 컨베이어 벨트 위의 로봇
 * @날짜 210904
 * @분류 구현, 시뮬레이션
 * @조건
 * # 2 <= N <= 100
 * @풀이
 * # 컨베이어 벨트를 배열로 관리
 * # 로봇을 큐로 관리(로봇의 위치)
 * @comment
 * 
 */

import java.util.*;
import java.io.*;

public class BOJ_20055 {

	static class Section {
		int durability; // 내구도
		boolean isRobotOn; // 로봇이 칸에 있으면 true

		public Section(int durability, boolean isRobotOn) {
			this.durability = durability;
			this.isRobotOn = isRobotOn;
		}
	}

	static int N, K, step, zeroDurabilityCounts;
	static Section[] belt;
	static Queue<Integer> robots;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		// init
		step = 1;
		zeroDurabilityCounts = 0; // 내구도 0인 칸의 개수
		belt = new Section[2 * N];
		robots = new LinkedList<>();
		for (int n = 0; n < 2 * N; n++) {
			int durability = sc.nextInt();
			belt[n] = new Section(durability, false);
		} // input end
		sc.close();

		solve();

		// print
		System.out.println(step);
	}

	static void solve() {
		while (true) {
			rotate();
			move();
			push();

			if (zeroDurabilityCounts >= K) {
				break;
			}
			step++;
		}
	}

	static void rotate() {
		// 1-1. 벨트 회전 => copy
		Section[] copy = new Section[2 * N];
		for (int i = 0; i < 2 * N - 1; i++) {
			copy[i + 1] = belt[i];
		}
		copy[0] = belt[2 * N - 1];

		belt = copy;

		// 1-2. 로봇 인덱스 1씩 증가
		int size = robots.size();
		while (size-- > 0) {
			int pos = robots.poll();

			if (pos == 2 * N - 1) { // 마지막 위치의 로봇이면 0 위치로
				robots.offer(0);
			} else {
				robots.offer(pos + 1);
			}
		}
	}

	static void move() {
		// 2. 로봇 이동
		int size = robots.size();
		while (size-- > 0) {
			int cur = robots.poll();

			belt[cur].isRobotOn = false;
			if (cur == 2 * N - 1) {
				check(0);
			} else {
				check(cur + 1);
			}
		}
	}

	static void check(int nPos) {
		// nPos 즉, 새로운 위치에 로봇이 없거나 내구도가 0이상이면 움직임
		if (belt[nPos].durability > 0 && !belt[nPos].isRobotOn) {
			belt[nPos].durability--; // 내구도 1 감소

			if (belt[nPos].durability == 0) {
				zeroDurabilityCounts++;
			}
			robots.offer(nPos); // 새로운 위치로 이동
			belt[nPos].isRobotOn = true;
		}
	}

	static void push() {
		// 3. 로봇 첫 번째 칸에 올리기
		if (belt[0].durability != 0) {
			robots.offer(0); // 로봇 올리고
			belt[0].isRobotOn = true;
			belt[0].durability--; // 내구도 감소
			if (belt[0].durability == 0) {
				zeroDurabilityCounts++;
			}
		}
	}

}

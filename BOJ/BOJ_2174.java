package study.week31;

/**
 * @문제 로봇 시뮬레이션_G5
 * @날짜 210814
 * @분류 
 * @조건
 * # 1 <= R, C, N, M <= 100
 * @풀이
 * # 회전 명령: 반복 횟수 % 4
 * # 이동 명령: 재귀로 한칸씩 이동하면서 충돌여부 체크
 * @comment
 * # indexOf : 자료구조에서 특정 문자의 인덱스를 찾음
 * # 자바에서 배열에서는 indexOf를 지원x, ArrayList 자료구조에서만 지원. => 배열을 asList()를 통해 변환
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2174 {

	static class Robot {
		int r, c, d;

		public Robot(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}

	static int R, C, N, M;
	static int[][] map; // map[r][c] = n => n번 로봇이 (r, c)에 위치
	static Robot[] robots; // 로봇 정보(위치, 바라보는 방향)
	static List<String> directions;

	static int[] dr = {-1, 0, 1, 0}; // 북동남서
	static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// init
		map = new int[R][C];
		robots = new Robot[N + 1]; // from 1
		directions = new ArrayList<>();
		// 북동남서
		directions.add("N");
		directions.add("E");
		directions.add("S");
		directions.add("W");
		for (int n = 1; n <= N; n++) {
			st = new StringTokenizer(br.readLine(), " ");
			int c = Integer.parseInt(st.nextToken()) - 1;
			int r = R - Integer.parseInt(st.nextToken());
			int d = directions.indexOf(st.nextToken());
			map[r][c] = n; // map에 로봇 번호 마킹
			robots[n] = new Robot(r, c, d); // 로봇 정보 저장
		}
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			char command = st.nextToken().charAt(0);
			int repeatCounts = Integer.parseInt(st.nextToken());
			// 명령어 분기
			if (command == 'L' || command == 'R') {
				rotate(n, repeatCounts, command); // 회전
			} else {
				move(n, repeatCounts, 0, robots[n].d, robots[n].r, robots[n].c); // 이동
			}
		}

		// print
		System.out.println("OK");
	}

	// 로봇 번호, 반복 횟수, 회전 방향
	static void rotate(int n, int counts, char command) {
		int d = robots[n].d; // 현재 로봇이 바라보는 방향

		if (command == 'L') {
			d = (d - (counts % 4) + 4) % 4;
		} else {
			d = (d + counts) % 4;
		}

		robots[n].d = d;
	}
	
	// 로봇 번호, 총 반복 횟수, 현재까지 명령어 시행한 횟수, 바라보는 방향
	static void move(int n, int counts, int depth, int d, int r, int c) {
		// base condition
		if (depth == counts) {
			// 이동하기 전 초기 좌표 마킹 해제 + 갱신
			map[robots[n].r][robots[n].c] = 0;
			robots[n].r = r;
			robots[n].c = c;

			// 로봇 이동(map에서 이동)
			map[r][c] = n;

			return;
		}

		// 이동하게 될 다음 좌표
		int nr = r + dr[d];
		int nc = c + dc[d];

		// 경계 체크(벽 충돌)
		if (isOut(nr, nc)) {
			System.out.println("Robot " + n + " crashes into the wall");
			System.exit(0);
		}

		// 다른 로봇과의 충돌 체크
		if (map[nr][nc] > 0) {
			System.out.println("Robot " + n + " crashes into robot " + map[nr][nc]);
			System.exit(0);
		}

		// 이동
		move(n, counts, depth + 1, d, nr, nc);
	}

	static boolean isOut(int r, int c) {
		return (r < 0 || r >= R || c < 0 || c >= C);
	}

}

/**
 * @문제 주사위 굴리기_G4
 * @날짜 210812
 * @분류 구현, 시뮬레이션
 * @조건
 * 1 <= R, C <= 20
 * 1 <= K <= 1000
 * @풀이
 * # 주사위가 놓여져 있을 때 6면의 정보를 유지한다.
 * # 굴린 후에 놓여져 있는 상태에서 정보를 업데이트한다.
 * @comment
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14499 {
	
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	// -----------------------------------------------------------------------------

	static class Pair {
		int r, c;

		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int R, C, K;
	static int[] dice = new int[6]; // 주사위 6면 정보
	static int[][] map;
	static Pair dicePos; // 주사위 위치 좌표

	static int[] dr = {0, 0, 0, -1, 1}; // 원점, 동, 서, 남, 북
	static int[] dc = {0, 1, -1, 0, 0};

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		// init
		map = new int[R][C];
		dicePos = new Pair(x, y);
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine(), " ");
		for (int k = 0; k < K; k++) {
			roll(Integer.parseInt(st.nextToken()));
		} // input end

		// print
		System.out.println(sb.toString());
	}
	
	static void roll(int d) {
		int nr = dicePos.r + dr[d];
		int nc = dicePos.c + dc[d];

		// 경계 체크
		if (isOut(nr, nc)) {
			return;
		}

		// 좌표 이동
		dicePos.r = nr;
		dicePos.c = nc;

		update(d, nr, nc); // 주사위 6면 숫자 갱신
	}

	static boolean isOut(int r, int c) {
		return (r < 0 || r >= R || c < 0 || c >= C);
	}

	static void update(int d, int nr, int nc) {
		int[] copyDice = new int[6];

		switch (d) {
			case 1: // 동
				copyDice[0] = dice[4];
				copyDice[2] = dice[0];
				copyDice[5] = dice[2];
				copyDice[4] = dice[5];
				copyDice[1] = dice[1];
				copyDice[3] = dice[3];
				break;
			case 2: // 서
				copyDice[0] = dice[2];
				copyDice[2] = dice[5];
				copyDice[5] = dice[4];
				copyDice[4] = dice[0];
				copyDice[1] = dice[1];
				copyDice[3] = dice[3];
				break;
			case 3: // 북
				copyDice[0] = dice[1];
				copyDice[3] = dice[0];
				copyDice[5] = dice[3];
				copyDice[1] = dice[5];
				copyDice[2] = dice[2];
				copyDice[4] = dice[4];
				break;
			case 4: // 남
				copyDice[0] = dice[3];
				copyDice[3] = dice[5];
				copyDice[5] = dice[1];
				copyDice[1] = dice[0];
				copyDice[2] = dice[2];
				copyDice[4] = dice[4];
				break;
		}

		int num = map[nr][nc]; // 굴린 후 바닥에 쓰여있는 숫자
		if (num == 0) {
			map[nr][nc] = copyDice[5]; // 주사위 바닥면 숫자 => 지도에 복사
		} else {
			map[nr][nc] = 0;
			copyDice[5] = num;
		}

		dice = copyDice;

		// append
		sb.append(dice[0]).append("\n");
	}

}

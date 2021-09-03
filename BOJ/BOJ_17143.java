/**
 * @문제 낚시왕_G2
 * @날짜 210903
 * @분류 구현, 시뮬레이션
 * @조건
 * # 2 <= R, C <= 100
 * # 0 <= M(상어의 수) <= R * C
 * # 0 <= s(상어의 속력) <= 1000
 * # 1 <= z(상어의 크기) <= 10000
 * @풀이
 * # 상어 클래스 배열 만들어서 상어 관리
 * # Queue<상어의 인덱스>[][]로 map 관리
 * @comment
 * 
 */

import java.io.*;
import java.util.*;

public class BOJ_17143 {

	static class Shark {
		int r, c, s, d, z;

		public Shark(int r, int c, int s, int d, int z) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}
	}

	static int R, C, M;
	static Shark[] sharks;
	static Queue<Integer> map[][];
	static int pos, ans;

	static int[] dr = {-1, 1, 0, 0}; // 위 아래 오른쪽 왼쪽
	static int[] dc = {0, 0, 1, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// init
		sharks = new Shark[M]; // 상어 배열
		map = new LinkedList[R][C];
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				map[r][c] = new LinkedList<>();
			}
		}
		pos = -1; // 낚시왕 초기 위치 열
		ans = 0;

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken()) - 1;
			int z = Integer.parseInt(st.nextToken());

			sharks[m] = new Shark(r, c, s, d, z);
			map[r][c].offer(m); // 상어의 인덱스 map에 넣기
		} // input end

		solve();

		// print
		System.out.println(ans);
	}

	static void solve() {
		// 낚시왕이 열 끝으로 이동할때 까지 반복
		while (++pos < C) {
			hunt();
			move();
		}
	}

	static void hunt() {
		// pos 열의 상어 중 가장 작은 행의 상어를 잡음
		for (int r = 0; r < R; r++) {
			if (map[r][pos].size() > 0) { // 상어가 존재하면
				int sharkIndex = map[r][pos].poll(); // 상어의 인덱스
				ans += sharks[sharkIndex].z; // 크기 누적
				sharks[sharkIndex] = null;

				return;
			}
		}
	}

	static void move() {
		// 상어가 이동
		// 상어 배열을 순회하면서 not null이면 이동
		for (int i = 0; i < M; i++) {
			if (sharks[i] != null) {
				Shark cur = sharks[i]; // 이동할 상어 정보
				map[cur.r][cur.c].poll(); // 현재 위치에서 꺼내기

				// r, c, d
				int[] newShark = getNew(cur.r, cur.c, cur.s, cur.d); // 이동 후 상어의 새로운 정보

				// 상어 배열 정보 갱신
				int nr = newShark[0];
				int nc = newShark[1];
				int nd = newShark[2];
				sharks[i].r = nr;
				sharks[i].c = nc;
				sharks[i].d = nd;

				// map 상의 새로운 위치에 넣기
				map[nr][nc].offer(i);
			}
		}

		checkDuplicate(); // 한 칸에 여러 상어 있는 경우 체크하여 잡아먹기
	}

	static int[] getNew(int r, int c, int s, int d) {
		// mod 연산?

		int[] ret = new int[3];

		int nr = r;
		int nc = c;
		int nd = d;
		// 속력만큼 칸 수 이동
		while (s-- > 0) {
			// 이동할 다음 위치
			int nnr = nr + dr[nd];
			int nnc = nc + dc[nd];

			// 경계체크
			if (isOut(nnr, nnc)) {
				// 방향 바꾸기
				switch (nd) {
					case 0:
						nd = 1;
						break;
					case 1:
						nd = 0;
						break;
					case 2:
						nd = 3;
						break;
					case 3:
						nd = 2;
						break;
				}

				// nnr, nnc 변경
				nnr = nr + dr[nd];
				nnc = nc + dc[nd];
			}

			nr = nnr;
			nc = nnc;
		}

		// 리턴 배열 구성
		ret[0] = nr;
		ret[1] = nc;
		ret[2] = nd;

		return ret;
	}

	static boolean isOut(int r, int c) {
		return (r < 0 || r >= R || c < 0 || c >= C);
	}

	static void checkDuplicate() {
		// 상어 배열 순회하면서 not null인 상어의 위치로 map의 size를 체크하여 잡아먹기
		for (int i = 0; i < M; i++) {
			if (sharks[i] != null) {
				int r = sharks[i].r;
				int c = sharks[i].c;

				if (map[r][c].size() > 1) { // 2마리 이상의 상어가 존재 시
					// 잡아먹기
					eat(r, c);
				}
			}
		}
	}

	static void eat(int r, int c) {
		List<Integer> list = new ArrayList<>(); // 큐에서 전부 빼낸 상어들의 인덱스 리스트
		int max = 0;
		int maxIndex = 0;

		// 큐에서 다 빼내고 크기 비교
		for (int i = 0, end = map[r][c].size(); i < end; i++) {
			int index = map[r][c].poll();
			list.add(index);

			int size = sharks[index].z;
			if (max < size) {
				maxIndex = i;
			}
		}

		int king = list.remove(maxIndex); // 최대 상어 제외
		map[r][c].offer(king);

		// 리스트에 담긴 잡아먹힐 상어들 없애기
		for (int i = 0, end = list.size(); i < end; i++) {
			int index = list.get(i);

			sharks[index] = null;
		}
	}

}

/**
 * @문제 캐슬 디펜스_G4
 * @날짜 210728
 * @분류 구현, 브루트포스, BFS, 시뮬레이션
 * @조건
 * # 거리가 D이하인 적 중에서 가장 가까운 적을 공격
 * @풀이
 * # 15C3 * 시뮬레이션
 * # 가장 가까운 적을 탐색할 때 BFS 이용
 * # delta 왼쪽부터 수행
 * @comment
 * # 자바 Set의 중복 제거 로직은 equals가 true일때만 중복제거한다.
 * # 따라서 자바 Set 중복 제거시, new 객체를 넣으면 내부 값이 같아도 중복제거 안해준다. => hashCode(), equals() 메소드 재정의 필요
 */

import java.util.*;
import java.io.*;

public class BOJ_17135 {

	static class Pair {
		int r, c;

		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public int hashCode() {
			return Objects.hash(this.r + this.c);
		}

		@Override
		public boolean equals(Object o) {
			Pair p = (Pair)o;
			return (this.r == p.r && this.c == p.c);
		}
	}

	static int N, M, D, max, enemyCounts, remainEnemies;
	static char[][] map;
	static boolean[][] visited;
	static int[] archers; // 선택된 궁수 3명의 column 인덱스를 담는 배열
	static Set<Pair> killedEnemies; // 동시성 고려 죽은 적 좌표를 담는 Set

	static int[] dr = {0, -1, 0}; // 좌상우 시계방향
	static int[] dc = {-1, 0, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		// init
		map = new char[N][M];
		archers = new int[3];
		max = 0;
		enemyCounts = 0; // 적의 수
		killedEnemies = new HashSet<>();
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < M; c++) {
				map[r][c] = st.nextToken().charAt(0);
				if (map[r][c] == '1') {
					enemyCounts++; // 적의 수 카운팅
				}
			}
		} // input end

		solve();

		// print
		System.out.println(max);
	}

	static void solve() {
		nCr(0, 0);
	}

	static void nCr(int cnt, int start) {
		if (cnt == 3) { // 궁수 3명 뽑으면
			// 최대값 갱신
			max = Math.max(max, getKillCounts());
			return;
		}

		// 0 ~ M 중 3개를 뽑는다.
		for (int i = start; i < M; i++) {
			archers[cnt] = i;
			nCr(cnt + 1, i + 1);
		}
	}

	static int getKillCounts() {
		// 뽑은 3명의 궁수 위치 기반으로 게임이 끝날때까지 턴을 돌려 제거한 적의 수를 카운팅한다.
		int cnt = 0;
		remainEnemies = enemyCounts; // 맵에 남은 적 수
		// 조합마다 게임 진행 과정에서 적의 마킹(1)을 제거하기 위해 맵 copy
		char[][] cmap = new char[N][M];
		for (int r = 0; r < N; r++) {
			cmap[r] = Arrays.copyOf(map[r], M);
		}

		// 맵에 적이 있을동안 게임 진행
		while (remainEnemies > 0) {
			// 3명 동시에 한 턴 진행
			for (int i = 0; i < 3; i++) {
				play(i, cmap); // 사냥
			}
			// 동시성 고려 Set에서 제거된 적 꺼내서 제거
			cnt += killedEnemies.size(); // 사냥으로 제거한 적 숫자 누적
			remainEnemies -= killedEnemies.size(); // 맵에 남은 적 수 갱신
			for (Pair p : killedEnemies) {
				cmap[p.r][p.c] = '0';
			}
			killedEnemies.clear(); // set 클리어

			// 적들 아래로 한칸 이동
			move(cmap);

		}

		return cnt;
	}

	static void play(int i, char[][] cmap) {
		// 거리가 1인 지점에 적이 있으면 바로 제거
		if (cmap[N - 1][archers[i]] == '1') {
			killedEnemies.add(new Pair(N - 1, archers[i]));
			return;
		}

		// BFS로 가장 가까운 적을 찾는다.
		// 궁수 위치 (r, c)
		int r = N - 1; // 좌표 계산을 위해 거리가 1인 곳부터 생각
		int c = archers[i];
		int distance = 1;
		Queue<Pair> q = new LinkedList<>();
		q.offer(new Pair(r, c));
		boolean[][] visited = new boolean[N][M]; // 한 궁수, 한 턴마다 visited 생성
		visited[r][c] = true;

		while (!q.isEmpty() && distance < D) {
			// 거리 계산을 위해 한 싸이클 당 거리 1증가
			int size = q.size();

			while (size-- > 0) {
				Pair cur = q.poll();

				for (int d = 0; d < 3; d++) {
					int nr = cur.r + dr[d];
					int nc = cur.c + dc[d];

					if (isOut(nr, nc)) continue;
					if (!visited[nr][nc]) {
						if (cmap[nr][nc] == '1') { // 적을 찾았으면
							killedEnemies.add(new Pair(nr, nc)); // 제거될 적 좌표 추가
							return;
						}

						visited[nr][nc] = true; // 방문체크
						q.offer(new Pair(nr, nc));
					}
				}
			}

			// 한 싸이클마다 거리 1증가
			distance++;
		}
	}

	static void move(char[][] cmap) {
		// 맨 아래행에 있어 제거될 적 세기
		for (int c = 0; c < M; c++) {
			if (cmap[N - 1][c] == '1') {
				remainEnemies--;
			}
		}
		// 아래로 하나씩 내린다.
		for (int r = N - 1; r > 0; r--) {
			cmap[r] = Arrays.copyOf(cmap[r - 1], M);
		}
		// 첫 행 비우기
		for (int c = 0; c < M; c++) {
			cmap[0][c] = '0';
		}
	}

	static boolean isOut(int r, int c) {
		return (r < 0 || r >= N || c < 0 || c >= M);
	}

}

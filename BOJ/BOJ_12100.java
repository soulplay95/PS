/**
 * @문제 2048 (Easy)_G2
 * @날짜 210816
 * @분류 구현, 브루트포스, 시뮬레이션, 백트래킹
 * @조건
 * # 1 <= N <= 20
 * @풀이
 * # 최대 4^5 만큼 돌린다.
 * # 2, 4, 8, .., 1024의 블록 개수를 관리해서 남은 횟수로 이미 구한 최대값을 넘길수 없으면 백트래킹
 * @comment
 * # 중력, 쌓기 => queue 또는 Dequeue 이용
 * # getLast, pollLast => Dequeue
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_12100 {
	
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	// -----------------------------------------------------------------------------

	static class Block {
		int num;
		boolean isUnion; // 이미 합쳐진 블록이냐

		public Block(int _num, boolean _isUnion) {
			num = _num;
			isUnion = _isUnion;
		}
	}

	static int N, map[][], max, counts[];

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		// init
		map = new int[N][N];
		counts = new int[16]; // log1024 = 10
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] > 0) {
					counts[log2(map[r][c])]++;
				}
			}
		} // input end

		getMax();
		solve();

		// print
		System.out.println(max);
	}

	static void getMax() {
		for (int i = 15; i > 0; i--) {
			if (counts[i] > 0) {
				max = (int)Math.pow(2, i);
				return;
			}
		}
	}

	static int log2(int x) {
		return (int) (Math.log(x) / Math.log(2));
	}
	
	static void solve() {
		dfs(0, max);
	}

	static void dfs(int depth, int maxTemp) {
//		if (!check(depth, maxTemp)) {
//			max = Math.max(max, maxTemp); // 최대값 갱신
//			return;
//		}

		if (depth == 5) {
			max = Math.max(max, maxTemp); // 최대값 갱신
			return;
		}

		for (int d = 0; d < 4; d++) {
			int[][] cmap = new int[N][N];
			// 맵 복사
			for (int r = 0; r < N; r++) {
				cmap[r] = Arrays.copyOf(map[r], N);
			}
			dfs(depth + 1, move(d, maxTemp));
			map = cmap; // 맵 복원
		}
	}

	static int move(int d, int maxTemp) {
		// 방향에 따라 블록 이동 후 맵을 갱신하고 최대값 리턴
		int ret = maxTemp;
		Deque<Block> q = new LinkedList<>();

		switch (d) {
			case 0: // 위쪽
				for (int c = 0; c < N; c++) {
					for (int r = 0; r < N; r++) {
						if (map[r][c] > 0) { // 블럭이면
							if (q.size() == 0) { // 첫 블록
								q.offer(new Block(map[r][c], false));
								map[r][c] = 0;
								continue;
							}
							Block last = q.getLast();
							if (!last.isUnion && last.num == map[r][c]) {
								// 합치기
								if (map[r][c] == ret) {
									ret = maxTemp * 2;
								}
								q.pollLast();
								q.offer(new Block(map[r][c] * 2, true));
							} else {
								q.offer(new Block(map[r][c], false));
							}
						}
						map[r][c] = 0;
					}

					// 당기기
					int r = 0;
					while (!q.isEmpty()) {
						map[r++][c] = q.poll().num;
					}
				}
				break;
			case 1: // 아래
				for (int c = 0; c < N; c++) {
					for (int r = N - 1; r >= 0; r--) {
						if (map[r][c] > 0) { // 블럭이면
							if (q.size() == 0) { // 첫 블록
								q.offer(new Block(map[r][c], false));
								map[r][c] = 0;
								continue;
							}
							Block last = q.getLast();
							if (!last.isUnion && last.num == map[r][c]) {
								// 합치기
								if (map[r][c] == ret) {
									ret = maxTemp * 2;
								}
								q.pollLast();
								q.offer(new Block(map[r][c] * 2, true));
							} else {
								q.offer(new Block(map[r][c], false));
							}
						}
						map[r][c] = 0;
					}

					// 당기기
					int r = N - 1;
					while (!q.isEmpty()) {
						map[r--][c] = q.poll().num;
					}
				}
				break;
			case 2: // 왼쪽
				for (int r = 0; r < N; r++) {
					for (int c = 0; c < N; c++) {
						if (map[r][c] > 0) { // 블럭이면
							if (q.size() == 0) { // 첫 블록
								q.offer(new Block(map[r][c], false));
								map[r][c] = 0;
								continue;
							}
							Block last = q.getLast();
							if (!last.isUnion && last.num == map[r][c]) {
								// 합치기
								if (map[r][c] == ret) {
									ret = maxTemp * 2;
								}
								q.pollLast();
								q.offer(new Block(map[r][c] * 2, true));
							} else {
								q.offer(new Block(map[r][c], false));
							}
						}
						map[r][c] = 0;
					}

					// 당기기
					int c = 0;
					while (!q.isEmpty()) {
						map[r][c++] = q.poll().num;
					}
				}
				break;
			case 3: // 오른쪽
				for (int r = 0; r < N; r++) {
					for (int c = N - 1; c >= 0; c--) {
						if (map[r][c] > 0) { // 블럭이면
							if (q.size() == 0) { // 첫 블록
								q.offer(new Block(map[r][c], false));
								map[r][c] = 0;
								continue;
							}
							Block last = q.getLast();
							if (!last.isUnion && last.num == map[r][c]) {
								// 합치기
								if (map[r][c] == ret) {
									ret = maxTemp * 2;
								}
								q.pollLast();
								q.offer(new Block(map[r][c] * 2, true));
							} else {
								q.offer(new Block(map[r][c], false));
							}
						}
						map[r][c] = 0;
					}

					// 당기기
					int c = N - 1;
					while (!q.isEmpty()) {
						map[r][c--] = q.poll().num;
					}
				}
				break;
		}

		return ret;
	}

	static boolean check(int depth, int maxTemp) {
		// count한 배열을 오른쪽 -> 왼쪽으로 탐색하면서 최대값과 그 다음 큰 값의 간격을 구하고 5 - depth(남은횟수)와 비교한다.
		int maxIndex = log2(maxTemp);
		int nextMaxIndex = 0;
		for (int i = maxIndex - 1; i > 0; i--) {
			if (counts[i] > 0) {
				nextMaxIndex = i;
				break;
			}
		}

		int gap = maxIndex - nextMaxIndex; // 이미 최대값까지 도달하려면 최소 남은 횟수
		int remainCounts = 5 - depth; // 실제 남은 횟수

		if (remainCounts < gap) { // 아무리 비벼도 실제 남은 횟수로는 이미 최대값에 도달하지 못하면
			return false;
		}

		return true;
	}

}

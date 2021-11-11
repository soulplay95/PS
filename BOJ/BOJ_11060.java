/**
 * @문제 점프 점프_S2
 * @날짜 211111
 * @분류 DP, BFS
 * @조건
 * # 1 <= 칸 개수(N) <= 1000
 * # 0 <= 칸에 쓰인 수(Ai) <= 100
 * @풀이
 * # dp + bfs
 * @comment
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_11060 {

	static class JumpInfo {
		int pos, jumpCounts;

		public JumpInfo(int pos, int jumpCounts) {
			this.pos = pos;
			this.jumpCounts = jumpCounts;
		}
	}

	static int N;
	static int[] input, best;

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		// init
		input = new int[N + 1];
		best = new int[N + 1];
		Arrays.fill(best, 1001);

		for (int i = 1; i <= N; i++) {
			input[i] = sc.nextInt();
		}

		// print
		System.out.println(bfs());
	}

	static int bfs() {
		Queue<JumpInfo> q = new LinkedList<>();
		q.offer(new JumpInfo(1, 0));
		best[1] = 0;

		while (!q.isEmpty()) {
			JumpInfo cur = q.poll();

			int jump = input[cur.pos];

			for (int i = 1; i <= jump; i++) {
				int nextPos = cur.pos + i; // 다음 위치

				if (nextPos >= N) {
					nextPos = N;
				}

				if (input[nextPos] != 0 && best[nextPos] > cur.jumpCounts + 1) { // 더 나은 선택이면
					q.offer(new JumpInfo(nextPos, cur.jumpCounts + 1));
					best[nextPos] = cur.jumpCounts + 1;
				}
			}
		}

		return best[N] == 1001 ? -1 : best[N];
	}

}

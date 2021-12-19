/**
 * @문제 소수 경로_G4
 * @날짜 211219
 * @분류 
 * @조건
 * # 항상 네 자리 소수를 유지
 * @풀이
 * # BFS
 * 모든 자리수의 수를 바꿔서 소수, visited를 체크해서 큐에 넣는다.
 * @comment
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_1963 {

	private static int start, end;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());

			int result = solve();

			sb.append(result == -1 ? "Impossible" : result).append("\n");
		}

		// print
		System.out.println(sb.toString());
	}

	private static int solve() {
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[10000]; // 1000 ~ 9999 까지의 수를 방문 체크
		q.offer(start);
		visited[start] = true;

		int moves = 0; // 변환 회수
		while (!q.isEmpty()) {
			int size = q.size();

			while (size-- > 0) {
				int cur = q.poll();

				if (cur == end) {
					return moves;
				}

				// 천의자리 수 변환
				for (int i = 1; i <= 9; i++) {
					int num = (i * 1000) + (cur % 1000);

					if (isOut(num)) continue;
					if (!visited[num] && isPrime(num)) { // 방문하지 않았고 소수이면 큐에 넣는다.
						visited[num] = true;
						q.offer(num);
					}
				}

				// 백의자리 ~ 일의자리 수 변환
				int head = 0;
				int num = 0;
				for (int i = 0; i <= 9; i++) {
					for (int ten = 1; ten <= 100; ten *= 10) {
						if (ten == 1) {
							head = (cur / 10) * 10;
							num = head + i;
						} else if (ten == 10) {
							head = (cur / 100) * 100 + (cur % 10);
							num = head + (i * ten);
						} else if (ten == 100) {
							head = (cur / 1000) * 1000 + (cur % 100);
							num = head + (i * ten);
						}

						if (isOut(num)) continue;
						if (!visited[num] && isPrime(num)) {
							visited[num] = true;
							q.offer(num);
						}
					}
				}
			}

			moves++;
		}

		return -1;
	}

	private static boolean isOut(int num) {
		// 네자리 수(1000 ~ 9999)를 넘어가면 false
		return (num < 1000 || num >= 10000);
	}

	private static boolean isPrime(int num) {
		for (int i = 2; i * i <= num; i++) {
			if (num % i == 0) return false;
		}

		return true;
	}

}

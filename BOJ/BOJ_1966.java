/**
 * @문제 프린터 큐_S3
 * @날짜 210831
 * @분류 구현, 자료 구조, 시뮬레이션, 큐
 * @조건
 * # 1 <= N <= 100
 * @풀이
 * # 큐에 넣고 시뮬레이션.
 * @comment
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1966 {
	
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	// -----------------------------------------------------------------------------

	static int N, M, importance[];
	static Queue<Integer> q;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		q = new LinkedList<>();
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			// init
			importance = new int[10];
			st = new StringTokenizer(br.readLine(), " ");
			for (int n = 0; n < N; n++) {
				int num = Integer.parseInt(st.nextToken());
				importance[num]++; // 중요도 카운트 세기
				q.offer(num); // 큐에 삽입
			}

			solve();
		}

		// print
		System.out.println(sb.toString());
	}
	
	static void solve() {
		int counts = 1;

		while (!q.isEmpty()) {
			int cur = q.poll(); // 큐의 맨 앞 원소

			if (check(cur)) { // 중요도가 이보다 높은 문서가 없다면
				if (M == 0) { // 인쇄했는데 타겟이면
					break;
				}
				counts++; // 인쇄 카운트 증가
				importance[cur]--;
			} else { // 재배치
				if (M == 0) { // 타겟이 재배치되면
					M = q.size() + 1;
				}
				q.offer(cur);
			}

			M--;
		}

		// print
		sb.append(counts).append("\n");

		// 큐 클리어
		q.clear();
	}

	static boolean check(int n) {
		// 중요도 높은 문서가 있는지 체크
		for (int i = n + 1; i < 10; i++) {
			if (importance[i] > 0) {
				return false;
			}
		}

		return true;
	}

}

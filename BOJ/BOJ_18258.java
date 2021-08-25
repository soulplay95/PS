/**
 * @문제 큐 2_S4
 * @날짜 210825
 * @분류 자료구조, 큐
 * @조건
 * # 1 <= 명령의 수 <= 200만
 * @풀이
 * # back : back 정보를 유지
 * @comment
 * # push도 출력하는 줄 알고 삽질 => 문제 똑바로 보기
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_18258 {
	
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	// -----------------------------------------------------------------------------

	static int N;
	static Deque<Integer> q;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		// init
		q = new LinkedList<>();
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine(), " ");
			String command = st.nextToken();

			if (command.equals("push")) {
				int num = Integer.parseInt(st.nextToken());
				q.offer(num);
//				sb.append(num).append("\n");
			} else {
				solve(command);
			}
		}

		// print
		System.out.print(sb.toString());
	}
	
	static void solve(String command) {
		// 명령어에 따라 분기
		if (command.equals("pop")) {
			if (q.isEmpty()) { // 큐가 비어 있는 경우 -1 출력
				sb.append(-1).append("\n");
			} else {
				sb.append(q.pollFirst()).append("\n");
			}
		} else if (command.equals("size")) {
			sb.append(q.size()).append("\n");
		} else if (command.equals("empty")) {
			sb.append(q.isEmpty() ? 1 : 0).append("\n");
		} else if (command.equals("front")) {
			if (q.isEmpty()) {
				sb.append(-1).append("\n");
			} else {
				sb.append(q.peekFirst()).append("\n");
			}
		} else {
			if (q.isEmpty()) {
				sb.append(-1).append("\n");
			} else {
				sb.append(q.peekLast()).append("\n");
			}
		}
	}

}

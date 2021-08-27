/**
 * @문제 덱_S4
 * @날짜 210827
 * @분류 
 * @조건
 * # 1 <= 명령의 수 <= 10000
 * @풀이
 * # Deque 사용
 * @comment
 * # String 비교 equlas 쓸 때 NPE 안나려면 "".equals(str) 형태로 사용하기!
 * # offer() == offerLast()
 * # poll() == pollFirst()
 * # peek() == peekFirst()
 * # 즉, 일반 큐처럼 동작
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_10866 {

	static int N;
	static Deque<Integer> dq;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException, NumberFormatException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		// init
		dq = new LinkedList<>();

		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine(), " ");
			String command = st.nextToken();

			if ("push_back".equals(command)) {
				dq.offerLast(Integer.parseInt(st.nextToken()));
			} else if ("push_front".equals(command)) {
				dq.offerFirst(Integer.parseInt(st.nextToken()));
			} else {
				solve(command);
			}
		}

		// print
		System.out.println(sb.toString());
	}

	static void solve(String command) {
		if ("pop_front".equals(command)) {
			if (dq.isEmpty()) {
				sb.append(-1).append("\n");
			} else {
				sb.append(dq.pollFirst()).append("\n");
			}
		} else if ("pop_back".equals(command)) {
			if (dq.isEmpty()) {
				sb.append(-1).append("\n");
			} else {
				sb.append(dq.pollLast()).append("\n");
			}
		} else if ("size".equals(command)) {
			sb.append(dq.size()).append("\n");
		} else if ("empty".equals(command)) {
			sb.append(dq.isEmpty() ? 1 : 0).append("\n");
		} else if ("front".equals(command)) {
			if (dq.isEmpty()) {
				sb.append(-1).append("\n");
			} else {
				sb.append(dq.peekFirst()).append("\n");
			}
		} else if ("back".equals(command)) {
			if (dq.isEmpty()) {
				sb.append(-1).append("\n");
			} else {
				sb.append(dq.peekLast()).append("\n");
			}
		}
	}

}

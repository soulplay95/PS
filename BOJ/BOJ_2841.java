package study.week49;

/**
 * @문제 외계인의 기타 연주_S1
 * @날짜 220102
 * @분류 자료 구조, 스택
 * @조건
 * # 음의 수 (N) <= 50만
 * # 2 <= 프렛 수 (P) <= 30만
 * @풀이
 * # 각 줄마다 top에 가장 높은 프렛을 유지하는 스택으로 관리
 * @comment
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_2841 {

	private static int N, P;
	private static Stack<Integer>[] frets;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		// init
		frets = new Stack[7];
		for (int i = 1; i <= 6; i++) {
			frets[i] = new Stack<>();
		}

		int moveCounts = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			if (frets[r].isEmpty()) { // 해당 줄에 스택이 비어있으면 push
				moveCounts++;
				frets[r].push(c);
			} else {
				int top = frets[r].peek();

				if (c > top) { // 기존 음보다 높으면 push
					moveCounts++;
					frets[r].push(c);
				} else if (c < top) { // 낮으면 높을때까지 pop하고 push
					moveCounts++;
					frets[r].pop();
					while (true) {
						if (frets[r].isEmpty()) {
							moveCounts++;
							frets[r].push(c);
							break;
						}
						int ttop = frets[r].peek();

						if (c > ttop) {
							moveCounts++;
							frets[r].push(c);
							break;
						} else if (c == ttop) {
							break;
						} else {
							moveCounts++;
							frets[r].pop();
						}
					}
				}
			}
		}

		// print
		System.out.println(moveCounts);
	}

}

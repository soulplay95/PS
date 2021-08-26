/**
 * @문제 카드2_S4
 * @날짜 210826
 * @분류 
 * @조건
 * # 1 <= N <= 50만
 * @풀이
 * # Deque 사용
 * @comment
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BOJ_2164 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		sc.close();

		Deque<Integer> dq = new LinkedList<>();
		for (int n = 1; n <= N; n++) {
			dq.offerLast(n);
		}

		while (dq.size() != 1) {
			dq.pollFirst(); // 1. 제일 위의 카드를 버림
			dq.offerLast(dq.pollFirst()); // 2. 제일 위의 카드를 제일 아래로 옮긴다.
		}

		// print
		System.out.println(dq.poll());
	}

}

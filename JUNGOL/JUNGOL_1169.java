/**
 * @문제 주사위 던지기1
 * @날짜 211014
 * @분류 
 * @조건
 * #
 * @풀이
 * # 1: 중복 순열
 * # 2: 중복 조합
 * # 3: 순열
 * @comment
 * # 기본기 전투력 측정 문제
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JUNGOL_1169 {
	
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	// -----------------------------------------------------------------------------

	static int N, M;
	static int[] ans;
	static boolean[] used;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// init
		ans = new int[N];
		used = new boolean[7];

		switch (M) {
			case 1:
				run1(0);
				break;
			case 2:
				run2(0, 1);
				break;
			case 3:
				run3(0);
				break;
		}
	}

	static void print() {
		for (int i = 0; i < N; i++) {
			System.out.print(ans[i] + " ");
		}
		System.out.println();
	}

	static void run1(int level) {
		if (level == N) {
			print();
			return;
		}

		for (int i = 1; i <= 6; i++) {
			ans[level] = i;
			run1(level + 1);
		}
	}

	static void run2(int level, int start) {
		// 다음 숫자는 앞에있는 것 부터 시작
		if (level == N) {
			print();
			return;
		}

		for (int i = start; i <= 6; i++) {
			ans[level] = i;
			run2(level + 1, i);
		}
	}

	static void run3(int level) {
		// 같은 눈금이 안나오게 하는 방법
		if (level == N) {
			print();
			return;
		}

		for (int i = 1; i <= 6; i++) {
			if (used[i]) continue;

			used[i] = true;
			ans[level] = i;
			run3(level + 1);
			used[i] = false;
		}
	}

}

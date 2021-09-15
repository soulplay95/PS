/**
 * @문제 반복수열_S4
 * @날짜 210916
 * @분류 
 * @조건
 * # 1 <= 초항(A) <= 9999
 * # 1 <= 각 자리수에 곱하는 수(P) <= 5
 * @풀이
 * # visited[9^5*4]을 만들고 등장한 수마다 몇번째 등장했는지 체크한다.
 * # 877이 3번째 등장했으면 visited[877] = -3
 * # visited가 음수이면 중단
 * @comment
 * # list에 계속 넣어서 list.contains()로 체크하고 list.indexOf()로 답을 구하는 방법도 있음
 */

import java.util.*;
import java.io.*;

public class BOJ_2331 {

	static int[] visited;
	static int A, P;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		A = sc.nextInt();
		P = sc.nextInt();
		// input end
		sc.close();

		// init
		visited = new int[250000]; // 1 ~ 9999

		// print
		System.out.println(solve(A, 1));
	}

	static int solve(int num, int depth) {
		if (visited[num] < 0) {
			return -visited[num] - 1;
		}

		visited[num] = -depth;
		return solve(getNextNum(num), depth + 1);
	}

	static int getNextNum(int num) {
		int ret = 0;

		// 각 자리수별로 쪼개서 P곱함
		while (num != 0) {
			ret += Math.pow(num % 10, P);
			num /= 10;
		}

		return ret;
	}

}

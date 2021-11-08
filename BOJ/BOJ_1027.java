/**
 * @문제 고층 건물_G4
 * @날짜 211108
 * @분류 수학, 완전 탐색, 기하학
 * @조건
 * # 빌딩의 수(N) <= 50
 * @풀이
 * # 한 빌딩 기준으로 왼쪽, 오른쪽 빌딩들과의 기울기를 구하고 왼쪽은 기울기가 감소하면 볼 수 있고, 오른쪽은 기울기가 증가하면 볼 수 있다.
 * @comment
 * # 수학 문제 소수점 조심
 * # 정답의 최대치
 */

import java.util.*;
import java.io.*;

public class BOJ_1027 {

	static int N, ans;
	static int[] heights;

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		// init
		heights = new int[N];

		for (int i = 0; i < N; i++) {
			heights[i] = sc.nextInt();
		}

		solve();

		// print
		System.out.println(ans);
	}

	static void solve() {
		ans = getRightCounts(0);
		for (int i = 1; i < N - 1; i++) {
			ans = Math.max(ans, getLeftCounts(i) + getRightCounts(i));
		}
		ans = Math.max(ans, getLeftCounts(N - 1));
	}

	static int getLeftCounts(int i) {
		int counts = 0;
		double tilt = 1e9;

		for (int index = i - 1; index >= 0; index--) {
			double tempTilt = (double) (heights[index] - heights[i]) / (index - i);

			if (tilt > tempTilt) {
				tilt = tempTilt;
				counts++;
			}
		}

		return counts;
	}

	static int getRightCounts(int i) {
		int counts = 0;
		double tilt = -1e9;

		for (int index = i + 1; index < N; index++) {
			double tempTilt = (double)(heights[index] - heights[i]) / (index - i);

			if (tilt < tempTilt) {
				tilt = tempTilt;
				counts++;
			}
		}

		return counts;
	}

}

/**
 * @문제 어두운 굴다리_S5
 * @날짜 211008
 * @분류 구현, 이분 탐색
 * @조건
 * # 1 <= 굴다리의 길이 N <= 10만
 * # 1 <= 가로등 개수 M <= N
 * @풀이
 * # 가로등의 최소 높이를 이분탐색으로 구한다.
 * # 각 가로등의 위치 간의 간격 중 최대값과 커버할 수 있는 범위(정한 가로등의 높이 * 2)를 비교하여 커버되는 최소 높이를 구한다.
 * # O(Mlog10만)
 * @comment
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_17266 {

	static int N, M, maxInterval;
	static int[] input;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		input = new int[M];
		for (int i = 0; i < M; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}

		// print
		System.out.println(solve());
	}

	static int solve() {
		int L = 0, R = N;
		int ans = N;

		while (L <= R) {
			int mid = (L + R) / 2;

			if (isCanCover(mid)) { // 모두 비출 수 있으면
				ans = mid;
				R = mid - 1;
			} else {
				L = mid + 1;
			}
		}

		return ans;
	}

	static boolean isCanCover(int h) {
		int last = 0;
		for (int i = 0; i < M; i++) {
			if (input[i] - last <= h) {
				last = input[i] + h;
			} else {
				return false;
			}
		}

		return last >= N;
	}

}

package study.week46;

/**
 * @문제 사냥꾼_G4
 * @날짜 211211
 * @분류 정렬, 이분 탐색
 * @조건
 * # 1 <= 사대의 수 (M) <= 10만
 * # 1 <= 동물의 수 (N) <= 10만
 * # 1 <= 사정거리 (L) <= 10억
 * @풀이
 * # 각 동물들의 위치에서 사정거리 내에 있는 사대를 이분탐색으로 찾는다.
 * # O(MlogM + NlogM)
 * @comment
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_8983 {

	private static int M, N, LL, ans;
	private static int[] shootPoints; // 사대 x좌표

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		LL = Integer.parseInt(st.nextToken());
		// init
		ans = 0;
		shootPoints = new int[M];

		st = new StringTokenizer(br.readLine(), " ");
		for (int m = 0; m < M; m++) {
			shootPoints[m] = Integer.parseInt(st.nextToken());
		}

		// 사대 x좌표 오름차순 정렬 for 이분탐색
		Arrays.sort(shootPoints);

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			ans += isCatch(x, y);
		}

		// print
		System.out.println(ans);
	}

	private static int isCatch(int x, int y) {
		// 사정거리 L 내에 있는 사대를 이분탐색으로 찾는다.
		int L = 0;
		int R = M - 1;

		while (L <= R) {
			int mid = (L + R) / 2;
			int distance = Math.abs(shootPoints[mid] - x) + y; // 동물-사대 간 거리
			if (distance <= LL) {
				return 1;
			} else if (x < shootPoints[mid]) {
				R = mid - 1;
			} else if (x >= shootPoints[mid]) {
				L = mid + 1;
			}
		}

		return 0;
	}

}

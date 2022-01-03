package study.week49;

/**
 * @문제 수 고르기_G5
 * @날짜 220103
 * @분류 정렬, 두 포인터
 * @조건
 * # 1 <= 수열 길이 (N) <= 10만
 * @풀이
 * # 완탐 => O(N^2) (x)
 * # 정렬(NlogN) + 투 포인터(logN) => O(NlogN + N) (o)
 * @comment
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_2230 {

	private static int N, M;
	private static int[] input;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// init
		input = new int[N];

		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(br.readLine());
		}

		// print
		System.out.println(solve());
	}

	private static int solve() {
		// 1. 수열 오름차순 정렬
		Arrays.sort(input);

		// 2. 투포인터
		int left = 0;
		int right = 0;
		int min = Integer.MAX_VALUE;
		while (right < N) { // right 포인터가 배열 인덱스 범위를 벗어나기 전까지 반복
			int diff = input[right] - input[left]; // 두 수의 차이

			if (diff == M) { // 최소 차이값이 M이므로 바로 리턴
				return M;
			} else if (diff < M) { // 차이 값이 M 이상이 되어야 하므로 부분 배열의 크기를 증가시킨다.
				right++;
			} else if (diff > M) { // 차이 값이 M에 가까워 지도록 부분 배열의 크기를 감소시킨다.
				if (diff < min) { // 조건은 만족하므로 최소 차이값 갱신
					min = diff;
				}
				left++;
			}
		}

		return min;
	}

}

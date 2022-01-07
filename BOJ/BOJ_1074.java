/**
 * @문제 Z_S1
 * @날짜 220107
 * @분류 분할 정복, 재귀
 * @조건
 * # N <= 15
 * @풀이
 * # 재귀로 스텝 구하기
 * @comment
 */

import java.io.*;
import java.util.*;

public class BOJ_1074 {
	// 1 <= N <= 15
	static int N, r, c, step; // step 최대 2^30 - 1
	
	public static void main(String[] args) {
		// input
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		r = sc.nextInt();
		c = sc.nextInt();
		
		getStepRecur(N);
		
		// print
		System.out.println(step);
		
		sc.close();
	}

	// 재귀적으로 step을 구함
	private static void getStepRecur(int N) {
		if (N == 0) {
			return;
		}
		
		int half = (int)Math.pow(2, N - 1); // 배열을 절반 자른 인덱스
		int offset = (int)Math.pow(2, N) * (int)Math.pow(2, N) / 4; // 사분면 까지의 step
		if (r < half) {
			if (c < half) {
				// 2사분면
				offset = 0;
			} else {
				// 1사분면
				offset *= 1;
				c -= half;
			}
		} else {
			if (c < half) {
				// 3사분면
				offset *= 2;
				r -= half;
			} else {
				// 4사분면
				offset *= 3;
				r -= half;
				c -= half;
			}
		}
		step += offset;
		getStepRecur(N - 1);
	}
	
}
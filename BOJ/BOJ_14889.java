/**
 * @문제 스타트와 링크_S3
 * @날짜 210708
 * @분류 
 * @조건
 * 
 * @풀이
 * 
 * @comment
 * 
 */

import java.util.Scanner;

public class BOJ_14889 {
	static boolean[] selected;
	static int minDiff;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		int R = N / 2;
		selected = new boolean[N];
		minDiff = Integer.MAX_VALUE;
		nCr(N, R, map, 0, 0);
		
		System.out.println(minDiff);
		sc.close();
	}
	
	static void nCr(int N, int R, int[][]map, int cnt, int start) {
		if (cnt == R) {
			int sum1 = 0; // start팀 시너지 합
			int sum2 = 0; // link팀 시너지 합
			for (int i = 0; i < N - 1; i++) {
				for (int j = i + 1; j < N; j++) {
					if (selected[i]) {
						if (selected[j]) {
							sum1 += map[i][j];
							sum1 += map[j][i];
						}
					} else {
						if (!selected[j]) {
							sum2 += map[i][j];
							sum2 += map[j][i];
						}
					}
				}
			}
			
			int diff = Math.abs(sum1 - sum2); // 차이값
			minDiff = Math.min(minDiff, diff);
			
			return;
		}
		
		for (int i = start; i < N; i++) {
			selected[i] = true;
			nCr(N, R, map, cnt + 1, i + 1);
			selected[i] = false;
		}
	}

}

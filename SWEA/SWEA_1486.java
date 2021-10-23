/**
 * @문제 장훈이의 높은 선반
 * @날짜 211023
 * @분류 
 * @조건
 * 
 * @풀이
 * 
 * @comment
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N, B, diff;
	static int[] height;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			height = new int[N];
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				height[i] = Integer.parseInt(st.nextToken());
			}
			
			diff = Integer.MAX_VALUE;
			for (int i = N; i > 0; i--) {
				nCr(0, 0, i, 0);
			}
			
			// print
			System.out.println("#" + t + " " + (diff - B));
		}
	}
	
	static void nCr(int cnt, int start, int r, int sum) {
		if (cnt == r) {
			if (sum >= B) {
				diff = Math.min(diff, sum);
			}
			return;
		}
		
		for (int i = start; i < N; i++) {
			nCr(cnt + 1, i + 1, r, sum + height[i]);
		}
	}
}

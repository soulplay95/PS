/**
 * @문제 연산자 끼워넣기_S1
 * @날짜 210616
 * @분류 
 * @조건
 * 식의 계산은 앞에서부터
 * 나눗셈은 몫만 취함
 * 2 <= N <= 11
 * @풀이
 * 
 * @comment
 * 
 */

import java.util.*;
import java.io.*;

public class BOJ_14888 {
	
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	// -----------------------------------------------------------------------------
	
	static int N;
	static int[] A, op;
	
	static int max, min;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		op = new int[4];
		st = new StringTokenizer(br.readLine(), " ");
		for (int n = 0; n < N; n++) {
			A[n] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < 4; i++) {
			op[i] = Integer.parseInt(st.nextToken());
		} // input end
		
		solve();
		
		// print
		System.out.println(max);
		System.out.println(min);
	}
	
	static void solve() {
		// init
		max = -Integer.MAX_VALUE;
		min = Integer.MAX_VALUE;
		
		dfs(A[0], 1);
	}
	
	static void dfs(int result, int depth) {
		// 바닥 조건
		if (depth == N) {
			max = Math.max(max, result);
			min = Math.min(min, result);
			
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			if (op[i] > 0) {
				op[i]--;
				
				switch (i) {
				case 0:
					dfs(result + A[depth], depth + 1);
					break;
				case 1:
					dfs(result - A[depth], depth + 1);
					break;
				case 2:
					dfs(result * A[depth], depth + 1);
					break;
				case 3:
					dfs(result / A[depth], depth + 1);
					break;
				}
				
				op[i]++;
			}
		}
	}

}
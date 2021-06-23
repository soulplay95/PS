/**
 * @문제 몬스터 사냥_D3
 * @날짜 210623
 * @분류 
 * @조건
 * 
 * @풀이
 * 
 * @comment
 * 소수점 날라가는거 고려
 */

import java.util.*;
import java.io.*;

public class SWEA_11387 {
	
	static int D, L, N, ans;
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			D = sc.nextInt();
			L = sc.nextInt();
			N = sc.nextInt();
			
			// TC append
			sb.append("#").append(t).append(" ").append(solve()).append("\n");
		}
		
		// print
		System.out.println(sb.toString());
		
		sc.close();
	}
	
	static long solve() {
		double ret = 0.0;
		
		for (int n = 0; n < N; n++) {
			ret += D * (1.0 + (double)(n * L) / 100.0);
		}
		
		return (int)ret;
	}

}
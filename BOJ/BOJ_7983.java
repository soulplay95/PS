package week20;
/**
 * @문제 내일 할거야_G5
 * @날짜 210626
 * @분류 
 * @조건
 * 1 <= n <= 10^6
 * 1 <= d, t <= 10^9
 * @풀이
 * deadline 기준 정렬하여 마감일이 늦는 과제부터 최대한 마감일에 근접하여 게으르게 과제를 한다.
 * @comment
 * 
 */

import java.util.*;
import java.io.*;

public class BOJ_7983 {
	
	static int N;
	static int[][] dt; 

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		dt = new int[N][2];
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine(), " ");
			dt[n][0] = Integer.parseInt(st.nextToken());
			dt[n][1] = Integer.parseInt(st.nextToken());
		} // input end
		
		// print
		System.out.println(solve());
	}
	
	static int solve() {
		// 마감일 기준 정렬
		Arrays.sort(dt, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[1], o2[1]);
			}
		});
		
		int ret = dt[N - 1][1]; // 다음 과제를 할 수 있는 최대 마감일
		
		for (int i = N - 1; i >= 0; i--) {
			if (dt[i][1] > ret) {
				ret = ret - dt[i][0]; 
			} else { // 다음 과제의 마감일에 맞춰 과제 진행
				ret = dt[i][1] - dt[i][0]; // 마감일에 딱 맞춰서 과제를 했을 때 다음 과제의 최대 마감일
			}
		}
		
		return ret;
	}

}
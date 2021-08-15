/**
 * @문제 RGB거리_S1
 * @날짜 210815
 * @분류 DP
 * @조건
 *
 * @풀이
 *
 * @comment
 *
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1149 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine()); // 2 <= N <= 1000
		int[][] rgbCost = new int[N][3];
		int[][] dt = new int[N][3]; // 동적 테이블. N-1행의 각 값은 누적 비용
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			rgbCost[i][0] = Integer.parseInt(st.nextToken());
			rgbCost[i][1] = Integer.parseInt(st.nextToken());
			rgbCost[i][2] = Integer.parseInt(st.nextToken());
		}
		
		// init
		dt[0][0] = rgbCost[0][0];
		dt[0][1] = rgbCost[0][1];
		dt[0][2] = rgbCost[0][2];
		
		for (int i = 1; i < N; i++) {
			dt[i][0] = rgbCost[i][0] + Math.min(dt[i - 1][1],  dt[i - 1][2]);
			dt[i][1] = rgbCost[i][1] + Math.min(dt[i - 1][0],  dt[i - 1][2]);
			dt[i][2] = rgbCost[i][2] + Math.min(dt[i - 1][0],  dt[i - 1][1]);
		}
		
		// print
		System.out.println(Math.min(dt[N - 1][0], Math.min(dt[N - 1][1], dt[N - 1][2])));
	}

}

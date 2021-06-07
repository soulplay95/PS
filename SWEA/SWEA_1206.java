/**
 * @문제 [S/W 문제해결 기본] 1일차 - View D3
 * @날짜 210607
 * @분류 
 * @조건
 * 각 세대에서 양쪽(왼쪽, 오른쪽) 모두 거리 2 이상의 공간이 확보될 때 조망권이 확보된다고 판단
 * 가로 길이 <= 1000
 * 양쪽 끝 두칸은 건물 x
 * 각 빌딩의 높이 최대 255
 * @풀이
 * 한 빌딩 기준 양쪽 2칸(총 4칸) 중 최대값의 빌딩 높이를 뺀 값을 구한다.
 * 0이면 건너뛴다.
 * @comment
 * 
 */

import java.util.*;
import java.io.*;

public class SWEA_1206 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder("");
		
		for (int t = 1; t <= 10; t++) {
			int n = Integer.parseInt(br.readLine()); // 가로 길이
			st = new StringTokenizer(br.readLine(), " ");
			int[] bh = new int[n]; // Building Heights
			int i = 0;
			while (st.hasMoreTokens()) {
				bh[i++] = Integer.parseInt(st.nextToken());
			} // input end
			
			int ans = 0;
			// 양쪽 2칸씩 에서 최대값만큼 뺀 높이를 누적한다.
			for (int j = 2; j < n - 2; j++) {
				// 기준 건물의 높이가 0이면 건너뜀
				if (bh[j] == 0) {
					continue;
				}
				
				int max = getMax(bh, j); // 4개의 건물 높이 중 최대 높이
				if (bh[j] - max > 0) {
					ans += bh[j] - max;
				}
			}
			
			// TC append
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		
		// print
		System.out.println(sb.toString());
	}
	
	// i번째 건물의 양쪽 2개씩의 건물 중 최대 높이를 구한다.
	static int getMax(int[] bh, int j) {
		int max = -1;
		
		max = Math.max(max, bh[j - 2]);
		max = Math.max(max, bh[j - 1]);
		max = Math.max(max, bh[j + 1]);
		max = Math.max(max, bh[j + 2]);
		
		return max;
	}

}
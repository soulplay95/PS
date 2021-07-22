package week12;

/**
 * @문제 히스토그램_P5
 * @날짜 210722
 * @분류 
 * @조건
 * 높이 0이나 같은 높이 여러개 주어질 수 있음
 * 1 <= N <= 10만
 * 0 <= 각 칸의 높이 <= 10억
 * @풀이
 * https://blog.naver.com/kks227/220776241154
 * https://www.geeksforgeeks.org/largest-rectangular-area-in-a-histogram-set-1/
 * 분할정복
 * 가운데 잘라서 경우를 3가지로 나눔.
 * 정답(최대 넓이의 직사각형)이 왼쪽, 오른쪽에 쏠려 있는 경우
 * 가운데 걸쳐 있는 경우 => 가운데 막대기준 양쪽 막대 중 더 높은 막대 쪽으로 확장하고 그떄마다 최대값 갱신
 * @comment
 * 분할정복, 세그먼트 트리, 스택 여러가지 풀이법이 있음.
 */

import java.util.*;
import java.io.*;

public class BOJ_1725 {
	
	// input, output handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	// -----------------------------------------------------------------------------
	
	static int N;
	static int[] heights;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		heights = new int[N];
		for (int i = 0; i < N; i++) {
			heights[i] = Integer.parseInt(br.readLine());
		}
		
		// print
		System.out.println(getMax(0, N));
	}
	
	static int getMax(int start, int end) {
		// 기저 조건
		if (start == end) return 0;
		if (start + 1 == end) return heights[start]; // 밑변이 1인 상황
		
		int mid = (start + end) / 2;
		
		// 중간 기준 최대 넓이를 가지는 직사각형이 한쪽에 쏠려 있는 경우 
		int max = Math.max(getMax(start, mid), getMax(mid, end)); // 한쪽에 쏠려 있는 경우 고려하고 시작
		
		// 중간에 걸쳐 있는 경우 고려
		// 높이가 큰 막대쪽으로 한칸씩 확장해가면서 그때의 넓이를 구하고 최대값 갱신
		int width = 1, height = heights[mid]; // 초기 밑변, 높이 값
		int lp = mid, rp = mid; // 중간에서 lp : 왼쪽으로 나아가는 포인터, rp : 오른쪽으로 나아가는 포인터
		while (rp - lp + 1 < end - start) { // 포인터 범위가 start ~ end 사이에 있을동안만 반복
			int lh = lp > start ? heights[lp - 1] : -1; // 왼쪽으로 확장했을 때 막대 높이
			int rh = rp < end - 1 ? heights[rp + 1] : -1; // 오른쪽으로 확장했을 때 막대 높이
			
			if (lh >= rh) { // 왼쪽이 크다면
				// 왼쪽으로 확장하기로 선택
				height = Math.min(height, lh); // 기존 높이보다 크게는 확장할 수 없으므로 min메소드 이용
				lp--; // 왼쪽 확장을 선택했으므로 L포인터도 옮기기
			} else {
				// 오른쪽 확장 선택
				height = Math.min(height, rh);
				rp++;
			}
			
			int squareArea = (++width) * height; // 확장 후 사각형 넓이
			max = Math.max(max, squareArea); // 확장 할때마다 최대값 갱신
		}
		
		return max;
	}

}
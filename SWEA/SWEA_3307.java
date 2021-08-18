/**
 * @문제 최장 증가 부분 수열_D3
 * @날짜 210818
 * @분류
 * @조건
 *
 * @풀이
 *
 * @comment
 * # BinarySearch로 속도 개선 가능
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_3307 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			int[] sequence = new int[N]; // 주어진 수열
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				sequence[i] = Integer.parseInt(st.nextToken());
			}
			
			int[] lis = new int[N]; // lis[k] : k를 포함하여 만들 수 있는 최장 증가 부분 수열의 길이
			Arrays.fill(lis, 1); // 1로 초기화
			int maxLength = 1;
			for (int i = 1; i < N; i++) {
				for (int j = 0; j < i; j++) {
					// 수열 상에서 기준 i의 값이 i의 전 값들을 훑는 j보다 크거나 같고, 최장 길이에 +1 한 것(i를 포함하므로)이 기존 값보다 크거나 같다면 갱신 
					if (sequence[i] >= sequence[j] && lis[j] + 1 >= lis[i]) {
						lis[i] = lis[j] + 1;
					}
				}
				// 최대값 갱신
				if (maxLength <= lis[i]) {
					maxLength = lis[i];
				}
			}
			
			sb.append("#" + t + " " + maxLength + "\n");
		}

		// print
		System.out.println(sb.toString());
	}

}

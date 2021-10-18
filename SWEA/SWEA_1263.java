/**
 * @문제 [S/W 문제해결 응용] 8일차 - 사람 네트워크2
 * @날짜 211018
 * @분류 
 * @조건
 * #
 * @풀이
 * # 플로이드 와샬
 * @comment
 * #
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1263 {
	
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	// -----------------------------------------------------------------------------

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder("");
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken()); // 정점 수
			int[][] adjMatrix = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					adjMatrix[i][j] = Integer.parseInt(st.nextToken());
					if (i != j && adjMatrix[i][j] == 0) {
						adjMatrix[i][j] = 1001;
					}
				}
			}

			for (int k = 0; k < N; k++) {
				for (int i = 0; i < N; i++) {
					if (i == k) continue;
					for (int j = 0; j < N; j++) {
						if (j == k || j == i) continue;
						adjMatrix[i][j] = Math.min(adjMatrix[i][j], adjMatrix[i][k] + adjMatrix[k][j]);
					}
				}
			}

			int min = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				int cc = 0;
				for (int j = 0; j < N; j++) {
					cc += adjMatrix[i][j];
				}
				min = Math.min(min, cc);
			}

			sb.append("#" + t + " " + min + "\n");
		}

		// print
		System.out.println(sb.toString());
	}

}

package study.week31;

/**
 * @문제 저울_G3
 * @날짜 210815
 * @분류 그래프, 플로이드-와샬
 * @조건
 * # 5 <= N <= 100
 * # 0 <= M <= 2000
 * @풀이
 * # 각 물건을 노드로 한 그래프에서 주어진 입력 외에 유추할 수 있는 정보(2 > 3, 3 > 4이면 2 > 4이다)를 플로이드-와샬을 이용하여 구한다.
 * # O(N^3)
 * # adjMatrix[i][j] = true => i > j라는 정보. i입장에서 j보다 크다는 비교 결과를 알 수있고, j입장에서도 i보다 작다는 비교 결과를 알 수 있다.
 * @comment
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10159 {
	
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	// -----------------------------------------------------------------------------

	static int N, M;
	static boolean[][] adjMatrix; // 인접 행렬

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		// init
		adjMatrix = new boolean[N + 1][N + 1];
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine(), " ");
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			adjMatrix[i][j] = true; // i > j
		} // input end

		solve();

		// print
		System.out.println(sb.toString());
	}
	
	static void solve() {
		// 플로이드-와샬을 이용해 경유지를 달리하는 모든 쌍에 대해 탐색하면서 ex) 2 > 3, 3 > 4 => 2 > 4인 애들을 찾아낸다.
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				if (i == k) continue;
				for (int j = 1; j <= N; j++) {
					if (j == k || j == i) continue;
					if (adjMatrix[i][k] && adjMatrix[k][j]) { // i > k, k > j이면
						// i > j
						adjMatrix[i][j] = true;
					}
				}
			}
		}

		getCounts();
	}

	static void getCounts() {
		for (int r = 1; r <= N; r++) {
			int counts = 0;
			for (int c = 1; c <= N; c++) {
				if (r == c) continue;
				// r 기준 어떤 c에 대해 얘보다 크거나 작다는 비교 결과가 없으면
				if (!adjMatrix[r][c] && !adjMatrix[c][r]) {
					counts++;
				}
			}

			// append
			sb.append(counts).append("\n");
		}
	}

}

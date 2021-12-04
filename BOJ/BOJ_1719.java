package study.week45;

/**
 * @문제 택배_G4
 * @날짜 211204
 * @분류 
 * @조건
 * # 1 <= 정점 개수 (n) <= 200
 * # 1 <= 간선 개수 (m) <= 1만
 * @풀이
 * # 플로이드 와샬에서 갱신될때 마다
 * @comment
 * # 플로이드 와샬 초기화 할때 Integer.MAX_VALUE로 초기화하지 말기 => 오버플로우
 */

import java.util.*;
import java.io.*;

public class BOJ_1719 {

	static int N, M;
	static int[][] adjMatrix, firstPoint; // firstPoint[x][y] : x에서 y로 최단 경로로 갈 때 제일 처음 들리는 정점의 번호
	static StringBuilder sb = new StringBuilder();
	static final int MAX = 987654321;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// init
		adjMatrix = new int[N + 1][N + 1];
		firstPoint = new int[N + 1][N + 1];

		init();

		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			adjMatrix[x][y] = w;
			adjMatrix[y][x] = w;
			// 인접한 정점의 번호로 초기화
			firstPoint[x][y] = y;
			firstPoint[y][x] = x;
		}

		floyd();

		print();
	}

	static void init() {
		// 인접하지 않으면 가중치 MAX로 초기화
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				if (r == c) continue;
				adjMatrix[r][c] = MAX;
			}
		}
	}

	static void floyd() {
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				if (i == k) continue;
				for (int j = 1; j <= N; j++) {
					if (j == k || j == i) continue;
					if (adjMatrix[i][j] > adjMatrix[i][k] + adjMatrix[k][j]) {
						adjMatrix[i][j] = adjMatrix[i][k] + adjMatrix[k][j];
						firstPoint[i][j] = firstPoint[i][k];
					}
				}
			}
		}
	}

	static void print() {
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				if (r == c) {
					sb.append("-").append(" ");
					continue;
				}
				sb.append(firstPoint[r][c]).append(" ");
			}
			sb.append("\n");
		}

		System.out.print(sb.toString());
	}

}

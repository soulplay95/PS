/**
 * @문제 서로소 집합_D4
 * @날짜 210719
 * @분류 
 * @조건
 * 
 * @풀이
 * 
 * @comment
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_3289 {
	static int N, M;
	static int[] p; // parents
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder sb = new StringBuilder("");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append("#" + t + " ");
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			// p 배열 초기화
			p = new int[N + 1]; //n은 1부터 시작하므로 index 맞추기
			for (int i = 1; i <= N; i++) {
				p[i] = i;
			}

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int branch = Integer.parseInt(st.nextToken()); // 0 : union, 1 : check
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				if (branch == 0) {
					union(x, y);
				} else {
					if (isConnect(x, y)) {
						sb.append("1");
					} else {
						sb.append("0");
					}
				}
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	
	private static int find(int x) {
		if (x == p[x]) return x;
		else return p[x] = find(p[x]);
	}
	
	private static void union(int x, int y) {
		int px = find(x);
		int py = find(y);
		// x에 y 붙이기
		p[py] = px;
	}
	
	private static boolean isConnect(int x, int y) {
		return (find(x) == find(y));
	}

}

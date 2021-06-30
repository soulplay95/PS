/**
 * @문제 단식원
 * @날짜 210701
 * @분류 
 * @조건
 * 
 * @풀이
 * 
 * @comment
 * 
 */

import java.util.*;
import java.io.*;

public class SWEA_12102 {
	
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	// -----------------------------------------------------------------------------
	
	static class Pair {
		int r, c;

		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int N, M;
	static int[][] map;
	static int count; // 빈칸 개수
	static int ans;
	static Pair[] picked;
	static List<Pair> eList; // 빈칸 리스트
	static List<Pair> cList; // 치킨 리스트

	static int[] dr = {-1, 0, 1, 0};	
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			eList = new ArrayList<>();
			cList = new ArrayList<>();
			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < M; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					if (map[r][c] == 0) {
						eList.add(new Pair(r, c));
					} else if (map[r][c] == 2) {
						cList.add(new Pair(r, c));
					}
				}
			} // input end

			count = eList.size();
			ans = 0;
			solve();
			
			// TC append
			sb.append('#').append(t).append(' ').append(ans).append('\n');
		}
		
		// print
		System.out.println(sb.toString());
	}
	
	static void solve() {
		picked = new Pair[3];
		
		// 조합
		nCr(0, 0);
	}
	
	static void nCr(int cnt, int start) {
		if (cnt == 3) {
			ans = Math.max(ans, getMax());
			return; // 다시 조합
		}
		
		for (int i = start; i < count; i++) {
			picked[cnt] = eList.get(i);
			nCr(cnt + 1, i + 1);
		}
	}
	
	static int getMax() {
		// 뽑힌 3개로 치킨 냄새 확장 후 빈칸 개수 리턴
		int ret = 0;
		
		// 맵에 마킹
		for (Pair p : picked) {
			map[p.r][p.c] = 1; 
		}
		
		// 치킨 확장
		for (Pair p : cList) {
			for (int d = 0; d < 4; d++) {
				int nr = p.r;
				int nc = p.c;
				
				while (true) {
					nr += dr[d];
					nc += dc[d];
					
					if (isOut(nr, nc) || map[nr][nc] == 1 || map[nr][nc] == 2) {
						break;
					}
					if (map[nr][nc] == 0) {
						ret++;
					}
				}
			}
		}
		
		// 마킹 복원
		for (Pair p : picked) {
			map[p.r][p.c] = 0; 
		}
		
		return count - ret - 3;
	}
	
	static boolean isOut(int r, int c) {
		return (r < 0 || r >= N || c < 0 || c >= N);
	}

}
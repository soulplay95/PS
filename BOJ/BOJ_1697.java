/**
 * @문제 숨바꼭질_S1
 * @날짜 210611
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

public class BOJ_1697 {
	
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	// -----------------------------------------------------------------------------
	
	static class Pair {
		int pos, time; // 현재 위치, 경과 시간

		public Pair(int pos, int time) {
			this.pos = pos;
			this.time = time;
		}
	}
	
	static int N, K;
	static boolean[] visited;

	static final int MAX = 100001;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		// init
		visited = new boolean[MAX];
		
		int ans = bfs();
		
		// print
		System.out.println(ans);
	}
	
	static int bfs() {
		Queue<Pair> q = new LinkedList<>();
		q.offer(new Pair(N, 0));
		visited[N] = true;
		
		while (!q.isEmpty()) {
			Pair cur = q.poll();
			
			// 도착 여부 체크
			if (cur.pos == K) {
				return cur.time;
			}
			
			int nPos = 0;
			int nTime = cur.time;
			
			// 이동할 수 있는 3가지 경우에 대해 이동
			nPos = cur.pos - 1;
			if (check(nPos)) {
				q.offer(new Pair(nPos, nTime + 1));
				visited[nPos] = true;
			}
			
			nPos = cur.pos + 1;
			if (check(nPos)) {
				q.offer(new Pair(nPos, nTime + 1));
				visited[nPos] = true;
			}
			
			nPos = cur.pos * 2;
			if (check(nPos)) {
				q.offer(new Pair(nPos, nTime + 1));
				visited[nPos] = true;
			}
		}
		
		return 0;
	}
	
	static boolean check(int n) {
		// isIn(nPos) && !visited[nPos]
		return ((n >= 0 && n < MAX) && (!visited[n]));
	}

}
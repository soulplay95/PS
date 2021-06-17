/**
 * @문제 스타트링크
 * @날짜 210618
 * @분류 BFS
 * @조건
 * 
 * @풀이
 * F만큼 visited 잡고 이미 방문한 곳은 탐색하지 않는다.
 * 큐의 모든걸 털어 내고 누른 버튼수 + 1
 * @comment
 * 
 */

import java.util.*;
import java.io.*;

public class BOJ_5014 {
	
	static int F, S, G, U, D;
	static int ans;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException, NumberFormatException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		F = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		U = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		// input end
		
		solve();
	}
	
	static void solve() {
		// Init
		visited = new boolean[F + 1];
		ans = 0;
		
		// print
		System.out.println(bfs() ? ans : "use the stairs");
	}

	static boolean bfs() {
		// 시작점 S를 큐에 넣고 방문체크 후 시작
		Queue<Integer> q = new LinkedList<>();
		q.offer(S);
		visited[S] = true;
		
		// 도착점 찾을때까지 반복
		while (!q.isEmpty()) {
			int size = q.size();
			
			// 한 싸이클 => 큐의 사이즈로 판단
			while (size-- > 0) {
				int curPos = q.poll();
				
				// 도착여부 체크
				if (curPos == G) {
					return true;
				}
				
				// Up 버튼 눌렀을 때
				int newUpPos = curPos + U;
				// 새로운 층이 범위 내에 있고, 방문하지 않았으면
				if (newUpPos <= F && !visited[newUpPos]) {
					visited[newUpPos] = true; // 방문체크 후
					q.offer(newUpPos); // 큐에 넣기
				}
				
				// Down 버튼 눌렀을 때
				int newDownPos = curPos - D;
				if (newDownPos >= 1 && !visited[newDownPos]) {
					visited[newDownPos] = true; // 방문체크 후
					q.offer(newDownPos); // 큐에 넣기
				}
			}
			
			// 한 싸이클 돌았으면(버튼 눌렀으면)
			ans++; // 버튼 누른 횟수 증가
		}
		
		// 도착점을 못찾고 빠져나왔으면
		return false;
	}
}
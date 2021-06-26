package week20;
/**
 * @문제 미친 아두이노_G4
 * @날짜 210626
 * @분류 
 * @조건
 * 1 <= R, C <= 100
 * @풀이
 * visited로 파괴 관리
 * @comment
 * 리스트 for문 돌면서 remove 할 때 조심 => ConcurrentModificationException
 * 삭제될때마다 인덱스값--? / iterator.remove()? / removeIf? => 직관적이고 편한거 사용
 * 리스트에서 특정 객체 삭제하고 싶을 때 equals 메소드 재정의
 */

import java.util.*;
import java.io.*;

public class BOJ_8972 {
	
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

		@Override
		public boolean equals(Object o) {
			Pair oo = (Pair)o;
			return (this.r == oo.r && this.c == oo.c);
		}
	}
	
	static int R, C;
	static Pair user; // 종수 아두이노 위치
	static List<Pair> mad; // 미친 아두이노 위치 리스트
	static boolean[][] visited;
	static String inputDirection; // 입력으로 주어지는 이동 방향

	static int[] dr = {0, 1, 1, 1, 0, 0, 0, -1, -1, -1};
	static int[] dc = {0, -1, 0, 1, -1, 0, 1, -1, 0, 1};
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		mad = new ArrayList<>();
		visited = new boolean[R][C];
		for (int r = 0; r < R; r++) {
			String row = br.readLine();
			for (int c = 0; c < C; c++) {
				char el = row.charAt(c);
				if (el == 'I') {
					user = new Pair(r, c);
				} else if (el == 'R') {
					mad.add(new Pair(r, c));
					visited[r][c] = true; // 미친 아두이노의 위치 마킹
				}
			}
		}
		inputDirection = br.readLine();
		// input end
		
		solve();
		
		// print
		System.out.println(sb.toString());
	}
	
	static void solve() {
		// 주어진 입력의 방향 만큼 이동
		for (int i = 0, end = inputDirection.length(); i < end; i++) {
			if (!move(inputDirection.charAt(i) - '0')) { // return false : 게임 지는 경우 
				sb.append("kraj ").append(i + 1);
				return;
			}
		}
		
		// 보드 상태 출력
		print();
		
		return;
	}
	
	static boolean move(int d) {
		// init
		// 겹치는지 체크하기 위해 이동마다 used 만들어서 체크
		boolean[][] used = new boolean[R][C];
		// 파괴되는 지점 저장 => 마지막에 visited false로 마킹 위해서
		Set<Pair> bomb = new HashSet<>();
		
		// 1. 종수 이동
		user.r += dr[d];
		user.c += dc[d];
		
		// 2. 미친 아두이노가 있는 칸인지 체크
		if (visited[user.r][user.c]) {
			return false;
		}
		
		// 3. 미친 아두이노 이동
		Iterator<Pair> it = mad.iterator();
		while (it.hasNext()) {
			Pair cur = it.next();
			// 종수와 가장 가까운 좌표 구하기
			int nr = getClosestCoor(cur.r, user.r);
			int nc = getClosestCoor(cur.c, user.c);
			
			// 종수와 겹치는지 체크
			if (nr == user.r && nc == user.c) {
				return false;
			}
			
			// 미친 아두이노와 겹치는지 체크
			if (used[nr][nc]) {
				// 해당 미친 아두이노 제거
				it.remove();
				// 파괴된 지점 set에 담기
				bomb.add(new Pair(nr, nc));
			} else {
				// 이동 후 지점 마킹
				cur.r = nr;
				cur.c = nc;
				used[nr][nc] = true;
			}
		}
		
		// 파괴된 지점 거르기
		for (Pair cur : bomb) {
			used[cur.r][cur.c] = false; 
			// 초기에 들어간 아두이노 제거
			mad.remove(cur);
		}
		
		visited = used; // visited 갱신
		
		return true;
	}
	
	static int getClosestCoor(int n, int u) {
		if (n > u) {
			return n - 1;
		} else if (n == u) {
			return n;
		} else {
			return n + 1;
		}
	}
	
	static void print() {
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (r == user.r && c == user.c) {
					sb.append('I');
				} else if (visited[r][c]) {
					sb.append('R');
				} else {
					sb.append('.');
				}
			}
			sb.append("\n");
		}
	}

}
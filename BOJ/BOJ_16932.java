package study.week50;

/**
 * @문제 모양 만들기_G4
 * @날짜 220104
 * @분류 완전 탐색, DFS, BFS
 * @조건
 * # 2 <= 맵 크기 (N, M) <= 1000
 * @풀이
 * # 1 -> 0으로 변경했을때 최대 크기가 나올 수 있는가? x
 * # 1. BFS로 각 모양마다 구분 => map에 <모양 번호, 모양에 포함된 1의 개수>로 저장
 * # 2. 모든 0에 대하여 4방에 인접한 모양 번호를 가지고 개수 계산
 * @comment
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_16932 {

	private static class Pair {
		int r, c;

		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	private static int R, C;
	private static int[][] map;
	private static boolean[][] visited;
	private static HashMap<Integer, Integer> oneCountsInShape;

	private static int[] dr = {-1, 0, 1, 0}; // 상우하좌 시계방향
	private static int[] dc = {0, 1, 0, -1}; // 상우하좌 시계방향

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		// init
		map = new int[R][C];
		visited = new boolean[R][C];
		oneCountsInShape = new HashMap<>();

		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// print
		System.out.println(solve());
	}

	private static int solve() {
		// 1. 1인 칸에서 BFS를 돌아 인접한 칸(모양)을 2번부터 마킹
		int index = 2;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (map[r][c] == 1) {
					int oneCounts = bfs(r, c, index); // 모양 내 1의 개수
					oneCountsInShape.put(index++, oneCounts);
				}
			}
		}

		// 2. 모든 0인 칸에 대하여 완전탐색 => 최대 개수 갱신
		int max = 0;
		Set<Integer> shapeNumbers = new HashSet<>(); // 4방으로 인접한 모양의 번호
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (map[r][c] == 0) {
					// 4방 탐색하여 인접한 모양을 찾는다.
					for (int d = 0; d < 4; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];

						if (isOut(nr, nc)) {
							continue;
						}
						if (map[nr][nc] > 0) {
							shapeNumbers.add(map[nr][nc]); // 모양의 번호를 set에 저장
						}
					}

					// set에 포함된 모양내 1의 개수를 전부 더하고 현재 칸(0)의 개수 + 1한 값으로 최대값 갱신
					int sum = 1; // 현재 칸 포함
					for (int num : shapeNumbers) {
						sum += oneCountsInShape.get(num);
					}

					max = Math.max(max, sum);

					shapeNumbers.clear(); // set 초기화
				}
			}
		}

		return max;
	}

	private static int bfs(int r, int c, int index) {
		Queue<Pair> q = new LinkedList<>();
		q.offer(new Pair(r, c));
		visited[r][c] = true;
		map[r][c] = index; // 각 모양의 번호로 마킹
		int counts = 1; // 모양 내 1의 개수

		while (!q.isEmpty()) {
			Pair cur = q.poll();

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				// 경계 체크
				if (isOut(nr, nc)) {
					continue;
				}

				if (!visited[nr][nc] && map[nr][nc] == 1) { // 방문하지 않았고 1이면
					visited[nr][nc] = true;
					q.offer(new Pair(nr, nc));
					map[nr][nc] = index; // 각 모양의 번호로 마킹
					counts++;
				}
			}
		}

		return counts;
	}

	private static boolean isOut(int r, int c) {
		return (r < 0 || r >= R || c < 0 || c >= C);
	}

}

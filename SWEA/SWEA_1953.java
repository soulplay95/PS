import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @문제 [모의 SW 역량테스트] 탈주범 검거
 * @날짜 211016
 * @분류 delta 탐색, BFS, DFS
 * @풀이
 * @comment
 * DFS버전
 */

public class SWEA_1953 {

	static int N, M, R, C, L, map[][], visited[][];
	static int[] dr = {-1, 0, 0, 1}; // 상 0, 좌 1, 우 2, 하 3
	static int[] dc = {0, -1, 1, 0}; // 상 0, 좌 1, 우 2, 하 3
	static String[] type = {
			null,
			"0312", // 1 : 상하좌우
			"03", // 2 : 상하
			"12", // 3 : 좌우
			"02", // 4 : 상우
			"32", // 5 : 하우
			"31", // 6 : 하좌
			"01", // 7 : 상좌
	};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			visited = new int[N][M]; // 방문 시간으로 관리

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					visited[i][j] = Integer.MAX_VALUE; // 최소값 갱신위해 max값으로 초기화
				}
			}

			dfs(R, C, 1);

			// print
			System.out.println("#" + t + " " + getCount());
		}
	}

	private static int getCount() {
		int count = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if(visited[r][c] != Integer.MAX_VALUE) ++count;
			}
		}

		return count;
	}

	static void dfs(int r, int c, int time) {
		visited[r][c] = time;
		if (time == L) return;

		String info = type[map[r][c]]; // 현 구조물의 타입으로 이동가능한 방향의 정보

		for (int d = 0, length = info.length(); d < length; d++) {
			int dir = info.charAt(d) - '0';
			int nr = r + dr[dir];
			int nc = c + dc[dir];
			if (nr >= 0 && nr<N && nc>=0 && nc<M
					&& map[nr][nc] > 0
					&& type[map[nr][nc]].contains(Integer.toString(3-dir))
					&& visited[nr][nc] > time) {
				dfs(nr, nc, time + 1);
			}
		}
	}

}
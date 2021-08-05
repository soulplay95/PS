/**
 * @문제 연구소_G5
 * @날짜 210806
 * @분류
 * @조건
 *
 * @풀이
 * 4방 탐색
 * 벽 3개를 세워서 안전영역 크기 최대화
 * map 크기 <= 8이므로 최악의 경우 벽 없이 바이러스 1개 => 63개의 좌표 중 3개 선택하여 벽 세우기
 * 64C3 == 4만정도
 * 4만가지 경우의 수에 대해 BFS?
 * @comment
 *
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

import com.sun.org.apache.bcel.internal.generic.ISHL;

public class BOJ_14502 {

	// 좌표 클래스
	static class Pair {
		int r, c;

		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int N, M;
	static int[][] map;
	static List<Pair> initialVirusPos; // 초기 바이러스 위치
	static List<Pair> blank; // 빈칸 위치
	static Pair[] newWall; // 새로 세운 벽 위치 저장하는 배열
	static int max; // 안전 영역 최대 크기
	static int[] dr = {-1, 1, 0, 0}; // 상하좌우
	static int[] dc = {0, 0, -1, 1}; // 상하좌우
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		initialVirusPos = new ArrayList<>();
		newWall = new Pair[3];
		blank = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					initialVirusPos.add(new Pair(i, j)); // 바이러스 초기 위치 큐에 저장
				} else if (map[i][j] == 0) {
					blank.add(new Pair(i, j)); // 빈칸 위치 저장
				}
			}
		}
		
		max = 0;
		nCr(0, 0);
		
		// print
		System.out.println(max);
	}
	
	static void nCr(int cnt, int start) {
		// 빈칸 위치 리스트중에 3개 뽑아서 벽으로 만들고 bfs
		if (cnt == 3) {
			// 새로 세운 벽 3개가 적용된 맵을 만들기
			int[][] newMap = new int[N][M];
			for (int i = 0; i < N; i++) {
				System.arraycopy(map[i], 0, newMap[i], 0, M);
			}
			for (int i = 0; i < 3; i++) {
				newMap[newWall[i].r][newWall[i].c] = 1;
			}
			
			bfs(newMap);
			return;
		}
		
		for (int i = start, listSize = blank.size(); i < listSize; i++) {
			newWall[cnt] = blank.get(i);
			nCr(cnt + 1, i + 1);
		}
	}
	
	static void bfs(int[][] newMap) {
		Queue<Pair> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		// 모든 바이러스 queue에 넣기
		for (int i = 0, qSize = initialVirusPos.size(); i < qSize; i++) {
			Pair virus = initialVirusPos.get(i);
			queue.offer(virus);
			visited[virus.r][virus.c] = true;
		}
		
		while (!queue.isEmpty()) {
			Pair cur = queue.poll();
			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				if (!isIn(nr, nc)) continue;
				if (visited[nr][nc]) continue;
				if (newMap[nr][nc] == 0) {
					queue.offer(new Pair(nr, nc));
					visited[nr][nc] = true;
					newMap[nr][nc] = 2;
				}
			}
		}
		
		// 안전 영역 크기 구하기
		int size = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (newMap[i][j] == 0) {
					size++;
				}
			}
		}
		
		max = Math.max(max, size);
	}
	
	static boolean isIn(int r, int c) {
		return (r >= 0 && r < N && c >= 0 && c < M);
	}

}

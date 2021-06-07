import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_9205 {

	// 좌표 클래스
	static class Coor {
		int x, y;

		public Coor(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N;
	static Coor[] nodes; // 집, 편의점, 도착지 좌표 모두 담는 배열
	static int[][] adjMatrix; // 인접 행렬. 거리가 1000m 이하면 인접해있다고 판단
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder("");
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine()); // 편의점 개수 0 <= N <= 100
			nodes = new Coor[N + 2];
			adjMatrix = new int[N + 2][N + 2];
			for (int i = 0; i < N + 2; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				nodes[i] = new Coor(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			} // nodes[0] : 집, nodes[N + 1] : 도착지
			
			for (int i = 0; i < N + 2; i++) {
				for (int j = 0; j < N + 2; j++) {
					if (i == j) continue;
					int distance = Math.abs(nodes[i].x - nodes[j].x) + Math.abs(nodes[i].y - nodes[j].y);
					// 인접하면 1, 인접하지 않으면 MAX로 초기화
					if (distance <= 1000) {
						adjMatrix[i][j] = 1;
					} else {
						adjMatrix[i][j] = 99999; // 32767 * 2보다 큰 값
					}
				}
			}
			
			for (int k = 0; k < N + 2; k++) {
				for (int i = 0; i < N + 2; i++) {
					if (i == k) continue;
					for (int j = 0; j < N + 2; j++) {
						if (j == i || j == k) continue;
						adjMatrix[i][j] = Math.min(adjMatrix[i][j], adjMatrix[i][k] + adjMatrix[k][j]);
					}
				}
			}
			
			sb.append(adjMatrix[0][N + 1] == 99999 ? "sad" : "happy");
			sb.append("\n");
		}
		
		// print
		System.out.println(sb.toString());
	}
	

}

/**
 * 맥주 마시면서 걸어가기_S1
 * SSAFY_HW_0325
 */
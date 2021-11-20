import java.util.Scanner;

public class SWEA_1954 {
	private static int T, N;
	private static int[][] map;
	private static int[] dr = {0, 1, 0, -1}; // 오아왼위
	private static int[] dc = {1, 0, -1, 0};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			N = sc.nextInt();
			map = new int[N][N];

			// logic
			int d = 0; // 시작 방향 오른쪽
			int num = 1;
			int r = 0;
			int c = 0;
			while (num <= N * N) {
				map[r][c] = num++;
				// 경계를 벗어나거나, 숫자가 이미 있는 곳이면
				if (isOut(r + dr[d], c + dc[d]) || map[r + dr[d]][c + dc[d]] != 0) {
					d = (d + 1) % 4; // 방향 오아왼위 순으로 바꾸기
				}
				r += dr[d];
				c += dc[d];
			}

			// print
			System.out.println("#" + t);
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}

		}
		sc.close();
	}

	private static boolean isOut(int r, int c) {
		if (r >= 0 && r < N && c >= 0 && c < N) {
			return false;
		} else {
			return true;
		}
	}
}
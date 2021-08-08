
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @문제 [모의 SW 역량테스트] 활주로 건설
 * @날짜 210808
 * @분류
 * @조건
 *
 * @풀이
 *
 * @comment
 *
 */

public class SWEA_4014 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int N, X;
	static int[][] map;
	static int ans;
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			// -- input
			
			ans = 0;
			// 모든 행 활주로 체크
			for (int i = 0; i < N; i++) {
				ans += check(i);
			}
			
			// TODO : 행렬 90도 회전
			map = rotate();
			
			for (int i = 0; i < N; i++) {
				ans += check(i);
			}
			
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		
		// print
		System.out.println(sb.toString());
	}
	
	// 가로방향으로 한 행에 활주로 건설할 수 있는지 체크(가능하면 1반환, 불가능하면 0반환)
	static int check(int i) {
		int cnt = 1; // 연속된 높이의 지형 개수
		
		for (int j = 0; j < N - 1; j++) {
			int hd = map[i][j + 1] - map[i][j]; // 높이 차이
			if (Math.abs(hd) > 1) { // 높이 차이가 2이상이면 활주로 건설 불가 
				return 0;
			} else if (hd == 0) { // 높이 차이가 없으면
				cnt++;
			} else { // 높이 차이가 1이면 경사로로 커버 가능한지 체크
				if (hd > 0) { // 오르막이면
					// 지나왔던 길(cnt) 체크
					if (cnt >= X) { // 지금까지 밟은 땅이 경사로 길이를 커버 가능하면
						cnt = 1;
						continue;
					} else {
						return 0;
					}
				} else { // 내리막이면
					// 앞으로 X만큼 체크해서 가능한지 확인
					if (j + X >= N) return 0; // 범위 벗어나면 return
					else {
						int k = 0;
						for (k = j + 1; k < j + X; k++) {
							if (map[i][k] != map[i][k + 1]) { // 높이가 달라지면
								return 0;
							}
						}
						j = k - 1;
						cnt = 1;
						continue;
					}
				}
			}
		}
		
		return 1;
	}
	
	// 90도 회전 메소드
	static int[][] rotate() {
		int[][] newMap = new int[N][N];
		// 회전
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				newMap[i][j] = map[N - 1 - j][i];
			}
		}
		
		return newMap;
	}
	
}

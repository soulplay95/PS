package week12;

/**
 * @문제 고층 빌딩_G1
 * @날짜 210424
 * @분류 DP
 * @조건
 * 같은 높이를 가지는 빌딩 없음
 * 빌딩 "순서"의 경우의 수
 * 1 <= N <= 100
 * @풀이
 * N-1 => N이 되는 과정을 생각
 * "순서"의 경우의 수를 구해야 하므로 추가한 빌딩을 어느 자리에 놓느냐에 따라 경우의 수가 나눠짐
 * N-1개의 빌딩이 놓여진 상태에서 1개의 빌딩을 추가할 때 놓을 수 있는 자리는 N개
 * 추가할 빌딩의 높이를 불특정하게 생각할 필요가 없음 => 빌딩의 높이는 다 다르므로
 * 기존 N-1개의 빌딩 보다 제일 작은 높이의 빌딩을 추가한다고 생각
 * 왼쪽 끝에 놓을 경우 N-1개의 빌딩을 놓을 때 왼쪽에서 L-1개, 오른쪽에선 그대로 R개가 보일 때 경우의 수와 같다.
 * 오른쪽도 마찬가지
 * 가운데 놓을 경우 N-1개의 빌딩에서 L, R은 변하지 않으므로  가운데 놓는 경우의 수 N-2개 각각 같다. 
 * dp[N][L][R] : N개의 빌딩을 놓는데 왼쪽에서 바라봤을 때 개수가 L개이고, 오른쪽에서 봤을 때 개수가 R개일때 가능한 빌딩 배치 순서 개수
 * dp[N][L][R] = dp[N-1][L-1][R] + dp[N-1][L][R-1] + dp[N-1][L-1][R-1] * (N-2)
 * @comment
 * 쉬운 dp 문제는 N=1, 2, ...부터 경우의 수를 나열해보면서 패턴을 찾는 방식으로 풀릴 수 있지만 경우의 수가 많아지고 좀만 복잡해지면 이런 방식으로 풀기 힘들다.
 * dp문제는 동적테이블(메모이제이션으로 활용한 공간)의 정의를 명확히 내리고, n-1에서 n이 되기 위해서 어떤 과정을 거치는지 생각해본다.
 * 문제 상황에서 고려되는 변수들만 생각해본다.
 * 이 문제의 경우 "순서"의 경우의 수를 구하는 문제이므로 N-1개의 빌딩이 배치된 상태에서 1개의 빌딩을 추가할 때 놓을 자리에 따라 경우의 수가 나뉨을 알 수 있음
 * 추가할 빌딩의 높이가 불특정한 경우 변수가 너무 많다 => 제일 크거나 작은 높이의 빌딩을 추가해본다고 생각
 */

import java.util.*;
import java.io.*;

public class BOJ_1328 {
	
	// input, output handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	// -----------------------------------------------------------------------------
	
	static int N, L, R;
	static final int MOD = 1000000007;
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		// print
		System.out.println(solve());
	}
	
	static long solve() {
		if ((L == 1 && R == N) || (L == N && R == 1)) {
			return 1L;
		}
		
		long[][][] dp = new long[101][101][101];
		dp[1][1][1] = 1;
		
		for (int n = 2; n <= N; n++) {
			for (int l = 1; l <= L; l++) {
				for (int r = 1; r <= R; r++) {
					dp[n][l][r] = (dp[n-1][l-1][r] + dp[n-1][l][r-1] + dp[n-1][l][r] * (n-2)) % MOD;
				}
			}
		}
		
		return dp[N][L][R];
	}

}

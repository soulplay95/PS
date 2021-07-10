package week22;
/**
 * @문제 용돈 관리_S3
 * @날짜 210710
 * @분류 이분 탐색
 * @조건
 * # 1 <= N <= 10만
 * # 돈이 모자라면 다시 K원으로 맞춘다.
 * # 어떻게든 M번의 인출을 맞춰야 함 
 * @풀이
 * # K의 범위 : 
 * MAX(사용 금액 list)(?번의 인출로 커버 가능) <= 
 * <= 1만원(하루에 최대로 사용하는 금액) * 10만일(최대 일 수) = 10억원(단 한번의 인출로 커버 가능)
 * # 특정 K에 대해 인출 횟수를 구해서(O(N)) M과 비교해나가면서 탐색 범위를 좁힐 수 있다. => 이분탐색(O(logN))
 * # O(NlogN) => 10만 * 16.x
 * @comment
 * # 순차 접근 시 시간복잡도를 벗어나는 범위가 주어진 최적화 문제(최소 또는 최대값 찾기)에는 이분탐색 고려
 * # 구하고자 하는 값의 범위를 특정할 수 있고, 그 값이 정답에 가까운지 비교할 수 있는 대상이 있으면 이분탐색 가능
 */

import java.util.*;
import java.io.*;

public class BOJ_6236 {
	
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	// -----------------------------------------------------------------------------
	
	static int N, M;
	static int[] inputs;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// init
		inputs = new int[N];
		
		for (int n = 0; n < N; n++) {
			inputs[n] = Integer.parseInt(br.readLine());
		} // input end
		
		// print
		System.out.println(solve());
	}
	
	static int solve() {
		int l = 0, r = 0, m = 0;
		
		// l, r의 범위를 지정한다.
		for (int n = 0; n < N; n++) {
			int input = inputs[n];
			
			l = Math.max(l, input); // 입력 중 최대 값으로 갱신
			r += input; // 딱 1번 인출했을때 커버 가능한 최소 수준
		}
		
		// 이분 탐색으로 최소 K값을 구해나간다.
		while (l < r) {
			m = (l + r) / 2; // 범위의 중간값으로 k값 특정하기
			int withdrawalCounts = getWithdrawalCounts(m); // k원에 대해 필요한 인출 횟수
			
			if (withdrawalCounts > M) { // 돈을 M보다 자주 뽑아야 하네? 좀 더 큼직하게 뽑아라
				l = m + 1;
			} else { // 최소값 구해야 하므로 인출 횟수가 M을 달성했더라도 계속 갱신 
				r = m;
			}
		}
		
		return l;
	}
	
	static int getWithdrawalCounts(int k) {
		int count = 1; // 최초 1회 k원 인출
		int money = k; // 최초 수중에 있는 돈
		
		// 매일 사용할 금액에 대해 남아 있는 금액으로 인출이 필요한지, 하루를 보낼 수 있는지 판단하면서 인출 횟수를 카운팅한다.
		for (int n = 0; n < N; n++) {
			if (money >= inputs[n]) { // 하루를 보낼 수 있으면
				money -= inputs[n];
			} else { // 돈이 모자라면
				count++; // 인출
				money = k - inputs[n];
			}
		}
		
		return count;
	}
	
}

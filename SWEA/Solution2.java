// 2. 지하철 노선

import java.util.*;
import java.io.*;

public class Solution2 {
	
	static int N, max;
	static int[] input;
	static Set<Integer> set; // a, b가 정해진 후 a, b와 인접한 역의 인덱스를 담는 set
	static List<Integer> L, R; // a, b가 정해진 후 a, b를 기준으로 두 구역으로 나눴을 때 각 지하철 역을 포함하는 두 리스트
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			// init
			max = 0;
			input = new int[N];
			set = new HashSet<Integer>();
			L = new ArrayList<Integer>();
			R = new ArrayList<Integer>();
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				input[i] = Integer.parseInt(st.nextToken());
			}
			
			solve();
			
			// print
			System.out.println("#" + t + " " + max);
		}
	}
	
	static void solve() {
		for (int a = 0; a < N - 1; a++) { // 모든 지하철 역에 대하여 첫 번째 역 a 시작
			int l = a - 1 < 0 ? N - 1 : a - 1;
			int r = a + 1 >= N ? 0 : a + 1;
			for (int b = a + 1; b < N; b++) { // 두번째 역 b는 a와 인접하지 않은 애들 중에 선택
				if (b == l || b == r) continue;
				
				// b가 정해졌으므로 b와 인접한 인덱스 구하기
				int ll = b - 1 < 0 ? N - 1 : b - 1;
				int rr = b + 1 >= N ? 0 : b + 1;
				
				// set에 a, b역과 인접한 인덱스 넣기
				set.clear();
				set.add(a);
				set.add(b);
				set.add(l);
				set.add(r);
				set.add(ll);
				set.add(rr);
				
				// a, b 기준으로 두 구역으로 나누기
				L.clear();
				R.clear();
				boolean flag = false;
				for (int i = 0 ; i < N; i++) {
					if (i == a) {
						flag = true;
						continue;
					}
					else if (i == b) {
						flag = false;
						continue;
					}
					
					if (flag) {
						L.add(i);
					} else {
						R.add(i);
					}
				}
				
//				if (L.size() == 1 || R.size() == 1) continue;
				
				dfs(0, a, b, -1, -1); // 첫번째 직통 노선 a~b를 정한 후 두번째 직통 노선 정하기
			}
		}
	}
	
	static void dfs(int depth, int a, int b, int c, int d) {
		if (depth == 2) { // 조건에 위배되지 않는 가능한 두가지의 직통 노선을 찾았으면
			// 타당도 값 계산 후 최대값 갱신
			int result = (int)Math.pow(input[a] + input[b], 2) + (int)Math.pow(input[c] + input[d], 2);
			max = Math.max(max, result);
			
			return;
		}
		
		if (depth == 0) { // 두번째 노선에서 첫번째 역을 구할때 첫번째 노선의 두 지하철역과 인접한애들은 가지치기
			for (int i = 0; i < N; i++) {
				if (set.contains(i)) continue; // 인접한 역이면 continue
				// c포함 인접한애들도 안된다.
				set.add(i);
				int l = i - 1 < 0 ? N - 1 : i - 1;
				int r = i + 1 >= N ? 0 : i + 1;
				set.add(l);
				set.add(r);
				
				dfs(depth + 1, a, b, i, -1);
			}
		} else if (depth == 1) { // 첫번째 노선 기준 두 집합으로 나눴을 때 한쪽에서만 짝이 이루어져야함 => 교차 x
			for (int i = 0; i < N; i++) {
				if (set.contains(i)) continue; // 인접한 역이면 continue
				if ((L.contains(c) && L.contains(i)) || (R.contains(c) && R.contains(i))) {
					dfs(depth + 1, a, b, c, i);
				}
			}
		}
	}
	
}

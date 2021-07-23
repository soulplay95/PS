
/**
 * @문제 회전 초밥(고)
 * @날짜 210724
 * @분류
 * 
 * @풀이
 * 
 * @comment
 */

import java.io.*;
import java.util.*;

public class JUNGOL_2577 {

	static int N, d, k, c;
	static int[] belt;
	static int[] selected; // 인덱스에 해당하는 접시 번호
	static int start, end;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		belt = new int[N];
		selected = new int[d + 1];
		for (int n = 0; n < N; n++) {
			belt[n] = Integer.parseInt(br.readLine());
		}
		// --input

//		// 연속된 부분 벨트의 시작과 끝 인덱스
//		int max = 0; // 연속된 최대 개수
//		
//		start = 1;
//		end = 1;
//		while (end <= N) {
//			int cur = belt[end];
//			
//			if (selected[cur]) { // 선택된거면
//				max = Math.max(max, end - start + add()); // 고른 초밥 가짓 수 갱신
//				int index = start;
//				while (true) {
//					if (cur == belt[index]) {
//						start = index + 1;
//						break;
//					}
//					index++;
//				}
//			} else {
//				// 최대 연속된 접시에 도달하면
//				if (end - start >= k - 1) {
//					max = Math.max(max, end - start + 1 + add()); // 고른 초밥 가짓 수 갱신
//					selected[belt[start++]] = false;
//				}
//				selected[cur] = true;
//			}
//			end++;
//		}

		int max = 0;
		int cnt = 0;

		// 처음에 k개 박고 시작
		for (int i = 0; i < k; i++) {
			if (selected[belt[i]]++ == 0)
				cnt++;
		}
		max = cnt;
		if (selected[c] == 0)
			max++; // 쿠폰 사용가능하면 추가

		for (int i = 1; i < N; i++) {
			if (--selected[belt[i - 1]] == 0)
				cnt--;
			if (++selected[belt[(i + k - 1) % N]] == 1)
				cnt++;
			int add = (selected[c] == 0) ? 1 : 0;
			max = Math.max(cnt + add, max);
		}

		// print
		System.out.println(max);
	}

//	// 쿠폰으로 가짓수 늘릴 수 있으면 1리턴, 아니면 0리턴
//	static int add() {
//		for (int i = start; i <= end; i++) {
//			if (belt[i] == c) { // 쿠폰 번호와 겹치는게 있으면 0리턴
//				return 0;
//			}
//		}
//		
//		return 1;
//	}
}

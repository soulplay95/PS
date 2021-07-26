/**
 * @문제 DNA 조합
 * @날짜 210726
 * @분류 
 * @조건
 * 
 * @풀이
 * # n! 조합에 대해 백트래킹하면서 최소 길이 찾기
 * # 이어 붙이기 : 뒷 문자열의 첫 문자를 앞 문자열의 뒤부터 비교해가면서 붙여본다.
 * @comment
 * 5
 * TAG
 * TGCAG
 * CA
 * T
 * ACTG
 * => 11 ?
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JUNGOL_2217 {
	
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	// -----------------------------------------------------------------------------

	static int N, min;
	static String[] inputs, result;
	static boolean[] isSelected;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		// init
		min = 50; // 7 * 7 + 1
		inputs = new String[N];
		result = new String[N];
		isSelected = new boolean[N];
		for (int n = 0; n < N; n++) {
			inputs[n] = br.readLine();
		} // input end

		solve();

		// print
		System.out.println(min);
	}
	
	static void solve() {
		// N! 조합
		nPr(0);
	}

	static void nPr(int cnt) {
		// base condition
		if (cnt == N) {
			// 문자열을 연결해서 최소 길이를 구하고 갱신한다.
			min = Math.min(min, getLength());

			return;
		}

		for (int i = 0; i < N; i++) {
			if (isSelected[i]) continue; // 이미 뽑힌 문자열이면 continue

			result[cnt] = inputs[i]; // i번째 위치의 문자열을 선택
			isSelected[i] = true; // 선택 마킹
			nPr(cnt + 1); // 다음 문자열 뽑음
			isSelected[i] = false; // 선택을 되돌리고 다른 문자열을 뽑아야 하므로
		}
	}

	static int getLength() {
		String a = result[0];
		// 뽑힌 result 조합의 문자열들을 연결해본다.
		for (int i = 1; i < N; i++) { // i : 뒷 문자열을 가리키는 인덱스
			String b = result[i]; // 뒷 문자열
			char bHead = b.charAt(0); // 뒷 문자열의 첫 문자
			int max = 0;
			int overlapLen = 0; // 겹치는 부분 길이

			// 문자열 길이 1인것 처리
			if (a.length() == 1) {
				if (a.charAt(0) == bHead) {
					max = 0;
				}
			} else if (b.length() == 1) {
				if (a.charAt(a.length() - 1) == bHead) {
					max = 1;
				}
			} else {
				// 앞 문자열의 뒤부터 검사
				for (int j = a.length() - 1; j >= 0; j--) {
					overlapLen = 0;
					if (a.charAt(j) == bHead) { // 문자가 같으면
						// 겹치는 지점부터 앞 문자열의 끝까지 겹치는지 확인
						int k = j;
						for (int kk = 0, end = a.length(); k < end && kk < b.length(); k++) {
							if (a.charAt(k) == b.charAt(kk++)) {
								overlapLen++;
							} else {
								overlapLen = 0; // 하나라도 안겹치면 0으로 초기화
								break;
							}
						}

						if (overlapLen > 0 && k == a.length()) { // 겹쳤다면
							max = Math.max(max, overlapLen); // 최대값을 구함
						}
					}
				}
			}

			// 최대값 기준 문자열 연결
			if (max > 0 && max < b.length()) {
				a = a + b.substring(max);
			} else if (max == 0) {
				a = a + b;
			}

			// 백트래킹
			if (a.length() > min) {
				return 50;
			}
		}

		return a.length();
	}

}

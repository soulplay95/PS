package study.week30;

/**
 * @문제 즐거운 단어_G4
 * @날짜 210809
 * @분류 문자열, 브루트포스, 백트래킹
 * @조건
 * # 경우의 수 < 2^63 - 1
 * @풀이
 * # 입력 문자열에서 빈칸의 개수, 위치를 저장하고 L의 포함 여부를 체크한다.
 * # 첫 번째 빈칸부터 재귀 타고 들어가면서 L / 모음 / 자음(L 비포함)의 3가지 경우에 대해 가능한 경우의 수를 구한다.
 * @comment
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2922 {
	
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	// -----------------------------------------------------------------------------

	static char[] input;
	static List<Integer> blankPos; // 빈칸의 입력 문자열 내 인덱스
	static long ans;

	public static void main(String[] args) throws IOException {
		input = br.readLine().toCharArray();
		// init
		blankPos = new ArrayList<>();
		ans = 0L;

		boolean isLInclude = getBlankInfo();

		solve(0, 1, isLInclude);

		// print
		System.out.println(ans);
	}

	static boolean getBlankInfo() {
		boolean ret = false; // 입력 문자열 내 L의 포함 여부
		// 빈칸 정보를 리스트에 저장
		for (int i = 0, end = input.length; i < end; i++) {
			if (input[i] == '_') {
				blankPos.add(i);
			} else if (input[i] == 'L') { // L의 포함 여부 체크
				ret = true;
			}
		}

		return ret;
	}
	
	static void solve(int i, long n, boolean isLInclude) {
		// 모든 빈칸이 고려되었고 L을 포함했으면 경우의 수를 더한다.
		if (i == blankPos.size()) {
			if (isLInclude) ans += n;
			return;
		}

		int index = blankPos.get(i);

		// 각 case의 문자가 들어갈 수 있는지 판단한다.
		if (isMo(index)) { // 모음 가능?
			input[index] = 'A';
			solve(i + 1, n * 5, isLInclude); // 모음
			input[index] = '_';
		}
		if (isJa(index)) { // 자음 가능?
			input[index] = 'B';
			solve(i + 1, n, true); // L
			solve(i + 1, n * 20, isLInclude); // L 제외 자음
			input[index] = '_';
		}
	}

	static boolean isMo(int i) {
		// 모음 연속성 체크
		int index = i - 1;
		int cnt = 0;

		// left
		while (index >= 0) {
			if (input[index] == 'A' || input[index] == 'E' || input[index] == 'I' ||
					input[index] == 'O' || input[index] == 'U') {
				cnt++;
			} else {
				break;
			}

			index--;
		}

		index = i + 1;

		// right
		while (index < input.length) {
			if (input[index] == 'A' || input[index] == 'E' || input[index] == 'I' ||
					input[index] == 'O' || input[index] == 'U') {
				cnt++;
			} else {
				break;
			}

			index++;
		}

		if (cnt >= 2) {
			return false;
		}

		return true;
	}

	static boolean isJa(int i) {
		// 자음 연속성 체크
		int index = i - 1;
		int cnt = 0;

		// left
		while (index >= 0) {
			if (input[index] == 'A' || input[index] == 'E' || input[index] == 'I' ||
					input[index] == 'O' || input[index] == 'U' || input[index] == '_') {
				break;
			} else {
				cnt++;
			}

			index--;
		}

		index = i + 1;

		// right
		while (index < input.length) {
			if (input[index] == 'A' || input[index] == 'E' || input[index] == 'I' ||
					input[index] == 'O' || input[index] == 'U' || input[index] == '_') {
				break;
			} else {
				cnt++;
			}

			index++;
		}

		if (cnt >= 2) {
			return false;
		}

		return true;
	}

}

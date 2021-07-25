package study;

/**
 * @문제 애너그램_G4
 * @날짜 210723
 * @분류 수학, 문자열, 조합론, 백트래킹
 * @조건
 * 
 * @풀이
 * # NP
 * @comment
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_6443 {
	
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	// -----------------------------------------------------------------------------

	static int N, len; // len : 입력 문자열의 길이
	static char[] input;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		for (int n = 0; n < N; n++) {
			input = br.readLine().toCharArray();
			len = input.length;
			solve();
		}

		// print
		sb.setLength(sb.length() - 1);
		System.out.print(sb.toString());
	}

	static void solve() {
		// 1. 알파벳 순으로 정렬
		Arrays.sort(input);

		do {
			// 현재 순열부터 append
			sb.append(String.valueOf(input)).append("\n");
		} while (np());
	}

	static boolean np() {
		// 1. 꼭대기를 찾는다.
		int i = len - 1; // 맨 뒤 인덱스
		while (i > 0 && input[i - 1] >= input[i]) i--;
		// i가 0? => 현 순열의 상태가 가장 큰 순열이다.
		if (i == 0) return false;

		// 2. i - 1값보다 처음으로 큰 값의 인덱스 j를 찾는다.
		int j = len - 1;
		while (input[i - 1] >= input[j]) j--;

		// 3. 두 값을 swap
		swap(i - 1, j);

		// 4. 꼭대기 포함 끝까지 오름차순 정렬
		int k = len - 1;
		while (i < k) {
			swap(i++, k--);
		}

		return true;
	}

	static void swap(int i, int j) {
		char temp = input[i];
		input[i] = input[j];
		input[j] = temp;
	}

}

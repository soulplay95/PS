package study.week32;

/**
 * @문제 가르침_G4
 * @날짜 210821
 * @분류 브루트포스, 비트마스킹, 백트래킹
 * @조건
 * # N <= 50
 * # 8 <= 단어 길이 <= 15
 * @풀이
 * # a, n, t, i, c 외의 필요한 단어들 중 K-5개를 뽑아 읽을 수 있는 단어의 최대 개수를 센다.
 * @comment
 * # set to array => T[] array = set.toArray(new T[0]);
 * # 반례
 * 2 7
 * antatica
 * antaktica
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_1062 {
	
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	// -----------------------------------------------------------------------------

	static int N, K, max;
	static boolean[] readable; // 읽을 수 있는 알파벳
	static String[] input;
	static Character[] alphabets; // antic 외에 필요한 단어들
	static int end;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		// init
		max = 0;
		readable = new boolean[26];
		input = new String[N];
		for (int n = 0; n < N; n++) {
			String s = br.readLine();
			input[n] = s.substring(4, s.length() - 4); // 가운데 문자열만 뽑아서 저장
		} // input end

		if (K < 5) {
			System.out.println(0);
			System.exit(0);
		} else if (K == 26) {
			System.out.println(N);
			System.exit(0);
		}

		solve();

		// print
		System.out.println(max);
	}
	
	static void solve() {
		// 꼭 필요한 알파벳 antic 세팅
		readable['a' - 'a'] = true;
		readable['n' - 'a'] = true;
		readable['t' - 'a'] = true;
		readable['i' - 'a'] = true;
		readable['c' - 'a'] = true;

		// 주어진 단어에서 antic 외에 필요한 단어 몇갠지 세기
		Set<Character> needs = new HashSet<>();

		for (int n = 0; n < N; n++) {
			for (int i = 0, end = input[n].length(); i < end; i++) {
				char c = input[n].charAt(i);

				if (!readable[c - 'a']) { // 필요한 단어이면
					// set에 넣기
					needs.add(c);
				}
			}
		}

		// set to array
		alphabets = needs.toArray(new Character[0]);
		
		// 필요한 단어가 K - 5개보다 적으면
		if (alphabets.length < K - 5) {
			end = alphabets.length;
		} else {
			end = K - 5;
		}

		// 필요한 단어 조합 중 K - 5개 뽑아서 최대값 갱신
		nCr(0, 0);
	}

	static void nCr(int cnt, int start) {
		if (cnt == end) {
			max = Math.max(max, getCounts());
			return;
		}

		for (int i = start, end = alphabets.length; i < end; i++) {
			readable[alphabets[i] - 'a'] = true;
			nCr(cnt + 1, i + 1);
			readable[alphabets[i] - 'a'] = false;
		}
	}

	static int getCounts() {
		// 읽을 수 있는 단어 개수를 센다.
		int counts = 0;

		for (int n = 0; n < N; n++) {
			boolean isReadable = true;

			for (int i = 0, end = input[n].length(); i < end; i++) {
				if (!readable[input[n].charAt(i) - 'a']) { // 뽑은 알파벳 조합으로 읽을 수 없으면
					isReadable = false;
					break;
				}
			}

			if (isReadable) {
				counts++;
			}
		}

		return counts;
	}

}

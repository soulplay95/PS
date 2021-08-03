package week10;

/**
 * 행운의 문자열_G5
 * 
 * 순열 10!
 */

import java.util.*;
import java.io.*;

public class BOJ_1342 {

//	static char[] input; // 입력 문자열
//	static int N; // 입력 문자열 길이
//	static int[] availableCounts; // 인덱스에 해당하는 문자가 선택되었는지
//	static int count; // 만들 수 있는 행운의 문자열 개수
//	
//	// --
//	static char[] arr;
//	
//	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		input = sc.next().toCharArray();
//		N = input.length;
//		isSelected = new boolean[N];
//		
//		//--
//		arr = new char[N];
//		
//		count = 0;
//		nPr(0, '?');
//		
//		// print
//		System.out.println(count);
//		
//		sc.close();
//	}
//	
//	static void nPr(int cnt, char prev) { // cnt : 몇번째 인덱스에 들어갈 문자를 뽑는지 나타냄. prev : 이전에 뽑은 문자
//		// 행운의 문자열이 정상적으로 완성되면
//		if (cnt == N) {
//			System.out.println(Arrays.toString(arr));
//			count++;
//			return;
//		}
//		
//		for (int i = 0; i < N; i++) {
//			if (isSelected[i] || input[i] == prev) {
//				continue;
//			}
//			
//			arr[cnt] = input[i];
//			isSelected[i] = true;
//			nPr(cnt + 1, input[i]);
//			isSelected[i] = false;
//		}
//	}
	
//	static int[] input;
//	static int N;
//	
//	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		char[] temp = sc.next().toCharArray();
//		N = temp.length;
//		input = new int[N];
//		for (int i = 0; i < N; i++) {
//			input[i] = temp[i];
//		}
//		
//		int count = 0;
//		Arrays.sort(input); // 오름차순 정렬하여 가장 작은 순열의 상태로 만듦
//		
//		do {
//			// 행문의 문자열인지 체크
//			boolean flag = false;
//			for (int i = 1; i < N; i++) {
//				if (input[i] == input[i - 1]) {
//					flag = true;
//					break;
//				}
//			}
//			if (!flag) count++;
//		} while(np());
//		
//		// print
//		System.out.println(count);
//		
//		sc.close();
//	}
//	
//	public static boolean np() {
//		// 1. 꼭대기를 찾는다.
//		int i = N - 1; // 맨 뒤 인덱스
//		while (i > 0 && input[i - 1] >= input[i]) i--;
//		// i가 0이라는 것은 현 순열의 상태가 가장 큰 순열의 상태임을 나타냄. 즉, 더이상 np를 돌릴수가 없음
//		if (i == 0) return false;
//		
//		// 2. i-1값보다 처음으로 큰 값의 인덱스 j을 찾는다.
//		int j = N - 1;
//		while (input[i - 1] >= input[j]) j--;
//		
//		// 3. 두 값을 swap
//		swap(i - 1, j);
//		
//		// 4. 꼭대기 포함 끝까지 오름차순 정렬
//		int k = N - 1;
//		while (i < k) {
//			swap(i++, k--);
//		}
//		
//		return true;
//	}
//	
//	private static void swap(int i, int j) {
//		int temp = input[i];
//		input[i] = input[j];
//		input[j] = temp;
//	}
	
	static char[] input;
	static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input = br.readLine().toCharArray();
		N = input.length;
		
		int count = 0;
		Arrays.sort(input); // 오름차순 정렬하여 가장 작은 순열의 상태로 만듦
		
		do {
			if (check()) count++;
		} while(np());
		
		// print
		System.out.println(count);
		
	}
	
	public static boolean np() {
		// 1. 꼭대기를 찾는다.
		int i = N - 1; // 맨 뒤 인덱스
		while (i > 0 && input[i - 1] >= input[i]) i--;
		// i가 0이라는 것은 현 순열의 상태가 가장 큰 순열의 상태임을 나타냄. 즉, 더이상 np를 돌릴수가 없음
		if (i == 0) return false;
		
		// 2. i-1값보다 처음으로 큰 값의 인덱스 j을 찾는다.
		int j = N - 1;
		while (input[i - 1] >= input[j]) j--;
		
		// 3. 두 값을 swap
		swap(i - 1, j);
		
		// 4. 꼭대기 포함 끝까지 오름차순 정렬
		int k = N - 1;
		while (i < k) {
			swap(i++, k--);
		}
		
		return true;
	}
	
	private static void swap(int i, int j) {
		char temp = input[i];
		input[i] = input[j];
		input[j] = temp;
	}
	
	// 행운의 문자열인지 체크
	static boolean check() {
		for (int i = 1; i < N; i++) {
			if (input[i] == input[i - 1]) return false;
		}
		
		return true;
	}

}

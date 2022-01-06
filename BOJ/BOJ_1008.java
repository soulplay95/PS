/**
 * @문제 A/B_B4
 * @날짜 220106
 * @분류 수학, 구현, 사칙연산
 * @조건
 * 0 < A, B < 10
 * @풀이
 * Double로 형변환
 * @comment
 */

import java.util.*;
import java.io.*;

public class BOJ_1012 {
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		// print
		System.out.println((double)a / b);
	}
	
}
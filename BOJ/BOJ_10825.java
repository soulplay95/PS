/**
 * @문제 국영수_S4
 * @날짜 211012
 * @분류 정렬
 * @조건
 * # 1 <= 학생 수 N <= 10만
 * @풀이
 * # Comparable로 정렬
 * @comment
 * # s1.compareTo(s2) => s1 문자열의 ASCII 코드 값에서 s2 문자열의 ASCII 코드값을 뺀 차이값을 반환
 * # 양수면 s1이 사전순으로 뒤에 있음 / 음수면 s1이 사전순으로 앞에
 * # 비교대상 문자열의 각 문자들을 첫번째자리 부터 하나씩 비교하다가 가장 먼저 만나는 다른 문자들의 아스키 코드 값 차이를
 * 반환하고 끝냄. 이후의 문자열을 비교하지 않음
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_10825 {
	
	// IO Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	// -----------------------------------------------------------------------------

	static class Student implements Comparable<Student> {
		String name;
		int a, b, c; // 국, 영, 수

		public Student(String name, int a, int b, int c) {
			this.name = name;
			this.a = a;
			this.b = b;
			this.c = c;
		}

		@Override
		public int compareTo(Student o) {
			if (this.a == o.a) { // 국어 점수가 같으면
				if (this.b == o.b) { // 영어 점수도 같으면
					if (this.c == o.c) { // 수학 점수도 같으면
						// 이름이 사전순 증가. 아스키코드 순
						return this.name.compareTo(o.name);
					}
					return o.c - this.c; // 수학 내림차순
				}
				return this.b - o.b;
			}
			return o.a - this.a;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	static int N;
	static Student[] students;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		// init
		students = new Student[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			String name = st.nextToken();
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			students[i] = new Student(name, a, b, c);
		}

		Arrays.sort(students);

		// print
		for (Student s : students) {
			System.out.println(s.toString());
		}
	}

}

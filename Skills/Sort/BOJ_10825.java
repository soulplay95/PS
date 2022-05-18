package algorithm.sort;

/**
 * @문제 국영수 [S4]
 * @날짜 220519
 * @분류 정렬
 * @조건
 * # 1 <= N <= 10만
 * # 1 <= 점수 <= 100
 * @풀이
 * # 정렬
 * @comments
 * # 정답의 최대치: Integer
 * # 시간 복잡도: O(NlogN)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_10825 {

    static StringBuilder sb = new StringBuilder();

    static class Student implements Comparable<Student> {
        String name;
        int korean, english, math;

        public Student(String name, int korean, int english, int math) {
            this.name = name;
            this.korean = korean;
            this.english = english;
            this.math = math;
        }

        @Override
        public int compareTo(Student o) {
            if (this.korean == o.korean) {
                if (this.english == o.english) {
                    if (this.math == o.math) {
                        return this.name.compareTo(o.name);
                    }

                    return Integer.compare(o.math, this.math);
                }

                return Integer.compare(this.english, o.english);
            }

            return Integer.compare(o.korean, this.korean);
        }
    }

    static int N;
    static Student[] students;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        print();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        // init
        students = new Student[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            String name = st.nextToken();
            int korean = Integer.parseInt(st.nextToken());
            int english = Integer.parseInt(st.nextToken());
            int math = Integer.parseInt(st.nextToken());
            students[i] = new Student(name, korean, english, math);
        }
    }

    static void solve() {
        Arrays.sort(students, 0, N);

        for (Student student : students) {
            sb.append(student.name).append("\n");
        }
    }

    static void print() {
        System.out.println(sb.toString());
    }

}

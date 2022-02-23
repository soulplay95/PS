/**
 * @문제 수열 정렬_S4
 * @날짜 220223
 * @분류
 * @조건
 * # 1 <= 배열 크기 (N) <= 50
 * # 1 <= 각 원소 <= 1000
 * @풀이
 * - A를 (값, 인덱스)로 표현하고 값에 대해 오름차순 정렬하여 B를 만든다 => O(NlogN)
 * - P[인덱스] = B에서 인덱스에 해당하는 값을 이용하여 P 배열을 구성한다 => O(N)
 * @comments
 * # 시간 복잡도: O(NlogN), max: O(50log50)
 * # 공간 복잡도: O(N)
 * # 정답의 최대치:
 */

import java.io.*;
import java.util.*;

public class BOJ_1015 {

    static StringBuilder sb = new StringBuilder();

    static class Element implements Comparable<Element> {
        int index, value; // index: A 배열의 인덱스를 저장, value: A[index]의 값

        public Element(int index, int value) {
            this.index = index;
            this.value = value;
        }

        @Override
        public int compareTo(Element other) {
            // value 기준 비 내림차순
            if (this.value != other.value) {
                return this.value - other.value;
            }
            // value가 같으면 index 기준 오름차순
            return this.index - other.index;
        }
    }

    static int N;
    static Element[] B;
    static int[] P;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        print();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        // init
        B = new Element[N];
        P = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            int value = Integer.parseInt(st.nextToken());
            B[i] = new Element(i, value);
        }
    }

    static void solve() {
        // B 배열 정렬
        Arrays.sort(B);

        // P 배열 채우기
        for (int bIndex = 0; bIndex < N; bIndex++) {
            P[B[bIndex].index] = bIndex;
        }
    }

    static void print() {
        for (int i = 0; i < N; i++) {
            sb.append(P[i]).append(" ");
        }

        System.out.println(sb.toString());
    }

}

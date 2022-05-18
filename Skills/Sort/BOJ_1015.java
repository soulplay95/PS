package algorithm.sort;

/**
 * @문제 수열 정렬 [S4]
 * @날짜 220519
 * @분류 정렬
 * @조건
 * # 1 <= N <= 50
 * @풀이
 * # 정렬
 * - A 배열에 index 정보를 추가해서 정렬한다.
 * - P[index] = 정렬 후 각 원소의 index
 * @comments
 * # 정답의 최대치: Integer
 * # 시간 복잡도: O(NlogN)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_1015 {

    static StringBuilder sb = new StringBuilder();

    static class Element implements Comparable<Element> {
        int index, value;

        public Element(int index, int value) {
            this.index = index;
            this.value = value;
        }

        @Override
        public int compareTo(Element o) {
            return Integer.compare(this.value, o.value); // Stable
        }
    }

    static int N;
    static Element[] A;
    static int[] P;

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
        A = new Element[N];
        P = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            A[i] = new Element(i, Integer.parseInt(st.nextToken()));
        }
    }

    static void solve() {
        Arrays.sort(A, 0, N);

        for (int indexAfterSort = 0; indexAfterSort < N; indexAfterSort++) {
            P[A[indexAfterSort].index] = indexAfterSort;
        }

        for (int i = 0; i < N; i++) {
            sb.append(P[i]).append(" ");
        }
    }

    static void print() {
        System.out.println(sb.toString());
    }

}

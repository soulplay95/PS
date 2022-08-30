package practice.sort;

/**
 * @문제 수열 정렬 [S4]
 * @날짜 220827
 * @분류 정렬
 * @조건
 * 1 <= N <= 50
 * @풀이
 * # 정렬
 * @복잡도
 * # 정답의 최대치: Integer
 * # 시간 복잡도: O(NlogN)
 * # 공간 복잡도:
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1015 {

    static StringBuilder sb = new StringBuilder();

    static class Element implements Comparable<Element> {
        public int num, index;

        @Override
        public int compareTo(Element o) {
            return Integer.compare(num, o.num);
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
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        // init
        A = new Element[N];
        P = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            A[i] = new Element();
            A[i].index = i;
            A[i].num = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        Arrays.sort(A);

        for (int i = 0; i < N; i++) {
            P[A[i].index] = i;
        }
    }

    static void print() {
        for (int i = 0; i < N; i++) {
            sb.append(P[i]).append(" ");
        }

        System.out.println(sb.toString());
    }

}

package algorithm.sort.practice;

/**
 * @문제 단어 정렬 [S5]
 * @날짜 220520
 * @분류 문자열 / 정렬
 * @조건
 * # 1 <= N <= 2만
 * @풀이
 * # 정렬
 * @comments
 * # 정답의 최대치:
 * # 시간 복잡도: O(NlogN)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_1181 {

    static StringBuilder sb = new StringBuilder();

    static int N;
    static String[] words;

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
        words = new String[N];

        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }
    }

    static void solve() {
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() != o2.length()) return Integer.compare(o1.length(), o2.length());
                return o1.compareTo(o2);
            }
        });

        String before = words[0];
        sb.append(words[0]).append("\n");
        for (int i = 1; i < N; i++) {
            if (words[i].equals(before)) continue;
            sb.append(words[i]).append("\n");
            before = words[i];
        }
    }

    static void print() {
        System.out.println(sb.toString());
    }

}

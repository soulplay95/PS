package practice.sort;

/**
 * @문제 단어 정렬 [S5]
 * @날짜 220830
 * @분류 문자열 / 정렬
 * @조건
 * 1 <= N <= 2만
 * 1 <= 문자열 길이 <= 50
 * @풀이
 * # 정렬
 * - 정렬 조건 세우기
 * - 중복 처리: set 이용
 * @복잡도
 * # 정답의 최대치:
 * # 시간 복잡도: O(NlogN)
 * # 공간 복잡도:
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.StringTokenizer;

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
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        HashSet<String> wordSet = new HashSet<>();
        for (int i = 0; i < N; i++) {
            wordSet.add(br.readLine());
        }

        // init
        words = wordSet.toArray(new String[0]);
    }

    static void solve() {
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() == o2.length()) {
                    return o1.compareTo(o2);
                }
                return Integer.compare(o1.length(), o2.length());
            }
        });

        // append
        for (String word : words) {
            sb.append(word).append("\n");
        }
    }

    static void print() {
        System.out.println(sb.toString());
    }

}

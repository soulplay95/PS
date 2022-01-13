/**
 * @문제 DNA_S5
 * @날짜 220113
 * @분류 구현, 문자열, 완탐
 * @조건
 * # 문자열 개수 (N) <= 1000
 * # 문자열 길이 (M) <= 50
 * @풀이
 * # 모든 문자열에 대하여 각 위치의 문자들 중 제일 많이 등장하면서 사전순으로 앞서는 문자를 택하고 distance를 구한다.
 * @comment
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_1969 {

    private static int N, M;
    private static String[] inputs;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        // init
        inputs = new String[N];

        for (int i = 0; i < N; i++) {
            inputs[i] = br.readLine();
        }

        solve();

        // print
        System.out.println(sb.toString());
    }

    private static void solve() {
        int[] counts; // 각 알파벳별 등장 횟수
        int distance = 0;

        // 각 문자 위치에서 제일 많이 등장하면서 사전순으로 앞서는 문자를 탐색
        for (int i = 0; i < M; i++) {
            counts = new int[26]; // 각 위치마다 초기화

            for (int j = 0; j < N; j++) {
                char c = inputs[j].charAt(i);

                counts[c - 'A']++;
            }

            int index = 0;
            int max = 0; // 등장 횟수
            for (int k = 0; k < 26; k++) {
                if (counts[k] > max) {
                    max = counts[k];
                    index = k;
                }
            }

            distance += N - counts[index]; // 선정된(index) 문자와 다른 문자 개수만큼 누적

            // append
            sb.append((char)('A' + index));
        }

        sb.append("\n");
        sb.append(distance);
    }

}

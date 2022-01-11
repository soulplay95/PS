/**
 * @문제 문자열 반복_B2
 * @날짜 220111
 * @분류 구현, 문자열
 * @조건
 * # 1 <= 반복 횟수(R) <= 8
 * @풀이
 * # charAt
 * @comment
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_2675 {

    private static int R;
    private static String S;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine(), " ");
            R = Integer.parseInt(st.nextToken());
            S = st.nextToken();

            solve();
        }

        // print
        System.out.print(sb.toString());
    }

    private static void solve() {
        for (int i = 0, end = S.length(); i < end; i++) {
            char c = S.charAt(i);

            for (int r = 0; r < R; r++) {
                sb.append(c);
            }
        }

        sb.append("\n");
    }

}

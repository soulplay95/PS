/**
 * @문제 저항_B2
 * @날짜 220414
 * @분류 구현
 * @조건
 * #
 * @풀이
 * # 해쉬
 * @comments
 * # 정답의 최대치:
 * # 시간 복잡도:
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_1076 {

    static StringBuilder sb = new StringBuilder();

    static HashMap<String, Long[]> values;
    static String[] input;
    static long answer;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        print();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        input = new String[3];
        for (int i = 0; i < 3; i++) {
            input[i] = br.readLine();
        }
    }

    static void solve() {
        // init
        values = new HashMap<>();
        values.put("black", new Long[]{0L, 1L});
        values.put("bronw", new Long[]{1L, 10L});
        values.put("red", new Long[]{2L, 100L});
        values.put("orange", new Long[]{3L, 1000L});
        values.put("yellow", new Long[]{4L, 10000L});
        values.put("green", new Long[]{5L, 100000L});
        values.put("blue", new Long[]{6L, 1000000L});
        values.put("violet", new Long[]{7L, 10000000L});
        values.put("grey", new Long[]{8L, 100000000L});
        values.put("white", new Long[]{9L, 1000000000L});

        answer = (values.get(input[0])[0] * 10 + values.get(input[1])[0]) * values.get(input[2])[1];
    }

    static void print() {
        System.out.println(answer);
    }

}

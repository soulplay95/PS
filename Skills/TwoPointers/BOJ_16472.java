package algorithm.two_pointers;

/**
 * @문제 고냥이 [G4]
 * @날짜 220530
 * @분류 두 포인터
 * @조건
 * 1 < N <= 26
 * 1 <= 문자열 길이 <= 10만
 * @풀이
 * # 두 포인터
 * @comments
 * # 정답의 최대치: Integer => N이 26일 때, 문자열 전체를 인식하므로 max N = 10만이 최대
 * # 시간 복잡도: O(N)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_16472 {

    static StringBuilder sb = new StringBuilder();

    static int N, kind;
    static String input;
    static int[] count;

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        input = br.readLine();
        // init
        count = new int[26];
    }

    static void solve() {
        int ans = 0;
        int length = input.length();
        for (int R = 0, L = 0; R < length; R++) {
            add(input.charAt(R)); // R 번째 문자를 포함

            // 가능할 때 까지 L을 이동
            while (kind > N) {
                erase(input.charAt(L++));
            }

            // 정답 갱신
            ans = Math.max(ans, R - L + 1);
        }

        // print
        System.out.println(ans);
    }

    static void add(char x) {
        count[x - 'a']++;
        if (count[x - 'a'] == 1) { // 새롭게 나타난 알파벳이면
            kind++;
        }
    }

    static void erase(char x) {
        count[x - 'a']--;
        if (count[x - 'a'] == 0) { // 인식 해야 하는 알파벳에서 빠지는 순간
            kind--;
        }
    }

}

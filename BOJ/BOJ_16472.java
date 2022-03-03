/**
 * @문제 고냥이_G4
 * @날짜 220303
 * @분류 두 포인터
 * @조건
 * # 1 < N <= 26
 * # 1 <= 문자열의 길이 <= 10만
 * @풀이
 * # 두 포인터
 * - [L...R] 범위의 문자열에서 등장한 문자의 개수를 나타내는 counts 배열을 만든다.
 * - counts 배열에서 원소의 값이 0이 아닌 개수가 문자 종류 개수이다. (kind) => O(1)
 * - R을 고정하고 kind가 N 이하 일때까지 L을 왼쪽으로 늘려간다. => O(문자열길이)
 * @comments
 * # 정답의 최대치: Integer
 * - N이 26일때, 모든 문자를 인식할 수 있으므로 문자열의 길이 최대값아 정답 => 10만
 * # 시간 복잡도: O(문자열길이)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_16472 {

    static StringBuilder sb = new StringBuilder();

    static int N, ans, kind;
    static int[] counts;
    static String A;

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
        counts = new int[26];
        A = br.readLine();
    }

    static void add(char x) {
        // x 라는 알파벳을 counts 배열에 추가
        counts[x - 'a']++;
        // O(26) -> O(1)로 줄이는 테크닉
        // kind가 바뀌는 시점은 counts 배열이 바뀔때
        if (counts[x - 'a'] == 1) { // 새로운 문자가 등장했음. 즉, kind가 1증가
            kind++;
        }
    }

    static void erase(char x) {
        // x 라는 알파벳을 counts 배열에서 제거
        counts[x - 'a']--;
        if (counts[x - 'a'] == 0) {
            kind--;
        }
    }

    static void solve() {
        int len = A.length();
        kind = 0;
        ans = 0;

        for (int R = 0, L = 0; R < len; R++) {
            // R번째 문자를 오른쪽에 추가
            add(A.charAt(R));

            // 불가능하면, 가능할 때 까지 L을 이동
//            while (true) {
//                int kind = 0;
//                // kind 구하기
//                for (int i = 0; i < 26; i++) {
//                    if (counts[i] != 0) {
//                        kind++;
//                    }
//                }
//                if (kind <= N) { // 가능하면
//                    break;
//                }
//                // L을 오른쪽으로 땡김 (문자열 범위 줄임)
//                erase(A.charAt(L));
//                L++;
//            }
            while (kind > N) {
                erase(A.charAt(L++));
            }

            // 정답 갱신
            ans = Math.max(ans, R - L + 1);
        }
    }

    static void print() {
        System.out.println(ans);
    }

}

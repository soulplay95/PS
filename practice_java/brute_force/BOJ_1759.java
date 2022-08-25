package practice.bruteforce;

/**
 * @문제 암호 만들기 [G5]
 * @날짜 220825
 * @분류 수학 / 완탐 / 조합론 / 백트래킹
 * @조건 # 3 <= L <= C <= 15
 * @풀이 # 조합
 * - 주어지는 알파벳 소문자들을 사전순으로 정렬하고 L개를 뽑는다.
 * - 모음, 자음 개수가 각각 1개, 2개 이상이 아니면 백트래킹한다.
 * @복잡도 # 정답의 최대치: Integer
 * # 시간 복잡도: O(CCL)
 * # 공간 복잡도:
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1759 {

    static StringBuilder sb = new StringBuilder();

    static int L, C;
    static char[] candidates, selected;

    static final String VOWELS = "aeiou";

    public static void main(String[] args) throws IOException {
        init();
        solve();
        print();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        candidates = new char[C];
        selected = new char[L];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < C; i++) {
            candidates[i] = st.nextToken().charAt(0);
        }
    }

    static void solve() {
        Arrays.sort(candidates); // 사전순 오름차순 정렬

        dfs(0, 0, 0);
    }

    static void dfs(int depth, int start, int vowelCounts) { // vowelCounts: 모음 개수
        if (depth == L) {
            if (vowelCounts >= 1 && (L - vowelCounts) >= 2) {
                sb.append(String.valueOf(selected)).append("\n");
            }
            return;
        }

        for (int candidateIndex = start; candidateIndex < C; candidateIndex++) {
            selected[depth] = candidates[candidateIndex];

            if (VOWELS.contains(String.valueOf(selected[depth]))) {
                dfs(depth + 1, candidateIndex + 1, vowelCounts + 1);
            } else {
                dfs(depth + 1, candidateIndex + 1, vowelCounts);
            }
        }
    }

    static void print() {
        System.out.println(sb.toString());
    }

}

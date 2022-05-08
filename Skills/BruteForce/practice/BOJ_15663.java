package algorithm.brute_force.practice;

/**
 * @문제 N과 M (9) [S2]
 * @날짜 220509
 * @분류 백트래킹
 * @조건
 * # 1 <= M <= N <= 8
 * @풀이
 * # 순열
 * - 같은 depth에서 전에 뽑은 숫자를 기억해놓고 다음에 같은 수를 뽑지 않는다.
 * @comments
 * # 정답의 최대치: Integer
 * # 시간 복잡도:
 * # 공간 복잡도:
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_15663 {

    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static int[] numbers, selected;
    static boolean[] used;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        print();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        // init
        numbers = new int[N + 1];
        selected = new int[M + 1];
        used = new boolean[N + 1];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void recursive(int depth) {
        if (depth == M + 1) {
            // selected 출력
            for (int i = 1; i <= M; i++) {
                sb.append(selected[i]).append(" ");
            }
            sb.append("\n");
        } else {
            int lastUsedNumber = 0; // 마지막에 사용된 값
            // 모든 후보들을 체크한다.
            // 이미 사용된 원소 제외 + 전에 사용된 값과 같은 값을 가진 원소 제외
            for (int candidateIndex = 1; candidateIndex <= N; candidateIndex++) {
                if (used[candidateIndex] || numbers[candidateIndex] == lastUsedNumber) {
                    continue;
                }

                used[candidateIndex] = true;
                selected[depth] = numbers[candidateIndex];

                lastUsedNumber = numbers[candidateIndex]; // 최근에 사용된 값 갱신
                recursive(depth + 1);

                used[candidateIndex] = false;
                selected[depth] = 0;
            }
        }
    }

    static void solve() {
        Arrays.sort(numbers, 1, N + 1);

        recursive(1);
    }

    static void print() {
        System.out.println(sb.toString());
    }

}

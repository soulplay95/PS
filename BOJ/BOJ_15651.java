/**
 * @문제 N과 M (3)_S3
 * @날짜 220222
 * @분류 백트래킹
 * @조건
 * # 1 <= M <= N <= 7
 * @풀이
 * # 중복 순열
 * # 백트래킹
 * @comments
 * # 시간 복잡도: O(N^M)
 * # 공간 복잡도: O(M)
 * # 정답의 최대치: 7^7 ~= 약 80만
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15651 {

    // IO Handlers
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st = null;
    private static StringBuilder sb = new StringBuilder();

    static int N, M;
    static int[] selected;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        print();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        // init
        selected = new int[M + 1];
    }

    static void solve() {
        recursiveFunc(1); // 1번째 원소부터 M번째 원소를 조건에 맞는 모든 방법을 찾아줘
    }

    static void recursiveFunc(int k) {
        if (k == M + 1) {
            // selected 배열에 저장된 결과 출력
            for (int i = 1; i <= M; i++) {
                sb.append(selected[i]).append(' ');
            }
            sb.append('\n');
        } else {
            for (int candidate = 1; candidate <= N; candidate++) {
                selected[k] = candidate; // k번째 원소에 값을 넣는다.
                // k + 1 ~ M 번을 모두 탐색하는 일을 해야 하는 상황
                recursiveFunc(k + 1);
                selected[k] = 0;
            }
        }
    }

    static void print() {
        System.out.println(sb.toString());
    }

}

package algorithm.brute_force;

/**
 * @문제 N과 M (4) [S3]
 * @날짜 220507
 * @분류 뱩트래킹
 * @조건
 * # 1 <= M <= N <= 8
 * @풀이
 * # 중복 조합 : 중복을 허용해서 고르기
 * - 첫 번째 숫자는 1 ~ N의 숫자가 모두 올 수 있다.
 * - 두 번째 숫자는 첫번째 숫자부터 뽑아야 한다.
 * - 세 번재 숫자는 두번째 숫자부터 뽑아야 한다.
 * @comments
 * # 정답의 최대치: Integer => 최대 N 이라는 숫자가 등장 <= 8
 * # 시간 복잡도: O(< N^M) => 8^8 ~= 1677만보다 작다.
 * # 공간 복잡도: O(M)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15652_중복조합 {

    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static int[] selected;

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
        selected = new int[M + 1];
    }

    static void solve() {
        recursiveFunc(1, 1);
    }

    static void recursiveFunc(int k, int start) {
        if (k == M + 1) { // M개를 전부 고름 => 조건에 맞는 탐색을 한 가지 성공!
            // selected 배열에 저장된 결과 출력
            for (int i = 1; i <= M; i++) {
                sb.append(selected[i]).append(' ');
            }
            sb.append('\n');
        } else {
            // 현재 k번째 숫자는 start부터 뽑기
            for (int candidate = start; candidate <= N; candidate++) {
                selected[k] = candidate;
                recursiveFunc(k + 1, candidate); // 현재 뽑은 수를 다음 start로 넘겨줌
                selected[k] = 0;
            }
        }
    }

    static void print() {
        System.out.println(sb.toString());
    }

}

package algorithm.brute_force;

/**
 * @문제 N과 M (1) [S3]
 * @날짜 220507
 * @분류 뱩트래킹
 * @조건
 * # 1 <= M <= N <= 8
 * @풀이
 * # 순열 : 중복없이 순서 있게 나열하기
 * - 첫번째 칸에 N개의 숫자가 모두 올 수 있다.
 * - 두번째 칸에는 앞에 쓰인 숫자를 제외한 N - 1개의 숫자가 올 수 있다.
 * - 세번째 칸에는 앞에 쓰인 2개의 숫자를 제외한 N - 2개의 숫자가 올 수 있다.
 * - ...
 * @comments
 * # 정답의 최대치: Integer => 최대 N 이라는 숫자가 등장 <= 8
 * # 시간 복잡도: O(NPM) = O(N! / (N - M)!) => 8!/0! = 40,320
 * # 공간 복잡도: O(M)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15649_순열 {

    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static int[] selected;
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
        selected = new int[M + 1];
        used = new boolean[N + 1];
    }

    static void solve() {
        recursiveFunc(1); // 1번째 원소부터 M번째 원소를 조건에 맞는 모든 방법을 찾아줘
    }

    // 재귀 함수
    static void recursiveFunc(int k) {
        if (k == M + 1) { // M개를 전부 고름 => 조건에 맞는 탐색을 한 가지 성공!
            // selected 배열에 저장된 결과 출력
            for (int i = 1; i <= M; i++) {
                sb.append(selected[i]).append(' ');
            }
        } else {
            // 1 ~ N 까지 보는데, 이미 앞에서 사용되었으면 continue
            for (int candidate = 1; candidate <= N; candidate++) {
                if (used[candidate]) continue;

                // k번째에 candidate가 올 수 있으면
                selected[k] = candidate;
                used[candidate] = true;

                recursiveFunc(k + 1);

                selected[k] = 0;
                used[candidate] = false;
            }
        }
    }

    static void print() {
        System.out.println(sb.toString());
    }

}

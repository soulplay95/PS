package algorithm.brute_force;

/**
 * @문제 N과 M (3) [S3]
 * @날짜 220507
 * @분류 뱩트래킹
 * @조건
 * # 1 <= M <= N <= 7
 * @풀이
 * # 중복 순열 : 중복을 허용해서 순서 있게 나열하기
 * - 각 칸에 1부터 N까지 올 수 있다.
 * @comments
 * # 정답의 최대치: Integer => 최대 N 이라는 숫자가 등장 <= 7
 * # 시간 복잡도: O(N^M) => 7^7 ~= 82만
 * # 공간 복잡도: O(M)
 */

import java.io.*;
import java.util.*;

public class BOJ_15651_중복순열 {

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
            // 1 ~ N 까지를 k번 원소로 한 번씩 정하고,
            // 매번 k + 1번 부터 M번 원소로 재귀호출 해주기
            for (int candidate = 1; candidate <= N; candidate++) {
                selected[k] = candidate; // k번째 원소 자리에 값을 넣는다.
                // (k + 1) ~ M번을 모두 탐색하는 일을 해야 하는 상황
                recursiveFunc(k + 1);
                selected[k] = 0; // 값 삭제 처리
            }
        }
    }

    static void print() {
        System.out.println(sb.toString());
    }

}

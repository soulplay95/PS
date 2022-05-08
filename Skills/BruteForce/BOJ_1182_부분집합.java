package algorithm.brute_force;

/**
 * @문제 부분수열의 합 [S2]
 * @날짜 220508
 * @분류 완전 탐색 / 백트래킹
 * @조건
 * # 1 <= N <= 20
 * # |S| <= 100만
 * # |Ai| <= 10만
 * @풀이
 * # 부분 수열의 개수 : 2^n
 * # 부분 집합
 * - 중복 없이 뽑는다. 조합 => 1 ~ N개
 * - 1번 원소를 부분 수열에 포함시킨다 or 포함시키지 않는다.
 * - ...
 * @comments
 * # 정답의 최대치: Integer => 부분 수열 개수 상한 : 2^20
 * # 시간 복잡도: O(nC1 + nC2 + ... + nCn)
 * # 공간 복잡도:
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1182_부분집합 {

    static StringBuilder sb = new StringBuilder();

    static int N, S, ans;
    static int[] nums;

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
        S = Integer.parseInt(st.nextToken());
        // init
        ans = 0;
        nums = new int[N + 1];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        recursiveFunc(1, 0);

        if (S == 0) {
            ans--;
        }
    }

    static void recursiveFunc(int depth, int sum) {
        if (depth == N + 1) {
            if (sum == S) {
                ans++;
            }
        } else {
            recursiveFunc(depth + 1, sum + nums[depth]); // 뽑는다.
            recursiveFunc(depth + 1, sum); // 뽑지 않는다.
        }
    }

    static void print() {
        System.out.println(ans);
    }

}

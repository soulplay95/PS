/**
 * @문제 부분수열의 합_S2
 * @날짜 220222
 * @분류
 * @조건
 * # 1 <= 정수의 개수 (N) <= 20
 * # |수의 합 (S)| <= 100만
 * @풀이
 * # 중복 없이 뽑는다. 조합 => 1 ~ N개
 * @comments
 * # 시간 복잡도: O(nC1 + nC2 + ... + nCn)
 * # 공간 복잡도: O(N)
 * # 정답의 최대치:
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1182 {

    static StringBuilder sb = new StringBuilder();

    static int N, S, ans;
    static int[] num, selected;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        print();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        // init
        ans = 0;
        selected = new int[N + 1];
        num = new int[N + 1];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        // nC1, nC2, ..., nCn
        for (int size = 1; size <= N; size++) {
            nCr(size, 1, 1, 0);
        }
    }

    static void nCr(int size, int depth, int start, int sum) {
        if (depth == size + 1) {
            if (sum == S) {
                ans++;
            }
        } else {
            for (int i = start; i <= N; i++) {
                selected[depth] = num[i];
                nCr(size, depth + 1, i + 1, sum + num[i]);
                selected[depth] = 0;
            }
        }
    }

    static void print() {
        System.out.println(ans);
    }

}

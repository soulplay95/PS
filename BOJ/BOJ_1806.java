/**
 * @문제 부분합_G4
 * @날짜 220302
 * @분류 두 포인터
 * @조건
 * # 10 <= 수열 길이 (N) < 10만
 * # 1 <= 수열의 각 숫자 <= 1만
 * # 0 < 부분합 (S) <= 1억
 * @풀이
 * # 두 포인터
 * - L이 증가함에 따라 R은 기존 위치(전 단계에서 멈춘 위치)부터 시작하여 계산한다.
 * @comments
 * # 정답의 최대치: Integer
 * - 정답은 N이하
 * - 수열의 모든 수의 합 <= 10^9
 * # 시간 복잡도: O(N)
 * # 공간 복잡도: O(N)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1806 {

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
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        // init
        nums = new int[N + 1];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        int R = 0, sum = 0;
        ans = N + 1;

        for (int L = 1; L <= N; L++) {
            // L - 1을 구간에서 제외하기
            sum -= nums[L - 1];

            // R을 옮길 수 있을 때 까지 옮기기
            while (R + 1 <= N && sum < S) {
                R++;
                sum += nums[R];
            }

            // [L...R]의 합, 즉 sum이 조건을 만족하면 정답 갱신하기
            if (sum >= S) {
                ans = Math.min(ans, R - L + 1);
            }
        }

        // ans 값이 한번도 갱신이 안되었다면 불가능
        if (ans == N + 1) {
            ans = 0;
        }
    }

    static void print() {
        System.out.println(ans);
    }

}

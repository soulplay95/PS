package algorithm.two_pointers.practice;

/**
 * @문제 수열 [S3]
 * @날짜 220531
 * @분류 누적 합 / 두 포인터 / 슬라이딩 윈도우
 * @조건
 * 2 <= N <= 10만
 * -100 <= 온도 <= 100
 * @풀이
 * # 두 포인터
 * - L: 1 ~ (N - K + 1)
 * - R: K ~ N
 * - 범위에서 하나씩 오른쪽을 이동하면서 구간의 합을 구한다.
 * @comments
 * # 정답의 최대치: Integer => 100(max 온도) * 100000(max N) = 1천만
 * # 시간 복잡도: O(N)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_2559 {

    static StringBuilder sb = new StringBuilder();

    static int N, K;
    static int[] temperatures;

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        // init
        temperatures = new int[N + 1];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            temperatures[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        int L = 1, R = K;
        int sum = 0;

        // 초기 누적 합 구하기
        for (int i = L; i <= R; i++) {
            sum += temperatures[i];
        }

        // 다른 구간의 누적합 구하기
        int ans = sum;
        while (R + 1 <= N) {
            sum -= temperatures[L++];
            sum += temperatures[++R];
            ans = Math.max(ans, sum);
        }

        // print
        System.out.println(ans);
    }

}

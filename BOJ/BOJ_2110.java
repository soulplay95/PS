package algorithm.이분탐색.매개변수탐색;

/**
 * @문제 공유기 설치_S1
 * @날짜 210920
 * @분류 이분 탐색, 매개 변수 탐색
 * @조건
 * # 2 <= 집의 개수(N) <= 20만
 * # 2 <= 공유기의 개수(C) <= N
 * # 0 <= 집의 좌표(x) <= 10억
 * @풀이
 * # 매개 변수 탐색
 * @comment
 * # 매개 변수 탐색 기본 문제
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2110 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, C;
    static int[] input;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        // init
        input = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            input[i] = Integer.parseInt(br.readLine());
        }

        // print
        System.out.println(solve());
    }

    static int solve() {
        // determination을 빠르게 하기 위해 정렬
        Arrays.sort(input);

        int L = 1, R = 1000000000, ans = 0;
        while (L <= R) {
            int mid = (L + R) / 2;
            if (determination(mid)) {
                ans = mid;
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }

        return ans;
    }

    static boolean determination(int D) {
        // D 만큼의 거리 차이를 둔다면 C개 만큼의 공유기를 설치할 수 있는가?

        // 제일 왼쪽 집부터 가능한 많이 설치해보자
        // D 만큼의 거리를 두면서 최대로 설치한 개수와 C를 비교하자
        int cnt = 1, last = input[1]; // 설치한 개수, 마지막에 설치한 집의 좌표
        for (int i = 2; i <= N; i++) {
            // 이번에 input[i]에 설치가 가능한가?
            if (input[i] - last >= D) {
                cnt++;
                last = input[i];
            }
        }

        return cnt >= C;
    }

}

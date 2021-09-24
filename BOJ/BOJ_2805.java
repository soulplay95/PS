package algorithm.이분탐색.매개변수탐색;

/**
 * @문제 나무 자르기_S3
 * @날짜 210920
 * @분류 이분 탐색
 * @조건
 * # 1 <= 나무 개수(N) <= 100만
 * # 1 <= 필요한 나무의 길이(M) <= 20억
 * # 0 <= 각 나무의 높이 <= 10억
 * @풀이
 * # 매개 변수 탐색
 * # 문제를 결정 문제로 뒤집는다.
 * @comment
 * # 매개 변수 탐색 기본 문제
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2805 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static int[] input;

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        // init
        input = new int[N + 1];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        // print
        System.out.println(solve());
    }

    static long solve() {
        long L = 0L, R = 2000000000L, ans = 0L;
        // [L...R] 범위 안에 정답이 존재
        while (L <= R) {
            long mid = (L + R) / 2;
            if (determination((int) mid)) {
                ans = mid;
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }

        return ans;
    }

    static boolean determination(int H) {
        // H 높이로 나무들을 잘랐을 때, M 만큼을 얻을 수 있으면 true, 없으면 false를 return하는 함수
        long sum = 0L;
        for (int i = 1; i <= N; i++) {
            if (input[i] > H) {
                sum += input[i] - H;
            }
        }

        return sum >= M;
    }

}

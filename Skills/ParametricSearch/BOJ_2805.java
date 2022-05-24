package algorithm.parametric_search;

/**
 * @문제 나무 자르기 [S3]
 * @날짜 220525
 * @분류 이분 탐색 / 매개 변수 탐색
 * @조건
 * 1 <= N <= 100만
 * 1 <= M <= 20억
 * 0 <= 나무 높이 <= 10억
 * @풀이
 * # 0 ~ 10억(또는 입력 높이 중 최대 높이)의 범위에서 높이 하나를 정하고 잘랐을 때 M 이상인지 구한다.
 * @comments
 * # 정답의 최대치: Long => 계산 과정 중 잘린 나무의 길이 합을 구할때 max 100만 * 10억
 * # 시간 복잡도: O(Nlog10억)
 * # 공간 복잡도:
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2805 {

    static StringBuilder sb = new StringBuilder();

    static int N, M, ans;
    static int[] heights;

    static final int MAX_HEIGHT = 1000000000;

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
        heights = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        int L = 0, R = MAX_HEIGHT;
        ans = 0;
        while (L <= R) {
            int M = (L + R) / 2;
            if (determination(M)) {
                ans = M;
                L = M + 1;
            } else {
                R = M - 1;
            }
        }
    }

    static boolean determination(int H) {
        long sum = 0L;
        for (int i = 0; i < N; i++) {
            if (heights[i] > H) {
                sum += heights[i] - H;
            }
        }

        return sum >= M;
    }

    static void print() {
        System.out.println(ans);
    }

}

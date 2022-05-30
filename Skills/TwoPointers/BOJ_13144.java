package algorithm.two_pointers;

/**
 * @문제 List of Unique Numbers [G4]
 * @날짜 220530
 * @분류 두 포인터
 * @조건
 * 1 <= N <= 10만
 * 1 <= 원소 <= 10만
 * @풀이
 * # 투 포인터
 * - 원소의 범위가 1 ~ 10만인 것을 알고 있으므로, count 배열을 통해 현재 구간에서 해당 숫자가 사용되었는지 체크한다.
 * - L: 1 ~ N에서 시작
 * - R: 이전 R 위치에서 시작 => 기존 L~R 구간에서 중복이 없었으면 L+1 ~ R 구간에서도 중복이 없으므로
 * @comments
 * # 정답의 최대치: Long
 * # 시간 복잡도: O(N)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_13144 {

    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[] numbers, count;

    static final int MAX = 100000;

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        // init
        numbers = new int[N + 1];
        count = new int[MAX + 1];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        long ans = 0L;

        for (int L = 1, R = 0; L <= N; L++) {
            // R을 옮길 수 있을 만큼 옮긴다.
            while (R + 1 <= N && count[numbers[R + 1]] == 0) {
                R++;
                count[numbers[R]]++;
            }

            // 정답 갱신
            ans += R - L + 1;

            // L을 옮기기 전에 numbers[L]의 개수를 감소
            count[numbers[L]]--;
        }

        System.out.println(ans);
    }

}

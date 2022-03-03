/**
 * @문제 List of Unique Numbers_G4
 * @날짜 220303
 * @분류 두 포인터
 * @조건
 * # 1 <= 수열 길이 (N) <= 10만
 * # 1 <= 각 숫자 <= 10만
 * @풀이
 * # 두 포인터
 * - 왼쪽 끝에서부터 L, R 시작
 * - 카운팅 배열을 이용해 R을 이동할 수 있는데 까지 이동
 * - L 한칸 이동 (기존 값의 counts 값 감소)
 * - R은 기존 위치에서 시작
 * @comments
 * # 정답의 최대치: Long
 * - 모든 연속 구간이 정답에 카운트 될 경우: 10만 길이의 수열에 1~10만 까지 들어가 있을 때
 * - N + (N - 1) + ... + 2 + 1 ~= 50억
 * # 시간 복잡도: O(N)
 * - L과 R이 각각 최대 N번 이동하므로, O(N)
 * # 공간 복잡도: O(N)
 */

import java.io.*;
import java.util.*;

public class BOJ_13144 {

    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[] A, counts;
    static long ans;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        print();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        // init
        A = new int[N + 1];
        counts = new int[N + 1];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        ans = 0L;

        for (int L = 1, R = 0; L <= N; L++) {
            // R을 옮길 수 있을 때 까지 옮겨준다.
            while (R + 1 <= N && counts[A[R + 1]] == 0) {
                counts[A[++R]]++;
            }

            // 정답 갱신
            ans += R - L + 1;

            // L을 한 칸 이동하기 전에 기존 L에 있는 counts 값을 감소
            counts[A[L]]--;
        }
    }

    static void print() {
        System.out.println(ans);
    }

}

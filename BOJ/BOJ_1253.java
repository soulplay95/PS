/**
 * @문제 좋다_G4
 * @날짜 220303
 * @분류 자료 구조 / 정렬 / 이분 탐색 / 해시 / 두 포인터
 * @조건
 * # 1 <= 수의 개수 (N) <= 2000
 * # -10억 <= 각 숫자 (Ai) <= 10억
 * @풀이
 * # 두 포인터
 * - 수열을 오름차순 정렬 한다. => O(NlogN)
 * - 타겟 수 하나를 고른다. => O(N)
 * - 타겟을 제외한 다른 두 숫자의 합이 타겟이 되는지 확인한다. => O(N)
 * -- L과 R을 양쪽 끝에 놓고 더했을 때 타겟 보다 크면, R 입장(최대값)에서 L(최소값) 제외 다른 어떠한 수랑 더해도 타겟보다 클 것이므로 R 감소
 * -- 타겟 보다 작으면, L 증가
 * @comments
 * # 정답의 최대치: Integer
 * - 정답은 원소의 개수 N(<= 2000)보다 작거나 같을 수 밖에 없으므로
 * - 원소 두 개의 합도 최대 2*10^9 이므로 Integer 범위
 * # 시간 복잡도: O(N^2)
 * # 공간 복잡도: O(N)
 */

import java.io.*;
import java.util.*;

public class BOJ_1253 {

    static StringBuilder sb = new StringBuilder();

    static int N, ans;
    static int[] A;

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

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        // 최소, 최대 값을 빠르게 알기 위한 정렬
        Arrays.sort(A, 1, N + 1);

        ans = 0;
        for (int i = 1; i <= N; i++) {
            // i번째 원소가 서로 다른 두 수의 합으로 표현이 되는가?
            if (isGood(i)) {
                ans++;
            }
        }
    }

    static boolean isGood(int target_idx) {
        int L = 1, R = N;
        int target = A[target_idx];
        while (L < R) {
            // target 값을 제외
            if (L == target_idx) {
                L++;
            } else if (R == target_idx) {
                R--;
            } else {
                if (A[L] + A[R] == target) {
                    return true;
                }
                if (A[L] + A[R] > target) {
                    R--;
                } else {
                    L++;
                }
            }
        }

        return false;
    }

    static void print() {
        System.out.println(ans);
    }

}

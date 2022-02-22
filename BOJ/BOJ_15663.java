/**
 * @문제 N과 M (9)_S2
 * @날짜 220222
 * @분류 백트래킹
 * @조건
 * # 1 <= M <= N <= 8
 * @풀이
 * # 순열
 * - 같은 depth에서 마지막에 고른 숫자를 기억해두고 같은 숫자이면 제외
 * @comments
 * # 시간 복잡도: O(N^M) ~= 8^8
 * # 공간 복잡도: O(M)
 * # 정답의 최대치:
 */

import java.io.*;
import java.util.*;

public class BOJ_15663 {

    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static int[] nums, selected;
    static boolean[] used;

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
        M = Integer.parseInt(st.nextToken());
        // init
        nums = new int[N + 1];
        selected = new int[M + 1];
        used = new boolean[N + 1];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        Arrays.sort(nums, 1, N + 1); // 인덱스 1~N까지 오름차순 정렬

        recursive(1);
    }

    static void recursive(int depth) {
        if (depth == M + 1) {
            for (int i = 1; i <= M; i++) {
                sb.append(selected[i]).append(' ');
            }
            sb.append('\n');
        } else {
            int previousSelectedNum = 0; // 각 depth마다 초기화
            for (int candidate = 1; candidate <= N; candidate++) {
                if (used[candidate] || nums[candidate] == previousSelectedNum) {
                    continue;
                }

                selected[depth] = nums[candidate];
                used[candidate] = true;
                previousSelectedNum = nums[candidate];
                recursive(depth + 1);
                used[candidate] = false;
            }
        }
    }

    static void print() {
        System.out.println(sb.toString());
    }

}

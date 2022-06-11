package algorithm.tree.practice;

/**
 * @문제 사촌 [G4]
 * @날짜 220611
 * @분류 트리
 * @조건
 * 1 <= n <= 1천
 * 1 <= k <= 100만
 * @풀이
 * - 규칙에 따라 트리를 구성한다. parent 배열 채우기 => O(n)
 * - k의 부모의 부모를 부모로 둔 노드들을 구한다 => O(n)
 * - 이 노드들을 부모로 둔 자식 노드의 개수를 구한다. => O(n^2)
 * @comments
 * # 정답의 최대치: Integer => max n
 * # 시간 복잡도: O(n^2)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_9489 {

    static StringBuilder sb = new StringBuilder();

    static int n, k, ans;
    static int[] input, parent;

    public static void main(String[] args) throws IOException {
        input();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            if (n == 0 && k == 0) {
                break;
            }

            // init
            ans = 0;
            input = new int[n + 1];
            parent = new int[n + 1];

            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 1; i <= n; i++) {
                input[i] = Integer.parseInt(st.nextToken());
            }

            solve();

            // append
            sb.append(ans).append('\n');
        }

        // print
        System.out.println(sb);
    }

    static void solve() {
        // 1. 트리를 구성한다.
        int rootIndex = 0;
        for (int i = 2; i <= n; i++) {
            if (input[i - 1] + 1 == input[i]) { // 연속된 수이면
                parent[i] = rootIndex;
            } else {
                parent[i] = ++rootIndex;
            }
        }

        int kIndex = 0;
        for (int i = 1; i <= n; i++) {
            if (input[i] == k) {
                kIndex = i;
                break;
            }
        }

        // k가 루트 노드 이거나 루트의 자식일 때
        if (parent[kIndex] == 0 || parent[kIndex] == 1) {
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (parent[parent[i]] == parent[parent[kIndex]] && parent[i] != parent[kIndex]) {
                ans++;
            }
        }
    }

}

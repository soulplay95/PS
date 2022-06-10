package algorithm.tree.practice;

/**
 * @문제 이진 검색 트리 [G5]
 * @날짜 220610
 * @분류 그래프 탐색 / 트리 / 재귀
 * @조건
 * 1 <= 노드 수 <= 1만
 * 1 <= key 값 <= 10^6
 * @풀이
 * - 전위 순회 결과에서 연속된 부분 수열을 루트, 왼쪽 서브트리, 오른쪽 서브트리로 자른다.
 * -- 제일 왼쪽은 루트
 * -- 제일 왼쪽에서 다음 노드의 key 값 기준 처음으로 큰 key 값을 가진 노드 까지가 왼쪽 서브트리
 * -- 나머지 오른쪽 서브트리
 * - 재귀로 후위 순회 출력
 * @comments
 * # 정답의 최대치:
 * # 시간 복잡도:
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_5639 {

    static StringBuilder sb = new StringBuilder();

    static ArrayList<Integer> preOrderTraversal;

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // init
        preOrderTraversal = new ArrayList<>();

        while (true) {
            String key = br.readLine();
            if (key == null || key.isEmpty()) {
                break;
            }

            preOrderTraversal.add(Integer.parseInt(key));
        }
    }

    static void solve() {
        postOrderTraversal(0, preOrderTraversal.size() - 1);

        // print
        System.out.println(sb);
    }

    // preOrderTraversal 에서 L ~ R의 인덱스 구간을 가진 서브트리를 후위 순회한다.
    static void postOrderTraversal(int L, int R) {
        if (L > R) return;

        // L + 1 ~ R 구간에서 왼쪽 서브트리, 오른쪽 서브트리를 나누는 기준(mid)를 찾는다.
        int mid = R;
        for (int i = L + 1; i <= R; i++) {
            if (preOrderTraversal.get(i) > preOrderTraversal.get(L)) {
                mid = i - 1;
                break;
            }
        }

        // 후위 순회
        postOrderTraversal(L + 1, mid);
        postOrderTraversal(mid + 1, R);
        sb.append(preOrderTraversal.get(L)).append('\n');
    }

}

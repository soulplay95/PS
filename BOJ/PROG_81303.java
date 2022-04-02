package study.w220404; /**
 * @문제
 * @날짜 220401
 * @분류
 * @조건
 * #
 * @풀이
 * #
 * @comments
 * # 정답의 최대치:
 * # 시간 복잡도:
 * # 공간 복잡도:
 */

import java.util.*;

public class PROG_81303 {

    static class Node {
        int prev, cur, next;

        Node(int _prev, int _cur, int _next) {
            prev = _prev;
            cur = _cur;
            next = _next;
        }
    }

    // 이중 연결 리스트의 개념을 활용하여 특정 index의 앞, 뒤 index를 저장하는 배열을 만든다. => prev[], next[]
    // 이동: prev, next 배열을 통해 정확히 X번만 이동한다.
    // 삭제: 현재 index의 prev, next 값을 수정하고 스택에 넣는다.
    // 복구: 스택에서 pop하여 prev, next 값을 수정한다.
    // O(n + sum(X))
    public String solution(int n, int k, String[] cmd) {
        // init
        int[] prev = new int[n];
        int[] next = new int[n];
        Stack<Node> deletedNodes = new Stack<>();
        // prev, next 배열 초기화
        for (int cur = 0; cur < n; cur++) { // O(n)
            prev[cur] = cur - 1;
            next[cur] = cur + 1;
        }

        // 명령 순차 실행
        int x = 0;
        for (String command : cmd) {
            String[] split = command.split(" ");

            switch (split[0]) {
                case "U":
                    x = Integer.parseInt(split[1]);
                    while (x-- > 0) { // x번 이동
                        k = prev[k]; // 예외 처리x => 범위를 벗어나는 이동은 입력으로 주어지지 않음.
                    }
                    break;
                case "D":
                    x = Integer.parseInt(split[1]);
                    while (x-- > 0) {
                        k = next[k];
                    }
                    break;
                case "C":
                    deletedNodes.push(new Node(prev[k], k, next[k])); // stack에 저장
                    // prev, next 수정
                    if (prev[k] != -1) { // 첫번째 행이 아니면(이전 행이 존재하면)
                        next[prev[k]] = next[k]; // 이전 행의 next를 next[k]로 변경한다.
                    }
                    if (next[k] != n) { // 마지막 행이 아니면(다음 행이 존재하면)
                        prev[next[k]] = prev[k]; // 다음 행의 prev를 prev[k]로 변경한다.
                    }

                    // k 이동
                    if (next[k] == n) { // 마지막행이면
                        k = prev[k];
                    } else {
                        k = next[k];
                    }
                    break;
                case "Z":
                    // prev, next 수정
                    Node restoreNode = deletedNodes.pop();
                    if (restoreNode.prev != -1) {
                        next[restoreNode.prev] = restoreNode.cur;
                    }
                    if (restoreNode.next != n) {
                        prev[restoreNode.next] = restoreNode.cur;
                    }
                    break;
            }
        }

        // 정답 세팅
        StringBuilder sb = new StringBuilder("O".repeat(n));
        while (!deletedNodes.isEmpty()) {
            sb.setCharAt(deletedNodes.pop().cur, 'X');
        }

        return sb.toString();
    }

}

/**
 * @문제 크레인 인형뽑기 게임
 * @날짜 220316
 * @분류 구현
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

class PROG_64061 {
    /**
     * board의 각 열을 Stack으로 관리한다.
     * 바구니 또한 Stack으로 관리.
     */
    public int solution(int[][] board, int[] moves) {
        int boardSize = board.length;
        Stack<Integer>[] dolls = new Stack[boardSize];
        Stack<Integer> bucket = new Stack<>();
        int answer = 0;

        // board 배열을 왼쪽 열에서 시작하여 아래 행부터 보면서 Stack을 채운다.
        for (int c = 0; c < boardSize; c++) {
            dolls[c] = new Stack<>();

            for (int r = boardSize - 1; r >= 0; r--) {
                int num = board[r][c];

                if (num == 0) {
                    break;
                }

                dolls[c].push(num);
            }
        }

        // 크레인 작동
        for (int c : moves) {
            c--;
            if (dolls[c].isEmpty()) {
                continue;
            }

            int num = dolls[c].pop();
            if (!bucket.isEmpty() && bucket.peek() == num) { // 같은 인형이면
                bucket.pop();
                answer += 2;
            } else { // 비어있거나, 다른 인형이면
                bucket.push(num);
            }
        }

        return answer;
    }
}
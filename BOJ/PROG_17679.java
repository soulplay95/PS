/**
 * @문제
 * @날짜 220412
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

public class PROG_17679 {

    static int R, C, answer;
    static char[][] map;
    static boolean[][] deleted;

    static int[] dr = {0, 0, 1, 1};
    static int[] dc = {0, 1, 1, 0};

    public static void main(String[] args) {
        System.out.println(solution(
                4,
                5,
                new String[] {"CCBDE", "AAADE", "AAABF", "CCBBF"}
        ));
    }

    static boolean isValidate(int r, int c) {
        return (map[r][c] == map[r + 1][c] && map[r][c] == map[r][c + 1] && map[r][c] == map[r + 1][c + 1]);
    }

    static void drop() {
        // deleted를 기반으로 중력을 구현한다.
        // 빈칸은 'B'로 표시
        for (int c = 0; c < C; c++) { // 첫번째 행부터 시작
            // 맨 아래 행부터 시작하여 빈칸(dropPoint)이 나올때 까지 반복
            int r = R - 1;
            while (r >= 0 && !deleted[r][c]) {
                r--;
            }

            if (r < 0) {
                continue;
            }

            // 현재 dropPoint => r
            int dropPoint = r;
            map[r][c] = 'B';

            // r - 1부터 다시 비어있지 않은 칸이 나올때 까지 반복
            r--;
            while (r >= 0) {
                if (!deleted[r][c]) {
                    map[dropPoint--][c] = map[r][c]; // 이동
                    map[r][c] = 'B';
                }
                r--;
            }
        }
    }

    static int delete() {
        // init
        deleted = new boolean[R][C];
        int counts = 0;

        // 모든 칸에 대해 해당 칸을 2*2에서 좌상단으로 잡고 2*2 모양을 만족하는지 따져본다.
        for (int r = 0; r < R - 1; r++) {
            for (int c = 0; c < C - 1; c++) {
                if (map[r][c] == 'B') { // B: Blank. 비어있는 칸이면
                    continue;
                }
                if (!isValidate(r, c)) { // 유효한 2*2 모양이 아니면
                    continue;
                }

                // deleted 체크
                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if (!deleted[nr][nc]) {
                        deleted[nr][nc] = true;
                        counts++;
                    }
                }
            }
        }

        return counts;
    }

    public static int solution(int m, int n, String[] board) {
        // init
        R = m;
        C = n;
        map = new char[R][C];
        answer = 0; // 삭제된 블록 개수
        for (int r = 0; r < R; r++) {
            map[r] = board[r].toCharArray(); // board -> char[][] 으로 변환
        }

        while (true) {
            int deletedCounts = delete(); // 한 턴에 삭제된 블록 개수
            if (deletedCounts == 0) { // 더이상 삭제되지 않는다면 종료
                break;
            }

            drop();
            answer += deletedCounts;
        }

        return answer;
    }

}

package programmers.level2;

import java.util.*;

class PROG_86052 {

    static int R, C;
    static boolean[][][] visited; // visitied[r][c][d]: 격자의 (r, c) 칸에서 d방향으로 향하는 경로를 방문했냐

    static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1}; // 상우하좌 시계방향

    static final int DIRECTION_COUNTS = 4; // 방향 수

    // 빛을 어느 위치에서 어느 방향으로 쏴도 사이클이 생긴다.
    // 빛이 이동하는 경로를 각 칸에서 4방향 중 하나로 쐈을 때 그 조합으로 생각할 수 있다.
    // 따라서 아직 방문하지 않은 경로(visitied[r][c][d])가 있다면 중복되지 않는 새로운 사이클이 생김을 의미한다.
    public int[] solution(String[] grid) {
        // init
        ArrayList<Integer> answer = new ArrayList<Integer>();
        R = grid.length;
        C = grid[0].length();
        visited = new boolean[R][C][DIRECTION_COUNTS];

        // 아직 방문하지 않은 경로에서 새로운 사이클을 타면서 경로의 길이를 구한다.
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                for (int d = 0; d < DIRECTION_COUNTS; d++) {
                    if (!visited[r][c][d]) {
                        answer.add(getNewCyclePathLength(grid, r, c, d));
                    }
                }
            }
        }

        return answer.stream().sorted().mapToInt(i -> i).toArray();
    }

    static int getNewCyclePathLength(String[] grid, int r, int c, int d) {
        int length = 0;

        while (true) {
            if (visited[r][c][d]) { // 사이클이 끝나는 경우
                break;
            }

            // 방문 처리
            visited[r][c][d] = true;

            // 현재 칸(r, c)에 쓰여진 격자 방향에 따라 새로운 방향을 구한다.
            char directionMark = grid[r].charAt(c);
            if (directionMark == 'L') {
                d = (d - 1 + DIRECTION_COUNTS) % DIRECTION_COUNTS;
            } else if (directionMark == 'R') {
                d = (d + 1) % DIRECTION_COUNTS;
            }

            // 이동
            r = (r + dr[d] + R) % R;
            c = (c + dc[d] + C) % C;
            length++;
        }

        return length;
    }

}
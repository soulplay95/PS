package programmers.level2;

import java.util.*;

class PROG_1844 {

    static class Pair {
        int r, c;

        Pair(int _r, int _c) {
            r = _r;
            c = _c;
        }
    }

    static int R, C;

    static int[] dr = {-1, 0, 1, 0}; // 상우하좌 시계방향
    static int[] dc = {0, 1, 0, -1};

    static final int WALL = 0;
    static final int CAN_GO = 1;
    static final int VISITED = 2;

    // 1. (1, 1)에서 BFS 탐색한다.
    // 2. (N, M)에 도달하면 탐색을 종료한다.
    // 방문처리: map에 2로 마킹
    // O(V + E) = O(NM)
    public int solution(int[][] maps) {
        return bfs(maps);
    }

    static int bfs(int[][] map) {
        // init
        R = map.length;
        C = map[0].length;
        Pair end = new Pair(R - 1, C - 1); // 도착지
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(0, 0));
        int moves = 1; // 최초 칸 포함
        map[0][0] = VISITED;

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                Pair cur = q.poll();

                if (cur.r == end.r && cur.c == end.c) { // 도착 여부 확인
                    return moves;
                }

                for (int d = 0; d < 4; d++) {
                    int nr = cur.r + dr[d];
                    int nc = cur.c + dc[d];

                    if (isOut(nr, nc)) continue; // 경계 체크
                    if (map[nr][nc] == CAN_GO) {
                        map[nr][nc] = VISITED; // 방문 체크
                        q.offer(new Pair(nr, nc));
                    }
                }
            }

            moves++;
        }

        return -1; // 도달할 수 없는 경우
    }

    static boolean isOut(int r, int c) {
        return (r < 0 || r >= R || c < 0 || c >= C);
    }

}

/*
class Solution {
    static class Pair {
        int r, c;

        Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    // BFS
    public int solution(int[][] maps) {
        // init
        int R = maps.length;
        int C = maps[0].length;
        int[] dr = {-1, 0, 1, 0}; // 상우하좌 시계방향
        int[] dc = {0, 1, 0, -1};
        Queue<Pair> q = new LinkedList<>();
        int moves = 1;

        q.offer(new Pair(0, 0));
        maps[0][0] = 2; // 방문 표시

        while (!q.isEmpty()) {
            int size = q.size();

            // 한 싸이클씩 이동
            while (size-- > 0) {
                Pair cur = q.poll();

                // 종료 체크
                if (cur.r == R - 1 && cur.c == C - 1) {
                    return moves;
                }

                for (int d = 0; d < 4; d++) {
                    int nr = cur.r + dr[d];
                    int nc = cur.c + dc[d];

                    if (nr < 0 || nr >= R || nc < 0 || nc >= C) continue; // 경계 체크
                    if (maps[nr][nc] == 1) {
                        q.offer(new Pair(nr, nc));
                        maps[nr][nc] = 2;
                    }
                }
            }

            moves++;
        }

        return -1;
    }
}*/

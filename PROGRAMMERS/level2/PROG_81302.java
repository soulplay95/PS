package programmers.level2;

import java.util.*;

class PROG_81302 {

    static class Pair {
        int r, c;

        Pair(int _r, int _c) {
            r = _r;
            c = _c;
        }
    }

    static int[] dr = {-1, 0, 1, 0}; // 상우하좌 시계방향
    static int[] dc = {0, 1, 0, -1};

    static final int SIZE = 5;

    // 모든 응시자 위치에서 거리 2까지만 BFS 탐색한다.
    public int[] solution(String[][] places) {
        int[] answer = new int[SIZE];

        for (int i = 0; i < SIZE; i++) {
            answer[i] = isGood(places[i]);
        }

        return answer;
    }

    static int isGood(String[] map) {
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                if (map[r].charAt(c) == 'P') {
                    if (!bfs(r, c, map)) {
                        return 0;
                    }
                }
            }
        }

        return 1;
    }

    static boolean bfs(int r, int c, String[] map) {
        Queue<Pair> q = new LinkedList<>();
        boolean[][] visited = new boolean[SIZE][SIZE];
        q.offer(new Pair(r, c));
        visited[r][c] = true;
        int distance = 1;

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                Pair cur = q.poll();

                for (int d = 0; d < 4; d++) {
                    int nr = cur.r + dr[d];
                    int nc = cur.c + dc[d];

                    if (isOut(nr, nc)) continue;
                    if (map[nr].charAt(nc) == 'X') continue;
                    if (visited[nr][nc]) continue;

                    if (map[nr].charAt(nc) == 'P' && distance <= 2) {
                        return false;
                    }

                    q.offer(new Pair(nr, nc));
                    visited[nr][nc] = true;
                }
            }

            distance++;
        }

        return true;
    }

    static boolean isOut(int r, int c) {
        return (r < 0 || r >= SIZE || c < 0 || c >= SIZE);
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

    static char[][] map;

    static int[] dr = {-1, 0, 1, 0}; // 상우하좌 시계방향
    static int[] dc = {0, 1, 0, -1};

    static boolean isValidate(int r, int c) {
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(r, c));
        boolean[][] visited = new boolean[5][5];
        visited[r][c] = true;
        int distance = 1;

        while (!q.isEmpty()) {
            if (distance == 3) {
                return true;
            }

            int size = q.size();

            while (size-- > 0) {
                Pair cur = q.poll();

                for (int d = 0; d < 4; d++) {
                    int nr = cur.r + dr[d];
                    int nc = cur.c + dc[d];

                    if (nr < 0 || nr >= 5 || nc < 0 || nc >= 5) {
                        continue;
                    }
                    if (visited[nr][nc]) {
                        continue;
                    }

                    if (map[nr][nc] == 'P') { // 거리 내에 존재하면
                        return false;
                    }

                    if (map[nr][nc] == 'O') {
                        q.offer(new Pair(nr, nc));
                        visited[nr][nc] = true;
                    }
                }
            }

            distance++;
        }

        return true;
    }

    static int bfs() {
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                if (map[r][c] == 'P') {
                    if (!isValidate(r, c)) { // 거리두기가 지켜지지 않고 있으면
                        return 0;
                    }
                }
            }
        }

        return 1;
    }

    // 모든 P 위치에서 거리 2까지만 BFS 탐색한다.
    public int[] solution(String[][] places) {
        int[] answer = new int[5];

        for (int room = 0; room < 5; room++) {
            map = new char[5][5];

            // map 구성하기
            for (int r = 0; r < 5; r++) {
                map[r] = places[room][r].toCharArray();
            }

            // 모든 P 위치에서 BFS 탐색
            answer[room] = bfs();
        }

        return answer;
    }
}*/

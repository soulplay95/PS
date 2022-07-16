package programmers.level2;

class PROG_49994 {

    static final int MAX_R = 10;
    static final int MAX_C = 10;

    static int[] dr = {-1, 0, 1, 0}; // 상우하좌 시계방향
    static int[] dc = {0, 1, 0, -1};

    // 좌표 평면을 2차원 배열로 표현
    // 길의 표현: 좌표 평면 배열에 방향 차원 추가
    // O(dirs.length)
    public int solution(String dirs) {
        // init
        int answer = 0;
        boolean[][][] visited = new boolean[MAX_R + 1][MAX_C + 1][4]; // visited[r][c][d]: (r, c)위치로 d방향을 통해 방문했는지
        // 시작 좌표
        int r = MAX_R / 2;
        int c = MAX_C / 2;

        for (char dir : dirs.toCharArray()) {
            int d = 0; // dr, dc 배열에서의 index
            switch(dir) {
                case 'U':
                    d = 0;
                    break;
                case 'R':
                    d = 1;
                    break;
                case 'D':
                    d = 2;
                    break;
                case 'L':
                    d = 3;
                    break;
            }

            int nr = r + dr[d];
            int nc = c + dc[d];

            if (nr < 0 || nr > MAX_R || nc < 0 || nc > MAX_C) continue; // 경계 체크 - 무시
            if (!visited[nr][nc][d]) { // 방문 체크
                visited[nr][nc][d] = true; // 방문 표시
                visited[r][c][(d + 2) % 4] = true; // 역방향 방문 표시
                answer++;
            }

            // 이동
            r = nr;
            c = nc;
        }

        return answer;
    }
}

/*
class Solution {

    private static class Pair {
        int r, c;

        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    private static final int R = 10;
    private static final int C = 10;

    public int solution(String dirs) {
        int answer = 0;
        boolean[][][] visited = new boolean[R + 1][C + 1][4]; // [r][c][d] : (r, c) 좌표에 d 방향으로부터 온 길이 이미 거쳐간 길이면 true
        Pair cur = new Pair(R / 2, C / 2); // 현재 위치. 좌표 평면상에서 (0, 0), map에서 (5, 5)
        int[] dr = {-1, 0, 1, 0}; // 상우하좌 시계방향
        int[] dc = {0, 1, 0, -1};

        for (int i = 0, end = dirs.length(); i < end; i++) {
            char command = dirs.charAt(i);
            int d = 0; // 방향

            if (command == 'U') {
                d = 0;
            } else if (command == 'R') {
                d = 1;
            } else if (command == 'D') {
                d = 2;
            } else if (command == 'L') {
                d = 3;
            }

            // 이미 거쳐간 길인지 체크
            int nr = cur.r + dr[d];
            int nc = cur.c + dc[d];

            if (isOut(nr, nc)) continue; // 경계 체크
            if (!visited[nr][nc][d]) { // 거쳐간 길이 아니면
                answer++;
                visited[nr][nc][d] = true; // 방문 체크
                visited[cur.r][cur.c][(d + 2) % 4] = true; // 반대 방향의 길도 방문 체크
            }

            // 이동
            cur.r = nr;
            cur.c = nc;
        }

        return answer;
    }

    private static boolean isOut(int r, int c) {
        return (r < 0 || r > R || c < 0 || c > C);
    }

}*/

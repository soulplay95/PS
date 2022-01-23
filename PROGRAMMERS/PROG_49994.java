package study.w220124;

/**
 * @문제 방문 길이
 * @날짜 220123
 * @분류
 * @조건
 * # 명령어 개수 <= 500
 * @풀이
 * # visited[11][11][4] 로 이미 지나간 길인지 체크
 * @comment
 * #
 */

public class PROG_49994 {

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

}

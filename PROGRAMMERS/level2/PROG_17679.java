package programmers.level2;

class PROG_17679 {

    static int answer;
    static int R, C;
    static char[][] map;

    static int[] dr = {0, 0, 1, 1}; // * 우 하 우하
    static int[] dc = {0, 1, 0, 1};

    static final char BLANK = '*';

    // 1. pop(): 모든 칸에서 자기 칸을 좌상단으로 하여 2x2 영역을 체크
    // 2. rain(): dropPoint를 갱신하면서 중력 구현
    public int solution(int m, int n, String[] board) {
        // init
        R = m;
        C = n;
        map = new char[R][C];
        for (int r = 0; r < R; r++) {
            map[r] = board[r].toCharArray();
        }

        int beforeAnswer = 0;
        while (true) {
            beforeAnswer = answer;
            // 1. pop()
            // Map copy
            char[][] copyMap = new char[R][C];
            for (int r = 0; r < R; r++) {
                copyMap[r] = map[r].clone();
            }
            for (int r = 0; r < R - 1; r++) {
                for (int c = 0; c < C - 1; c++) {
                    if (check(r, c)) {
                        for (int d = 0; d < 4; d++) {
                            int nr = r + dr[d];
                            int nc = c + dc[d];
                            if (copyMap[nr][nc] != BLANK) {
                                answer++;
                            }
                            copyMap[nr][nc] = BLANK;
                        }
                    }
                }
            }
            map = copyMap;

            // 종료 시점 체크 - 더 이상 지워지는 블럭이 없으면 종료
            if (beforeAnswer == answer) {
                break;
            }

            // 2. rain()
            rain();
        }

        return answer;
    }

    static boolean check(int r, int c) {
        char tag = map[r][c];

        // 예외 처리 - 이미 지워진 칸은 제외
        if (tag == BLANK) {
            return false;
        }

        return (map[r + 1][c] == tag) && (map[r][c + 1] == tag) && (map[r + 1][c + 1] == tag);
    }

    static void rain() {
        for (int c = 0; c < C; c++) {
            int dropPoint = R - 1;

            // Get dropPoint
            while (dropPoint >= 0 && map[dropPoint][c] != BLANK) {
                dropPoint--;
            }
            if (dropPoint == 0) continue;

            // dropPoint를 갱신하면서 블럭을 떨어뜨린다.
            for (int r = dropPoint - 1; r >= 0; r--) {
                if (map[r][c] != BLANK) { // 떨어뜨려야 할 블럭이 있다면
                    map[dropPoint--][c] = map[r][c];
                    map[r][c] = BLANK;
                }
            }
        }
    }
}

/*
class Solution {

    static int R, C, answer;
    static char[][] map;
    static boolean[][] deleted;

    static int[] dr = {0, 0, 1, 1};
    static int[] dc = {0, 1, 1, 0};

    static boolean isValidate(int r, int c) {
        return (map[r][c] == map[r + 1][c] && map[r][c] == map[r][c + 1] && map[r][c] == map[r + 1][c + 1]);
    }

    static void drop() {
        // deleted를 기반으로 중력을 구현한다.
        // 빈칸은 '#'로 표시
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
            map[r][c] = '#';

            // r - 1부터 다시 비어있지 않은 칸이 나올때 까지 반복
            r--;
            while (r >= 0) {
                if (!deleted[r][c]) {
                    map[dropPoint--][c] = map[r][c]; // 이동
                    map[r][c] = '#';
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
                if (map[r][c] == '#') { // B: Blank. 비어있는 칸이면
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

    public int solution(int m, int n, String[] board) {
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

}*/

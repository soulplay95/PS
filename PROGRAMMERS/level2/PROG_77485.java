package programmers.level2;

class PROG_77485 {

    static int[] answer;
    static int[][] map;

    static int[] dr = {1, 0, -1, 0}; // 하우상좌. 반시계방향
    static int[] dc = {0, 1, 0, -1};

    public int[] solution(int rows, int columns, int[][] queries) {
        // init
        answer = new int[queries.length];
        map = new int[rows + 1][columns + 1];
        // map 초기화
        for (int r = 1; r <= rows; r++) {
            for (int c = 1; c <= columns; c++) {
                map[r][c] = (r - 1) * columns + c;
            }
        }

        // 쿼리 수행
        for (int i = 0; i < queries.length; i++) {
            answer[i] = getMin(queries[i]);
        }

        return answer;
    }

    static int getMin(int[] query) {
        int x1 = query[0];
        int y1 = query[1];
        int x2 = query[2];
        int y2 = query[3];

        int min = map[x1][y1];
        int temp = map[x1][y1];

        int r = x1;
        int c = y1;
        int d = 0;

        while (true) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (nc < y1 || nc > y2 || nr < x1 || nr > x2) { // 회전 영역을 벗어나면
                // 방향 바꿔서 새로운 좌표 구하기
                d++;
                nr = r + dr[d];
                nc = c + dc[d];
            }

            // 한바퀴 돌았는지 체크
            if (nr == x1 && nc == y1) {
                map[r][c] = temp;
                break;
            }

            map[r][c] = map[nr][nc]; // 시계방향으로 향하는 좌표에 값 덮어쓰기
            min = Math.min(min, map[nr][nc]); // 최소값 갱신
            // 좌표 갱신
            r = nr;
            c = nc;
        }

        return min;
    }

}

/*
import java.util.*;

class Solution {
    static int[][] map;
    static int[] answer;

    static int[] dr = {1, 0, -1, 0}; // 하우상좌
    static int[] dc = {0, 1, 0, -1}; // 하우상좌

    // 행렬에서 테두리 회전 구현
    // - 회전하는 방향(시계 방향) 반대 반향으로 반복문을 돌면서 값을 덮어 쓴다. + 최소값 갱신
    // - 범위 끝에 도달 시 방향을 바꿔준다.
    // 정답의 최대치: Integer
    // O((100 + 100 + 98 + 98) * 10000)
    public int[] solution(int rows, int columns, int[][] queries) {
        init(rows, columns, queries.length);

        // 쿼리 수행
        int index = 0; // answer 배열에 값을 채워 넣기 위한 index
        for (int i = 0; i < queries.length; i++) {
            answer[index++] = rotate(queries[i]);
        }

        return answer;
    }

    static void init(int R, int C, int queryCounts) {
        map = new int[R + 1][C + 1];
        answer = new int[queryCounts];

        // 행렬에 초기 숫자 채워넣기
        int num = 1;
        for (int r = 1; r <= R; r++) {
            for (int c = 1; c <= C; c++) {
                map[r][c] = num++;
            }
        }
    }

    static int rotate(int[] query) {
        int r1 = query[0];
        int c1 = query[1];
        int r2 = query[2];
        int c2 = query[3];

        int min = map[r1][c1];
        int temp = map[r1][c1]; // 좌상단 값 저장
        int r = r1;
        int c = c1;
        int d = 0;
        while (d < 4) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            // 범위 벗어나면 방향 전환
            if (isOut(nr, nc, r1, c1, r2, c2)) {
                d++;
                continue;
            }

            map[r][c] = map[nr][nc];
            min = Math.min(min, map[r][c]); // 최소값 갱신
            r = nr;
            c = nc;
        }
        map[r1][c1 + 1] = temp;

        return min;
    }

    static boolean isOut(int r, int c, int r1, int c1, int r2, int c2) {
        return (r < r1 || r > r2 || c < c1 || c > c2);
    }
}*/

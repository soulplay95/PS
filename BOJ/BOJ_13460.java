/**
 * @문제 구슬 탈출 2_G1
 * @날짜 211206
 * @분류 
 * @조건
 * # 3 <= 보드 크기 (N, M) <= 10
 * @풀이
 * # BFS
 * red, blue의 방문 처리를 위한 visited 배열을 만든다.
 * 움직이고 난 이후의 상황을 관리하는 class를 만든다.
 * 움직이려는 방향과 가까운 구슬부터 움직인다.
 * @comment
 * # 객체를 메소드의 매개변수로 집어 넣을 때 메소드 안에서 값을 변경하면 메소든 리턴 이후 객체를 사용할때 객체의 값이 바뀌어있다. 조심!
 * # 코드 최적화 하기. 중복되는 코드가 많음
 */

import java.util.*;
import java.io.*;

public class BOJ_13460 {

    // 좌표 클래스
    static class Pair {
        int r, c;

        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    // 보드 정보 - red, blue 구슬의 위치, 보드 상황
    static class BoardInfo {
        Pair red, blue;
        char[][] map;

        public BoardInfo(Pair red, Pair blue, char[][] map) {
            this.red = red;
            this.blue = blue;
            this.map = map;
        }
    }

    static int R, C;
    static Pair holePos; // 타겟 구멍 위치
    static Queue<BoardInfo> q; // BFS에서 활용할 큐
    static boolean[][][][] visited; // [redR][redC][blueR][blueC]의 상황이 있었으면 true

    static int[] dr = {-1, 0, 1, 0}; // 상우하좌 시계방향
    static int[] dc = {0, 1, 0, -1}; // 상우하좌 시계방향

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        char[][] initialMap = new char[R][C]; // 초기 맵
        Pair initialRedPos = new Pair(0, 0); // 초기 red 구슬 위치
        Pair initialBluePos = new Pair(0, 0); // 초기 blue 구슬 위치
        for (int r = 0; r < R; r++) {
            String row = br.readLine();
            for (int c = 0; c < C; c++) {
                initialMap[r][c] = row.charAt(c);
                // red, blue, hole 정보 파싱
                if (initialMap[r][c] == 'R') {
                    initialRedPos.r = r;
                    initialRedPos.c = c;
                } else if (initialMap[r][c] == 'B') {
                    initialBluePos.r = r;
                    initialBluePos.c = c;
                } else if (initialMap[r][c] == 'O') {
                    holePos = new Pair(r, c);
                }
            }
        }

        // init
        q = new LinkedList<>();
        visited = new boolean[R][C][R][C];
        visited[initialRedPos.r][initialRedPos.c][initialBluePos.r][initialBluePos.c] = true;
        BoardInfo initInfo = new BoardInfo(initialRedPos, initialBluePos, initialMap);
        q.offer(initInfo);

        // print
        System.out.println(bfs());
    }

    static int bfs() {
        int moveCounts = 0; // 움직인 횟수

        while (!q.isEmpty()) {
            int size = q.size();

            if (moveCounts >= 10) {
                return -1;
            }

            while (size-- > 0) {
                BoardInfo cur = q.poll();

                // 4방향으로 움직여서 가능한 경우 큐에 넣으면서 진행한다.
                // 불가능한 경우
                // red, blue 구슬 모두 이미 방문한 곳인 경우
                // 파란 구슬이 구멍에 빠지는 경우
                for (int d = 0; d < 4; d++) {
                    switch (d) {
                        case 0: // up
                            // 움직이려는 방향과 가까운(왼쪽으로 움직인다면 c값이 더 작은) 구슬 먼저 움직인다.
                            if (cur.red.r <= cur.blue.r) {
                                if (move(cur, d, "red")) {
                                    return moveCounts + 1;
                                }
                            } else {
                                if (move(cur, d, "blue")) {
                                    return moveCounts + 1;
                                }
                            }
                            break;
                        case 1: // right
                            // 움직이려는 방향과 가까운(왼쪽으로 움직인다면 c값이 더 작은) 구슬 먼저 움직인다.
                            if (cur.blue.c <= cur.red.c) {
                                if (move(cur, d, "red")) {
                                    return moveCounts + 1;
                                }
                            } else {
                                if (move(cur, d, "blue")) {
                                    return moveCounts + 1;
                                }
                            }
                            break;
                        case 2: // down
                            // 움직이려는 방향과 가까운(왼쪽으로 움직인다면 c값이 더 작은) 구슬 먼저 움직인다.
                            if (cur.blue.r <= cur.red.r) {
                                if (move(cur, d, "red")) {
                                    return moveCounts + 1;
                                }
                            } else {
                                if (move(cur, d, "blue")) {
                                    return moveCounts + 1;
                                }
                            }
                            break;
                        case 3: // left
                            // 움직이려는 방향과 가까운(왼쪽으로 움직인다면 c값이 더 작은) 구슬 먼저 움직인다.
                            if (cur.red.c <= cur.blue.c) {
                                if (move(cur, d, "red")) {
                                    return moveCounts + 1;
                                }
                            } else {
                                if (move(cur, d, "blue")) {
                                    return moveCounts + 1;
                                }
                            }
                            break;
                    }
                }
            }
            moveCounts++;
        }

        return -1;
    }

    static boolean move(BoardInfo cur, int d, String whoMovesFirst) {
        // copy
        Pair red = new Pair(cur.red.r, cur.red.c);
        Pair blue = new Pair(cur.blue.r, cur.blue.c);
        char[][] map = new char[R][C];
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                map[r][c] = cur.map[r][c];
            }
        }
        BoardInfo info = new BoardInfo(red, blue, map);
        // 홀인원 여부
        boolean isRedGoal = false;
        boolean isBlueGoal = false;

        int nr = 0;
        int nc = 0;
        int nr2 = 0;
        int nc2 = 0;

        if ("red".equals(whoMovesFirst)) {
            // red 구슬 먼저 움직인다.
            nr = info.red.r;
            nc = info.red.c;
            info.map[nr][nc] = '.'; // 움직이기 전 초기 구슬 위치 초기화
            while (true) {
                nr += dr[d];
                nc += dc[d];

                if (info.map[nr][nc] == '#' || info.map[nr][nc] == 'B') { // 장애물이거나 다른 구슬이면
                    nr -= dr[d];
                    nc -= dc[d];
                    break;
                }

                if (info.map[nr][nc] == 'O') { // 구멍이면
                    isRedGoal = true;
                    break;
                }
            }
            // 체크
            if (!isRedGoal) {
                // map에 마킹 + red 위치 정보 업데이트
                info.map[nr][nc] = 'R';
                info.red.r = nr;
                info.red.c = nc;
            }

            // blue 구슬 움직이기
            nr2 = info.blue.r;
            nc2 = info.blue.c;
            info.map[nr2][nc2] = '.'; // 움직이기 전 초기 구슬 위치 초기화
            while (true) {
                nr2 += dr[d];
                nc2 += dc[d];

                if (info.map[nr2][nc2] == '#' || info.map[nr2][nc2] == 'R') { // 장애물이거나 다른 구슬이면
                    nr2 -= dr[d];
                    nc2 -= dc[d];
                    break;
                }

                if (info.map[nr2][nc2] == 'O') { // 구멍이면
                    isBlueGoal = true;
                    break;
                }
            }
            // 체크
            if (!isBlueGoal) {
                // map에 마킹 + red 위치 정보 업데이트
                info.map[nr2][nc2] = 'B';
                info.blue.r = nr2;
                info.blue.c = nc2;
            }
        } else {
            // blue 구슬 먼저 움직인다.
            // blue 구슬 움직이기
            nr2 = info.blue.r;
            nc2 = info.blue.c;
            info.map[nr2][nc2] = '.'; // 움직이기 전 초기 구슬 위치 초기화
            while (true) {
                nr2 += dr[d];
                nc2 += dc[d];

                if (info.map[nr2][nc2] == '#' || info.map[nr2][nc2] == 'R') { // 장애물이거나 다른 구슬이면
                    nr2 -= dr[d];
                    nc2 -= dc[d];
                    break;
                }

                if (info.map[nr2][nc2] == 'O') { // 구멍이면
                    isBlueGoal = true;
                    break;
                }
            }
            // 체크
            if (!isBlueGoal) {
                // map에 마킹 + red 위치 정보 업데이트
                info.map[nr2][nc2] = 'B';
                info.blue.r = nr2;
                info.blue.c = nc2;
            }

            nr = info.red.r;
            nc = info.red.c;
            info.map[nr][nc] = '.'; // 움직이기 전 초기 구슬 위치 초기화
            while (true) {
                nr += dr[d];
                nc += dc[d];

                if (info.map[nr][nc] == '#' || info.map[nr][nc] == 'B') { // 장애물이거나 다른 구슬이면
                    nr -= dr[d];
                    nc -= dc[d];
                    break;
                }

                if (info.map[nr][nc] == 'O') { // 구멍이면
                    isRedGoal = true;
                    break;
                }
            }
            // 체크
            if (!isRedGoal) {
                // map에 마킹 + red 위치 정보 업데이트
                info.map[nr][nc] = 'R';
                info.red.r = nr;
                info.red.c = nc;
            }
        }

        // 체크
        if (isRedGoal && !isBlueGoal) {
            return true; // 종료
        }
        if (visited[nr][nc][nr2][nc2]) { // 둘 다 방문한 위치이면 큐에 삽입 없이 종료
            return false;
        }
        if (!isBlueGoal) { // 파란 구슬이 안들어갔으면
            // 방문 처리 후 큐에 삽입
            visited[nr][nc][nr2][nc2] = true;
            q.offer(info);
        }

        return false;
    }

}

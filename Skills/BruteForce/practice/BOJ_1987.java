package algorithm.brute_force.practice;

/**
 * @문제 알파벳 [G4]
 * @날짜 220511
 * @분류 그래프 탐색 / DFS / 백트래킹
 * @조건
 * # 1 <= R, C <= 20
 * @풀이
 * # DFS
 * - used[새로 이동한 칸의 알파벳]이 true이면 이동하지 않는다. => 백트래킹
 * @comments
 * # 정답의 최대치: Integer => 20 * 20
 * # 시간 복잡도:
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_1987 {

    static StringBuilder sb = new StringBuilder();

    static int R, C, max;
    static char[][] map;
    static boolean[] used; // used[i] : 'A' + i 알파벳이 사용되었으면 true

    static int[] dr = {-1, 0, 1, 0}; // 상우하좌 시계방향
    static int[] dc = {0, 1, 0, -1}; // 상우하좌 시계방향

    static final int ALPHABET_COUNTS = 26;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        print();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        // init
        map = new char[R][C];
        used = new boolean[ALPHABET_COUNTS];
        max = 1;

        // map 구성
        for (int r = 0; r < R; r++) {
            map[r] = br.readLine().toCharArray();
        }
    }

    static boolean isOut(int r, int c) {
        return (r < 0 || r >= R || c < 0 || c >= C);
    }

    static void dfs(int r, int c, int depth) {
        max = Math.max(max, depth);

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (isOut(nr, nc)) continue; // 경계 체크
            if (used[map[nr][nc] - 'A']) continue; // 방문 체크

            used[map[nr][nc] - 'A'] = true;
            dfs(nr, nc, depth + 1);
            used[map[nr][nc] - 'A'] = false;
        }
    }

    static void solve() {
        used[map[0][0] - 'A'] = true;
        dfs(0, 0, 1);
    }

    static void print() {
        System.out.println(max);
    }

}

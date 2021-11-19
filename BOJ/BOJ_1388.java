/**
 * @문제 바닥 장식_S4
 * @날짜 211119
 * @분류 DFS, BFS
 * @조건 # 1 <= 방 크기(N, M) <= 50
 * @풀이 # -이면 좌우, |이면 상하로 dfs 돌면서 방문체크
 * @comment #
 */

import java.util.*;
import java.io.*;

public class BOJ_1388 {

    static int N, M;
    static char[][] map;
    static boolean[][] checked;

    static final int HORIZONTAL = 0;
    static final int VERTICAL = 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        // init
        map = new char[N][M];
        checked = new boolean[N][M];

        for (int r = 0; r < N; r++) {
            map[r] = br.readLine().toCharArray();
        }

        // print
        System.out.println(solve());
    }

    static int solve() {
        int ans = 0;

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (!checked[r][c]) {
                    ans++;
                    if (map[r][c] == '-') {
                        dfs(r, c, HORIZONTAL);
                    } else if (map[r][c] == '|') {
                        dfs(r, c, VERTICAL);
                    }
                }
            }
        }

        return ans;
    }

    static void dfs(int r, int c, int type) {
        checked[r][c] = true; // 방문 체크

        if (type == HORIZONTAL) {
            if (c - 1 >= 0 && !checked[r][c - 1] && map[r][c - 1] == '-') {
                dfs(r, c - 1, type);
            }
            if (c + 1 < M && !checked[r][c + 1] && map[r][c + 1] == '-') {
                dfs(r, c + 1, type);
            }
        } else if (type == VERTICAL) {
            if (r - 1 >= 0 && !checked[r - 1][c] && map[r - 1][c] == '|') {
                dfs(r - 1, c, type);
            }
            if (r + 1 < N && !checked[r + 1][c] && map[r + 1][c] == '|') {
                dfs(r + 1, c, type);
            }
        }
    }

}

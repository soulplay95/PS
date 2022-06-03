package algorithm.graph_search;

/**
 * @문제 단지번호붙이기 [S1]
 * @날짜 220603
 * @분류 그래프 탐색 / DFS / BFS
 * @조건 5 <= N <= 25
 * @풀이
 * @comments
 * # 정답의 최대치: Integer => 5^2(max N^2)
 * # 시간 복잡도: O(N^2)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_2667 {

    static StringBuilder sb = new StringBuilder();

    static class Pair {
        int r, c;

        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int N, totalComplexCounts, homeCounts;
    static char[][] map; // 방문 표시: 2
    static ArrayList<Integer> homeCountsInEachComplex;

    // 상우하좌 시계방향
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        // init
        totalComplexCounts = 0;
        map = new char[N][N];
        homeCountsInEachComplex = new ArrayList<>();

        for (int r = 0; r < N; r++) {
            map[r] = br.readLine().toCharArray();
        }
    }

    static void solve() {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (map[r][c] == '1') {
                    homeCounts = 0;
                    dfs(r, c);
                    totalComplexCounts++;
                    homeCountsInEachComplex.add(homeCounts);
                }
            }
        }

        Collections.sort(homeCountsInEachComplex);

        // print
        System.out.println(totalComplexCounts);
        for (int homeCounts : homeCountsInEachComplex) {
            System.out.println(homeCounts);
        }
    }

    static void dfs(int r, int c) {
        map[r][c] = '2'; // 방문 표시
        homeCounts++;

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (isOut(nr, nc)) continue;
            if (map[nr][nc] == '1') {
                dfs(nr, nc);
            }
        }
    }

    static boolean isOut(int r, int c) {
        return (r < 0 || r >= N || c < 0 || c >= N);
    }

}

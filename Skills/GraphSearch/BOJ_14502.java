package algorithm.graph_search;

/**
 * @문제 연구소 [G5]
 * @날짜 220603
 * @분류 구현 / 완전 탐색 / 그래프 탐색 / BFS
 * @조건
 * 3 <= N, M <= 8
 * 2 <= 초기 바이러스 개수 <= 10
 * @풀이
 * - 모든 빈칸 중 3개를 뽑아서 벽을 세운다.
 * - 각 경우에 BFS 탐색하여 안전 구역 개수를 센다.
 * @comments
 * # 정답의 최대치: Integer => 약 64(max N^2)
 * # 시간 복잡도: O(64C3 * N^2)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_14502 {

    static StringBuilder sb = new StringBuilder();

    static class Pair {
        int r, c;

        Pair(int _r, int _c) {
            r = _r;
            c = _c;
        }
    }

    static int R, C, ans;
    static int[][] map;
    static ArrayList<Pair> initialVirusPositions, initialBlankPositions;
    static Pair[] selected;

    // 상우하좌
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        // init
        map = new int[R][C];
        ans = 0;
        initialVirusPositions = new ArrayList<>();
        initialBlankPositions = new ArrayList<>();
        selected = new Pair[3];

        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int c = 0; c < C; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if (map[r][c] == 0) {
                    initialBlankPositions.add(new Pair(r, c));
                } else if (map[r][c] == 2) {
                    initialVirusPositions.add(new Pair(r, c));
                }
            }
        }
    }

    static void solve() {
        recursive(0, 0);

        // print
        System.out.println(ans);
    }

    static void recursive(int depth, int start) {
        if (depth == 3) {
            ans = Math.max(ans, bfs());
        } else {
            for (int candidateIndex = start; candidateIndex < initialBlankPositions.size(); candidateIndex++) {
                Pair candidate = initialBlankPositions.get(candidateIndex);
                selected[depth] = new Pair(candidate.r, candidate.c);
                recursive(depth + 1, candidateIndex + 1);
                selected[depth] = null;
            }
        }
    }

    static int bfs() {
        // init
        // 맵 복사
        int[][] copyMap = new int[R][C];
        for (int r = 0; r < R; r++) {
            copyMap[r] = Arrays.copyOf(map[r], C);
        }
        // 뽑은 새로운 벽 위치 마킹
        for (Pair wall : selected) {
            copyMap[wall.r][wall.c] = 1;
        }

        int virusCounts = 0;
        Queue<Pair> q = new LinkedList<>();
        // 초기 바이러스 세팅
        for (Pair virus : initialVirusPositions) {
            copyMap[virus.r][virus.c] = 3; // 방문 표시
            q.offer(new Pair(virus.r, virus.c));
        }

        while (!q.isEmpty()) {
            Pair cur = q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];

                if (isOut(nr, nc)) continue; // 경계 체크
                if (copyMap[nr][nc] == 0) {
                    copyMap[nr][nc] = 3; // 방문 체크
                    virusCounts++;
                    q.offer(new Pair(nr, nc));
                }
            }
        }

        return initialBlankPositions.size() - 3 - virusCounts;
    }

    static boolean isOut(int r, int c) {
        return (r < 0 || r >= R || c < 0 || c >= C);
    }

}

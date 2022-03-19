package study.w220321;

/**
 * @문제 마법사 상어와 비바라기_G5
 * @날짜 220320
 * @분류 구현 / 시뮬레이션
 * @조건
 * # 2 <= 맵 크기 (N) <= 50
 * # 1 <= 이동 횟수 (M) <= 100
 * # 0 <= 초기 물의 양 (A[r][c]) <= 100
 * # 1 <= 이동 거리 (si) <= 50
 * @풀이
 * # 시뮬레이션
 * 1, 2, 3: 구름을 구성하는 모든 칸의 이동. (r, c) => ((r + dr[d] * s + N) % N, (c + dc[d] * s + N) % N)
 * - 구름에 포함되는 칸의 좌표를 List에 저장하여 관리
 * - 각 칸의 좌표를 새로 계산하여 이동하면서 물의양 증가
 * - O(N^2)
 * 4: 물복사 버그. 4방향 대각선에 물이 있는지 판별
 * - O(4*N^2)
 * 5: 새로운 구름 갱신.
 * - O(N^2)
 * @comments
 * # 정답의 최대치:
 * # 시간 복잡도: O(M*N^2)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_21610 {

    static StringBuilder sb = new StringBuilder();

    static class Pair {
        int r, c;

        Pair(int _r, int _c) {
            r = _r;
            c = _c;
        }
    }

    static int N, M, offset;
    static int[][] A;
    static boolean[][] isCloud;
    static ArrayList<Pair> cloudPositions;

    static int[] dr = {0, -1, -1, -1, 0, 1, 1, 1}; // 좌~ 시계방향
    static int[] dc = {-1, -1, 0, 1, 1, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        input();
        print();
    }

    static void init() {
        A = new int[N][N];
        cloudPositions = new ArrayList<>();
        offset = N * 50;

        // 초기 구름 위치 세팅
        cloudPositions.add(new Pair(N - 1, 0));
        cloudPositions.add(new Pair(N - 1, 1));
        cloudPositions.add(new Pair(N - 2, 0));
        cloudPositions.add(new Pair(N - 2, 1));
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        init();

        // 초기 바구니에 담긴 물 양 채워넣기
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int c = 0; c < N; c++) {
                A[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        // M번 이동
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine(), " ");
            int d = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());

            move(d, s);
        }
    }

    static void move(int d, int s) {
        cloudMove(d, s); // 1, 2, 3
        copy(); // 4
        update(); // 5
    }

    static void cloudMove(int d, int s) {
        // init
        isCloud = new boolean[N][N];
        ArrayList<Pair> newCloudPositionList = new ArrayList<>();

        // 구름 칸 위치 정보를 담은 리스트에서 좌표를 꺼내 새로운 좌표를 계산하고, 해당 칸의 물 양을 1증가 시킨다.
        for (Pair cur : cloudPositions) {
            int nr = (cur.r + (dr[d] * s) + offset) % N;
            int nc = (cur.c + (dc[d] * s) + offset) % N;

            if (nr == -2 || nc == -2) {
                System.out.println("?");
            }

            newCloudPositionList.add(new Pair(nr, nc));
            isCloud[nr][nc] = true;
            A[nr][nc]++;
        }

        cloudPositions = newCloudPositionList;
    }

    static void copy() {
        // 구름을 구성하는 모든 칸의 4방향 대각선을 살펴본다.
        for (Pair cur : cloudPositions) {
            int counts = 0; // 대각선 방향에 물이 있는 칸의 개수

            for (int d = 1; d <= 7; d += 2) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];

                if (isOut(nr, nc)) {
                    continue;
                }

                if (A[nr][nc] > 0) {
                    counts++;
                }
            }

            A[cur.r][cur.c] += counts;
        }
    }

    static void update() {
        // init
        cloudPositions.clear();

        // 모든 칸을 탐색하여 물의 양이 2 이상인 칸을 구름으로 만든다.
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (isCloud[r][c]) {
                    continue;
                }
                if (A[r][c] >= 2) {
                    A[r][c] -= 2;
                    cloudPositions.add(new Pair(r, c));
                }
            }
        }
    }

    static boolean isOut(int r, int c) {
        return (r < 0 || r >= N || c < 0 || c >= N);
    }

    static void print() {
        int answer = 0;

        // 바구니에 들어있는 물의 양 합하기
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                answer += A[r][c];
            }
        }

        System.out.println(answer);
    }

}

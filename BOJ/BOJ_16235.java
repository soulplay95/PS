package study.w220425;

/**
 * @문제 나무 재테크_G4
 * @날짜 220424
 * @분류 구현 / 시뮬레이션
 * @조건
 * # 1 <= 맵 가로, 세로 크기 (N) <= 10
 * # 1 <= 흐른 시간 (K) <= 1000
 * # 1 <= 로봇이 각 칸에 추가하는 양분의 양 (A) <= 100
 * # 1 <= 입력으로 주어지는 각 나무의 나이 <= 10
 * @풀이
 * #
 * @comments
 * # 정답의 최대치:
 * # 시간 복잡도:
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_16235 {

    static StringBuilder sb = new StringBuilder();

    static class Tree implements Comparable<Tree> {
        int r, c, age; // 나무의 위치 (r, c), 나이

        Tree(int r, int c, int age) {
            this.r = r;
            this.c = c;
            this.age = age;
        }

        @Override
        public int compareTo(Tree o) {
            return Integer.compare(this.age, o.age); // 나이 기준 오름차순 정렬
        }
    }

    static int N, M, K;
    static int[][] map, A; // map[r][c]: 현재 (r, c) 칸에 있는 양분의 양
    static PriorityQueue<Tree> trees; // 현재 살아있는 나무들
    static ArrayList<Tree> deadTrees, cloneTrees; // 봄에 죽은 나무 리스트, 번식하게 될 나무 리스트

    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1}; // 상 부터 시계방향
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) throws IOException {
        input();
        solve();
        print();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        // init
        map = new int[N][N];
        A = new int[N][N];
        trees = new PriorityQueue<>();
        deadTrees = new ArrayList<>();
        cloneTrees = new ArrayList<>();

        // map[][], A[][] 초기화
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int c = 0; c < N; c++) {
                map[r][c] = 5;
                A[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        // 초기 나무 리스트 초기화
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int age = Integer.parseInt(st.nextToken());
            trees.add(new Tree(r, c, age));
        }
    }

    static void solve() {
        // K년 동안 반복
        while (K-- > 0) {
            spring();
            summer();
            fall();
            winter();
        }
    }

    static void spring() {
        PriorityQueue<Tree> liveTrees = new PriorityQueue<>(); // 봄이 진행되면서 살아 남은 나무들

        // 현재 나무 리스트를 순회하여 양분을 소모하고 살아있는, 죽은 나무를 갱신
        while (!trees.isEmpty()) {
            Tree cur = trees.poll();
            int r = cur.r;
            int c = cur.c;
            int age = cur.age;

            if (age > map[r][c]) { // 양분이 부족하면
                deadTrees.add(new Tree(r, c, age));
            } else {
                // 나이만큼 양분 소모
                map[r][c] -= age;
                if (++age % 5 == 0) { // 나이 1 증가 & 가을에 번식하게 될 나무인지 체크
                    cloneTrees.add(new Tree(r, c, age));
                }
                liveTrees.add(new Tree(r, c, age));
            }
        }

        trees = liveTrees;
    }

    static void summer() {
        // 죽은 나무 양분으로 변환
        for (Tree deadTree : deadTrees) {
            int r = deadTree.r;
            int c = deadTree.c;
            int age = deadTree.age;

            map[r][c] += age / 2;
        }

        deadTrees.clear();
    }

    static void fall() {
        for (Tree tree : cloneTrees) {
            int r = tree.r;
            int c = tree.c;

            for (int d = 0; d < 8; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (isOut(nr, nc)) { // 경계 체크
                    continue;
                }

                trees.add(new Tree(nr, nc, 1)); // 나이가 1인 나무 추가
            }
        }

        cloneTrees.clear();
    }

    static void winter() {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                map[r][c] += A[r][c];
            }
        }
    }

    static boolean isOut(int r, int c) {
        return (r < 0 || r >= N || c < 0 || c >= N);
    }

    static void print() {
        System.out.println(trees.size());
    }

}

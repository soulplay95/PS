/**
 * @문제 영우는 사기꾼?_G3
 * @날짜 220224
 * @분류 구현 / 위상 정렬
 * @조건
 * # 1 <= N, M, K <= 10만
 * @풀이
 * - 인접 리스트로 그래프를 표현하고, 각 노드마다 indegree를 계산한다.
 * - possible[i]: i번 건물을 건설할 수 있으면 true, counts[i]: i번 건물 개수
 * - 초기에 indegree가 0인 건물의 possible을 true로 초기화하고 K번 시뮬레이션
 * @comments
 * # 시간 복잡도: O(K)
 * # 공간 복잡도: O(N)
 * # 정답의 최대치:
 */

import java.io.*;
import java.util.*;

public class BOJ_14676 {

    static StringBuilder sb = new StringBuilder();

    static int N, M, K;
    static ArrayList<Integer>[] adjList;
    static int[] indegree, counts;
    static boolean[] possible;

    public static void main(String[] args) throws IOException {
        input();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        // init
        adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }
        indegree = new int[N + 1];
        counts = new int[N + 1];
        possible = new boolean[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            adjList[from].add(to);
            indegree[to]++;
        }

        init();

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int command = Integer.parseInt(st.nextToken());
            int vertex = Integer.parseInt(st.nextToken());

            if (!isPossible(command, vertex)) { // 불가능한 명령이면
                System.out.println("Lier!");
                return;
            }
        }

        System.out.println("King-God-Emperor");
    }

    static void init() {
        // indegree가 0인 건물들의 possible을 true로 초기화
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                possible[i] = true;
            }
        }
    }

    static boolean isPossible(int command, int vertex) {
        if (command == 1) { // 건설
            if (possible[vertex]) { // 건설 가능하면
                counts[vertex]++; // 건설 개수 증가시킴
                return true;
            } else {
                return false;
            }
        } else if (command == 2) { // 파괴
            if (counts[vertex] == 0) { // 파괴 시키지 못하면
                return false;
            } else if (counts[vertex] == 1) {
                // 건물이 없어지는 순간
                // 바로 인접한 건물들은 더이상 지을 수 없게 된다.
                for (int adjVertex : adjList[vertex]) {
                    possible[adjVertex] = false;
                }
            }

            counts[vertex]--;
            return true;
        }

        return true;
    }

}

/**
 * @문제 음악프로그램_G2
 * @날짜 220222
 * @분류 위상 정렬
 * @조건
 * # 1 <= 가수의 수 (N) <= 1000
 * # 1 <= 보조 PD 수 (M) <= 100
 * @풀이
 * 1. 보조 PD가 정한 순서들을 통해 간선을 정의한다.
 * 2. 위상 정렬을 진행한다.
 * 3. 모든 정점이 정렬이 안되었는데 큐가 비면 0을 출력한다.
 * @comments
 * #
 */

import java.io.*;
import java.util.*;

public class BOJ_2623 {

    // IO Handlers
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st = null;
    private static StringBuilder sb = new StringBuilder();

    private static int N, M;
    private static ArrayList<Integer>[] adjList;
    private static int[] indegree;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        print();
    }

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        // init
        adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }
        indegree = new int[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int size = Integer.parseInt(st.nextToken());
            if (size <= 1) {
                continue;
            }
            int[] temp = new int[size];
            for (int j = 0; j < size; j++) {
                temp[j] = Integer.parseInt(st.nextToken());
            }

            // 인접 리스트 구성하고 indegree 배열 채우기
            for (int from = 0; from < size - 1; from++) {
                for (int to = from + 1; to < size; to++) {
                    adjList[temp[from]].add(temp[to]);
                    indegree[temp[to]]++;
                }
            }
        }
    }

    private static void solve() {
        // 위상 정렬
        Queue<Integer> q = new LinkedList<>();
        int sortedVertexCounts = 0; // 정렬된 정점 개수
        // 초기화
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
                sortedVertexCounts++;
            }
        }

        while (!q.isEmpty()) {
            int cur = q.poll();
            sb.append(cur).append("\n");

            // 그래프에서 삭제
            for (int adj : adjList[cur]) {
                indegree[adj]--;
                if (indegree[adj] == 0) {
                    q.offer(adj);
                    sortedVertexCounts++;
                }
            }
        }

        if (sortedVertexCounts != N) {
            System.out.println(0);
            System.exit(0);
        }
    }

    private static void print() {
        System.out.print(sb);
    }

}

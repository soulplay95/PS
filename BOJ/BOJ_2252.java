package study.w220221;

/**
 * @문제 줄 세우기_G3
 * @날짜 220221
 * @분류 위상 정렬
 * @조건
 * # 1 <= 학생 수 (N) <= 32000
 * # 1 <= 비교 횟수 (M) <= 10만
 * @풀이
 * # 위상 정렬
 * - 그래프 정의
 * -- 정점(V) => i번 학생
 * -- 간선(E) => i번 학생이 j번 학생보다 먼저 서야 함 (i->j)
 * - 시간 복잡도
 * -- 인접 리스트 사용 시, O(V + E)
 * @comments
 * # 위상 정렬 기본 문제
 * # 원소들 간의 비교 결과(누가 우위에 있는지)가 주어지고 즉, 위상이 주어지고 위상에 따라 정렬하는 문제 => 그래프를 정의하고 위상 정렬로 해결
 */

import java.util.*;
import java.io.*;

public class BOJ_2252 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st = null;

    private static int V, E;
    private static ArrayList<Integer>[] adjList; // 그래프를 정의하기 위한 인접 리스트
    private static int[] indegree; // indegree[i]: i 정점으로 향하는 간선의 개수
    private static boolean[] visited; // Queue에 담긴 적이 있으면 true
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        input();
        solve();
        print();
    }

    private static void input() throws Exception {
        /**
         * 그래프를 정의(인접 리스트를 구성)하고, indegree 배열을 초기화한다.
         */
        st = new StringTokenizer(br.readLine(), " ");
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        // init
        adjList = new ArrayList[V + 1]; // index 1부터
        for (int i = 1; i <= V; i++) {
            adjList[i] = new ArrayList<Integer>();
        }
        indegree = new int[V + 1];
        visited = new boolean[V + 1];

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            adjList[from].add(to); // x -> y
            indegree[to]++;
        }
    }

    private static void solve() {
        /**
         * 위상 정렬
         */
        Queue<Integer> q = new LinkedList<Integer>(); // 아직 정렬되지 않은 원소들 중 제일 먼저 올 수 있는 후보들을 담는 큐
        // 초기화 - 제일 먼저 앞에 올 수 있는 후보들(indegree가 0인 원소들)을 큐에 넣고 방문 표시
        for (int i = 1; i <= V; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
                visited[i] = true;
            }
        }

        while (!q.isEmpty()) {
            int x = q.poll();
            sb.append(x).append(" "); // 정렬 결과에 추가

            // x를 그래프에서 제거하고 제거함으로써 새롭게 맨 앞에 올 수 있는 후보들(새롭게 indegree가 0이 된 원소들)을 찾아서 큐에 넣기
            for (Integer y : adjList[x]) {
                indegree[y]--;
                if (!visited[y] && indegree[y] == 0) {
                    visited[y] = true;
                    q.offer(y);
                }
            }
        }
    }

    private static void print() {
        System.out.println(sb.toString());
    }

}

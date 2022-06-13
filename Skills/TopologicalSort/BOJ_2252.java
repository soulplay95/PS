package algorithm.topological_sort;

/**
 * @문제 줄 세우기 [G3]
 * @날짜 220613
 * @분류 위상 정렬
 * @조건
 * 1 <= N <= 32,000
 * 1 <= M <= 10만
 * @풀이
 * # 위상 정렬
 * - A 학생이 B 학생보다 앞에 선다 => A->B 로 향하는 간선으로 정의
 * @comments
 * # 정답의 최대치: Integer
 * # 시간 복잡도: O(N + M)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_2252 {

    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static ArrayList<Integer>[] adjList;
    static int[] indegree;

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

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
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adjList[from].add(to);
            indegree[to]++;
        }
    }

    static void solve() {
        Queue<Integer> q = new LinkedList<>();

        // 1. indegree가 0인(제일 앞에 올 수 있는 후보들) 정점들을 큐에 넣는다.
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        // 2. 큐가 빌때까지 반복
        while (!q.isEmpty()) {
            int cur = q.poll();
            // 2-1. 큐에서 하나 뽑아서 정렬시킨다.
            sb.append(cur).append(' ');

            // 2-2. 그래프에서 뽑은 정점을 삭제시킨다 -> 이 정점과 인접한 정점들의 indegree 값을 감소시킨다.
            for (int adjVertex : adjList[cur]) {
                indegree[adjVertex]--;
                // 2-3. 새롭게 indegree가 0이 된 정점들을 큐에 넣는다.
                if (indegree[adjVertex] == 0) {
                    q.offer(adjVertex);
                }
            }
        }

        // print
        System.out.println(sb);
    }

}

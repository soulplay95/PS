package algorithm.topological_sort.practice;

/**
 * @문제 음악프로그램 [G3]
 * @날짜 220614
 * @분류 위상 정렬
 * @조건
 * 1 <= N <= 1000
 * 1 <= M <= 100
 * @풀이
 * # 위상 정렬
 * - N1, N2, N3, ... 의 순서로 주어지면 N1 -> N2, N2 -> N3, .. 로 간선을 만들어준다.
 * @comments
 * # 정답의 최대치:
 * # 시간 복잡도: O(N)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_2623 {

    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static int[] indegree;
    static ArrayList<Integer>[] adjList;

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
        indegree = new int[N + 1];
        adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int counts = Integer.parseInt(st.nextToken());
            if (counts == 0 || counts == 1) {
                continue;
            }
            int from = Integer.parseInt(st.nextToken());
            for (int j = 1; j < counts; j++) {
                int to = Integer.parseInt(st.nextToken());
                adjList[from].add(to);
                indegree[to]++;
                from = to;
            }
        }
    }

    static void solve() {
        // init
        ArrayList<Integer> ans = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();

        // 1. indegree가 0인 노드들을 큐에 넣는다.
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        // 2. 큐가 빌때까지 반복하면서 원소를 뽑아 정렬하고, 새롭게 indegree가 0이 되는 노드를 큐에 넣는다.
        while (!q.isEmpty()) {
            int cur = q.poll();
            ans.add(cur); // 정렬

            for (int adjVertex : adjList[cur]) {
                indegree[adjVertex]--;
                if (indegree[adjVertex] == 0) {
                    q.offer(adjVertex);
                }
            }
        }

        // 정답 리스트 사이즈가 N이 아닌 경우 => DAG가 아닌 경우. 즉, 순서를 정하는 것이 불가능한 경우
        if (ans.size() != N) {
            sb.append(0);
        } else {
            for (int node : ans) {
                sb.append(node).append('\n');
            }
        }

        // print
        System.out.println(sb.toString());
    }

}

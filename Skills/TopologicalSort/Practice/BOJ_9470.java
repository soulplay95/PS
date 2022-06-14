package algorithm.topological_sort.practice;

/**
 * @문제 Strahler 순서 [G3]
 * @날짜 220614
 * @분류 위상 정렬
 * @조건
 * 2 <= M <= 1000
 * @풀이
 * # 위상 정렬
 * - 위상 정렬 순서로 각 노드의 순서를 채우되, 사이클 단위로 채운다.
 * @comments
 * # 정답의 최대치: Integer => max 500
 * # 시간 복잡도: O(M)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_9470 {

    static StringBuilder sb = new StringBuilder();

    static int K, M, P;
    static ArrayList<Integer>[] adjList;
    static int[] indegree, order, maxCounts;

    public static void main(String[] args) throws IOException {
        input();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int TC = Integer.parseInt(br.readLine());
        for (int t = 1; t <= TC; t++) {
            st = new StringTokenizer(br.readLine(), " ");
            K = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            P = Integer.parseInt(st.nextToken());
            // init
            adjList = new ArrayList[M + 1];
            for (int i = 1; i <= M; i++) {
                adjList[i] = new ArrayList<>();
            }
            indegree = new int[M + 1];
            order = new int[M + 1];
            maxCounts = new int[M + 1];

            for (int i = 0; i < P; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                adjList[from].add(to);
                indegree[to]++;
            }

            solve();
        }

        // print
        System.out.println(sb.toString());
    }

    static void solve() {
        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i <= M; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
                order[i] = maxCounts[i] = 1;
            }
        }

        int ans = 0;
        while (!q.isEmpty()) {
            int cur = q.poll();

            // 최대 순서가 2개 이상 들어온 경우
            if (maxCounts[cur] >= 2) {
                order[cur]++;
            }
            ans = Math.max(ans, order[cur]);

            for (int adjVertex : adjList[cur]) {
                indegree[adjVertex]--;
                if (indegree[adjVertex] == 0) {
                    q.offer(adjVertex);
                }

                if (order[adjVertex] == order[cur]) { // 최대 순서가 또 들어오는 경우
                    maxCounts[adjVertex]++;
                } else if (order[adjVertex] < order[cur]) { // 새로운 최대 순서가 들어오는 경우
                    order[adjVertex] = order[cur];
                    maxCounts[adjVertex] = 1;
                }
            }
        }

        // append
        sb.append(K).append(' ').append(ans).append('\n');
    }

}

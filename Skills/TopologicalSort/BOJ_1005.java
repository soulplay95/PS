package algorithm.topological_sort;

/**
 * @문제 ACM Craft [G3]
 * @날짜 220613
 * @분류 DP / 위상 정렬
 * @조건
 * 2 <= N <= 1000
 * 1 <= K <= 10만
 * 0 <= Di <= 10만
 * @풀이
 * # 위상 정렬
 * - completeTime[x] = max(x 이전에 지어야만 하는 건물들의 completeTime) + time[x]
 * @comments
 * # 정답의 최대치: Integer => 10^5 * 10^3 (max Di * max N)
 * # 시간 복잡도: O(N + K)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_1005 {

    static StringBuilder sb = new StringBuilder();

    static int N, K, target;
    static ArrayList<Integer>[] adjList;
    static int[] indegree, time, completeTime;

    public static void main(String[] args) throws IOException {
        input();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int TC = Integer.parseInt(br.readLine());
        while (TC-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            // init
            adjList = new ArrayList[N + 1];
            indegree = new int[N + 1];
            time = new int[N + 1];
            completeTime = new int[N + 1];

            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 1; i <= N; i++) {
                adjList[i] = new ArrayList<>();
                time[i] = Integer.parseInt(st.nextToken());
            }
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                adjList[from].add(to);
                indegree[to]++;
            }
            target = Integer.parseInt(br.readLine());

            solve();
        }

        // print
        System.out.println(sb);
    }

    static void solve() {
        // 건물 짓는 순서를 위상 정렬로 계산하면서 completeTime 배열을 채운다.
        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
                completeTime[i] = time[i];
            }
        }

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int adjVertex : adjList[cur]) {
                indegree[adjVertex]--;
                if (indegree[adjVertex] == 0) {
                    q.offer(adjVertex);
                }
                completeTime[adjVertex] = Math.max(completeTime[adjVertex], completeTime[cur] + time[adjVertex]);
            }
        }

        // append
        sb.append(completeTime[target]).append('\n');
    }

}

/**
 * @문제 ACM Craft_G3
 * @날짜 220221
 * @분류 DP / 위상 정렬
 * @조건
 * # 2 <= 건물 개수 (N) <= 1000
 * # 1 <= 건설 순서 규칙 개수 (K) <= 10만
 * # 0 <= 각 건물당 건설에 걸리는 시간 (Di) <= 10만
 * @풀이
 * - 어떤 건물을 짓기 위한 시간 = max(선행 건물을 짓기 위한 시간) + 건설에 걸리는 시간(D)
 * - 건물을 짓는 순서가 필요 => 위상 정렬로 건물 짓는 순서를 구한다.
 * @comments
 * # 정답의 최대치
 * - 타겟 건물의 번호가 1000번이고, 1000번 건물을 짓기 위해 1번 건물부터 순서대로 지어야만 한다고 하면
 * - 건물의 최대 개수(10^3) * 각 건물을 짓는데 걸리는 최대 시간(10^5) := 10^8
 * - 따라서 Integer로 커버 가능
 * # 시간복잡도
 * - 위상 정렬: O(V + E)
 * # 위상 정렬을 잘 이해했는지 판단할 수 있는 문제
 * - 위상 정렬 순서로 문제를 풀면서 최적의 값을 찾아나가는 문제
 */

import java.io.*;
import java.util.*;

public class BOJ_1005 {

    // IO Handlers
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st = null;
    private static StringBuilder sb = new StringBuilder();

    private static int V, E, target;
    private static ArrayList<Integer>[] adjList;
    private static int[] delay, time, indegree; // 각 건물당 건설에 걸리는 시간, 건물을 짓기 위한 시간
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            input();
            solve();
            print();
        }
    }

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        // init
        delay = new int[V + 1];
        time = new int[V + 1];
        visited = new boolean[V + 1];
        indegree = new int[V + 1];
        adjList = new ArrayList[V + 1];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= V; i++) {
            adjList[i] = new ArrayList<>();
            delay[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            adjList[x].add(y);
            indegree[y]++;
        }
        target = Integer.parseInt(br.readLine());
    }

    private static void solve() {
        /**
         * 위상 정렬을 통해 건물 짓는 순서를 결정하고, 하나씩 건물을 지으면서 time[target]이 채워지면 종료
         */
        Queue<Integer> q = new LinkedList<>();
        // 초기화
        for (int i = 1; i <= V; i++) {
            if (indegree[i] == 0) {
                visited[i] = true;
                q.offer(i);
                time[i] = delay[i];
            }
        }

        while (!q.isEmpty()) {
            int x = q.poll();
            for (int y : adjList[x]) {
                indegree[y]--;
                if (!visited[y] && indegree[y] == 0) {
                    visited[y] = true;
                    q.offer(y);
                }

                // y 즉, x가 건설이 끝나야만 건설할 수 있는 건물의 건설 시간은 기존 값 vs x가 건설된 시간 + y를 건설하는데 걸리는 시간 중 최대값이다.
                time[y] = Math.max(time[y], time[x] + delay[y]);
            }
        }
    }

    private static void print() {
        System.out.println(time[target]);
    }

}

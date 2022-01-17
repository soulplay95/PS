/**
 * @문제 효율적인 해킹_S1
 * @날짜 220118
 * @분류 그래프 탐색
 * @조건
 * # 컴퓨터 수 <= 1만
 * # 간선 수 <= 10만
 * @풀이
 * # 인접 리스트로 그래프 구성 => 방향을 반대로(A->B를 B->A로)
 * # 모든 노드에서 dfs 탐색. 탐색마다 visited를 초기화, 방문한 노드의 최대값 갱신
 * # 해킹할 수 있는 컴퓨터 개수를 저장해놓고 최대값에 해당하는 노드 번호 리스트에 저장 + 오름차순 정렬
 * @comment
 * # dfs시간초과
 */

import java.util.*;
import java.io.*;

public class BOJ_1325 {

    private static int N, M, max, counts;
    private static ArrayList<Integer>[] adjList; // 인접 리스트
    private static boolean[] visited;
    private static int[] hackCounts; // 각 노드마다 해킹할 수 있는 컴퓨터 개수
    private static ArrayList<Integer> answers;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        // init
        max = 0;
        adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }
        hackCounts = new int[N + 1];
        answers = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            adjList[to].add(from);
        }

        solve();

        // print
        System.out.println(sb.toString());
    }

    private static void solve() {
        for (int i = 1; i <= N; i++) {
            // 모든 노드에 대하여 dfs 탐색
            max = Math.max(max, getCounts(i));
        }

        // 최대값에 해당하는 노드 번호 구해서 리스트에 저장
        for (int i = 1; i <= N; i++) {
            if (hackCounts[i] == max) {
                sb.append(i).append(" ");
            }
        }
    }

    private static int getCounts(int index) {
        visited = new boolean[N + 1]; // 탐색마다 visited 초기화
        counts = 0;

//        dfs(index);
        bfs(index);
        hackCounts[index] = counts;

        return counts;
    }

//    private static void dfs(int cur) {
//        visited[cur] = true;
//
//        for (int next : adjList[cur]) {
//            if (!visited[next]) { // 방문하지 않았으면
//                counts++;
//                dfs(next);
//            }
//        }
//    }

    private static void bfs(int index) {
        visited[index] = true;
        Queue<Integer> q = new LinkedList<>();
        q.offer(index);

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int next : adjList[cur]) {
                if (!visited[next]) { // 방문하지 않았으면
                    visited[next] = true;
                    counts++;
                    q.offer(next);
                }
            }
        }
    }

}

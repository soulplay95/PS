package programmers.level2; /**
 * @문제
 * @날짜 220702
 * @분류
 * @조건
 * @풀이
 * @comments
 * # 정답의 최대치:
 * # 시간 복잡도:
 */

import java.util.*;

class PROG_86971 {

    static ArrayList<Integer>[] adjList;
    static int answer;

    public int solution(int n, int[][] wires) {
        // init
        answer = 100;
        adjList = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adjList[i] = new ArrayList<>();
        }
        // 인접 리스트 구성
        for (int[] wire : wires) {
            int from = wire[0];
            int to = wire[1];
            adjList[from].add(to);
            adjList[to].add(from);
        }

        // 모든 간선을 하나씩 끊어본다.
        for (int[] wire : wires) {
            int from = wire[0];
            int to = wire[1];

            // 해당 간선을 끊은 상태로 한 노드에서 bfs 탐색한다.
            bfs(from, to, n);
        }

        return answer;
    }

    static void bfs(int from, int to, int n) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        q.offer(from);
        visited[from] = true;
        int counts = 1;

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int adjVertex : adjList[cur]) {
                if (adjVertex == to) continue;
                if (!visited[adjVertex]) {
                    q.offer(adjVertex);
                    counts++;
                    visited[adjVertex] = true;
                }
            }
        }

        // 정답 갱신
        answer = Math.min(answer, Math.abs(counts - (n - counts)));
    }
}

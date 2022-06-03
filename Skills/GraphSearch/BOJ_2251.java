package algorithm.graph_search;

/**
 * @문제 물통 [G5]
 * @날짜 220603
 * @분류 그래프 탐색 / DFS / BFS
 * @조건
 * 1 <= A, B, C <= 200
 * @풀이
 * - 현재 물의 양을 하나의 상태(정점)로 생각하고, 물을 붓는 행위를 간선이라 생각한다.
 * @comments
 * # 정답의 최대치: Integer
 * # 시간 복잡도: O(200^3) => (max A, B, C)^3
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_2251 {

    static StringBuilder sb = new StringBuilder();

    static class State {
        int[] amount;

        State(int[] _amount) {
            amount = new int[MAX_COUNTS];
            for (int i = 0; i < MAX_COUNTS; i++) {
                amount[i] = _amount[i];
            }
        }

        // from -> to
        State move(int from, int to, int[] limit) {
            int[] newAmount = new int[] {amount[0], amount[1], amount[2]};

            if (amount[from] + amount[to] <= limit[to]) { // from 에 있는 물을 전부 비운 경우
                newAmount[to] = newAmount[from] + newAmount[to];
                newAmount[from] = 0;
            } else { // from 에 있는 물의 일부만 비울 수 있는 경우(to 의 물이 가득 차는 경우)
                newAmount[from] -= limit[to] - newAmount[to];
                newAmount[to] = limit[to];
            }

            return new State(newAmount);
        }
    }

    static int[] limit;
    static boolean[] possible;
    static boolean[][][] visited;

    static final int MAX_COUNTS = 3;
    static final int MAX_LIMIT = 200;

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        // init
        limit = new int[MAX_COUNTS];
        possible = new boolean[MAX_LIMIT + 1];
        visited = new boolean[MAX_LIMIT + 1][MAX_LIMIT + 1][MAX_LIMIT + 1];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < 3; i++) {
            limit[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void solve() {
        bfs(0, 0, limit[2]);

        // print
        for (int i = 0; i <= MAX_LIMIT; i++) {
            if (possible[i]) {
                sb.append(i).append(' ');
            }
        }
        System.out.println(sb.toString());
    }

    static void bfs(int a, int b, int c) {
        Queue<State> q = new LinkedList<>();
        visited[a][b][c] = true;
        q.add(new State(new int[]{a, b, c}));

        while (!q.isEmpty()) {
            State cur = q.poll();

            // 정답 체크
            if (cur.amount[0] == 0) {
                possible[cur.amount[2]] = true;
            }

            // 물을 붓는 6가지 경우의 수를 따져본다.
            for (int from = 0; from < MAX_COUNTS; from++) {
                for (int to = 0; to < MAX_COUNTS; to++) {
                    if (from == to) continue;

                    State newState = cur.move(from, to, limit); // 새로운 상태

                    if (!visited[newState.amount[0]][newState.amount[1]][newState.amount[2]]) {
                        visited[newState.amount[0]][newState.amount[1]][newState.amount[2]] = true;
                        q.add(newState);
                    }
                }
            }
        }
    }

}

/**
 * @문제 물통_S1
 * @날짜 220301
 * @분류 DFS / BFS
 * @조건
 * # 1 <= 최대 부피 (A, B, C) <= 200
 * @풀이
 * # 하나의 상태(각 물통에 현재 차있는 물의 양)를 정점으로 생각하여 물을 붓는 행위(6가지 경우) 중 문제 조건에 맞게 부을 수 있으면 간선이 있다고 생각한다.
 * @comments
 * # 정답의 최대치: Integer
 * - 최대 201^3가지 경우의 수
 * # 시간 복잡도: O(201^3)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_2251 {

    static StringBuilder sb = new StringBuilder();

    static class State {
        int[] volume;

        State(int[] _cur) {
            volume = new int[3];
            for (int i = 0; i < 3; i++) {
                volume[i] = _cur[i];
            }
        }

        // from 물통에서 to 물통으로 물을 옮긴다.
        State move(int from, int to) {
            int[] new_cur = new int[]{volume[0], volume[1], volume[2]};

            if (volume[from] + volume[to] >= maxVolume[to]) { // to 물통이 가득 차는 경우
                new_cur[from] -= maxVolume[to] - volume[to];
                new_cur[to] = maxVolume[to];
            } else { // from 물통이 먼저 바닥나는 경우
                new_cur[to] += new_cur[from];
                new_cur[from] = 0;
            }

            return new State(new_cur);
        }
    }

    static int[] maxVolume = new int[3];
    static boolean[][][] visited; // visited[i][j][k]: A, B, C 물통에 i, j, k만큼 물이 차 있는 경우를 따져본 경우 true
    static boolean[] answer;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        print();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < 3; i++) {
            maxVolume[i] = Integer.parseInt(st.nextToken());
        }
        // init
        answer = new boolean[maxVolume[2] + 1];
        visited = new boolean[maxVolume[0] + 1][maxVolume[1] + 1][maxVolume[2] + 1];
    }

    static void bfs(int a, int b, int c) {
        Queue<State> q = new LinkedList<>();
        visited[a][b][c] = true;
        q.offer(new State(new int[]{a, b, c}));

        while (!q.isEmpty()) {
            State cur = q.poll();

            // 정답 체크
            if (cur.volume[0] == 0) { // A 물통이 비어있다면
                answer[cur.volume[2]] = true;
            }

            // 물을 붓는 행위를 진행 => 총 6가지 경우
            for (int from = 0; from < 3; from++) {
                for (int to = 0; to < 3; to++) {
                    if (from == to) {
                        continue;
                    }

                    State newState = cur.move(from, to);
                    if (!visited[newState.volume[0]][newState.volume[1]][newState.volume[2]]) {
                        visited[newState.volume[0]][newState.volume[1]][newState.volume[2]] = true;
                        q.offer(newState);
                    }
                }
            }
        }
    }

    static void solve() {
        bfs(0, 0, maxVolume[2]);

        for (int i = 0; i <= maxVolume[2]; i++) {
            if (answer[i]) {
                sb.append(i).append(" ");
            }
        }
    }

    static void print() {
        System.out.println(sb.toString());
    }

}

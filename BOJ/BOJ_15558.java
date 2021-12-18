package study.week47;

/**
 * @문제 점프 게임_S1
 * @날짜 211217
 * @분류
 * @조건
 * # 1 <= 칸 개수, 점프 폭 (N, K) <= 10만
 * @풀이
 * # BFS
 * @comment
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_15558 {

    private static class UserInfo {
        int line, pos, time; // line : 어느 줄에 있는지, pos : 0부터 시작, time : 시간

        public UserInfo(int line, int pos, int time) {
            this.line = line;
            this.pos = pos;
            this.time = time;
        }
    }

    private static int N, K;
    private static String[] lines;

    private static final int LEFT = 0;
    private static final int RIGHT = 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        // init
        lines = new String[2];
        lines[LEFT] = br.readLine();
        lines[RIGHT] = br.readLine();

        // print
        System.out.println(solve());
    }

    private static int solve() {
        Queue<UserInfo> q = new LinkedList<>();
        boolean[][] visited = new boolean[2][N];
        q.offer(new UserInfo(LEFT, 0, 0));

        while (!q.isEmpty()) {
            UserInfo cur = q.poll();

            // 한 칸 앞으로 이동
            if (cur.pos + 1 >= N) {
                return 1;
            }
            if (!visited[cur.line][cur.pos + 1] && lines[cur.line].charAt(cur.pos + 1) != '0') {
                visited[cur.line][cur.pos + 1] = true;
                q.offer(new UserInfo(cur.line, cur.pos + 1, cur.time + 1));
            }

            // 한 칸 뒤로 이동
            if (cur.pos - 1 > cur.time && !visited[cur.line][cur.pos - 1] && lines[cur.line].charAt(cur.pos - 1) != '0') { // 이동하고 나서의 위치(cur.pos - 1)가 이동 후의 시간(cur.time + 1) 보다 커야한다.
                visited[cur.line][cur.pos - 1] = true;
                q.offer(new UserInfo(cur.line, cur.pos - 1, cur.time + 1));
            }

            // 반대편 줄로 +K칸 점프
            if (cur.pos + K >= N) {
                return 1;
            }
            if (cur.line == LEFT) {
                if (!visited[RIGHT][cur.pos + K] && lines[RIGHT].charAt(cur.pos + K) != '0') {
                    visited[RIGHT][cur.pos + K] = true;
                    q.offer(new UserInfo(RIGHT, cur.pos + K, cur.time + 1));
                }
            } else {
                if (!visited[LEFT][cur.pos + K] && lines[LEFT].charAt(cur.pos + K) != '0') {
                    visited[LEFT][cur.pos + K] = true;
                    q.offer(new UserInfo(LEFT, cur.pos + K, cur.time + 1));
                }
            }
        }

        return 0;
    }

}

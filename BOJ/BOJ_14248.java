/**
 * @문제 점프 점프_S2
 * @날짜 220108
 * @분류 그래프 탐색
 * @조건
 * # 1 <= 돌 개수 (n) <= 10만
 * @풀이
 * # 출발점에서 왼쪽, 오른쪽으로 dfs 하면서 방문하지 않은 칸의 개수를 센다.
 * @comment
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_14248 {

    private static int N, ans;
    private static int[] jumpDistances; // 각 칸에서 점프할 수 있는 거리
    private static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());
        // init
        jumpDistances = new int[N + 1];
        visited = new boolean[N + 1];
        ans = 1;

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            jumpDistances[i] = Integer.parseInt(st.nextToken());
        }
        int startPoint = Integer.parseInt(br.readLine());

        dfs(startPoint);

        // print
        System.out.println(ans);
    }

    private static void dfs(int point) {
        visited[point] = true;

        // 왼쪽 지점
        int leftPoint = point - jumpDistances[point];
        if (leftPoint > 0 && !visited[leftPoint]) { // 범위를 벗어나지 않고 방문하지 않았으면
            ans++;
            dfs(leftPoint);
        }

        // 오른쪽
        int rightPoint = point + jumpDistances[point];
        if (rightPoint <= N && !visited[rightPoint]) { // 범위를 벗어나지 않고 방문하지 않았으면
            ans++;
            dfs(rightPoint);
        }
    }

}

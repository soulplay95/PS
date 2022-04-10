package study.w220411;

/**
 * @문제 회사 문화 1_G4
 * @날짜 220410
 * @분류
 * @조건
 * # 2 <= 직원 수(n), 최초 칭찬 횟수(m) <= 10만
 * # 1 <= 칭찬 수치(w) <= 1000
 * @풀이
 * # dfs(O(V + E))를 최대 m번(10만) 수행? => 시간 초과
 * # 1. 트리를 단방향 인접 리스트로 표현한다.
 * # 2. 칭찬 받은 노드 번호를 인덱스로 가진 배열에 칭찬 받은 수치 w로 초기화한다.
 * # 3. 1번 노드(사장)에서 dfs 탐색 하여 칭찬 수치를 누적한다.
 * @comments
 * # 정답의 최대치: Integer -> 최대 칭찬 횟수(10만) * 최대 칭찬 수치(1천) <= 1억
 * # 시간 복잡도: O(V + E)
 * # 공간 복잡도: O(V + E)
 */

import java.io.*;
import java.util.*;

public class BOJ_14267 {

    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static ArrayList<Integer>[] adjList; // 인접 리스트
    static int[] dp; // dp[i]: i번 노드의 누적 칭찬 수치

    public static void main(String[] args) throws IOException {
        input();
        solve();
        print();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        // init
        adjList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }
        dp = new int[N + 1];

        // 인접 리스트 구성하기
        st = new StringTokenizer(br.readLine(), " ");
        for (int cur = 1; cur <= N; cur++) {
            int parent = Integer.parseInt(st.nextToken());
            if (parent == -1) {
                continue;
            }

            adjList[parent].add(cur);
        }

        // 초기 칭찬 수치 저장
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine(), " ");
            int i = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            dp[i] += w;
        }
    }

    static void dfs(int cur) {
        // 자식 노드들로 뻗어 나가면서 칭찬 수치를 누적한다.
        for (int child : adjList[cur]) {
            dp[child] += dp[cur];
            dfs(child);
        }
    }

    static void solve() {
        dfs(1); // 1번 노드부터 dfs 탐색
    }

    static void print() {
        for (int i = 1; i <= N; i++) {
            sb.append(dp[i]).append(" ");
        }

        System.out.println(sb.toString());
    }

}

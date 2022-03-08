/**
 * @문제 경로 찾기_S1
 * @날짜 220308
 * @분류 그래프 / 플로이드-와샬
 * @조건
 * # 1 <= 정점 개수 (N) <= 100
 * @풀이
 * # Floyd-Warshall
 * @comments
 * # 정답의 최대치:
 * # 시간 복잡도: O(N^3)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_11403 {

    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[][] adjMatrix;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        print();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        // init
        adjMatrix = new int[N][N];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int c = 0; c < N; c++) {
                adjMatrix[r][c] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void solve() {
        for (int mid = 0; mid < N; mid++) {
            for (int start = 0; start < N; start++) {
                for (int end = 0; end < N; end++) {
                    if (adjMatrix[start][mid] == 1 && adjMatrix[mid][end] == 1) {
                        adjMatrix[start][end] = 1;
                    }
                }
            }
        }
    }

    static void print() {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                sb.append(adjMatrix[r][c]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

}

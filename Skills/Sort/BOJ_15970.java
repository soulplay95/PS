package algorithm.sort;

/**
 * @문제 화살표 그리기 [S4]
 * @날짜 220519
 * @분류 완전 탐색 / 정렬
 * @조건
 * # 2 <= N <= 5000
 * @풀이
 * # 색깔별로 좌표를 모아서 정렬 후, 가장 가까운 점(왼쪽 혹은 오른쪽 점과의 거리)과의 거리를 계산한다.
 * @comments
 * # 정답의 최대치: Integer
 * # 시간 복잡도: O(NlogN)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_15970 {

    static StringBuilder sb = new StringBuilder();

    static int N, sum;
    static ArrayList<Integer>[] coordinatesByColors;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        print();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        // init
        coordinatesByColors = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            coordinatesByColors[i] = new ArrayList<>();
        }
        sum = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int coordinate = Integer.parseInt(st.nextToken());
            int color = Integer.parseInt(st.nextToken());

            coordinatesByColors[color].add(coordinate);
        }
    }

    static void solve() {
        // 각 색깔에 대해 좌표 오름차순 정렬 & 거리 합 구하기
        for (int color = 1; color <= N; color++) {
            Collections.sort(coordinatesByColors[color]);


            for (int i = 0, end = coordinatesByColors[color].size(); i < end; i++) {
                int coordinate = coordinatesByColors[color].get(i);

                int leftDistance = i == 0 ? Integer.MAX_VALUE : coordinate - coordinatesByColors[color].get(i - 1);
                int rightDistance = i == end - 1 ? Integer.MAX_VALUE : coordinatesByColors[color].get(i + 1) - coordinate;

                sum += Math.min(leftDistance, rightDistance);
            }
        }
    }

    static void print() {
        System.out.println(sum);
    }

}

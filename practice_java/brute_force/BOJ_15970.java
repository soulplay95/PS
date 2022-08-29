package practice.sort;

/**
 * @문제 화살표 그리기 [S4]
 * @날짜 220829
 * @분류 완탐 / 정렬
 * @조건
 * 2 <= N <= 5000
 * 0 <= x <= 10^5
 * @풀이
 * # 색깔별로 모아서 정렬 후, 좌우 중 더 짧은 거리를 구한다.
 * @복잡도
 * # 정답의 최대치: Integer
 * # 시간 복잡도: O(NlogN)
 * # 공간 복잡도:
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_15970 {

    static StringBuilder sb = new StringBuilder();

    static int N, answer;
    static ArrayList<Integer>[] positionByColor;

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
        answer = 0;
        positionByColor = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            positionByColor[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int pos = Integer.parseInt(st.nextToken());
            int color = Integer.parseInt(st.nextToken());
            positionByColor[color].add(pos);
        }
    }

    static void solve() {
        // 색깔 별 오름차순 정렬
        for (int i = 1; i <= N; i++) {
            Collections.sort(positionByColor[i]);

            for (int j = 0; j < positionByColor[i].size(); j++) {
                if (j == 0) {
                    answer += positionByColor[i].get(j + 1) - positionByColor[i].get(j);
                } else if (j == positionByColor[i].size() - 1) {
                    answer += positionByColor[i].get(j) - positionByColor[i].get(j - 1);
                } else {
                    answer += Math.min(
                            positionByColor[i].get(j + 1) - positionByColor[i].get(j),
                            positionByColor[i].get(j) - positionByColor[i].get(j - 1)
                    );
                }
            }
        }
    }

    static void print() {
        System.out.println(answer);
    }

}

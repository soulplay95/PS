package study.w220117;

/**
 * @문제 Fly me to the Alpha Centauri_G5
 * @날짜 220116
 * @분류 수학
 * @조건
 * # 0 <= 위치 < 2^31
 * @풀이
 * # 계단식 확장
 * # 도착 직전에 1광년만 이동해야 하므로 이동 거리 분포가 대충 산의 모양을 가져야 함
 * @comment
 * # https://st-lab.tistory.com/79
 */

import java.util.*;
import java.io.*;

public class BOJ_1011 {

    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            sb.append(getMinMoveCounts(y - x)).append("\n");
        }

        // print
        System.out.print(sb.toString());
    }

    private static int getMinMoveCounts(int distance) {
        int maxMoveDistance = (int) Math.sqrt(distance); // 최대 이동 거리

        if (maxMoveDistance == Math.sqrt(distance)) { // top이 루트(distance)인 대칭의 산 모양. 최대 이동거리가 늘어나는 첫 순간
            return maxMoveDistance * 2 - 1;
        } else if (distance <= maxMoveDistance * maxMoveDistance + maxMoveDistance) { // max길이의 절반 구간
            return maxMoveDistance * 2;
        } else { // 나머지 구간
            return maxMoveDistance * 2 + 1;
        }
    }

}

package study.w220207;

/**
 * @문제 빗물_G5
 * @날짜 220206
 * @분류 구현, 시뮬레이션
 * @조건
 * # 1 <= 맵 가로, 세로 길이 <= 500
 * @풀이
 * # Stack
 * @comment
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_14719 {

    private static int R, C, answer, max;
    private static Stack<Integer> heights = new Stack<>();

    public static void main(String[] args) throws IOException, NumberFormatException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        // init
        answer = 0;
        max = 0; // Stack 내 최대 블록 높이

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < C; i++) {
            int height = Integer.parseInt(st.nextToken());

            // Case에 따라 시뮬레이션
            if (heights.isEmpty()) { // Stack이 비어 있으면
                heights.push(height); // push
                max = height; // 최대 높이 초기화
            } else {
                if (i == C - 1) { // 마지막 블록인 경우
                    // 마지막 블록의 높이와 기존 max 블록의 높이 중 낮은 블록이 기준이 되어 차이값들을 계산
                    int min = Math.min(max, height);
                    while (!heights.isEmpty()) {
                        int temp = heights.pop();
                        int diff = min - temp;
                        if (diff > 0) {
                            answer += diff;
                        } else {
                            min = temp;
                        }
                    }
                    break;
                }

                if (height >= max) { // Stack 내의 최대 블록 높이보다 크거나 같은 경우
                    // max 높이를 가진 블럭이 pop 될 때까지 중간에 있는 블록(max보다 높이가 낮아 push 되었던)들을 pop 한다.
                    int temp = 0;
                    while ((temp = heights.pop()) != max) {
                        answer += max - temp; // max와 pop된 블록의 높이 차 만큼 물이 고이게 된다.
                    }

                    // 최대값 초기화
                    max = height;
                }
                heights.push(height); // push
            }
        }

        // print
        System.out.println(answer);
    }

}

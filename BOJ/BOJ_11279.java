/**
 * @문제 최대 힙_S2
 * @날짜 220211
 * @분류 자료 구조, 우선순위 큐
 * @조건
 * # 1 <= 연산의 개수 <= 10만
 * @풀이
 * # 최대 힙 => Collections.reverseOrder()
 * @comment
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_11279 {

    private static int N;
    private static PriorityQueue<Integer> maxHeap;

    public static void main(String[] args) throws IOException, NumberFormatException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        // init
        maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());

        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(br.readLine());

            if (x == 0) {
                if (maxHeap.isEmpty()) {
                    sb.append(0).append("\n");
                } else {
                    sb.append(maxHeap.poll()).append("\n");
                }
            } else {
                maxHeap.offer(x);
            }
        }

        // print
        System.out.print(sb.toString());
    }

}

/**
 * @문제 최소 힙_S2
 * @날짜 220211
 * @분류 자료 구조, 우선순위 큐
 * @조건
 * # 1 <= 연산 개수 <= 10만
 * @풀이
 * # 최소 힙
 * @comment
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_1927 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(br.readLine());

            if (x == 0) {
                if (minHeap.isEmpty()) {
                    sb.append(0).append("\n");
                } else {
                    sb.append(minHeap.poll()).append("\n");
                }
            } else {
                minHeap.offer(x);
            }
        }

        // print
        System.out.print(sb.toString());
    }

}

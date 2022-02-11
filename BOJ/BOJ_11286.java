/**
 * @문제 절댓값 힙_S1
 * @날짜 220211
 * @분류 자료 구조, 우선순위 큐
 * @조건
 * # 1 <= 연산 개수 <= 10만
 * @풀이
 * # Comparator를 정의한 Heap
 * @comment
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_11286 {

    private static int N;
    private static PriorityQueue<Integer> customHeap = new PriorityQueue<Integer>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            int absolute1 = Math.abs(o1);
            int absolute2 = Math.abs(o2);

            if (absolute1 == absolute2) {
                return Integer.compare(o1, o2);
            }

            return Integer.compare(absolute1, absolute2);
        }
    });

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(br.readLine());

            if (x == 0) {
                if (customHeap.isEmpty()) {
                    sb.append(0).append("\n");
                } else {
                    sb.append(customHeap.poll()).append("\n");
                }
            } else {
                customHeap.offer(x);
            }
        }

        // print
        System.out.print(sb.toString());
    }

}

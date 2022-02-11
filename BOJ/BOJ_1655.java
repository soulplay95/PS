/**
 * @문제 가운데를 말해요_G2
 * @날짜 220212
 * @분류 자료 구조, 우선순위 큐
 * @조건
 * # 1 <= 입력 개수 <= 10만
 * @풀이
 * # 전체 수열에서 앞부분 절반을 최대 힙, 나머지 절반을 최소 힙으로 생각한다.(최대힙-최소힙)
 * # 항상 최대 힙의 root 노드 값이 중간 값이 된다.
 * # 최대 힙부터 값을 넣는다.
 * # 값을 넣은 후 최대 힙의 루트 값과 최소 힙의 루트 값을 비교해서 최대 힙의 루트 값이 크다면 최소 힙의 루트 값과 교환한다.
 * @comment
 * # 중간 값을 빠르게 찾는 법 => 정렬된 전체 수열을 최대힙-최소힙 형태의 2개의 우선순위 큐로 관리한다.
 */

import java.util.*;
import java.io.*;

public class BOJ_1655 {

    private static int N;
    private static PriorityQueue<Integer> minHeap, maxHeap;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException, NumberFormatException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        // init
        minHeap = new PriorityQueue<Integer>();
        maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
        boolean isMaxHeapTurn = true; // 숫자를 최대 힙부터 최소 힙과 번갈아 넣기 위한 플래그

        for (int i = 0; i < N; i++) {
            int data = Integer.parseInt(br.readLine());

            // 힙에 삽입
            if (isMaxHeapTurn) {
                maxHeap.offer(data);
            } else {
                minHeap.offer(data);
            }

            // 정렬된 상태처럼 유지하기 위해 중간 부분(최대 힙의 root, 최소 힙의 root)값들을 비교하여 교환
            if (!maxHeap.isEmpty() && !minHeap.isEmpty() && maxHeap.peek() > minHeap.peek()) {
                int rootInMaxHeap = maxHeap.poll();
                int rootInMinHeap = minHeap.poll();

                // 스위칭
                maxHeap.offer(rootInMinHeap);
                minHeap.offer(rootInMaxHeap);
            }

            sb.append(maxHeap.peek()).append("\n"); // 중간값(최대 힙의 root)
            isMaxHeapTurn = !isMaxHeapTurn; // turn 변경
        }

        // print
        System.out.print(sb.toString());
    }

}

import java.util.PriorityQueue;

/**
 * @문제 더 맵게
 * @날짜 220209
 * @분류 Heap
 * @조건
 * # 2 <= 음식 개수 <= 100만
 * # 0 <= 타겟 스코빌 지수 <= 10억
 * # 0 <= 각 음식의 스코빌 지수 <= 100만
 * @풀이
 * # 1. 최소 힙에 모든 음식의 스코빌 지수를 넣음 => (log1 + log2 + ... + log100만)
 * # 2. 힙에서 2번 pop 하는데, 처음 pop한 값이 K 이상이면 종료
 * # 3. 공식을 적용하여 힙에 insert
 * # 4. 2번부터 반복
 * @comment
 * # https://programmers.co.kr/learn/courses/30/lessons/42626
 * # 우선순위 큐 => 기본 최소 힙
 */

public class PROG_42626 {

    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int answer = 0;

        // 1. 모든 음식의 스코빌 지수를 우선순위 큐(최소 힙)에 insert
        for (int value : scoville) {
            pq.offer(value);
        }

        while (true) {
            // 2. 2개의 스코빌 지수를 pop 하여 첫번째 pop한 지수(최소값)가 K이상이면 종료
            int min = pq.poll();
            if (min >= K) {
                return answer;
            }

            if (pq.isEmpty()) { // poll한 값이 K이상이 아닌데, 더 이상 섞을 음식이 남아있지 않다면 -1 리턴
                return -1;
            }

            int nextMin = pq.poll();
            pq.offer(min + (nextMin * 2));

            answer++;
        }
    }

}

package programmers.level2;

import java.util.*;

class PROG_42626 {

    public int solution(int[] scoville, int K) {
        int answer = 0;
        int N = scoville.length;
        PriorityQueue<Integer> minScoville = new PriorityQueue<>(); // 최소값 2개를 빠르게 뽑기 위한 자료 구조

        // 1. 모든 음식의 스코빌 지수를 PQ에 담는다. => O(N)
        for (int i = 0; i < N; i++) {
            minScoville.offer(scoville[i]);
        }

        // 2. 한 번 섞을때 마다 최소값이 K 이상인지 체크하고 섞는다. => O(NlogN)
        while (!minScoville.isEmpty()) {
            if (minScoville.peek() >= K) {
                break;
            }

            // 섞기
            if (minScoville.size() < 2) { // 예외 처리 - 섞을 수 없는 경우
                return -1;
            }
            int newScoville = minScoville.poll() + (minScoville.poll() * 2);
            minScoville.offer(newScoville);
            answer++;
        }

        return answer;
    }
}

/*
import java.util.*;

class Solution {
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
}*/

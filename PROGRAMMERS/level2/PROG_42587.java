package programmers.level2;

import java.util.*;

class PROG_42587 {

    static class Document {
        int priority, location; // location: 최초 대기목록에서의 위치

        Document(int _priority, int _location) {
            priority = _priority;
            location = _location;
        }
    }

    public int solution(int[] priorities, int location) {
        // init
        int printCounts = 1;
        Queue<Document> q = new LinkedList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        // 1. 최초 대기목록 순서대로 큐에 넣고, 최대 중요도를 알기 위해 중요도를 PQ에 넣는다.
        for (int i = 0; i < priorities.length; i++) {
            q.offer(new Document(priorities[i], i));
            pq.offer(priorities[i]);
        }

        // 2. 인쇄 요청한 문서가 인쇄될 때 까지 시뮬레이션한다.
        while (true) {
            Document cur = q.poll();
            int maxPriorityInCurrentQueue = pq.peek(); // 현재 대기목록에서 최대 우선순위

            if (cur.priority == maxPriorityInCurrentQueue) { // 현재 문서가 최대 우선순위를 가지면
                // 출력
                if (cur.location == location) { // 정답 체크
                    return printCounts;
                }
                pq.poll();
                printCounts++;
            } else {
                // 다시 넣기
                q.offer(cur);
            }
        }
    }
}

/*
class Solution {
    private static class Document {
        int priority; // 중요도
        boolean isTarget; // 인쇄를 요청한 문서인지

        public Document(int priority, boolean isTarget) {
            this.priority = priority;
            this.isTarget = isTarget;
        }
    }

    public int solution(int[] priorities, int location) {
        int answer = 1; // 인쇄 카운트
        Queue<Document> q = new LinkedList<>();
        int maxPriority = 0; // 현재 큐 내에서 최대 중요도

        // 큐 구성
        for (int i = 0, end = priorities.length; i < end; i++) {
            if (i == location) {
                q.offer(new Document(priorities[i], true));
            } else {
                q.offer(new Document(priorities[i], false));
            }

            // max값 갱신
            maxPriority = Math.max(maxPriority, priorities[i]);
        }

        // 시뮬레이션
        while (true) {
            Document cur = q.poll();

            // 원하는 문서가 인쇄되면 => 현재 문서의 중요도가 max값이고 인쇄 요청한 문서이면
            if (cur.priority == maxPriority && cur.isTarget) {
                return answer;
            }

            if (cur.priority == maxPriority) {
                answer++; // 인쇄 카운트 증가
                // 최대값 갱신
                maxPriority = 0;
                Iterator<Document> it = q.iterator();
                while (it.hasNext()) {
                    maxPriority = Math.max(maxPriority, it.next().priority);
                }
            } else { // 나머지 인쇄 대기목록에 나보다 중요도가 높은 문서가 있으면
                q.offer(cur); // 다시 넣기
            }
        }
    }
}*/

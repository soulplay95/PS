/**
 * @문제 프린터
 * @날짜 220128
 * @분류 스택, 큐
 * @조건
 * # 1 <= 문서 개수 <= 100
 * @풀이
 * # 큐로 시뮬레이션
 * @comment
 * # https://programmers.co.kr/learn/courses/30/lessons/42587
 */

import java.util.*;

public class PROG_42587 {

    public static void main(String[] args) {
        System.out.println(solution(new int[] {1, 1, 9, 1, 1, 1}, 0));
    }

    private static class Document {
        int priority; // 중요도
        boolean isTarget; // 인쇄를 요청한 문서인지

        public Document(int priority, boolean isTarget) {
            this.priority = priority;
            this.isTarget = isTarget;
        }
    }

    public static int solution(int[] priorities, int location) {
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

}

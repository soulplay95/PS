/**
 * @문제 이중우선순위큐
 * @날짜 220211
 * @분류 Heap
 * @조건
 * # 1 <= 명령어 개수 <= 100만
 * @풀이
 * # 최소, 최대 힙 두가지를 선언한다.
 * # insert시 두 가지의 힙에 삽입
 * # 최대값 삭제 시 최대힙에서는 poll(), 최소 힙에서는 remove(Object target)으로 삭제
 * # 최소값 삭제 시 최소힙에서 poll(), 최대힙에서 remover()
 * @comment
 * # https://programmers.co.kr/learn/courses/30/lessons/42628
 * # Java에서 PriorityQueue의 remove(Object o) 메소드는 해당 객체를 찾는데 O(n), 삭제하는데 O(logn)의 시간복잡도를 가진다.
 */

import java.util.*;

public class PROG_42628 {

    public static int[] solution(String[] operations) {
        StringTokenizer st = null; // 문자열로 주어진 명령어를 나누기 위해 선언

        // 최소, 최대 힙 두가지로 관리
        PriorityQueue<Integer> minPQ = new PriorityQueue<Integer>();
        PriorityQueue<Integer> maxPQ = new PriorityQueue<Integer>(Collections.reverseOrder());

        // 명령 수행
        for (String operation : operations) {
            st = new StringTokenizer(operation);
            String command = st.nextToken();
            Integer data = Integer.parseInt(st.nextToken());

            if ("I".equals(command)) { // 삽입 명령
                // 최소, 최대 힙 2가지에 모두 삽입
                minPQ.offer(data);
                maxPQ.offer(data);
            } else if ("D".equals(command)) { // 삭제 명령
                if (minPQ.isEmpty() || maxPQ.isEmpty()) { // 큐가 비어있으면 해당 명령 무시
                    continue;
                }
                if (data == 1) { // 최대값 삭제
                    int target = maxPQ.poll();
                    minPQ.remove(target);
                } else if (data == -1) { // 최소값 삭제
                    int target = minPQ.poll();
                    maxPQ.remove(target);
                }
            }
        }

        int[] answer = new int[2];
        if (minPQ.isEmpty() || maxPQ.isEmpty()) { // 모든 명령 수행 후 큐가 비어 있으면
            answer[0] = 0;
            answer[1] = 0;
        } else {
            answer[0] = maxPQ.poll();
            answer[1] = minPQ.poll();
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(new String[] {
                "I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"
        }));
    }

}

/**
 * @문제 다리를 지나는 트럭
 * @날짜 220204
 * @분류 스택/큐
 * @조건
 * # 1 <= 다리 길이 <= 1만
 * # 1 <= 트럭 개수 <= 1만
 * @풀이
 * # 다리를 큐로 구현
 * @comment
 * # https://programmers.co.kr/learn/courses/30/lessons/42583
 */

import java.util.*;

public class PROG_42583 {

    public static int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> bridge_queue = new LinkedList<>(); // 다리 큐
        int time = 0; // 시간
        int current_weight = 0; // 현재 다리에 있는 트럭 무게 합

        // 모든 트럭에 대하여 반복
        for (int w : truck_weights) {
            // 다리 위 트럭 구성은 같아도 다리 길이만큼 이동하는 것을 나타내기 위해 무한 루프 사용
            while (true) {
                if (bridge_queue.isEmpty()) { // case1. 다리가 비어 있으면
                    // 트럭 한대의 무게는 weight 이하 이므로 무조건 다리에 올린다.
                    bridge_queue.offer(w);
                    current_weight += w;
                    time++; // 시간 증가
                    break; // 다음 트럭 고려
                } else if (bridge_length == bridge_queue.size()) { // case2. 다리가 꽉 찬 경우
                    // 가상의 0kg 트럭을 enqueue 하는 방식으로 구현했기 떄문에 다리 큐의 사이즈가 다리 길이와 같다면 맨 앞 트럭은 이동을 마쳤다는 소리
                    current_weight -= bridge_queue.poll();
                } else { // case3. 그 외 경우 무게를 고려하여 트럭을 다리 위에 올릴 수 있는지 따져봐야 한다.
                    if (current_weight + w > weight) { // 올리려는 트럭의 무게를 고려했을 때 다리가 버틸 수 있는 무게를 초과한다면 가상의 0kg 트럭을 다리에 올림
                        bridge_queue.offer(0);
                        time++;
                    } else { // 트럭을 올릴 수 있다면
                        bridge_queue.offer(w);
                        current_weight += w;
                        time++;
                        break;
                    }
                }
            }
        }

        return time + bridge_length; // 마지막 트럭이 이동하는 시간을 더해줌
    }

    public static void main(String[] args) {
        System.out.println(solution(2, 10, new int[] {7, 4, 5, 6}));
    }

}

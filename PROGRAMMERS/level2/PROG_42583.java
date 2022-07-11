package programmers.level2;

import java.util.*;

class PROG_42583 {

    // 정답의 최대치: Integer. min 무게(1)이하 까지 견딜 수 있는 max 길이(1만) 다리에 min 무게(1)인 트럭 max 개수(1만)가 전부 지날 때 => 10000^2 := 1억
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> q = new LinkedList<>();
        int time = 0;
        int currentWeight = 0; // 현재 다리 위 트럭 무게 합

        // 모든 트럭에 대하여 고려한다.
        for (int w : truck_weights) {
            while (true) {
                if (q.isEmpty()) { // 다리가 비어 있으면
                    // 트럭 한대의 무게는 weight 이하이므로 무조건 다리에 올린다.
                    q.offer(w);
                    currentWeight += w;
                    time++;
                    break; // 다음 트럭 고려
                } else if (q.size() == bridge_length) { // 다리가 꽉 찬 경우
                    currentWeight -= q.poll(); // 맨 앞 트럭이 이동을 마친 경우
                } else {
                    // 무게를 고려하여 트럭을 다리에 올릴 수 있는지 따져본다.
                    if (currentWeight + w > weight) {
                        // 다리 길이를 고려하기 위해 가상의 0kg 트럭을 다리에 올린다.
                        q.offer(0);
                        time++;
                    } else {
                        q.offer(w);
                        currentWeight += w;
                        time++;
                        break; // 다음 트럭 고려
                    }
                }
            }
        }

        return time + bridge_length; // 마지막 트럭이 이동하는 시간을 추가한다.
    }

}

/*
class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
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
}*/

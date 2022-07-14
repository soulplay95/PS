package programmers.level2;

import java.util.*;

class PROG_42584 {

    // 완전 탐색 => O(N^2)
    // 시간(주식 가격이 기록된 시점)을 관리하는 Stack을 활용 => O(N)
    public int[] solution(int[] prices) {
        // init
        int length = prices.length;
        int[] answer = new int[length];
        Stack<Integer> timeStack = new Stack<>();

        // 하나씩 스택에 넣으면서 현재 시점의 가격이 기존 주식 가격(기존 스택의 top index에 있는 price)보다 작으면 가격이 유지된 시간을 기록해준다.
        for (int time = 0; time < length; time++) {
            while (!timeStack.isEmpty() && prices[time] < prices[timeStack.peek()]) {
                // top에 기록된 시점 입장에서 가격이 떨어짐
                answer[timeStack.peek()] = time - timeStack.pop();
            }
            timeStack.push(time);
        }

        // Stack에 남아있는 시간: 끝까지 가격이 떨어지지 않은 시간
        while (!timeStack.isEmpty()) {
            int time = timeStack.pop();
            answer[time] = length - time - 1; // 마지막 시간과의 간격을 기록
        }

        return answer;
    }
}

/*
class Solution {
    public int[] solution(int[] prices) {
        int[] ans = new int[prices.length];
        Stack<Integer> stack = new Stack(); // 시간(특정 주식 가격이 등장한 시점)을 관리

        for (int i = 0, end = prices.length; i < end; i++) {
            // prices[i] : 현 시점의 주식 가격
            // prices[stack.peek()] : 특정 시간의 주식 가격
            while (!stack.isEmpty() && prices[i] < prices[stack.peek()]) { // 현 시점(i)의 주식 가격이 기존 시간(stack.peek())에서의 주식 가격보다 떨어졌다면
                ans[stack.peek()] = i - stack.pop(); // 기존의 특정 시간의 답은 현재 시간(i) - 특정 시간(stack.pop()) 이다.
            }
            stack.push(i); // 현 시점의 시간 index 값을 push
        }

        // 스택에 남아있는 시간 index => 끝까지 가격이 떨어지지 않은 주식
        while (!stack.isEmpty()) {
            ans[stack.peek()] = prices.length - stack.pop() - 1; // 해당 시점의 정답은 해당 시점에서 끝 시간과의 시간 간격
        }

        return ans;
    }
}*/

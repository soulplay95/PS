/**
 * @문제 주식가격
 * @날짜 220203
 * @분류 스택/큐
 * @조건
 * # 1 <= 각 가격 <= 1만
 * # 2 <= prices 길이 <= 10만
 * @풀이
 * # 이중 반복문으로 완탐 => O(n^2)
 * # 스택 => O(n)
 * - 시간 index를 관리하는 stack을 사용
 * @comment
 * # https://programmers.co.kr/learn/courses/30/lessons/42584
 */

import java.util.*;

public class PROG_42584 {

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

}

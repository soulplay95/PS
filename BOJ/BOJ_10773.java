/**
 * @문제 제로_S4
 * @날짜 220213
 * @분류 구현, 자료 구조, 스택
 * @조건
 * # 1 <= 부른 수 <= 10만
 * @풀이
 * # 가장 최근에 쓴 수를 지운다 => Stack
 * @comments
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_10773 {

    private static int K, sum;
    private static Stack<Integer> stack = new Stack<Integer>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        // init
        sum = 0;

        for (int i = 0; i < K; i++) {
            Integer num = Integer.parseInt(br.readLine());

            if (num == 0) {
                // Stack에서 pop
                stack.pop();
            } else {
                stack.push(num);
            }
        }

        // Stack을 순회하여 합을 구한다.
        while (!stack.isEmpty()) {
            sum += stack.pop();
        }

        // print
        System.out.println(sum);
    }

}

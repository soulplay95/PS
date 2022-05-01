package programmers; /**
 * @문제
 * @날짜 220501
 * @분류
 * @조건
 * #
 * @풀이
 * #
 * @comments
 * # 정답의 최대치:
 * # 시간 복잡도:
 * # 공간 복잡도:
 */

import java.util.*;

public class PROG_12980 {

    // 짝수, 홀수의 경우로 나누어 생각한다.
    // 순간이동을 최대한 활용하려면 현재 위치(N)에서 2로 나누어 떨어지지 않을 때 까지 최대한 나눈다.
    // 현재 위치가 2로 나누어 떨어지지 않으면(홀수이면) 최소 1칸은 점프한 후 다시 순간 이동을 최대한 활용
    public int solution(int n) {
        int ans = 0;

        while (n != 0) {
            if (n % 2 == 0) {
                n /= 2;
            } else {
                n--;
                ans++;
            }
        }

        return ans;
    }

}

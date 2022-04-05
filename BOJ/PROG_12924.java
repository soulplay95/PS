/**
 * @문제
 * @날짜 220405
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

public class PROG_12924 {

    public static void main(String[] args) {
        System.out.println(solution(15));
    }

    // 투포인터
    public static int solution(int n) {
        int answer = 0;

        int R = 0, sum = 0;
        for (int L = 1; L <= n; L++) {
            // L - 1을 구간에서 제외하기
            sum -= L - 1;

            // R을 옮길 수 있을 때 까지 옮기기
            while (R + 1 <= n && sum < n) {
                R++;
                sum += R;
            }

            // sum이 n이면 정답 추가
            if (sum == n) {
                answer++;
            }
        }

        return answer;
    }

}

/**
 * @문제
 * @날짜 220328
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

public class PROG_12982 {

    // 신청한 금액이 적은 부서부터 지원한다.
    // 1. d배열 오름차순 정렬 => O(NlogN)
    // 2. 신청한 금액이 적은 부서부터 지원 => O(N)
    public int solution(int[] d, int budget) {
        int answer = 0;

        Arrays.sort(d);

        for (int i = 0; i < d.length; i++) {
            int remain = budget - d[i];

            if (remain >= 0) {
                budget = remain;
                answer++;
            } else {
                break;
            }
        }

        return answer;
    }

}

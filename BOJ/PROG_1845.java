/**
 * @문제
 * @날짜 220327
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

public class PROG_1845 {
    // 1. nums 배열을 정렬한다. => O(NlogN)
    // 2. N/2개를 뽑는 과정에서 중복되는 번호를 제외하고 최대한 뽑는다. => O(N)
    public int solution(int[] nums) {
        int N = nums.length;
        int half = N / 2;

        Arrays.sort(nums);

        int answer = 1; // 하나 뽑은 상태에서 시작
        int previous = nums[0]; // 이전에 뽑은 종류 번호
        for (int i = 1; i < nums.length; i++) {
            if (answer == half) { // 이미 N/2 마리를 뽑았으면 종료
                break;
            }

            if (nums[i] == previous) { // 이미 뽑은 종류는 뽑지 않는다.
                continue;
            }

            // 현재 종류를 뽑는다.
            answer++;
            previous = nums[i];
        }

        return answer;
    }
}

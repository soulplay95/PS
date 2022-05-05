package programmers; /**
 * @문제
 * @날짜 220505
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

public class PROG_42885 {

    // 남아있는 사람 중 최소, 최대 몸무게를 가진 사람이 같이 탄다.
    // 1. 몸무게 순 정렬 => O(NlogN)
    // 2. 몸무게 순회하면서 구하기 => O(N)
    public int solution(int[] people, int limit) {
        // init
        int N = people.length;
        int answer = 0;

        Arrays.sort(people);

        int minIndex = 0;
        int maxIndex = N - 1;
        while (minIndex <= maxIndex) {
            if (people[minIndex] + people[maxIndex] <= limit) {
                minIndex++;
            }

            maxIndex--;
            answer++;
        }

        return answer;
    }
}

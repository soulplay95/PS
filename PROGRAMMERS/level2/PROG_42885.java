package programmers.level2;

import java.util.*;

class PROG_42885 {

    // 남아있는 사람 중 최소, 최대 몸무게를 가진 사람이 같이 탄다.
    // 1. 몸무게 순 오름차순 정렬 => O(NlogN)
    // 2. 몸무게 순회 => O(N)
    public int solution(int[] people, int limit) {
        // init
        int answer = 0;
        int minIndex = 0;
        int maxIndex = people.length - 1;

        Arrays.sort(people);

        while (minIndex <= maxIndex) {
            // 태울 수 있는 경우 둘 다 태우고 다음 상황 고려(minIndex++, maxIndex--)
            // 태울 수 없는 경우 최대 몸무게를 가진 사람만 태우고 다음 상황 고려(maxIndex--)
            if (people[minIndex] + people[maxIndex] <= limit) {
                minIndex++;
            }
            maxIndex--;

            answer++;
        }

        return answer;
    }
}

/*
class Solution {
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
}*/

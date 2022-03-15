/**
 * @문제 로또의 최고 순위와 최저 순위_Lv.1
 * @날짜 220315
 * @분류 구현
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

class PROG_77484 {

    public int[] solution(int[] lottos, int[] win_nums) {
        // 0의 개수, 맞춘 번호 개수를 카운트한다.
        // 최고 순위 = 맞춘 번호 개수 + 0의 개수
        // 최저 쉰위 = 맞춘 번호 개수
        int[] rank = {6, 6, 5, 4, 3, 2, 1}; // index: 맞춘 번호 개수, value: 순위
        int sameCounts = 0; // 맞춘 번호 개수
        int zeroCounts = 0; // 0의 개수

        // 두 배열 오름차순 정렬
        Arrays.sort(lottos);
        Arrays.sort(win_nums);

        // 0의 개수를 센다.
        for (int num : lottos) {
            if (num == 0) {
                zeroCounts++;
            } else {
                break;
            }
        }

        // 맞춘 번호 개수를 센다.
        int L = zeroCounts;
        int R = 0;
        for (; L < 6; L++) {
            for (; R < 6; R++) {
                if (lottos[L] == win_nums[R]) {
                    sameCounts++;
                } else if (lottos[L] < win_nums[R]) {
                    break;
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = rank[sameCounts + zeroCounts];
        answer[1] = rank[sameCounts];

        return answer;
    }

}

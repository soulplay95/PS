/**
 * @문제 모의고사
 * @날짜 220205
 * @분류 완전탐색
 * @조건
 * # 문제 수 <= 1만
 * @풀이
 * # 완전 탐색
 * - 수포자 1, 2, 3 번의 패턴과 반복되는 길이만큼 나머지 연산을 취해 정답 여부를 체크한다.
 * @comment
 * # https://programmers.co.kr/learn/courses/30/lessons/42840?language=java
 * # List를 Primitive type의 배열로 변환하는 방법
 * - list.stream().mapToInt(i->i.intValue()).toArray();
 */

import java.util.*;

public class PROG_42840 {

    public int[] solution(int[] answers) {
        int[] pattern1 = new int[] {1, 2, 3, 4, 5};
        int[] pattern2 = new int[] {2, 1, 2, 3, 2, 4, 2, 5};
        int[] pattern3 = new int[] {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        int[] answerCounts = new int[3];

        for (int i = 0, end = answers.length; i < end; i++) {
            int answer = answers[i];

            if (pattern1[i % pattern1.length] == answer) {
                answerCounts[0]++;
            }
            if (pattern2[i % pattern2.length] == answer) {
                answerCounts[1]++;
            }
            if (pattern3[i % pattern3.length] == answer) {
                answerCounts[2]++;
            }
        }

        // 최대값 구하기
        int max = answerCounts[0];
        for (int i = 1; i < 3; i++) {
            if (answerCounts[i] > max) {
                max = answerCounts[i];
            }
        }

        ArrayList<Integer> answer = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            if (max == answerCounts[i]) {
                answer.add(i + 1);
            }
        }

        Collections.sort(answer);

        int[] array = new int[answer.size()];
        for (int i = 0; i < answer.size(); i++) {
            array[i] = answer.get(i);
        }

        return array;
    }

}

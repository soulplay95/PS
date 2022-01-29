/**
 * @문제 K번째수
 * @날짜 220130
 * @분류 정렬
 * @조건
 * # 1 <= 배열 길이 <= 100
 * @풀이
 * # i ~ j 만큼을 리스트로 구성해서 정렬
 * @comment
 * # https://programmers.co.kr/learn/courses/30/lessons/42748
 */

import java.util.*;

public class PROG_42748 {

    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];

        for (int i = 0, e = commands.length; i < e; i++) {
            int start = commands[i][0] - 1;
            int end = commands[i][1] - 1;
            int targetIndex = commands[i][2] - 1;

            // 각 명령마다 리스트를 구성
            ArrayList<Integer> sub = new ArrayList<>();
            for (; start <= end; start++) {
                sub.add(array[start]);
            }

            // 리스트 오름차순 정렬
            Collections.sort(sub);

            // k번째 수 get
            answer[i] = sub.get(targetIndex);
        }

        return answer;
    }

}

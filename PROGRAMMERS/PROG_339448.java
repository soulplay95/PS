/**
 * @문제 스킬 체크 테스트 LEVEL1 문제1
 * @날짜 220124
 * @분류
 * @조건
 * #
 * @풀이
 * # list -> array
 * @comment
 * #
 */

import java.util.*;

public class PROG_339448 {

    public int[] solution(int[] arr) {
        if (arr.length == 1) {
            return new int[] {-1};
        }

        int minValue = arr[0]; // 최소값

        // 배열을 순회하여 최소값을 구한다.
        for (int i = 1, end = arr.length; i < end; i++) {
            if (arr[i] < minValue) {
                minValue = arr[i];
            }
        }

        // 최소값을 제외한 다른 값들을 리스트에 저장한다.
        ArrayList<Integer> listExceptMinValue = new ArrayList<>();
        for (int value : arr) {
            if (value == minValue) {
                continue;
            }
            listExceptMinValue.add(value);
        }

        return listExceptMinValue.stream().mapToInt(i -> i).toArray(); // 리스트 -> 배열
    }

}

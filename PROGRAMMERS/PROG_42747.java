/**
 * @문제 H-Index
 * @날짜 220202
 * @분류 정렬
 * @조건
 * # 1 <= 논문의 수 <= 1000
 * @풀이
 * # 논문 별 인용횟수를 정렬하고 h의 최대값을 찾는다.
 * @comment
 * # https://programmers.co.kr/learn/courses/30/lessons/42747
 */

import java.util.*;

public class PROG_42747 {

    public int solution(int[] citations) {
        // 1. citations 배열 오름차순 정렬
        Arrays.sort(citations);

        // 2. 배열 앞에서부터 탐색하면서 조건에 맞는 h의 최대값을 리턴
        int h = citations.length; // 해당 논문 포함 인용 횟수 이상인 개수
        for (int i = 0, end = citations.length; i < end; i++) {
            if (h <= citations[i]) {
                return h;
            }
            h--;
        }

        return h;
    }

}

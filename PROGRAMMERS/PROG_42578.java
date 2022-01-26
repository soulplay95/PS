/**
 * @문제 위장
 * @날짜 220126
 * @분류 해시
 * @조건
 * # 1 <= 의상 수 <= 30
 * @풀이
 * # 경우의 수 : 상의의 수를 a, 하의의 수를 b라고 했을 때 상의와 하의를 조합하는 경우의 수는 a * b
 * # 여기서 상의만 선택하고 하의는 선택하지 않거나, 하의만 선택하고 상의를 선택하지 않을 수 있으므로 이때의 경우의 수는 (a + 1) * (b + 1)
 * # 이때 아무것도 입지 않는 경우의 수를 빼주면 최종적으로 (a + 1) * (b + 1) - 1
 * # HashMap<의상 종류, 개수>로 종류별 의상 개수를 세서 공식으로 풀이
 * @comment
 * # https://programmers.co.kr/learn/courses/30/lessons/42578
 */

import java.util.*;

public class PROG_42578 {

    public int solution(String[][] clothes) {
        HashMap<String, Integer> countsPerType = new HashMap<>(); // 의상 종류 별 개수

        for (int r = 0, end = clothes.length; r < end; r++) {
            String clothType = clothes[r][1]; // 의상 종류

            countsPerType.put(clothType, countsPerType.getOrDefault(clothType, 0) + 1);
        }

        int answer = 1;
        for (int counts : countsPerType.values()) {
            answer *= counts + 1;
        }

        return answer - 1;
    }

}

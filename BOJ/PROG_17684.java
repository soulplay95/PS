package programmers;

/**
 * @문제
 * @날짜 220426
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

public class PROG_17684 {

    // 사전 => HashMap<String, Integer>
    public int[] solution(String msg) {
        int N = msg.length();
        ArrayList<Integer> answer = new ArrayList<>();
        HashMap<String, Integer> dict = new HashMap<>();
        // init - 사전 초기화
        for (int i = 0; i < 26; i++) {
            String word = String.valueOf((char)('A' + i));
            dict.put(word, i + 1);
        }

        int L = 0;
        int R = 1;
        while (L < N) {
            // R을 이동한다 => L ~ R의 단어가 사전에 있을때 까지
            while (R <= N) {
                String word = msg.substring(L, R);
                if (dict.containsKey(word)) {
                    R++;
                } else {
                    // 새로 사전에 등록
                    dict.put(word, dict.size() + 1);
                    break;
                }
            }

            // 색인 번호 출력
            R--;
            String word = msg.substring(L, R);
            answer.add(dict.get(word));

            L = R;
            R++;
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }

}

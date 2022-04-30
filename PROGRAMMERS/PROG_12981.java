package programmers; /**
 * @문제
 * @날짜 220501
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

public class PROG_12981 {

    // 이미 사용된 단어 => HashSet
    // i번째에서 실패 => (i % n) + 1번 사람이 (i / n) + 1 번째 차례에 탈락자가 된다.
    public int[] solution(int n, String[] words) {
        // init
        int[] answer = new int[2];
        HashSet<String> history = new HashSet<>(); // 단어 등장 히스토리
        history.add(words[0]);

        for (int i = 1; i < words.length; i++) {
            String word = words[i];

            if (!history.add(word) || !isValidate(words[i - 1], word)) { // 중복 여부 & 끝말잇기 여부 체크
                answer[0] = (i % n) + 1;
                answer[1] = (i / n) + 1;
                break;
            }
        }

        return answer;
    }

    static boolean isValidate(String before, String cur) {
        return (before.charAt(before.length() - 1) == cur.charAt(0));
    }

}

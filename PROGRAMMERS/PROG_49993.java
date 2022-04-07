/**
 * @문제
 * @날짜 220408
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PROG_49993 {

    static int[] order;

    static boolean isValidate(String skill_tree) {
        int stage = 1;

        for (int i = 0; i < skill_tree.length(); i++) {
            char c = skill_tree.charAt(i);

            if (order[c - 'A'] == 0) {
                continue;
            }

            if (order[c - 'A'] == stage) {
                stage++;
            } else {
                return false;
            }
        }

        return true;
    }

    // skill에 나온 순서대로 int[] 배열에 1부터 차례대로 값을 넣는다.
    // 모든 스킬 트리에 대해 order에 맞게 나열되었는지 확인한다.
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        order = new int[26];

        // skill 순서 배치
        for (int i = 1; i <= skill.length(); i++) {
            char c = skill.charAt(i - 1);

            order[c - 'A'] = i;
        }

        for (String skill_tree : skill_trees) {
            if (isValidate(skill_tree)) {
                answer++;
            }
        }

        return answer;
    }

}

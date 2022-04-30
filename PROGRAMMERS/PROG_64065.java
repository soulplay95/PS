package programmers;

/**
 * @문제
 * @날짜 220430
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

public class PROG_64065 {

    // 1. s 문자열 파싱 => 숫자만 뽑기
    // 2. 숫자 HashMap<숫자, 등장 횟수> 에 넣기
    // 3. HashMap value 기준 내림차순 정렬하기
    public int[] solution(String s) {
        // init
        HashMap<Integer, Integer> appearCounts = new HashMap<>(); // 숫자별 등장 횟수

        // 1. 문자열 파싱하여 HashMap에 넣기
        s = s.substring(2, s.length() - 2); // 문자열 양 끝에 {{}} 제거
        s = s.replace("},{", "-"); // 각 집합을 구분하는 기호 "-"로 변경
        String[] sets = s.split("-"); // 각 집합
        for (String set : sets) {
            String[] elements = set.split(","); // 집합의 각 원소
            for (String element : elements) {
                int number = Integer.parseInt(element); // String -> Integer

                // 2. HashMap에 등장 횟수 넣기
                appearCounts.put(number, appearCounts.getOrDefault(number, 0) + 1);
            }
        }

        // 3. HashMap value 기준 내림차순 정렬
        ArrayList<Map.Entry<Integer, Integer>> entryList = new ArrayList<Map.Entry<Integer, Integer>>(appearCounts.entrySet());
        Collections.sort(entryList, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return Integer.compare(o2.getValue(), o1.getValue());
            }
        });

        // 정답 배열 생성
        int[] answer = new int[entryList.size()];
        for (int i = 0; i < entryList.size(); i++) {
            answer[i] = entryList.get(i).getKey();
        }

        return answer;
    }

}

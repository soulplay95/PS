package programmers.level2;

import java.util.*;

class PROG_64065 {

    // 1. 문자열 s를 파싱하여 숫자만 뽑기
    // 2. 뽑은 숫자를 HashMap<Integer, Integer>으로 매핑하기 => key: 숫자, value: 등장 횟수
    // 3. HashMap을 value 기준 내림차순 정렬하게 가장 많이 등장한 숫자부터 튜플의 첫 번째 원소로 채우기
    public int[] solution(String s) {
        // init
        HashMap<Integer, Integer> appearCounts = new HashMap<>();

        // 1. s를 파싱하여 숫자만 뽑기
        String[] sets = s.substring(2, s.length() - 2).replace("},{", "-").split("-");
        for (String set : sets) {
            // a1,a2,...,an
            String[] numbers = set.split(",");
            for (String number : numbers) {
                int n = Integer.parseInt(number);
                appearCounts.put(n, appearCounts.getOrDefault(n, 0) + 1); // 2. 뽑은 숫자를 등장 횟수로 매핑
            }
        }

        // 3. value 기준 내림차순 정렬하기
        List<Map.Entry<Integer, Integer>> entryList = new ArrayList<Map.Entry<Integer, Integer>>(appearCounts.entrySet());
        Collections.sort(entryList, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return Integer.compare(o2.getValue(), o1.getValue());
            }
        });

        // 정답 구성
        int[] answer = new int[entryList.size()];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : entryList) {
            answer[index++] = entry.getKey();
        }

        return answer;
    }
}

/*
class Solution {
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
}*/

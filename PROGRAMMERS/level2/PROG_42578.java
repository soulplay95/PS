package programmers.level2;

import java.util.*;

class PROG_42578 {

    // 의상 종류가 각 t1, t2, ..., tn이라면
    // 조합의 수 = (t1 의상의 개수 + 1) * (t2 의상의 개수 + 1) * ... * (tn 의상의 개수 + 1) - 1
    // tn 의상의 개수 + 1 => tn의상 중 하나를 입거나(tn) + 입지 않거나(1)
    // -1 => 전부 안입는 경우 제외
    // HashMap<String, Integer> => key: 의상 종류, value: 의상 개수
    public int solution(String[][] clothes) {
        // init
        HashMap<String, Integer> clothesCountsMappedByType = new HashMap<>();

        // 의상 종류별 개수를 맵에 저장한다.
        for (String[] info : clothes) {
            String type = info[1];

            clothesCountsMappedByType.put(type, clothesCountsMappedByType.getOrDefault(type, 0) + 1);
        }

        int answer = 1;
        for (int counts : clothesCountsMappedByType.values()) {
            answer *= counts + 1;
        }

        return answer - 1;
    }

}

/*
class Solution {
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
}*/

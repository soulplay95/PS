package study.w220404;

/**
 * @문제 뉴스 클러스터링
 * @날짜 220401
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

public class PROG_17677 {

    static final int MUL = 65536;

    // 1. 두 글자씩 끊어서 다중 집합을 만든다. => O(N)
    // 2. str1의 다중 집합 원소들을 HashMap<원소, 개수>에 넣는다.
    // 3. str2의 다중 집합 원소들을 하나씩 꺼내어 HashMap에 있으면 개수를 하나 감소 시키고 교집합 크기, 합집합 크기를 1씩 증가시킨다. => O(N)
    // 3-1. 없으면 합집합의 크기만 증가시킨다.
    // 4. HashMap에 남은 개수들을 합집합의 크기에 더한다.
    public int solution(String str1, String str2) {
        // init
        HashMap<String, Integer> hm = new HashMap<>();
        int interCounts = 0; // 교집합 크기
        int unionCounts = 0; // 합집합 크기
        // 대문자, 소문자 차이 무시하기 위해 소문자로 만든다.
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        // str1을 두글자씩 끊어서 HashMap에 넣기
        for (int start = 0; start < str1.length() - 1; start++) {
            String element = str1.substring(start, start + 2);

            // 유효성 체크 - 두 문자 모두 알파벳
            if (Character.isAlphabetic(element.charAt(0)) && Character.isAlphabetic(element.charAt(1))) {
                hm.put(element, hm.getOrDefault(element, 0) + 1);
            }
        }

        // str2를 두글자씩 끊어서 HashMap에 포함되는지 여부를 체크하여 교집합, 합집합 크기 구하기
        for (int start = 0; start < str2.length() - 1; start++) {
            String element = str2.substring(start, start + 2);

            if (Character.isAlphabetic(element.charAt(0)) && Character.isAlphabetic(element.charAt(1))) {
                if (hm.containsKey(element)) {
                    if (hm.get(element) == 1) {
                        hm.remove(element);
                    } else {
                        hm.put(element, hm.get(element) - 1);
                    }
                    interCounts++;
                    unionCounts++;
                } else {
                    unionCounts++;
                }
            }
        }

        // hm에 남은 value 누적
        for (int counts : hm.values()) {
            unionCounts += counts;
        }

        return unionCounts == 0 ? MUL : (interCounts * MUL) / unionCounts;
    }

}

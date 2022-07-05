package programmers.level2;

import java.util.*;

class PROG_17677 {

    static final int MUL = 65536;

    // 1. 두 글자씩 끊어서 쪼개기 => O(N)
    // 1-1. 다중 집합 만들기. HashMap<String, Integer> => key: 두 글자, value: 개수
    // 2. str2에 대해서도 같은 과정을 거친다. => O(N)
    // 2-1. HashMap에 있으면 교집합 개수 + 1
    // 2-2. 없으면 합집합 개수(초기값은 str1의 다중집합 크기) + 1
    // 합집합 개수가 0이면 1
    // O(N)
    public int solution(String str1, String str2) {
        // init
        HashMap<String, Integer> hm = new HashMap<>();
        int intersectionCounts = 0;
        int unionCounts = 0;
        // 대소문자 구분 제거
        str1 = str1.toUpperCase();
        str2 = str2.toUpperCase();

        // 1. str1에 대해 두 글자씩 쪼개어 다중 집합을 구성한다.
        for (int i = 0; i < str1.length() - 1; i++) {
            String element = str1.substring(i, i + 2);
            if (isValidate(element)) {
                hm.put(element, hm.getOrDefault(element, 0) + 1);
                unionCounts++;
            }
        }

        // 2. str2에 대해 두 글자씩 쪼개어 map에 있는지 따져본다.
        for (int i = 0; i < str2.length() - 1; i++) {
            String element = str2.substring(i, i + 2);
            if (isValidate(element)) {
                if (hm.containsKey(element)) {
                    if (hm.get(element) == 1) {
                        hm.remove(element);
                    } else {
                        hm.put(element, hm.get(element) - 1);
                    }
                    intersectionCounts++;
                } else {
                    unionCounts++;
                }
            }
        }

        if (unionCounts == 0) {
            return MUL;
        }

        return (int) (((double) intersectionCounts / unionCounts) * MUL);
    }

    static boolean isValidate(String s) {
        char c1 = s.charAt(0);
        char c2 = s.charAt(1);
        // return (c1 >= 'A' && c1 <= 'Z') && (c2 >= 'A' && c2 <= 'Z');
        return Character.isAlphabetic(c1) && Character.isAlphabetic(c2);
    }
}

/*
class Solution {
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
}*/

package programmers.level2;

import java.util.*;

class PROG_42746 {

    // 내림차순 정렬
    // O(NlogN)
    public String solution(int[] numbers) {
        // int[] -> String[]
        String[] stringNumbers = new String[numbers.length];
        for (int i = 0; i < stringNumbers.length; i++) {
            stringNumbers[i] = String.valueOf(numbers[i]);
        }

        // 정렬
        Arrays.sort(stringNumbers, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o2 + o1).compareTo(o1 + o2);
            }
        });

        // 예외처리
        if (stringNumbers[0].equals("0")) {
            return "0";
        }

        // 정답 구성
        StringBuilder sb = new StringBuilder();
        for (String num : stringNumbers) {
            sb.append(num);
        }

        return sb.toString();
    }
}

/*
class Solution {
    public String solution(int[] numbers) {
        String[] stringNumbers = new String[numbers.length];
        for (int i = 0, end = numbers.length; i < end; i++) {
            stringNumbers[i] = String.valueOf(numbers[i]);
        }

        Arrays.sort(stringNumbers, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o2 + o1).compareTo(o1 + o2);
            }
        });

        if (stringNumbers[0].equals("0")) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        for (String s : stringNumbers) {
            sb.append(s);
        }

        return sb.toString();
    }
}*/

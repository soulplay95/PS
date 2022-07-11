package programmers.level2;

import java.util.*;

class PROG_42747 {

    // 정렬
    public int solution(int[] citations) {
        // init
        int length = citations.length;
        int h = length;

        Arrays.sort(citations);

        for (int i = 0; i < length; i++) {
            if (h <= citations[i]) {
                return h;
            }
            h--;
        }

        return h;
    }
}

/*
class Solution {
    public int solution(int[] citations) {
        // 1. citations 배열 오름차순 정렬
        Arrays.sort(citations);

        // 2. 배열 앞에서부터 탐색하면서 조건에 맞는 h의 최대값을 리턴
        int h = citations.length; // 해당 논문 포함 인용 횟수 이상인 개수
        for (int i = 0, end = citations.length; i < end; i++) {
            if (h <= citations[i]) {
                return h;
            }
            h--;
        }

        return h;
    }
}*/

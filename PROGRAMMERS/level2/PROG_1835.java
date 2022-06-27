package programmers.level2;

/**
 * @문제
 * @날짜 220628
 * @분류
 * @조건
 * @풀이
 * @comments
 * # 정답의 최대치:
 * # 시간 복잡도:
 */

import java.util.*;

class PROG_1835 {

    static HashMap<Character, Integer> mappedIndex; // key: 프렌즈 이름, value: 매핑된 index
    static int[] position; // position[i]: index i로 매핑된 프렌즈의 위치
    static int answer;

    static final int MAX_PEOPLE_COUNTS = 8;
    static final char[] PEOPLES = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};

    // 조건에 맞지 않으면 백트래킹 한다.
    // O(MAX_PEOPLE_COUNTS! * n)
    // 정답의 최대치: MAX_PEOPLE_COUNTS! => Integer
    public int solution(int n, String[] data) {
        preprocess();

        dfs(0, n, data);

        return answer;
    }

    static void preprocess() {
        // init
        mappedIndex = new HashMap<>();
        position = new int[MAX_PEOPLE_COUNTS];
        answer = 0;

        // 프렌즈들을 index로 매핑한다.
        for (int i = 0; i < MAX_PEOPLE_COUNTS; i++) {
            mappedIndex.put(PEOPLES[i], i);
            position[i] = -1; // -1: 위치가 정해지지 않음
        }
    }

    static void dfs(int depth, int n, String[] data) {
        // 백트래킹 - 현재까지 배치한 순서가 가능한지 따져본다.
        if (!isPossible(n, data)) return;

        // Base Condition - 조건에 맞도록 모두 배치한 경우
        if (depth == MAX_PEOPLE_COUNTS) {
            answer++;
            return;
        }

        for (int candidateIndex = 0; candidateIndex < MAX_PEOPLE_COUNTS; candidateIndex++) {
            if (position[candidateIndex] != -1) continue; // 이미 배치된 경우

            position[candidateIndex] = depth;
            dfs(depth + 1, n, data);
            position[candidateIndex] = -1;
        }
    }

    static boolean isPossible(int n, String[] data) {
        // 조건을 전부 따져본다.
        for (String condition : data) {
            // Parsing
            int fromIndex = mappedIndex.get(condition.charAt(0));
            int toIndex = mappedIndex.get(condition.charAt(2));
            char comparison = condition.charAt(3);
            int targetGap = condition.charAt(4) - '0';

            // 예외 처리 - 둘 중 하나라도 위치가 정해지지 않았으면
            if (position[fromIndex] == -1 || position[toIndex] == -1) continue;

            int gap = Math.abs(position[fromIndex] - position[toIndex]) - 1;
            // 비교 연산자에 따라 분기
            switch (comparison) {
                case '=':
                    if (gap != targetGap) return false;
                    break;
                case '<':
                    if (gap >= targetGap) return false;
                    break;
                case '>':
                    if (gap <= targetGap) return false;
                    break;
            }
        }

        return true;
    }
}

/*
import java.util.*;

class Solution {

    static HashMap<Character, Integer> mappedIndex;
    static int answer;
    static int[] position; // position[i]: i번 인덱스를 가진 프렌즈의 위치

    static final char[] PEOPLE_LIST = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    static final int MAX_PEOPLE = 8;

    static void init() {
        mappedIndex = new HashMap<>();
        answer = 0;
        position = new int[MAX_PEOPLE];
        Arrays.fill(position, -1); // 모든 프렌즈가 아직 자리가 정해지지 않았다는 표시

        // 각 프렌즈를 index로 매핑시킨다.
        for (int i = 0; i < MAX_PEOPLE; i++) {
            mappedIndex.put(PEOPLE_LIST[i], i);
        }
    }

    static boolean isPossible(int n, String[] data) {
        // 모든 조건 검사
        for (String constraint : data) {
            // Parsing
            int aIndex = mappedIndex.get(constraint.charAt(0));
            int bIndex = mappedIndex.get(constraint.charAt(2));
            char compare = constraint.charAt(3);
            int maxGap = constraint.charAt(4) - '0';

            // 둘 중 한명이라도 자리가 안정해졌으면
            if (position[aIndex] == -1 || position[bIndex] == -1) {
                continue;
            }

            int gap = Math.abs(position[aIndex] - position[bIndex]) - 1; // 실제 간격

            // 비교 조건에 따라 분기
            if (compare == '=') {
                if (gap != maxGap) {
                    return false;
                }
            } else if (compare == '<') {
                if (gap >= maxGap) {
                    return false;
                }
            } else if (compare == '>') {
                if (gap <= maxGap) {
                    return false;
                }
            }
        }

        return true;
    }

    static void dfs(int pos, int n, String[] data) {
        // 모든 조건을 만족하는지 검사
        if (!isPossible(n, data)) {
            return;
        }

        // 모든 조건을 만족하는 자리 배치를 구했으면
        if (pos == MAX_PEOPLE) {
            answer++;
            return;
        }

        // 자리가 정해지지 않은 프렌즈를 현재 위치(pos)에 배치한다.
        for (int i = 0; i < MAX_PEOPLE; i++) {
            if (position[i] == -1) {
                position[i] = pos;
                dfs(pos + 1, n, data);
                position[i] = -1;
            }
        }
    }

    // 백트래킹
    // 정답의 최대치: MAX_PEOPLE!
    // 8명을 나열하는 경우의 수 중 조건을 만족하지 않으면 백트래킹
    public int solution(int n, String[] data) {
        init();

        dfs(0, n, data);

        return answer;
    }

}*/

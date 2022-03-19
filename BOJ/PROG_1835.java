/**
 * @문제
 * @날짜 220319
 * @분류
 * @조건
 * #
 * @풀이
 * #
 * @comments
 * # 정답의 최대치:
 * # 시간 복잡도:
 * # 공간 복잡도:
 * https://programmers.co.kr/learn/courses/30/lessons/1835
 */

import java.util.*;

public class PROG_1835 {

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
    public static int solution(int n, String[] data) {
        init();

        dfs(0, n, data);

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(2, new String[] {
                "N~F=0", "R~T>2"
        }));
    }

}

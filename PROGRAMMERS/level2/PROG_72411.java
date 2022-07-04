package programmers.level2;

import java.util.*;

class PROG_72411 {

    public static void main(String[] args) {
        System.out.println(solution(new String[] {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"}, new int[] {2, 3, 4}));
    }

    static int[] counts; // counts[0]: 'A'가 메뉴로 등장한 횟수
    static ArrayList<Integer> menus; // 코스요리 메뉴 구성에 포함될 수 있는 단품 메뉴 후보들
    static ArrayList<String>[] answerList; // 정답 리스트
    static int[] maxOrderedCounts; // 각 코스별 최대 주문 횟수
    static int courseCounts;
    static int[] courses;

    public static String[] solution(String[] orders, int[] course) {
        // init
        counts = new int[26];
        courses = course;
        menus = new ArrayList<>();
        courseCounts = course.length;
        answerList = new ArrayList[courseCounts];
        for (int i = 0; i < courseCounts; i++) {
            answerList[i] = new ArrayList<>();
        }
        maxOrderedCounts = new int[courseCounts];

        // 1. 주문표를 보고 각 단품메뉴가 등장한 횟수를 기록하고, 정렬한다.
        for (int i = 0; i < orders.length; i++) {
            Arrays.sort(orders[i].toCharArray()); // 사전순 정렬
            // 등장 횟수 기록
            for (int j = 0; j < orders[i].length(); j++) {
                counts[orders[i].charAt(j) - 'A']++;
            }
        }

        // 2. 2회 이상 등장한 단품 메뉴만 후보로 구성한다.
        for (int i = 0; i < 26; i++) {
            if (counts[i] >= 2) {
                menus.add(i);
            }
        }

        // 3. 코스에 필요한 단품 메뉴 개수만큼 뽑고 유효한지 체크한다.
        for (int i = 0; i < courseCounts; i++) {
            recursive(0, 0, i, "", orders);
        }

        // 정답 구성
        int size = 0;
        for (int i = 0; i < courseCounts; i++) {
            size += answerList[i].size();
        }

        String[] answer = new String[size];
        int index = 0;
        for (int i = 0; i < courseCounts; i++) {
            for (String result : answerList[i]) {
                answer[index++] = result;
            }
        }

        return answer;
    }

    static void recursive(int depth, int start, int courseIndex, String result, String[] orders) {
        // Base condition
        if (depth == courses[courseIndex]) { // 다 뽑았으면
            check(orders, result.toString(), courseIndex);
            return;
        }

        for (int candidateIndex = start; candidateIndex < menus.size(); candidateIndex++) {
            recursive(depth + 1, candidateIndex + 1, courseIndex, result + ((char)(menus.get(candidateIndex) + 'A')), orders);
        }
    }

    static void check(String[] orders, String result, int courseIndex) {
        // result(단품 메뉴 조합)를 몇명이 주문했는지 계산한다.
        int orderedCounts = 0;
        for (String order : orders) {
            if (isIn(order, result)) {
                orderedCounts++;
            }
        }

        if (orderedCounts >= 2) {
            if (orderedCounts > maxOrderedCounts[courseIndex]) {
                maxOrderedCounts[courseIndex] = orderedCounts;
                answerList[courseIndex].clear();
                answerList[courseIndex].add(result);
            } else if (orderedCounts == maxOrderedCounts[courseIndex]) {
                answerList[courseIndex].add(result);
            }
        }
    }

    static boolean isIn(String order, String result) {
        for (int i = 0; i < result.length(); i++) {
            char c = result.charAt(i);
            if (!order.contains(String.valueOf(c))) {
                return false;
            }
        }
        return true;
    }

}

/*
import java.util.*;

class Solution {

    static Character[] allMenus;
    static int max;
    static Set<char[]> sets;
    static int R;
    static boolean[][] map;

    public String[] solution(String[] orders, int[] course) {
        List<String> answer = new ArrayList<>();

        // init
        map = new boolean[orders.length][26];
        Set<Character> foods = new HashSet<>();
        sets = new HashSet<>();
        R = orders.length;

        // 1. map 채우기 + 등장한 단품요리들로만 구성된 배열 만들기
        for (int i = 0; i < orders.length; i++) {
            String menus = orders[i];

            for (int j = 0, end = menus.length(); j < end; j++) {
                char menu = menus.charAt(j);
                foods.add(menu);
                map[i][menu - 'A'] = true;
            }
        }

        // 1-2. Set to Array
        allMenus = foods.toArray(new Character[0]);

        // 2. course 구성에 따라 x개 뽑음
        for (int i = 0; i < course.length; i++) {
            int count = course[i];

            max = 0;
            sets.clear();
            nCr(0, 0, count, new char[count]);

            // 리스트의 코스메뉴들 정답에 추가
            for (char[] cur : sets) {
                answer.add(cur.toString());
            }
        }

        // 배열로 변환 후 정렬
        String[] ret = answer.toArray(new String[0]);
        Arrays.sort(ret);

        return ret;
    }

    static void nCr(int cnt, int start, int end, char[] output) {
        if (cnt == end) {
            // 3. 뽑은 구성으로 리스트 갱신
            int count = 0;
            for (int r = 0; r < R; r++) {
                boolean flag = true;
                for (int i = 0; i < output.length; i++) {
                    char c = output[i];

                    if (!map[r][c - 'A']) {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    count++;
                }
            }

            if (max > count) {
                return;
            }

            // 최대값보다 많으면 리스트 비우고 다시 추가
            if (max < count) {
                sets.clear();
            }

            // 오름차순 정렬 후 추가
            Arrays.sort(output);
            sets.add(output);

            return;
        }

        for (int i = start; i < allMenus.length; i++) {
            output[cnt] = allMenus[i];
            nCr(cnt + 1, i + 1, end, output);
        }
    }
}*/

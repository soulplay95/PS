/**
 * @문제 메뉴 리뉴얼
 * @날짜 210911
 * @분류
 * @조건
 * # 코스요리 메뉴는 최소 2가지 이상의 단품메뉴로 구성
 * # 최소 2명 이상의 손님으로부터 주문된 단품 메뉴 조합만 가능
 * @풀이
 * # 26 x 손님 수(orders 배열 크기) 만큼의 2차원 배열을 만들고 주문한 요리 알파벳을 채워넣음
 * # 등장한 단품 요리 중 x개(course 배열의 원소)를 뽑는다.
 * # 뽑은 구성으로 개수를 세서 최대로 겹치는 조합을 정답으로 구성한다.
 * @comment
 *
 */

package kakao2021;

import java.util.*;
import java.io.*;

public class P2 {

    static Character[] allMenus;
    static int max;
    static Set<char[]> sets;
    static int R;
    static boolean[][] map;

    public static void main(String[] args) {
        String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
        int[] courses = {2, 3, 4};
        System.out.println(solution(orders, courses).length);
    }

    public static String[] solution(String[] orders, int[] course) {
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

            // 최대값보다 많으면 리스트 비우고 다시 추가
            if (max < count) {
                max = count;
                sets.clear();
                // 오름차순 정렬 후 추가
                Arrays.sort(output);
                sets.add(output);
            } else if (max == count) {
                // 오름차순 정렬 후 추가
                Arrays.sort(output);
                sets.add(output);
            }

            return;
        }

        for (int i = start; i < allMenus.length; i++) {
            output[cnt] = allMenus[i];
            nCr(cnt + 1, i + 1, end, output);
        }
    }

}

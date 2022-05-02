package programmers; /**
 * @문제
 * @날짜 220502
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

public class PROG_72411 {

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

}

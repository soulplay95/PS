/**
 * @문제 베스트셀러_S4
 * @날짜 220114
 * @분류 자료 구조, 문자열, 정렬, 해시
 * @조건
 * # 팔린 책 개수 (N) <= 1000
 * # 책 제목 길이 <= 50
 * @풀이
 * # HashMap<책 제목, 팔린 개수>로 관리하고 정렬
 * @comment
 * # HashMap 반복 => map.forEach();
 */

import java.util.*;
import java.io.*;

public class BOJ_1302 {

    private static int N;
    private static HashMap<String, Integer> sellingCounts;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        // init
        sellingCounts = new HashMap<>();

        for (int i = 0; i < N; i++) {
            String key = br.readLine();

            if (sellingCounts.containsKey(key)) {
                sellingCounts.put(key, sellingCounts.get(key) + 1);
            } else {
                sellingCounts.put(key, 1);
            }
        }

        // print
        System.out.println(solve());
    }

    private static String solve() {
        int max = 0; // 팔린 개수 최대값
        String ans = "";

        Iterator it = sellingCounts.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Integer> entry = (Map.Entry<String, Integer>) it.next();
            String key = entry.getKey();
            Integer value = entry.getValue();
            if (value > max) {
                max = value;
                ans = key;
            } else if (value == max) {
                // 사전순 비교
                if (key.compareTo(ans) < 0) { // key가 ans보다 사전순으로 앞서면 갱신
                    ans = key;
                }
            }
        }

        return ans;
    }

}

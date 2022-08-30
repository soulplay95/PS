package practice.sort;

/**
 * @문제 파일 정리 [S3]
 * @날짜 220830
 * @분류 자료 구조 / 문자열 / 정렬 / 해시 / 파싱 / 트리
 * @조건
 * 1 <= N <= 5만
 * 3 <= 파일 이름 길이 <= 100
 * @풀이
 * # 해시
 * - 확장자별로 매핑하여 개수를 카운트한다.
 * - 확장자 이름(key) 별로 사전순 정렬한다.
 * @복잡도
 * # 정답의 최대치: Integer
 * # 시간 복잡도: O(NlogN)
 * # 공간 복잡도:
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_20291 {

    static StringBuilder sb = new StringBuilder();

    static int N;
    static HashMap<String, Integer> countsPerExtension;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        print();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        // init
        countsPerExtension = new HashMap<>();

        for (int i = 0; i < N; i++) {
            String[] split = br.readLine().split("\\.");
            String extension = split[1];

            countsPerExtension.put(extension, countsPerExtension.getOrDefault(extension, 0) + 1);
        }
    }

    static void solve() {
        // key 기준 사전순 정렬
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(countsPerExtension.entrySet());
        Collections.sort(entryList, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getKey().compareTo(o2.getKey());
            }
        });

        // append
        for (Map.Entry<String, Integer> entry : entryList) {
            sb.append(entry.getKey()).append(" ").append(entry.getValue()).append("\n");
        }
    }

    static void print() {
        System.out.println(sb.toString());
    }

}

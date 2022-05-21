package algorithm.sort.practice;

/**
 * @문제 좌표 압축 [S2]
 * @날짜 220521
 * @분류 정렬 / 좌표 압축
 * @조건
 * # 1 <= N <= 100만
 * @풀이
 * # 정렬 후, HashMap<element, rank>를 기록한다.
 * @comments
 * # 정답의 최대치: Integer => max 10^9
 * # 시간 복잡도: O(NlogN)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_18870 {

    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[] numbers, sortedNumbers;
    static HashMap<Integer, Integer> rankMap;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        print();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        // init
        numbers = new int[N];
        sortedNumbers = new int[N];
        rankMap = new HashMap<>();

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            int number = Integer.parseInt(st.nextToken());
            numbers[i] = number;
            sortedNumbers[i] = number;
        }
    }

    static void solve() {
        Arrays.sort(sortedNumbers);

        // 정렬된 배열을 순회하면서 rank 기록
        int rank = 0;
        for (int key : sortedNumbers) {
            if (!rankMap.containsKey(key)) {
                rankMap.put(key, rank++);
            }
        }

        // 원래 배열 순회하면서 rank를 구한다.
        for (int i = 0; i < N; i++) {
            sb.append(rankMap.get(numbers[i])).append(" ");
        }
    }

    static void print() {
        System.out.println(sb.toString());
    }

}

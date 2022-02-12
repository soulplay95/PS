/**
 * @문제 요세푸스 문제 0_S4
 * @날짜 220213
 * @분류 구현, 자료 구조, 큐
 * @조건
 * # 1 <= K <= N <= 1000
 * @풀이
 * # Queue
 * # 인덱스
 * @comments
 * # 원을 그리며 나열된 수열 구현
 * 1. Queue에서 k-1번 poll하여 k-1번 offer
 * 2. 리스트로 인덱스 + (k - 1) % 리스트 사이즈
 */

import java.util.*;
import java.io.*;

public class BOJ_11866 {

    private static int N, K;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        // init
        sb.append("<");

//        solve();
        solve2();

        sb.setLength(sb.length() - 2);
        sb.append(">");

        // print
        System.out.println(sb.toString());

        sc.close();
    }

    private static void solve() {
        // Queue를 활용한 풀이
        Queue<Integer> q = new LinkedList<Integer>();

        // 1. 1 ~ N번까지의 번호 큐에 넣기
        for (int i = 1; i <= N; i++) {
            q.offer(i);
        }

        // 2. 큐가 빌때 까지 반복
        while (!q.isEmpty()) {
            // K - 1번 poll하고 offer
            for (int i = 1; i < K; i++) {
                q.offer(q.poll());
            }

            // K번째 poll한 값 출력
            sb.append(q.poll()).append(", ");
        }
    }

    private static void solve2() {
        // List의 target index 조정을 통한 풀이
        ArrayList<Integer> list = new ArrayList<Integer>();
        int targetIndex = 0; // 리스트의 인덱스는 0부터 시작

        // 1. List에 1 ~ N 까지의 번호 add
        for (int i = 1; i <= N; i++) {
            list.add(i);
        }

        // 2. List의 size가 0이 될때까지 반복
        while (list.size() != 0) {
            // K - 1 : 반복마다 리스트에서 제거되므로, 제거된 후의 인덱스는 제거되기 전에 비해 1만큼 밀린 상태
            // % list.size() : 인덱스가 리스트 범위를 넘지 않게 하기 위함
            targetIndex = (targetIndex + (K - 1)) % list.size();

            sb.append(list.remove(targetIndex)).append(", ");
        }
    }

}

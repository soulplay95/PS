/**
 * @문제 회전하는 큐_S4
 * @날짜 220215
 * @분류 자료 구조 / 덱
 * @조건
 * # 1 <= 큐의 크기 <= 50
 * # 위치(인덱스)는 1부터 시작
 * @풀이
 * # 1. 초기 큐에 포함된 숫자들의 인덱스 번호를 Dequeue에 넣는다.
 * # 2. 타겟 인덱스를 최소 연산으로 뽑기 위해
 * - 2번 연산만 수행했을 때 필요한 연산 횟수 vs 3번 연산만 수행했을 때 필요한 연산 횟수(큐의 현재 사이즈 - 2번 연산만 수행했을 때 필요한 연산 횟수)를 비교하여 적은 쪽의 연산을 진행한다.
 * # 3. 타겟 숫자를 모두 뽑을때까지 반복한다.
 * @comments
 * # 그리디?
 */

import java.util.*;
import java.io.*;

public class BOJ_1021 {

    private static int N, M, answer;
    private static int[] targetIndexes;
    private static Deque<Integer> dq = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        // init
        answer = 0;
        targetIndexes = new int[M];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < M; i++) {
            targetIndexes[i] = Integer.parseInt(st.nextToken());
        }

        solve();

        // print
        System.out.println(answer);
    }

    private static void solve() {
        // 초기 큐에 포함된 숫자들의 인덱스 번호를 큐에 넣음
        for (int i = 1; i <= N; i++) {
            dq.offer(i);
        }

        // 타겟 숫자를 모두 뽑을 떄 까지 반복
        int index = 0;
        while (index < M) {
            // 2번 연산과 3번 연산의 연산 횟수 비교하여 적은 쪽 연산 선택
            int leftShiftCounts = 0; // 2번 연산 횟수
            Deque<Integer> copy = new LinkedList<>(dq);
            // 타겟 넘버를 찾을 때 까지 2번 연산 반복 수행
            while (true) {
                Integer head = copy.poll(); // 큐의 첫 번째 원소

                if (head == targetIndexes[index]) {
                    break;
                }

                copy.offer(head);
                leftShiftCounts++;
            }

            int rightShiftCounts = dq.size() - leftShiftCounts;
            if (leftShiftCounts < rightShiftCounts) {
                answer += leftShiftCounts;
                dq = copy;
            } else {
                answer += rightShiftCounts;
                // 3번 연산 수행
                while (true) {
                    Integer tail = dq.pollLast();

                    if (tail == targetIndexes[index]) {
                        break;
                    }

                    dq.offerFirst(tail);
                }
            }

            index++; // 다음 타겟
        }
    }

}

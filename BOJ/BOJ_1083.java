package study.w220207;

/**
 * @문제 소트_G4
 * @날짜 220206
 * @분류 그리디, 정렬
 * @조건
 * # 배열 크기 <= 50
 * # 원소 값 <= 100만
 * # 0 <= 최대 교환 횟수 <= 100만
 * @풀이
 * # 버블 정렬 => 내림차순
 * # swap시 카운팅 하여 S번 내에서 최대한 버블 정렬의 단계를 밟는다.
 *
 * - 최대 교환 횟수가 정렬 방식에 영향을 미친다.
 * - 최대 교환 횟수 내에서 최선의 선택
 *  - 첫 번째 인덱스 자리부터 따져본다.
 *  - 이후의 원소들과 비교하여 거리(해당 원소를 이 자리에 위치시키기 위해 필요한 최소 swap 횟수)가 남은 교환 횟수 내에 있고
 *  - 제일 큰 값과 swap한다.
 * @comment
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_1083 {

    private static int N, S;
    private static int[] array;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());
        // init
        array = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }
        S = Integer.parseInt(br.readLine());

        solve2();

        // print
        print();
    }

    private static void solve() {
        if (S == 0) {
            return;
        }

        int swapCounts = 0; // swap이 일어나면 증가하는 카운트
        final int MAX_SWAP_COUNTS = N * (N - 1) / 2; // 최대 swap 횟수

        // Bubble sort - 내림차순
        // 최대 swap 횟수에 제한이 있기 때문에 체크하는 방향은 기존 버블 정렬 처럼 왼쪽에서 오른쪽으로 보되,
        // 기존 버블 정렬이 한 단계가 끝나면 제일 큰 수의 위치가 결정되는 반면에 이 방식은 내림 차순으로 정렬하고자 하므로
        // 한 단계가 끝나도 위치가 결정되지 않음 => 매 단계마다 모든 원소 비교
        while (true) {
            for (int i = 1; i < N; i++) {
                if (array[i - 1] < array[i]) {
                    swap(i - 1, i);
                    swapCounts++;
                }

                // swap counts 체크
                if (swapCounts >= S || swapCounts >= MAX_SWAP_COUNTS) {
                    return;
                }
            }
        }
    }

    private static void solve2() {
        // 첫 번째 자리부터 따져본다.
        for (int i = 0; i < N; i++) {
            // 해당 자리 이후의 원소들 중 남은 교환 횟수 내에 swap이 가능하면서 제일 큰 값과 swap한다.
            int maxValue = array[i];
            int maxIndex = i;
            for (int j = i + 1; j < N; j++) {
                if (j - i > S) {
                    break;
                }
                if (array[j] > maxValue) {
                    maxValue = array[j];
                    maxIndex = j;
                }
            }

            swap2(i, maxIndex);
            S -= maxIndex - i;

            if (S == 0) {
                return;
            }
        }
    }

    private static void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static void swap2(int start, int end) {
        while (start != end) {
            int temp = array[end - 1];
            array[end - 1] = array[end];
            array[end] = temp;
            end--;
        }
    }

    private static void print() {
        for (int num : array) {
            System.out.print(num + " ");
        }
    }

}

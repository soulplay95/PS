package study.w220229;

/**
 * @문제 독서실 거리두기_G5
 * @날짜 220226
 * @분류 구현 / 시뮬레이션
 * @조건
 * # 1 <= 좌석 개수 (N) <= 100
 * # 1 <= 예약자 수 (T) <= 500
 * @풀이
 * # 시뮬레이션
 * # 한명 한명을 어느 좌석에 앉힐 것인가 => 가장 먼 좌석 번호 계산이 key
 * - 좌석에 앉는 순서 결정: 예약 정보(시작, 종료 시간)를 시작 시간 기준 오름차순, 같으면 이용 시간(종료 시간 - 시작 시간)순으로 오름차순 정렬한다. => O(TlogT)
 * - 좌석 별 예약 종료 시간 정보를 담은 배열(endTime[i]: i번 좌석의 예약이 끝나는 시간)을 두고 한명씩 자리에 앉힐때마다 저장한다.
 * - 자리에 앉힐 때, endTime 배열을 순회하여 현재 예약의 시작 시간보다 작거나 같은 종료시간을 가진 좌석은 제외하고 나머지 앉아 있는 좌석 정보로 가장 먼 좌석을 계산하여 앉힌다. => O(N)
 * - 만약, 앉게 되는 좌석이 타겟 좌석이면 이용 시간만큼 정답에서 뺀다.
 * @comments
 * # 시간 복잡도: O(NT)
 * # 공간 복잡도: O(T)
 * # 정답의 최대치: Integer(<= 720)
 */

import java.io.*;
import java.util.*;

public class BOJ_20665 {

    static StringBuilder sb = new StringBuilder();

    static class Reservation implements Comparable<Reservation> {
        int start, end; // 시작, 종료 시각(분)

        public Reservation(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Reservation o) {
            if (this.start == o.start) {
                return Integer.compare(this.end - this.start, o.end - o.start);
            }
            return Integer.compare(this.start, o.start);
        }
    }

    static int N, T, P, ans;
    static Reservation[] reservations; // 예약 정보
    static int[] endTime; // 좌석 별 종료 시간을 저장

    public static void main(String[] args) throws IOException {
        input();
        solve();
        print();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        // init
        ans = 720;
        reservations = new Reservation[T];
        endTime = new int[N + 1];

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            // 분 단위로 변환
            start = (start / 100) * 60 + (start % 100);
            end = (end / 100) * 60 + (end % 100);

            reservations[i] = new Reservation(start, end);
        }
    }

    static void solve() {
        Arrays.sort(reservations);

        for (int i = 0; i < T; i++) {
            Reservation cur = reservations[i];

            // 모든 좌석의 착석 여부, 종료 시간을 따져보고 앉게 될 좌석 번호를 구한다.
            int before = 1;
            int target = 0;
            int maxGap = 0; // 좌석간 최대 간격
            boolean isFirst = true;
            for (int j = 1; j <= N; j++) {
                if (endTime[j] <= cur.start) { // 이미 예약이 끝난 좌석이면 스킵
                    endTime[j] = 0; // 빈 좌석 체크
                    continue;
                }

                if (isFirst) { // 처음 등장한 좌석이면
                    isFirst = false;
                    if (j == 1) {
                        continue;
                    }
                    // 1번 좌석에 앉아야 제일 멀리 떨어짐
                    target = 1;
                    maxGap = j - 1;
                } else {
                    int gap = (j - before) / 2;
                    if (gap > maxGap) {
                        maxGap = gap;
                        target = before + gap;
                    }
                }

                before = j;
            }

            // 마지막에 등장한 좌석의 gap 계산
            if (N - before > maxGap) {
                target = N; // N번 좌석에 앉음
            }

            // 모든 좌석이 비어있다면
            if (isFirst) {
                target = 1; // 1번 좌석에 착석
            }

            // 갱신
            endTime[target] = cur.end;
            if (target == P) {
                ans -= cur.end - cur.start; // 이용 시간만큼 정답에서 차감
            }
        }
    }

    static void print() {
        System.out.println(ans);
    }

}

/**
 * @문제 징검다리
 * @날짜 220310
 * @분류 이분탐색
 * @조건
 * #
 * @풀이
 * #
 * @comments
 * # 정답의 최대치:
 * # 시간 복잡도:
 * # 공간 복잡도:
 * https://programmers.co.kr/learn/courses/30/lessons/43236?language=java
 */

import java.io.*;
import java.util.*;

public class PROG_43236 {

    public boolean determination(int gap, int[] rocks, int n) {
        // 정해진 제거 횟수 이하 만큼 제거하면서 바위를 놓을 수 있으면 true 리턴
        // 출발 지점에서 가까운 바위부터 놓아본다.
        int deleteCounts = n;
        int lastPos = 0; // 이전에 놓은 바위 위치 => 거리를 계산하기 위함
        for (int i = 0; i < rocks.length; i++) {
            if (rocks[i] - lastPos < gap) { // 두 바위 위치 사이의 거리가 설정한 최소 거리(gap)보다 작으면
                // 놓을 수 없으므로 바위 제거
                deleteCounts--;

                // 최대 제거 횟수 이상이 필요하다면 놓을 수 없음
                if (deleteCounts < 0) {
                    return false;
                }
            } else {
                lastPos = rocks[i];
            }
        }

        return true;
    }

    public int solution(int distance, int[] rocks, int n) {
        // 정답을 1~10억 사이에서 이분 탐색으로 찾는다.
        // 출발 지점에서 가까운 곳에 위치한 바위부터 놓을 수 있는지를 체크한다.
        // 주어진 바위 개수를 x라 할 때, O(xlogx + xlog(distance))

        // 바위 위치 오름차순 정렬
        Arrays.sort(rocks);

        int L = 1, R = distance, answer = 0;
        while (L <= R) {
            int mid = (L + R) / 2;

            if (determination(mid, rocks, n)) { // mid 거리만큼 벌리면서 바위를 놓을 수 있으면
                answer = mid;
                L = mid + 1; // 더 큰 탐색 범위로 늘린다.
            } else {
                R = mid - 1;
            }
        }

        return answer;
    }

}

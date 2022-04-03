/**
 * @문제
 * @날짜 220404
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

public class PROG_12941 {

    // 합이 최소가 되려면 각 원소(두 수를 곱한 값)가 최소가 되어야 한다.
    // 두 수를 곱했을 때 최소가 되려면 두 수의 차이가 클수록 최소가 된다.
    // 따라서, A의 최소값 * B의 최대값부터 곱해서 누적한다.
    // O(NlogN)
    public int solution(int []A, int []B) {
        // 각 배열 오름차순 정렬
        Arrays.sort(A);
        Arrays.sort(B);

        int answer = 0;
        int N = A.length;
        for (int i = 0; i < N; i++) {
            answer += A[i] * B[N - i - 1];
        }

        return answer;
    }

}

/**
 * @문제 소수 만들기_Lv.1
 * @날짜 220317
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PROG_12977 {

    static int answer;
    static boolean[] isPrime;

    static final int MAX_SUM = 3001;

    public static void main(String[] args) {
        System.out.println(solution(new int[] {1, 2, 3, 4}));
    }

    static void init() {
        answer = 0;
        isPrime = new boolean[MAX_SUM];
        isPrime[0] = isPrime[1] = true;

        // 3000이하의 수 중 소수 구하기
        // isPrime[i]: false이면 소수
        for (int i = 2; i <= Math.sqrt(MAX_SUM - 1); i++) {
            if (isPrime[i]) { // 소수가 아니면(방문 체크되었으면)
                continue;
            }

            // i의 배수들을 제외시킨다.
            for (int j = i * 2; j < MAX_SUM; j += i) {
                isPrime[j] = true;
            }
        }
    }

    static void combination(int depth, int start, int sum, int N, int[] nums) {
        if (depth == 3) {
            if (!isPrime[sum]) {
                answer++;
            }
            return;
        }

        for (int i = start; i < N; i++) {
            combination(depth + 1, i + 1, sum + nums[i], N, nums);
        }
    }

    public static int solution(int[] nums) {
        // 정답의 최대치: 50C3 = 19600 < Integer
        // 시간복잡도: O(NC3 * 소수판별)
        // 소수인지 체크하는 수는 3 ~ 3000 사이의 수 이므로, 3000이하의 수 중 소수인 수를 구해놓는다.
        init();
        combination(0, 0, 0, nums.length, nums);

        return answer;
    }

}

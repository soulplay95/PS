/**
 * @문제 스킬 체크 테스트 LEVEL1 문제2
 * @날짜 220124
 * @분류
 * @조건
 * #
 * @풀이
 * # 약수의 개수 구하기
 * @comment
 * #
 */

public class PROG_339448_2 {

    public int solution(int left, int right) {
        int answer = 0;

        while (left <= right) {
            if (getDivisorCounts(left) % 2 == 0) { // 약수의 개수가 짝수이면
                answer += left; // 더하기
            } else { // 홀수이면
                answer -= left; // 빼기
            }

            left++;
        }

        return answer;
    }

    private int getDivisorCounts(int num) {
        int counts = 0;

        for (int i = 1; i * i <= num; i++) { // O(sqrt(num))
            if (i * i == num) {
                counts++;
                break;
            }

            if (num % i == 0) {
                counts += 2;
            }
        }

        return counts;
    }

}

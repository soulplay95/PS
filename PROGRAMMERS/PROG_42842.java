/**
 * @문제 카펫
 * @날짜 220208
 * @분류 완전탐색
 * @조건
 * # 3 <= 격자 가로, 세로 길이
 * @풀이
 * # 식 (a >= b)
 * - 2 * (a + b) = brown - 4
 * - a * b = yellow
 * @comment
 * # https://programmers.co.kr/learn/courses/30/lessons/42842?language=java
 */

public class PROG_42842 {

    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int plusAnswer = (brown - 4) / 2;

        for (int height = 1; height <= 2000; height++) {
            for (int width = height; width <= 2000; width++) {
                int plus = height + width;
                int multiply = height * width;

                if (plus > plusAnswer || multiply > yellow) {
                    break;
                } else if (plus == plusAnswer && multiply == yellow) {
                    answer[0] = width + 2;
                    answer[1] = height + 2;
                    return answer;
                }
            }
        }

        return answer;
    }

}

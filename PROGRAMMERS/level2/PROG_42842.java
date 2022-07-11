package programmers.level2;

class PROG_42842 {

    static final int MAX = 2500;

    /*
# 네 모서리를 제외하고 가로의 길이(원래 길이 - 2)를 a, 세로 길이를 b라고 한다면
- 2 * (a + b) = brown - 4
- a * b = yellow
- 이 식에서 a >= b인 경우가 정답
*/
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int targetPlus = (brown - 4) / 2;

        for (int c = 1; c <= MAX; c++) {
            for (int r = c; r <= MAX; r++) {
                int plus = r + c;
                int multiply = r * c;
                if (plus > targetPlus || multiply > yellow) {
                    break;
                } else if (plus == targetPlus && multiply == yellow) {
                    answer[0] = r + 2;
                    answer[1] = c + 2;
                    return answer;
                }
            }
        }

        return answer;
    }
}

/*
# 네 모서리를 제외하고 가로의 길이(원래 길이 - 2)를 a, 세로 길이를 b라고 한다면
- 2 * (a + b) = brown - 4
- a * b = yellow
- 이 식에서 a >= b인 경우가 정답
*/
/*
class Solution {
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
}*/

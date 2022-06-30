package programmers.level2;

/**
 * @문제
 * @날짜 220630
 * @분류
 * @조건
 * @풀이
 * @comments
 * # 정답의 최대치:
 * # 시간 복잡도:
 */


public class PROG_60057 {

    public static void main(String[] args) {
        System.out.println(solution("aabbaccc"));
    }

    // 1 ~ (n / 2)개 단위까지 잘라본다.
    public static int solution(String s) {
        int N = s.length();
        int answer = N;

        for (int unit = 1; unit <= N / 2; unit++) {
            int L = 0, R = unit;
            String prev = s.substring(L, R);
            L = R;
            R += unit;
            int sameCounts = 1;
            int result = 0;

            while (R <= N) {
                String cur = s.substring(L, R);
                if (prev.equals(cur)) {
                    sameCounts++;
                } else {
                    result += unit;
                    if (sameCounts > 1) {
                        result += String.valueOf(sameCounts).length();
                    }
                    sameCounts = 1;
                }
                L = R;
                R += unit;
                prev = cur;
            }
            result += unit;
            if (sameCounts > 1) {
                result += String.valueOf(sameCounts).length();
            }
            result += N - L;

            answer = Math.min(answer, result);
        }

        return answer;
    }

}

/*class Solution {
    public int solution(String s) {
        int answer = 0;

        int len = s.length();
        answer = len;

        // 1개 단위부터 압축을 시도
        for (int i = 1, end = len / 2; i <= end; i++) {
            answer = Math.min(answer, getLength(s, i));
        }

        return answer;
    }

    static int getLength(String s, int g) {
        int startIndex = 0;
        int endIndex = g;
        int len = s.length();
        int ret = len;
        int sameCounts = 1;

        while (true) {
            String before = s.substring(startIndex, endIndex);
            startIndex += g;
            endIndex += g;
            if (endIndex > len) {
                if (sameCounts > 1) {
                    ret -= g * sameCounts;
                    ret += g + 1;
                }
                break;
            }
            String after = s.substring(startIndex, endIndex);

            if (before.equals(after)) {
                sameCounts++;
            } else {
                if (sameCounts > 1) {
                    ret -= g * sameCounts;
                    ret += g + 1;
                    sameCounts = 1;
                }
            }
        }

        return ret;
    }
}*/

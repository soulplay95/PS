/**
 * 문자열 압축
 * @풀이
 * # 주어진 문자열의 절반 길이만큼 1개 ~ 단위로 문자열 압축을 시도한다.
 */

package kakao2020;

public class P1 {

    public static void main(String[] args) {
        System.out.println(solution("aab"));
    }

    public static int solution(String s) {
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

}

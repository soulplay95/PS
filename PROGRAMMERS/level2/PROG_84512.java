package programmers.level2;

class PROG_84512 {

    static final String VOWEL = "AEIOU";

    // A, AA, AAA, AAAA
    // AAAAA, AAAAE, AAAAI, AAAAO, AAAAU
    // AAAE
    // AAAEA, AAAEE, AAAEI, AAAEO, AAAEU
    // AAAI
    // ...
    // UUUUU
    // => 문자열의 각 자리마다
    // - 맨 끝자리: 1씩 증가
    // - 그 앞자리(4번째 자리): 6씩 증가(1 * 5 + 1)
    // - 그 앞자리(3번째 자리): 31씩 증가(6 * 5 + 1)
    // ...
    public int solution(String word) {
        int length = VOWEL.length();
        int[] offset = new int[length];

        // 각 자리에 더해지는 가중치 값 구하기
        offset[length - 1] = 1;
        for (int i = length - 2; i >= 0; i--) {
            offset[i] = offset[i + 1] * length + 1;
        }

        // 각 자리에 따른 가중치 값 누적하기
        int answer = word.length();
        for (int i = 0; i < word.length(); i++) {
            int index = VOWEL.indexOf(word.charAt(i));
            answer += offset[i] * index;
        }

        return answer;
    }
}

// 규칙 찾기
// A, AA, AAA, AAAA
// AAAAA, AAAAE, AAAAI, AAAAO, AAAAU
// AAAE
// AAAEA, AAAEE, AAAEI, AAAEO, AAAEU
// AAAI
// ...
// => 문자열의 각 자리마다 맨 끝자리의 경우 1씩 증가
// 그 앞자리(4번째 자리)의 경우 6씩 증가(1 * 5 + 1)
// 그 앞자리(3번째 자리)의 경우 31씩 증가(6 * 5 + 1)
// ...
/*
class Solution {

    public int solution(String word) {
        String vowel = "AEIOU";
        int[] x = {781, 156, 31, 6, 1};
        int result = word.length();
        for(int i = 0; i < word.length(); i++) {
            int index = vowel.indexOf(word.charAt(i));
            result += x[i] * index;
        }

        return result;
    }
}*/

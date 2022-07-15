package programmers.level2;

class PROG_70129 {

    static final String END = "1";

    // 1. x의 모든 0을 제거 => replaceAll("0", "")
    // 2. x의 길이를 2진법으로 표현한 문자열 구하기 => Integer.toBinaryString()
    public int[] solution(String s) {
        // init
        int[] answer = new int[2];

        while (!END.equals(s)) {
            // 1
            String deletedZeroString = s.replaceAll("0", "");
            answer[1] += s.length() - deletedZeroString.length(); // 제거된 0의 개수 구하기

            // 2
            s = Integer.toBinaryString(deletedZeroString.length());
            answer[0]++;
        }

        return answer;
    }
}

/*
class Solution {
    // 1. 모든 0을 제거 => replaceAll()
    // 2. 2진법으로 표현한 문자열 => Integer.toBinaryString()
    public int[] solution(String s) {
        // init
        int[] answer = new int[2];
        String targetString = "1";

        while (!s.equals(targetString)) {
            String newZeroDeletedString = s.replaceAll("0", ""); // 모든 0을 제거
            answer[1] += s.length() - newZeroDeletedString.length(); // 제거된 0 개수 누적

            s = Integer.toBinaryString(newZeroDeletedString.length()); // 변환

            answer[0]++; // 변환 횟수 1증가
        }

        return answer;
    }
}*/

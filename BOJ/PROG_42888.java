/**
 * @문제 오픈채팅방
 * @날짜 220311
 * @분류 구현
 * @조건
 * # 1 <= record 길이 <= 10만
 * @풀이
 * # 해시맵으로 uid, 닉네임을 매핑시킨다.
 * @comments
 * # 정답의 최대치:
 * # 시간 복잡도: O(N)
 * # 공간 복잡도:
 */

import java.util.*;

public class PROG_42888 {

    public String[] solution(String[] record) {
        HashMap<String, String> nicknamePerUserId = new HashMap<>();

        int length = 0;
        for (String log : record) {
            String[] input = log.split(" ");

            if (input[0].equals("Enter")) {
                nicknamePerUserId.put(input[1], input[2]);
                length++;
            } else if (input[0].equals("Leave")) {
                length++;
            } else if (input[0].equals("Change")){
                nicknamePerUserId.put(input[1], input[2]);
            }
        }

        String[] answer = new String[length];
        int index = 0;
        for (String log : record) {
            String[] input = log.split(" ");

            if (input[0].equals("Enter")) {
                answer[index++] = String.valueOf(nicknamePerUserId.get(input[1])) + "님이 들어왔습니다.";
            } else if (input[0].equals("Leave")) {
                answer[index++] = String.valueOf(nicknamePerUserId.get(input[1])) + "님이 나갔습니다.";
            }
        }

        return answer;
    }

}

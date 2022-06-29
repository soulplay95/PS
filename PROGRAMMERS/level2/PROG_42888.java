package programmers.level2;

/**
 * @문제
 * @날짜 220629
 * @분류
 * @조건
 * @풀이
 * @comments
 * # 정답의 최대치:
 * # 시간 복잡도:
 */

import java.util.*;

class PROG_42888 {

    static class Log {
        String uid;
        int type; // 0: Enter, 1: Leave

        Log(String _uid, int _type) {
            uid = _uid;
            type = _type;
        }
    }

    static final int ENTER = 0;
    static final int LEAVE = 1;
    static final String[] messages = {"님이 들어왔습니다.", "님이 나갔습니다."};

    // record를 순회하면서 로그를 기록한다. => uid, type
    // - Enter, Change 시 uid에 매핑되는 닉네임을 갱신한다.
    // O(record.length)
    public String[] solution(String[] record) {
        HashMap<String, String> nickname = new HashMap<>(); // key: uid, value: nickname
        ArrayList<Log> logs = new ArrayList<>();

        for (String log : record) {
            String[] split = log.split(" ");
            String type = split[0];
            String uid = split[1];

            if (type.equals("Leave")) {
                logs.add(new Log(uid, LEAVE));
            } else {
                if (type.equals("Enter")) {
                    logs.add(new Log(uid, ENTER));
                }
                nickname.put(uid, split[2]); // 닉네임 갱신
            }
        }

        // 정답 구성
        String[] answer = new String[logs.size()];
        for (int i = 0; i < logs.size(); i++) {
            Log log = logs.get(i);
            answer[i] = nickname.get(log.uid) + messages[log.type];
        }

        return answer;
    }
}

/*
import java.util.*;

class Solution {
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
}*/

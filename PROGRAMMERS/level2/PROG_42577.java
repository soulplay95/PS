package programmers.level2;

import java.util.*;

class PROG_42577 {
    // 1. 모든 전화번호에 대하여 접두어 만들기 => O(NM)
    // 2. 각 접두어가 전화번호에 있는지 확인 => O(1)
    public boolean solution(String[] phone_book) {
        HashSet<String> phoneNumberSet = new HashSet<>();

        // 모든 전화번호를 Set에 넣는다.
        for (String phoneNumber : phone_book) {
            phoneNumberSet.add(phoneNumber);
        }

        // 모든 전화번호에 대하여 접두어를 만든다.
        for (String phoneNumber : phone_book) {
            for (int end = 1; end < phoneNumber.length(); end++) {
                String prefix = phoneNumber.substring(0, end);

                // O(1)
                if (phoneNumberSet.contains(prefix)) {
                    return false;
                }
            }
        }

        return true;
    }
}


/*
class Solution {
    public boolean solution(String[] phone_book) {
        HashSet<String> hs = new HashSet<>(); // 검색 속도를 줄이기 위해 HashSet 사용

        // HashSet에 넣기
        for (String phone_number : phone_book) {
            hs.add(phone_number);
        }

        // 각 전화번호의 접두어(부분 문자열) 후보들에 대하여 HashSet에 존재하는지 확인
        for (String phone_number : phone_book) {
            for (int i = 1, end = phone_number.length(); i < end; i++) {
                String prefix = phone_number.substring(0, i); // ex) 1235 => 1, 12, 123

                if (hs.contains(prefix)) {
                    return false;
                }
            }
        }

        return true;
    }
}*/

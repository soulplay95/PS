package study.w220124;

import java.util.HashSet;

/**
 * @문제 전화번호 목록
 * @날짜 220123
 * @분류
 * @조건
 * # 전화번호 개수 <= 100만
 * @풀이
 * # 모든 전화번호를 HashSet에 넣고 각 전화번호의 접두어가 HashSet에 있는지 확인
 * @comment
 * #
 */

import java.util.*;
import java.io.*;

class PROG_42577 {
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
}
/**
 * @문제 완주하지 못한 선수
 * @날짜 220125
 * @분류 해시
 * @조건
 * # 참가 선수 <= 10만
 * @풀이
 * # HashMap<String, Integer> => 사람 이름, 인원 수(동명이인이 있을 수 있으므로)
 * # 완주한 선수 이름 배열을 순회하면서 HashMap에서 제거
 * # HashMap에 남은 선수가 정답
 * @comment
 * # https://programmers.co.kr/learn/courses/30/lessons/42576?language=javahttps://programmers.co.kr/learn/courses/30/lessons/42576?language=java
 * # HashMap 순회 => .keySet() + iterator 또는 entrySet() + iterator
 * # hm.getOrDefault(key, default값) => key에 해당하는 value가 없으면 default값이 적용
 */

import java.util.*;
import java.io.*;

public class PROG_42576 {

    public String solution(String[] participant, String[] completion) {
        HashMap<String, Integer> hm = new HashMap<>(); // <사람이름, 인원 수>

        // 참가한 선수들 HashMap에 넣기
        for (String name : participant) {
            if (hm.containsKey(name)) {
                hm.put(name, hm.get(name) + 1);
            } else {
                hm.put(name, 1);
            }
        }

        // 왼주한 선수들 HashMap에서 제거
        for (String name : completion) {
            if (hm.get(name) == 1) {
                hm.remove(name);
            } else {
                hm.put(name, hm.get(name) - 1);
            }
        }

        // 남은 한명의 선수 리턴
        Set<String> set = hm.keySet();
        Iterator<String> it = set.iterator();

        return it.next();
    }

}

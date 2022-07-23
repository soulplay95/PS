package programmers.level2;

import java.util.ArrayList;
import java.util.HashMap;

class PROG_17684 {

    static ArrayList<Integer> indexList = new ArrayList<>();
    static HashMap<String, Integer> dict = new HashMap<>();

    // 현재 입력을 사전에서 검색 => Map<String, Integer> 사용. key: 검색할 문자열, value: 색인
    // O(msg 길이)
    public int[] solution(String msg) {
        // 1. 영문 대문자를 넣어 사전을 초기화한다.
        init();

        while (!msg.isEmpty()) {
            // 2. 현재 입력(msg)의 앞에서부터 사전에서 검색 가능한 최대 길이 문자열을 찾는다.
            int maxEnd = 1;
            for (int end = 2; end <= msg.length(); end++) {
                String w = msg.substring(0, end);

                if (dict.containsKey(w)) {
                    maxEnd = end;
                } else {
                    break;
                }
            }

            // 3. 색인 번호를 출력하고 입력에서 제거한다.
            String searchedWord = msg.substring(0, maxEnd);
            indexList.add(dict.get(searchedWord));
            String newMsg = msg.substring(maxEnd);

            // 4. 남아 있는 다음 글자가 있다면 사전에 등록
            if (maxEnd >= msg.length()) {
                break;
            } else {
                dict.put(msg.substring(0, maxEnd + 1), dict.size() + 1);
                msg = newMsg;
            }
        }

        return indexList.stream().mapToInt(i -> i).toArray();
    }

    static void init() {
        for (int i = 0; i < 26; i++) {
            dict.put(String.valueOf((char) ('A' + i)), i + 1);
        }
    }

}

/*
class Solution {
    // 사전 => HashMap<String, Integer>
    public int[] solution(String msg) {
        int N = msg.length();
        ArrayList<Integer> answer = new ArrayList<>();
        HashMap<String, Integer> dict = new HashMap<>();
        // init - 사전 초기화
        for (int i = 0; i < 26; i++) {
            String word = String.valueOf((char)('A' + i));
            dict.put(word, i + 1);
        }

        int L = 0;
        int R = 1;
        while (L < N) {
            // R을 이동한다 => L ~ R의 단어가 사전에 있을때 까지
            while (R <= N) {
                String word = msg.substring(L, R);
                if (dict.containsKey(word)) {
                    R++;
                } else {
                    // 새로 사전에 등록
                    dict.put(word, dict.size() + 1);
                    break;
                }
            }

            // 색인 번호 출력
            R--;
            String word = msg.substring(L, R);
            answer.add(dict.get(word));

            L = R;
            R++;
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }
}*/

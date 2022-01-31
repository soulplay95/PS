/**
 * @문제 단어 변환
 * @날짜 220201
 * @분류 DFS/BFS
 * @조건
 * # 3 <= 단어 길이 <= 10
 * # 3 <= words <= 50
 * @풀이
 * # BFS
 * @comment
 * #
 */

import java.util.*;

public class PROG_43163 {

    public static void main(String[] args) {
        System.out.println(solution("hit", "cog", new String[]{"hot", "dot", "dog", "lot", "log", "cog"}));
    }

    private static class Word {
        String stringWord; // 단어 문자열
        int indexInWordsArray; // words 배열 상의 인덱스. 배열 내 존재하지 않는 단어이면 -1

        public Word(String stringWord, int indexInWordsArray) {
            this.stringWord = stringWord;
            this.indexInWordsArray = indexInWordsArray;
        }
    }

    public static int solution(String begin, String target, String[] words) {
        Queue<Word> q = new LinkedList<>(); // BFS를 위한 큐
        boolean[] visited = new boolean[words.length]; // 해당 단어가 변환 과정에서 사용되었으면 true

        q.offer(new Word(begin, -1));
        int transferCounts = 0; // 변환 횟수
        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                Word cur = q.poll();

                // target 체크
                if (target.equals(cur.stringWord)) {
                    return transferCounts;
                }

                // 변환 가능한 모든 단어 후보 중에 사용되지 않았고, 조건에 맞는 단어 선택
                for (int i = 0, end = words.length; i < end; i++) {
                    if (!visited[i] && isPossible(words[i], cur.stringWord)) {
                        visited[i] = true;
                        q.offer(new Word(words[i], i));
                    }
                }
            }
            transferCounts++;
        }

        return 0;
    }

    private static boolean isPossible(String next, String before) {
        // 각 단어를 비교해서 한 개 이상의 차이가 발생하면 false 리턴
        int diffCounts = 0;
        for (int i = 0, end = before.length(); i < end; i++) {
            if (before.charAt(i) != next.charAt(i)) {
                diffCounts++;
            }
            if (diffCounts > 1) {
                return false;
            }
        }

        return true;
    }

}

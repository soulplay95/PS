/**
 * @문제 베스트앨범
 * @날짜 220127
 * @분류 해시
 * @조건
 * # 1 <= 노래 고유번호 <= 1만
 * # 장르 종류 < 100
 * @풀이
 * # 장르별 재생 횟수, 장르별 노래 고유번호 목록 2개의 해시맵으로 관리
 * # 정렬
 * @comment
 * # HashMap을 value 기준으로 정렬하기
 * 1. Map의 entry set을 list 형태로 저장 => List<Map.Entry<key, value>> entryList = new LinkedList<>(map.enytySet());
 * 2. list.sort(new Compartor<Map.Entry<key ,value>>() {})
 */

import java.util.*;

public class PROG_42579 {

    private static class Song {
        int index, plays; // 고유 번호, 재생 횟수

        public Song(int index, int plays) {
            this.index = index;
            this.plays = plays;
        }
    }

    public int[] solution(String[] genres, int[] plays) {
        HashMap<String, Integer> totalPlays = new HashMap<>(); // 장르별 총 노래 재생 횟수
        HashMap<String, Integer> indexPerGenre = new HashMap<>(); // 장르별 인덱스
        ArrayList<Song>[] listPerGenre = new ArrayList[100]; // 장르별 노래 리스트
        ArrayList<Integer> answer = new ArrayList<>();

        // 해시맵 채우기
        int index = 0;
        for (int i = 0, end = plays.length; i < end; i++) {
            String genre = genres[i];
            int playCounts = plays[i];

            totalPlays.put(genre, totalPlays.getOrDefault(genre, 0) + playCounts);
            if (!indexPerGenre.containsKey(genre)) {
                indexPerGenre.put(genre, index);
                listPerGenre[index] = new ArrayList<>();
                listPerGenre[index].add(new Song(i, playCounts));
                index++;
            } else {
                listPerGenre[indexPerGenre.get(genre)].add(new Song(i, playCounts));
            }
        }

        // 1. 많이 재생된 장르별로 정렬 - HashMap을 value 기준으로 정렬
        List<Map.Entry<String, Integer>> entyList = new LinkedList<>(totalPlays.entrySet()); // Map의 entry set을 list 형태로 저장
        entyList.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue() - o1.getValue(); // value 기준 내림차순 정렬
            }
        });

        for (Map.Entry<String, Integer> entry : entyList) {
            // 2. 장르 내에서 많이 재생된 노래 먼저 수록 - 장르별 노래 리스트에서 재생 횟수별 내림차순 정렬 + 3. 고유번호가 낮은 순으로
            ArrayList<Song> list = listPerGenre[indexPerGenre.get(entry.getKey())];
            list.sort(new Comparator<Song>() {
                @Override
                public int compare(Song o1, Song o2) {
                    if (o1.plays == o2.plays) {
                        return o1.index - o2.index;
                    }
                    return o2.plays - o1.plays;
                }
            });

            // 두곡 뽑기
            answer.add(list.get(0).index);
            if (list.size() > 1) {
                answer.add(list.get(1).index);
            }
        }

        int[] answers = new int[answer.size()];
        int i = 0;
        for (int key : answer) {
            answers[i++] = key;
        }

        return answers;
    }

}

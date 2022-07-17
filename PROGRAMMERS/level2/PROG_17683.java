package programmers.level2;

import java.util.ArrayList;
import java.util.Collections;

class PROG_17683 {

    static class Music implements Comparable<Music> {
        int playTime; // 분 단위
        String name;

        Music(int playTime, String name) {
            this.playTime = playTime;
            this.name = name;
        }

        @Override
        public int compareTo(Music o) {
            return Integer.compare(o.playTime, this.playTime);
        }
    }

    static final String NO_MATCH = "(None)";

    // 1. musicinfos를 순회하면서 멜로디와 일치하는 음악을 찾아서 리스트에 저장한다.
    // 1-1. 재생 시간을 고려하여 어떤 멜로디로 재생되었는지 구한다.
    // 1-2. 재생시간(분), 음악 제목 데이터로 파싱한다. => Music Class
    // 2. 후보 리스트를 조건에 맞게 정렬한다.
    public String solution(String m, String[] musicinfos) {
        // init
        ArrayList<Music> answerList = new ArrayList<>();
        m = change(m);

        // 1
        for (String musicInfo : musicinfos) {
            // 파싱
            String[] split = musicInfo.split(",");
            String[] startTimeString = split[0].split(":");
            String[] endTimeString = split[1].split(":");
            String name = split[2];
            String originalMusicSheet = split[3];

            // 재생 시간 구하기
            int startTime = Integer.parseInt(startTimeString[0]) * 60 + Integer.parseInt(startTimeString[1]);
            int endTime = Integer.parseInt(endTimeString[0]) * 60 + Integer.parseInt(endTimeString[1]);
            int playTime = endTime - startTime;

            // 어떤 멜로디로 재생되었는지 구하기
            int originalMusicPlayTime = originalMusicSheet.replaceAll("#", "").length(); // 원본 악보의 플레이 타임(분)
            StringBuilder sb = new StringBuilder();
            int index = 0;
            for (int i = 0; i < playTime; i++) {
                if (index >= originalMusicSheet.length()) { // 다시 처음부터 반복
                    index = 0;
                }

                sb.append(originalMusicSheet.charAt(index++));
                if (index < originalMusicSheet.length() && originalMusicSheet.charAt(index) == '#') {
                    sb.append('#');
                    index++;
                }
            }

            // 멜로디가 재생된 멜로디에 포함되는지 확인
            String musicSheet = change(sb.toString());
            if (musicSheet.contains(m)) {
                answerList.add(new Music(playTime, name));
            }
        }

        // 조건과 일치하는 음악이 없는 경우
        if (answerList.isEmpty()) {
            return NO_MATCH;
        }

        // 매칭된 음악이 여러개일 경우 조건에 맞게 정렬
        Collections.sort(answerList);

        return answerList.get(0).name;
    }

    static String change(String melody) {
        return melody.replaceAll("C#", "1")
                .replaceAll("D#", "2")
                .replaceAll("F#", "3")
                .replaceAll("G#", "4")
                .replaceAll("A#", "5");
    }
}

/*
class Solution {
    static class Music implements Comparable<Music> {
        int index; // 입력 순서
        int playTime; // 재생된 시간(분)
        String title, totalMelody; // 제목, 재생 시간 만큼의 전체 멜로디

        public Music(int index, int playTime, String title, String totalMelody) {
            this.index = index;
            this.playTime = playTime;
            this.title = title;
            this.totalMelody = totalMelody;
        }

        @Override
        public int compareTo(Music o) {
            if (this.playTime == o.playTime) {
                return Integer.compare(this.index, o.index);
            }
            return Integer.compare(o.playTime, this.playTime);
        }
    }

    // 재생 시간을 고려하여 각 음원마다 전체 멜로디 문자열을 구한다.
    // 음원의 정보를 담은 커스텀 클래스를 생성하고, 정렬 기준을 만들어 리스트를 정렬한다.
    // String의 contains() 메소드를 이용하여 기억한 멜로디가 음원의 전체 멜로디에 포함되는지 판단한다.
    public String solution(String m, String[] musicinfos) {
        ArrayList<Music> musicList = new ArrayList<>();
        int mLength = m.replace("#", "").length();

        for (int i = 0; i < musicinfos.length; i++) {
            String musicInfo = musicinfos[i];
            // parsing
            String[] splitInfos = musicInfo.split(",");
            // 재생 시간
            String[] startTime = splitInfos[0].split(":");
            String[] endTime = splitInfos[1].split(":");
            int iStartTime = Integer.parseInt(startTime[0]) * 60 + Integer.parseInt(startTime[1]); // HH:MM -> 분으로 변환
            int iEndTime = Integer.parseInt(endTime[0]) * 60 + Integer.parseInt(endTime[1]);
            int playTime = iEndTime - iStartTime; // 재생 시간(분)
            // 제목
            String title = splitInfos[2];
            // 전체 멜로디 구하기
            String melody = splitInfos[3];
            int codeCounts = melody.replace("#", "").length(); // 악보 전체 길이에서 #을 제외한 길이
            int repeatCounts = playTime / codeCounts; // 전체 반복 횟수
            int remainRepeatCounts = playTime % codeCounts; // 나머지 반복 횟수
            StringBuilder sb = new StringBuilder();
            for (int repeat = 0; repeat < repeatCounts; repeat++) {
                sb.append(melody);
            }
            int index = 0;
            int counts = 0;
            while (index < melody.length() && counts < remainRepeatCounts) {
                sb.append(melody.charAt(index++));
                if (index < melody.length() && melody.charAt(index) == '#') { // 다음 문자가 #이면 => 두 문자가 한 코드
                    sb.append("#");
                    index++;
                }
                counts++;
            }
            String totalMelody = sb.toString();

            musicList.add(new Music(i, playTime, title, totalMelody));
        }

        // 조건에 따라 리스트 정렬
        Collections.sort(musicList);

        // 리스트의 첫번째 원소부터 보면서 조건과 일치하는 음악이 있는지 확인
        for (Music music : musicList) {
            int fromIndex = 0;
            while (true) {
                if (fromIndex >= music.totalMelody.length()) {
                    break;
                }
                int findIndex = music.totalMelody.indexOf(m, fromIndex);
                if (findIndex != -1) {
                    int lastIndex = findIndex + m.length();
                    if (lastIndex < music.totalMelody.length() && music.totalMelody.charAt(lastIndex) == '#') {
                        fromIndex = lastIndex + 1;
                        continue;
                    }
                    return music.title;
                } else {
                    break;
                }
            }
        }

        return "(None)";
    }
}*/

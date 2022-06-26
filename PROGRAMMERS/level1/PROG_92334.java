package programmers.level1;

/**
 * @문제 신고 결과 받기
 * @날짜 220626
 * @분류
 * @조건
 * @풀이
 * @comments
 * # 정답의 최대치:
 * # 시간 복잡도:
 */

import java.util.*;

public class PROG_92334 {

    public int[] solution(String[] id_list, String[] report, int k) {
        // init
        HashMap<String, Integer> reportedCounts = new HashMap<>(); // key: 유저 ID, value: 신고 당한 횟수
        HashMap<String, HashSet<String>> reportSet = new HashMap<>(); // key: 유저 ID, value: key의 유저가 신고한 ID set

        // 신고 목록을 조회하여 각 유저별 신고 당한 횟수, 신고한 유저 리스트를 구한다.
        for (String reportInfo : report) {
            String[] fromTo = reportInfo.split(" ");
            String from = fromTo[0];
            String to = fromTo[1];

            if (!reportSet.containsKey(from)) {
                reportSet.put(from, new HashSet<String>());
                // from -> to 신고 내역 등록
                reportSet.get(from).add(to);
                // to의 신고 당한 횟수 증가
                reportedCounts.put(to, reportedCounts.getOrDefault(to, 0) + 1);
            }

            if (!reportSet.get(from).contains(to)) {
                // from -> to 신고 내역 등록
                reportSet.get(from).add(to);
                // to의 신고 당한 횟수 증가
                reportedCounts.put(to, reportedCounts.getOrDefault(to, 0) + 1);
            }
        }

        // 신고한 유저 리스트를 보고 신고 횟수가 k 이상인 유저 수를 카운트한다.
        int N = id_list.length;
        int[] answer = new int[N];
        for (int i = 0; i < N; i++) {
            String from = id_list[i];

            if (!reportSet.containsKey(from)) continue;

            // from이 신고한 유저 리스트
            for (String to : reportSet.get(from)) {
                if (reportedCounts.getOrDefault(to, 0) >= k) {
                    answer[i]++;
                }
            }
        }

        return answer;
    }

}

//import java.util.*;
//
//class Solution {
//    public int[] solution(String[] id_list, String[] report, int k) {
//        // init
//        int[] answer = new int[id_list.length];
//        HashMap<String, Integer> reportedCounts = new HashMap<>(); // key: ID, value: 해당 ID의 유저가 신고당한 횟수
//        HashMap<String, HashSet<String>> reportSetFromWho = new HashMap<>(); // key: ID, value: 신고한 ID 리스트
//
//        // report 배열을 순회하여 신고한 리스트, 각 유저가 신고당한 횟수를 기록한다.
//        for (String reportInfo : report) {
//            String[] fromTo = reportInfo.split(" ");
//            String from = fromTo[0];
//            String to = fromTo[1];
//
//            if (!reportSetFromWho.containsKey(from)) { // 신고했다는 사실이 처음 등장한 ID이면
//                reportSetFromWho.put(from, new HashSet<String>());
//                reportSetFromWho.get(from).add(to);
//                if (!reportedCounts.containsKey(to)) {
//                    reportedCounts.put(to, 1);
//                } else {
//                    reportedCounts.put(to, reportedCounts.get(to) + 1); // 신고 횟수 증가
//                }
//            } else {
//                // 이미 from 유저가 to 유저를 신고한 적이 있는지 체크한다.
//                if (!reportSetFromWho.get(from).contains(to)) { // 이미 신고한 적이 없으면
//                    reportSetFromWho.get(from).add(to);
//                    if (!reportedCounts.containsKey(to)) {
//                        reportedCounts.put(to, 1);
//                    } else {
//                        reportedCounts.put(to, reportedCounts.get(to) + 1); // 신고 횟수 증가
//                    }
//                }
//            }
//        }
//
//        // id_list 배열을 순회하여 해당 ID가 신고한 리스트를 보고 신고 횟수가 k 이상인 유저 수를 카운트한다.
//        for (int i = 0; i < id_list.length; i++) {
//            String from = id_list[i];
//
//            if (!reportSetFromWho.containsKey(from)) {
//                continue;
//            }
//
//            for (String to : reportSetFromWho.get(from)) {
//                if (!reportedCounts.containsKey(to)) {
//                    continue;
//                }
//
//                if (reportedCounts.get(to) >= k) {
//                    answer[i]++;
//                }
//            }
//        }
//
//        return answer;
//    }
//}
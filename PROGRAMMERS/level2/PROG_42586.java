package programmers.level2;

import java.util.*;

class PROG_42586 {

    // 각 작업이 끝나기 까지 남은 일수를 계산하고 큐에 넣는다.
    // 큐에서 처음 poll한 값보다 작거나 같을 때 까지 poll한다. => 한 번의 배포
    public int[] solution(int[] progresses, int[] speeds) {
        int N = progresses.length; // 작업 개수
        ArrayList<Integer> answerList = new ArrayList<>();
        Queue<Integer> remainDaysUntilRelease = new LinkedList<>();

        // 1. 각 작업이 끝나기 까지 남은 일수를 계산해서 큐에 넣는다. => O(N)
        for (int i = 0; i < N; i++) {
            int remainProgress = 100 - progresses[i];
            int remainDays = remainProgress / speeds[i] + (remainProgress % speeds[i] > 0 ? 1 : 0);
            remainDaysUntilRelease.offer(remainDays);
        }

        // 2. 각 배포마다 몇개의 기능이 배포되는지 계산한다.
        while (true) {
            if (remainDaysUntilRelease.isEmpty()) {
                break;
            }

            // 첫 기능 배포
            int days = remainDaysUntilRelease.poll();
            int releaseCountsPerRelease = 1;
            // 함께 배포되는 기능 개수 세기
            while (!remainDaysUntilRelease.isEmpty()) {
                int nextDays = remainDaysUntilRelease.peek();
                if (nextDays > days) {
                    break;
                }

                remainDaysUntilRelease.poll();
                releaseCountsPerRelease++;
            }

            // 한 배포 사이클마다 배포한 기능 개수 기록
            answerList.add(releaseCountsPerRelease);
        }

        // 정답 구성
        int size = answerList.size();
        int[] answer = new int[size];
        for (int i = 0; i < size; i++) {
            answer[i] = answerList.get(i);
        }

        return answer;
    }
}

/*
import java.util.LinkedList;
        import java.util.Queue;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};

        // 큐로 입력 받기
        Queue<Integer> progressesQ = new LinkedList<>();
        Queue<Integer> speedsQ = new LinkedList<>();
        for(int i = 0; i < progresses.length; i++) {
            progressesQ.offer(progresses[i]);
            speedsQ.offer(speeds[i]);
        }

        Queue<Integer> releaseQ = new LinkedList<>();
        int release = 0;
        int days = 1; // 소요 일
        // 전부 배포될 때 까지 반복
        // days만 증가하면서 head가 100넘었나를 매 반복문 마다 체크
        while (!progressesQ.isEmpty()) {
            int head = progressesQ.peek() + (speedsQ.peek() * days);
            if (head >= 100) {
                progressesQ.poll();
                speedsQ.poll();
                release++;
                if (progressesQ.isEmpty()) {
                    // 마지막꺼 뽑을 때 반복문을 빠져나가버리므로 여기서 체크
                    releaseQ.offer(release);
                }
            } else {
                if (release > 0) { // 헤드가 100이 안넘었는데 쌓인 배포가 하나라도 있다?
                    // 배포 수 저장하고 초기화
                    releaseQ.offer(release);
                    release = 0;
                }
                days++;
            }
        }

        int size = releaseQ.size();
        answer = new int[size];
        for (int i = 0; i < size; i++) {
            answer[i] = releaseQ.poll();
        }

        return answer;
    }
}*/

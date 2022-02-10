/**
 * @문제 디스크 컨트롤러
 * @날짜 220210
 * @분류 Heap
 * @조건
 * # 1 <= 작업 수 <= 500
 * @풀이
 * # SJF
 * - 요청 시간 기준 오름차순 정렬
 * - 현재 시간을 관리하면서 시뮬레이션
 * - 현재 시간 기준으로 작업할 수 있는(큐에 대기중인) 작업들 중 소요 시간이 가장 작은 작업을 먼저 수행 => 최소 Heap으로 관리
 * @comment
 * # https://programmers.co.kr/learn/courses/30/lessons/42627
 * # Job Scheduling
 * - ATT(Average Turnaround Time) : Job이 도착하고 끝날때 까지의 시간 평균
 * - ART(Average Response Time) : Job이 도착하고 시작될 떄 까지의 시간 평균
 * - ATT가 가장 작아지는 스케줄링 기법 => SJF(Shortest Job First)
 * # 한 Custom Class에 여러 개의 Comparator를 적용하려면 정렬 시점에 Comparator 정의해야 함
 */

import java.util.*;

public class PROG_42627 {

    private static class Job {
        int arrivalTime, burstTime; // 작업의 요청 시점, 소요 시간

        public Job(int arrivalTime, int burstTime) {
            this.arrivalTime = arrivalTime;
            this.burstTime = burstTime;
        }
    }

    public static int solution(int[][] jobs) {
        // Custom class에 옮겨 담기
        Job[] sortedJobs = new Job[jobs.length];
        for (int i = 0, end = jobs.length; i < end; i++) {
            sortedJobs[i] = new Job(jobs[i][0], jobs[i][1]);
        }

        // 1. 작업이 요청되는 시점 기준 오름차순 정렬(요청 시점이 같다면 소요 시간 기준 오름차순 정렬)
        Arrays.sort(sortedJobs, new Comparator<Job>() {
            @Override
            public int compare(Job o1, Job o2) {
                if (o1.arrivalTime == o2.arrivalTime) {
                    return Integer.compare(o1.burstTime, o2.burstTime);
                }

                return Integer.compare(o1.arrivalTime, o2.arrivalTime);
            }
        });

        // 2. 현재 시점을 관리하면서 시뮬레이션
        int turnaroundTimeSum = 0; // 모든 job의 "완료 시점 - 요청 시점"의 합
        int currentTime = sortedJobs[0].arrivalTime; // 요청 시점이 제일 먼저인 job부터 시작
        int jobIndex = 1; // 완료된 작업 까지의 포인터
        PriorityQueue<Job> readyQueue = new PriorityQueue<Job>(new Comparator<Job>() {
            @Override
            public int compare(Job o1, Job o2) {
                return o1.burstTime - o2.burstTime; // 소요 시간 기준 오름차순 정렬
            }
        });
        readyQueue.offer(sortedJobs[0]);

        while (!readyQueue.isEmpty()) {
            Job currentJob = readyQueue.poll(); // 현재 작업할 job

            currentTime += currentJob.burstTime; // 현재 작업의 소요 시간만큼 현재 시간 경과
            turnaroundTimeSum += currentTime - currentJob.arrivalTime; // 현재 작업이 끝났으므로 요청시간과의 차이를 누적

            // 현재 시간 보다 요청시간이 작거나 같은(수행할 수 있는) 작업들 readyQueue에 넣기
            while (jobIndex < jobs.length && sortedJobs[jobIndex].arrivalTime <= currentTime) {
                readyQueue.offer(sortedJobs[jobIndex++]);
            }

            // 현재 시점에 작업할 수 있는 job이 없지만, 해야할 job이 남아 있는 경우
            if (jobIndex < jobs.length && readyQueue.isEmpty()) {
                currentTime = sortedJobs[jobIndex].arrivalTime; // 현재 시간 미루기
                readyQueue.offer(sortedJobs[jobIndex++]);
            }
        }

        return turnaroundTimeSum / jobs.length; // 평균 리턴
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[][] {
                {0, 3},
                {1, 9},
                {2, 6}
        }));
    }

}

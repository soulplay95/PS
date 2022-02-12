/**
 * @문제 큐_S4
 * @날짜 220213
 * @분류 자료 구조, 큐
 * @조건
 * # 1 <= 명령의 수 <= 1만
 * # 1 <= 주어지는 정수 <= 10만
 * @풀이
 * # Queue를 만들고 명령 순서대로 처리
 * # Queue의 가장 뒤에 있는 정수는 push 명령 마다 업데이트 되는 변수로 관리한다.
 * @comments
 * # Queue의 가장 뒤에 있는 정수는 마지막에 offer한 값이다.
 */

import java.util.*;
import java.io.*;

public class BOJ_10845 {

    private static int N, lastDataInQueue;
    private static Queue<Integer> queue = new LinkedList<Integer>();
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException, NumberFormatException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            String command = st.nextToken();

            // 명령어 종류에 따라 분류
            switch (command) {
                case "push":
                    Integer data = Integer.parseInt(st.nextToken());
                    lastDataInQueue = data;
                    queue.offer(data);
                    break;
                case "pop":
                    sb.append(queue.isEmpty() ? -1 : queue.poll()).append("\n");
                    break;
                case "size":
                    sb.append(queue.size()).append("\n");
                    break;
                case "empty":
                    sb.append(queue.isEmpty() ? 1 : 0).append("\n");
                    break;
                case "front":
                    sb.append(queue.isEmpty() ? -1 : queue.peek()).append("\n");
                    break;
                case "back":
                    sb.append(queue.isEmpty() ? -1 : lastDataInQueue).append("\n");
                    break;
            }
        }

        // print
        System.out.print(sb.toString());
    }

}

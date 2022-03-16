/**
 * @문제 [카카오 인턴] 키패드 누르기_Lv.1
 * @날짜 220316
 * @분류
 * @조건
 * #
 * @풀이
 * #
 * @comments
 * # 정답의 최대치:
 * # 시간 복잡도:
 * # 공간 복잡도:
 */

import java.util.*;

class PROG_67256 {

    public static void main(String[] args) {
        System.out.println(solution(new int[] {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5}, "right"));
    }

    static class Pair {
        int r, c;

        Pair(int _r, int _c) {
            r = _r;
            c = _c;
        }
    }

    static int[][] keyPad;
    static HashMap<Integer, int[]> distanceByCenterNumbers; // key: 2, 5, 8, 0 | value: key번호로 부터의 거리
    static StringBuilder sb = new StringBuilder();
    static int L, R;

    static int[] dr = {-1, 0, 1, 0}; // 상우하좌 시계방향
    static int[] dc = {0, 1, 0, -1};

    static boolean isOut(int r, int c) {
        return (r < 0 || r >= 4 || c < 0 || c >= 3);
    }

    static int[] bfs(int i) {
        int[] distance = new int[12];
        Pair start = new Pair(i, 1);
        boolean[][] visited = new boolean[4][3];
        visited[i][1] = true;
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(i, 1));

        int dist = 1;
        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                Pair cur = q.poll();

                for (int d = 0; d < 4; d++) {
                    int nr = cur.r + dr[d];
                    int nc = cur.c + dc[d];

                    if (isOut(nr, nc)) {
                        continue;
                    }

                    if (!visited[nr][nc]) {
                        visited[nr][nc] = true;
                        distance[keyPad[nr][nc]] = dist;
                        q.offer(new Pair(nr, nc));
                    }
                }
            }

            dist++;
        }

        return distance;
    }

    static void init() {
        keyPad = new int[][] {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9},
                {10, 0, 11}
        }; // *: 10, #: 11
        distanceByCenterNumbers = new HashMap<>();

        int[] centerNumbers = {2, 5, 8, 0};
        // 2, 5, 8, 0 각 숫자로부터 모든 숫자(0, 1, ..., 9)까지의 거리를 계산한다.
        for (int i = 0; i < 4; i++) {
            distanceByCenterNumbers.put(centerNumbers[i], bfs(i));
        }

        L = 10;
        R = 11;
    }

    // 2, 5, 8, 0을 누를 때 현재 왼손, 오른쪽 손가락 위치까지의 거리를 비교해 누른다.
    public static String solution(int[] numbers, String hand) {
        init();

        for (int num : numbers) {
            if (num == 1 || num == 4 || num == 7) {
                L = num;
                sb.append("L");
            } else if (num == 3 || num == 6 || num == 9) {
                R = num;
                sb.append("R");
            } else {
                // 거리를 비교
                int distL = distanceByCenterNumbers.get(num)[L];
                int distR = distanceByCenterNumbers.get(num)[R];

                if (distL == distR) {
                    if (hand.equals("left")) {
                        L = num;
                        sb.append("L");
                    } else {
                        R = num;
                        sb.append("R");
                    }
                } else {
                    if (distL < distR) {
                        L = num;
                        sb.append("L");
                    } else {
                        R = num;
                        sb.append("R");
                    }
                }
            }
        }

        return sb.toString();
    }
}
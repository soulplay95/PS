package programmers.level2;

class PROG_68645 {

    static final int TYPE_COUNTS = 3; // 동작 개수

    // 정답 배열 최대 길이 & 배열 내 최대 원소: 1000 * (1000 + 1) / 2 = 500,500
    // 삼각형을 이차원 배열에 담는다.
    // 1. 아래로 이동
    // 2. 우측으로 이동
    // 3. 좌상단 대각선 방향으로 이동
    public int[] solution(int n) {
        // init
        int length = n * (n + 1) / 2;
        int[] answer = new int[length];
        int[][] map = new int[n][n];
        int number = 1; // 1부터 length 까지 채워 넣는다.
        int mappedR = -1, mappedC = 0; // 매핑되는 map 상의 위치

        for (int typeIndex = 0; typeIndex < n; typeIndex++) { // 총 동작 횟수: n
            int type = typeIndex % TYPE_COUNTS;

            for (int repeatCounts = typeIndex; repeatCounts < n; repeatCounts++) { // 동작 별 반복 횟수: n -> n - 1 -> ... -> 1
                if (type == 0) { // 1. 아래로 이동
                    mappedR++;
                } else if (type == 1) { // 2. 우측으로 이동
                    mappedC++;
                } else if (type == 2) { // 3. 좌상단 대각선으로 이동
                    mappedR--;
                    mappedC--;
                }

                map[mappedR][mappedC] = number++;
            }
        }

        // 정답 구성
        int index = 0;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c <= r; c++) {
                answer[index++] = map[r][c];
            }
        }

        return answer;
    }
}
package programmers.level2;

class PROG_68936 {

    static int[] answer;

    // 분할 정복 방식으로 영역 내 수가 모두 같은지 확인한다.
    // O(N^2)
    public int[] solution(int[][] arr) {
        // init
        answer = new int[2];

        dfs(arr, 0, 0, arr.length);

        return answer;
    }

    static void dfs(int[][] arr, int r, int c, int n) { // (r, c): 영역의 좌상단 좌표(arr에서의), n: 영역 크기
        if (isAllSame(arr, r, c, n)) { // 영역 내의 모든 수가 같으면
            return;
        }

        // 4개의 영역으로 쪼갠다.
        int half = n / 2;
        dfs(arr, r, c, half);
        dfs(arr, r + half, c, half);
        dfs(arr, r, c + half, half);
        dfs(arr, r + half, c + half, half);
    }

    static boolean isAllSame(int[][] arr, int startR, int startC, int n) {
        int num = arr[startR][startC];

        for (int r = startR, endR = startR + n; r < endR; r++) {
            for (int c = startC, endC = startC + n; c < endC; c++) {
                if (arr[r][c] != num) return false;
            }
        }

        answer[num]++;

        return true;
    }
}
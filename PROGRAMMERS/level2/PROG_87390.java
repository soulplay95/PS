package programmers.level2;

class PROG_87390 {

    // 1차원 배열에서의 index i는 2차원 배열상에서 좌표로 나타내면 (i / n, i % n)
    // (r, c)의 값: start + offset
    // - start: r + 1
    // - offset: c - r >  0 ? c - r : 0
    // O(right - left)
    public static int[] solution(int n, long left, long right) {
        // init
        int length = (int) (right - left + 1);
        int[] answer = new int[length];

        int answerIndex = 0; // 정답을 채우기 위한 index
        while (left <= right) {
            int r = (int) (left / n);
            int c = (int) (left % n);

            int start = r + 1;
            int offset = Math.max(c - r, 0);

            answer[answerIndex++] = start + offset;

            left++;
        }

        return answer;
    }
}
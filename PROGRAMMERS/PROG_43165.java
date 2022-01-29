/**
 * @문제 타겟 넘버
 * @날짜 220129
 * @분류 DFS, BFS
 * @조건
 * # 숫자 개수 <= 20개
 * @풀이
 * # DFS
 * @comment
 * # https://programmers.co.kr/learn/courses/30/lessons/43165
 */

public class PROG_43165 {

    public int solution(int[] numbers, int target) {
        return dfs(numbers, target, 0, 0);
    }

    private static int dfs(int[] numbers, int target, int depth, int sum) {
        if (depth == numbers.length) {
            if (sum == target) {
                return 1;
            } else {
                return 0;
            }
        }

        int counts = 0;

        counts += dfs(numbers, target, depth + 1, sum + numbers[depth]);
        counts += dfs(numbers, target, depth + 1, sum - numbers[depth]);

        return counts;
    }

}

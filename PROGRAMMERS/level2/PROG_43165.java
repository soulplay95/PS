package programmers.level2;

class PROG_43165 {

    static int N;
    static int answer;

    // O(2^N)
    public int solution(int[] numbers, int target) {
        // init
        N = numbers.length;
        answer = 0;

        dfs(0, 0, numbers, target);

        return answer;
    }

    static void dfs(int depth, int sum, int[] numbers, int target) {
        // Base condition
        if (depth == N) {
            // 모든 수를 고려한 경우
            if (sum == target) {
                answer++;
            }
            return;
        }

        // 더하거나 빼거나
        dfs(depth + 1, sum + numbers[depth], numbers, target);
        dfs(depth + 1, sum - numbers[depth], numbers, target);
    }
}

/*
class Solution {
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
*/

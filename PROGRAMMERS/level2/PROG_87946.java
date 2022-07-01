package programmers.level2; /**
 * @문제
 * @날짜 220701
 * @분류
 * @조건
 * @풀이
 * @comments
 * # 정답의 최대치:
 * # 시간 복잡도:
 */

public class PROG_87946 {

    static int max = 0;
    static boolean[] visited;

    // 백트래킹
    public int solution(int k, int[][] dungeons) {
        // init
        int N = dungeons.length;
        visited = new boolean[N];

        dfs(0, N, k, dungeons);

        return max;
    }

    static void dfs(int depth, int N, int k, int[][] dungeons) {
        max = Math.max(max, depth); // 최대 탐험한 던전 수 갱신

        // Base condition
        if (depth == N) {
            return;
        }

        for (int candidateIndex = 0; candidateIndex < N; candidateIndex++) {
            if (visited[candidateIndex]) continue;

            // 이번 던전을 갈 수 있는지 따져본다.
            int minNeed = dungeons[candidateIndex][0];
            int need = dungeons[candidateIndex][1];
            if (k >= minNeed) {
                visited[candidateIndex] = true;
                dfs(depth + 1, N, k - need, dungeons);
                visited[candidateIndex] = false;
            }
        }
    }

}

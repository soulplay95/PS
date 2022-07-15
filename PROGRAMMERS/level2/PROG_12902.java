package programmers.level2;

class PROG_12902 {

    static final int MOD = 1000000007;

    // DP
    // dp[i]: 가로 길이가 n인 직사각형을 채우는 방법의 수
    // 정답: dp[n]
    // Partitioning
    // - n이 홀수인 경우: 채울 수 없음
    // - n이 짝수인 경우: dp[i - 2] * dp[2] + SIGMA[k = 0 ~ i - 4 step 2](dp[k] * 2)
    // 3을 곱하는 과정에서 Integer.MAX_VALUE 범위를 넘을 수 있다 => long으로 처리
    public int solution(int n) {
        long[] dp = new long[n + 1];

        // 초기값 세팅
        dp[0] = 1;
        dp[2] = 3;

        // 점화식을 토대로 dp 배열을 채운다.
        for (int i = 4; i <= n; i += 2) {
            dp[i] = (dp[i - 2] * dp[2]) % MOD; // 가로 길이가 n - 2인 직사각형을 채우는 경우의 수 * 나머지 끝의 2x3 직사각형을 채우는 경우의 수(3)

            // 특수 모양을 만드는 경우의 수
            for (int j = i - 4; j >= 0; j -= 2) {
                dp[i] += (dp[j] * 2) % MOD;
            }

            dp[i] %= MOD;
        }

        return (int) dp[n];
    }
}
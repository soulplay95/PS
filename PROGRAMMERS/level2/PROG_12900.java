package programmers.level2;

class PROG_12900 {

    static int[] dp;

    static final int MOD = 1000000007;

    // dp[i] := 가로 길이가 i인 직사각형을 채우는 방법의 수
    // 정답: dp[n]
    // partitioning: 마지막에 1x2 타일을 놨냐 or 2x1 타일을 2개 놨냐
    // dp[i] = dp[i - 1] + dp[i - 2]
    public int solution(int n) {
        preprocess(n);

        return dp[n];
    }

    static void preprocess(int n) {
        // init
        dp = new int[n + 1];

        // 초기값 세팅
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % MOD;
        }
    }

}


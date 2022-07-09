package programmers.level2;

class PROG_12985
{

    // 번호가 짝수인지 홀수인지 나눠서 생각한다.
    // 현재 번호(n)가 짝수이면, 다음 라운드에서 n/2의 번호를 갖는다.
    // 홀수이면, 다음 라운드에서 n/2 + 1의 번호를 갖는다.
    // 정답의 최대치: 20
    // N = 2^k일 때, O(k)
    public int solution(int n, int a, int b)
    {
        // init
        int round = 0;

        while (true) {
            a = nextNumber(a);
            b = nextNumber(b);
            round++; // 다음 라운드로

            if (a == b) {
                break;
            }
        }

        return round;
    }

    static int nextNumber(int n) {
        return n % 2 == 0 ? n / 2 : (n / 2) + 1;
    }

}


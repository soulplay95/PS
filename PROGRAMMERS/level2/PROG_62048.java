package programmers.level2;

class PROG_62048 {

    // w, h의 최대 공약수로 각각을 나눈 w`, h`의 패턴이 최대공약수만큼 반복된다.
    // w * h 크기의 사각형을 대각선으로 잘랐을 때 영향을 받는 블록은 w + h - 1개의 블록이다.
    // 정답: (w * h) - (w` + h` - 1) * gcd = (w * h) - (w + h - gcd)
    // O(logN)
    public long solution(int w, int h) {
        return ((long) w * (long) h) - (w + h - getGCD(w, h));
    }

    static int getGCD(int a, int b) {
        int r = a % b;

        if (r == 0) {
            return b;
        } else {
            return getGCD(b, r);
        }
    }
}

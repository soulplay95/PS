package study.w220307;

/**
 * @문제 다이어트_G4
 * @날짜 220306
 * @분류
 * @조건
 * # 1 <= G <= 10만
 * @풀이
 * # x^2 - any^2 = G를 만족하는 x를 찾는다.
 * - any: 성원이가 기억하는 몸무게. 즉, 자연수
 * - any를 1부터 하나씩 증가시키면서 자연수로 나누어 떨어지는 x를 구한다.
 * - any를 어디까지 증가시킬 것인가(반복 횟수)?
 * -- 임의의 수 A와 그 다음 수 A+1의 각 제곱값의 차이가 G 이하일때까지. => (A + 1)^2 - A^2 <= G
 * -- 그 이상의 값에 대해서는 자연수로 나누어 떨어지는 x값을 구할 수 없다.
 * @comments
 * # 정답의 최대치: long
 * - (A + 1)^2 - A^2 <= 10만
 * - A: 약 5만, 50000^2 = 25억
 * # 시간 복잡도: O(G)
 * # 공간 복잡도:
 */

import java.io.*;
import java.util.*;

public class BOJ_1484 {

    static StringBuilder sb = new StringBuilder();

    static int G;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        print();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        G = Integer.parseInt(br.readLine());
    }

    static void solve() {
        // 성원이가 기억하고 있는 몸무게(i)를 max까지 1씩 증가시키면서 나누어 떨어지는 x값을 찾는다.
        int max = (G - 1) / 2; // (max + 1)^2 - max^2 <= MAX_G
        for (long i = 1; i <= max; i++) {
            long pow = (i * i) + G;
            double x = Math.sqrt(pow);
            if (x % 1 == 0) { // 나누어 떨어지면
                sb.append((int) x).append('\n');
            }
        }

        if (sb.length() == 0) {
            sb.append(-1);
        }
    }

    static void print() {
        System.out.println(sb.toString());
    }

}

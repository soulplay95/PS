/**
 * @문제 숫자의 합_B2
 * @날짜 220218
 * @분류 수학 / 문자열 / 사칙연산
 * @조건
 * # 1 <= 숫자 개수 N <= 100
 * # 각 숫자는 한자리 수
 * @풀이
 * # 각 숫자 문자를 Integer로 파싱
 * @comments
 * # O(n)
 */

import java.util.*;
import java.io.*;

public class BOJ_11720 {

    private static int N, sum;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String[] inputNumbersString = br.readLine().split("");

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(inputNumbersString[i]);
            sum += num;
        }

        // print
        System.out.println(sum);
    }

}

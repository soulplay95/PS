/**
 * @문제 상수_B2
 * @날짜 220216
 * @분류 수학 / 구현
 * @조건
 * # 1 <= 괄호열을 나타내는 문자열 길이 <= 30
 * @풀이
 * # 두 세자리 수를 문자열로 입력 받고 뒤에서부터 append한 새로운 문자열을 만든다.
 * @comments
 * #
 */

import java.util.*;
import java.io.*;

public class BOJ_2908 {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        String a = sc.next();
        String b = sc.next();

        String reverseA = "";
        String reverseB = "";

        for (int i = 2; i >= 0; i--) {
            reverseA += a.charAt(i);
            reverseB += b.charAt(i);
        }

        if (Integer.parseInt(reverseA) > Integer.parseInt(reverseB)) {
            System.out.println(reverseA);
        } else {
            System.out.println(reverseB);
        }

        sc.close();
    }

}

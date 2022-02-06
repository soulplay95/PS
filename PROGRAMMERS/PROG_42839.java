/**
 * @문제 소수 찾기
 * @날짜 220206
 * @분류 완전탐색
 * @조건
 * # 1 <= 숫자 개수 <= 7
 * @풀이
 * # 순열로 가능한 모든 수의 조합 뽑아서 소수 여부 체크
 * @comment
 * # https://programmers.co.kr/learn/courses/30/lessons/42839
 */

import java.util.*;

public class PROG_42839 {

    private static HashSet<Integer> primeNumbers = new HashSet<>();
    private static boolean[] visited;

    public int solution(String numbers) {
        visited = new boolean[numbers.length()];

        nPr(numbers, "", 0);

        return primeNumbers.size();
    }

    private static void nPr(String numbers, String cur, int depth) {
        if (depth == numbers.length()) {
            return;
        }

        for (int i = 0, end = numbers.length(); i < end; i++) {
            if (!visited[i]) { // 사용되지 않은 숫자이면
                String number = cur + numbers.charAt(i);
                int num = Integer.valueOf(number);
                visited[i] = true;

                if (isPrime(num)) {
                    primeNumbers.add(num);
                }

                nPr(numbers, number, depth + 1);
                visited[i] = false;
            }
        }
    }

    private static boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }

        for (int i = 2, end = (int) Math.sqrt(num); i <= end; i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }

}

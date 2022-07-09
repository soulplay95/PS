package programmers.level2;

import java.util.*;

class PROG_42839 {

    static int N;
    static boolean[] used;
    static HashSet<Integer> candidates;

    // 1. N개의 숫자 중 1개를 뽑아 나열, 2개를 뽑아 나열, ... N개를 뽑아 나열
    // 2. 소수 판정
    public int solution(String numbers) {
        init(numbers);

        recursive(0, "", numbers);

        return candidates.size();
    }

    static void recursive(int depth, String cur, String numbers) {
        if (depth == N) {
            return;
        }

        for (int candidateIndex = 0; candidateIndex < N; candidateIndex++) {
            if (used[candidateIndex]) continue;

            String newCur = cur + numbers.charAt(candidateIndex);
            int number = Integer.parseInt(newCur);
            if (isPrime(number)) {
                candidates.add(number);
            }

            used[candidateIndex] = true;
            recursive(depth + 1, newCur, numbers);
            used[candidateIndex] = false;
        }
    }

    static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }

        for (int i = 2, end = (int) Math.sqrt(n); i <= end; i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    static void init(String numbers) {
        N = numbers.length();
        candidates = new HashSet<>();
        used = new boolean[N];
    }

}

/*
class Solution {

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
}*/

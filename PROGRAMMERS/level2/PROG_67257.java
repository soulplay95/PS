package programmers.level2;

import java.util.*;

class PROG_67257 {

    static long answer;
    static ArrayList<Long> operands;
    static ArrayList<Character> operators;
    static int N; // 사용된 연산자의 개수
    static char[] possibleOperators, orderedOperators;
    static boolean[] used;

    // 1. expression에서 숫자와 연산자를 분리한다.
    // 2. 사용된 연산자의 우선순위를 구한다.
    // 3. 우선순위에 따라 계산하면서 최대값을 갱신한다.
    public long solution(String expression) {
        init(expression);

        recursive(0);

        return answer;
    }

    static void init(String expression) {
        // init
        answer = 0L;
        operands = new ArrayList<>();
        operators = new ArrayList<>();

        // expression에서 숫자와 연산자를 분리한다.
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (c == '-' || c == '+' || c == '*') { // 연산자이면
                operators.add(c);
                operands.add(Long.parseLong(sb.toString()));
                sb.setLength(0);
            } else { // 숫자이면
                sb.append(c);
            }
        }
        operands.add(Long.parseLong(sb.toString())); // 마지막 숫자 추가

        // 사용된 연산자를 구한다.
        HashSet<Character> set = new HashSet<>(operators);
        possibleOperators = new char[set.size()];
        int index = 0;
        Iterator<Character> it = set.iterator();
        while (it.hasNext()) {
            possibleOperators[index++] = it.next();
        }
        N = index;

        orderedOperators = new char[N];
        used = new boolean[N];
    }

    static void recursive(int depth) {
        if (depth == N) {
            answer = Math.max(answer, calculate());
            return;
        }

        for (int candidateIndex = 0; candidateIndex < N; candidateIndex++) {
            if (used[candidateIndex]) continue;

            used[candidateIndex] = true;
            orderedOperators[depth] = possibleOperators[candidateIndex];
            recursive(depth + 1);
            used[candidateIndex] = false;
            orderedOperators[depth] = '?';
        }
    }

    static long calculate() {
        // init
        // remove() 하며 계산할 것이므로 기존 숫자와 연산자를 copy한다.
        ArrayList<Long> copyOperands = new ArrayList<>(operands);
        ArrayList<Character> copyOperators = new ArrayList<>(operators);

        // 정해진 우선순위에 따라 계산한다.
        for (int i = 0; i < N; i++) {
            char op = orderedOperators[i];

            for (int j = 0; j < copyOperators.size(); j++) {
                if (copyOperators.get(j) == op) {
                    copyOperands.add(j, calc(copyOperands.remove(j), copyOperators.remove(j), copyOperands.remove(j))); // 계산이 이루어지는 위치에 계산 후 삽입
                    j--; // 현재 위치에서 다시 계산
                }
            }
        }

        return Math.abs(copyOperands.get(0));
    }

    static long calc(long a, char op, long b) {
        long result = a;

        switch (op) {
            case '-':
                result -= b;
                break;
            case '+':
                result += b;
                break;
            case '*':
                result *= b;
                break;
        }

        return result;
    }
}

/*class Solution {
    static List<Long> numbers; // 숫자 리스트
    static List<Character> operators; // 연산자 리스트
    static int N;
    static char[] input;
    static char[] ordered; // 우선순위가 결정된 연산자들의 배열
    static boolean[] isSelected;
    static long answer;

    public long solution(String expression) {
        answer = 0L;

        numbers = new ArrayList<>();
        operators = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        // 1. 숫자, 연산자 구분하여 리스트에 넣기
        for (int i = 0, end = expression.length(); i < end; i++) {
            char c = expression.charAt(i);

            if (c == '+' || c == '-' || c == '*') { // 연산자이면
                numbers.add(Long.parseLong(sb.toString())); // 숫자 넣기
                sb.setLength(0); // 빌더 초기화
                operators.add(c); // 연산자 넣기
            } else { // 숫자이면
                sb.append(c); // 빌더에 쌓기
            }
        }
        numbers.add(Long.parseLong(sb.toString()));

        // 2. 사용된 연산자에 따라 배열 구성
        HashSet<Character> op = new HashSet<>(operators);
        N = op.size();
        input = new char[N];
        ordered = new char[N];
        isSelected = new boolean[N];

        int index = 0;
        Iterator<Character> it = op.iterator();
        while(it.hasNext()) {
            input[index++] = it.next();
        }

        // 3. 경우의 수 각각에 대하여 최대값 구하기
        nPr(0); // nPn

        return answer;
    }

    static void nPr(int cnt) {
        if (cnt == N) {
            answer = Math.max(answer, getAnswer()); // 최대값 갱신

            return;
        }

        for (int i = 0; i < N; i++) {
            if (isSelected[i]) continue;

            ordered[cnt] = input[i];
            isSelected[i] = true;
            nPr(cnt + 1);
            isSelected[i] = false;
        }
    }

    static long getAnswer() {
        // 각 경우의 수마다 계산을 위해 리스트 복사
        List<Long> newNumbers = new ArrayList<Long>(numbers);
        List<Character> newOperators = new ArrayList<Character>(operators);

        // 연산자 종류만큼 반복
        for (int i = 0; i < N; i++) {
            // 리스트에서 remove하면서 변동되는 연산자 개수를 이용해 수식 계산
            for (int j = 0; j < newOperators.size(); j++) {
                if (newOperators.get(j) == ordered[i]) { // 우선순위에 따라 먼저 계산할 연산자가 있으면
                    newNumbers.add(j, calc(newNumbers.remove(j), ordered[i], newNumbers.remove(j)));
                    newOperators.remove(j--);
                }
            }
        }

        return Math.abs(newNumbers.get(0));
    }

    static long calc(long a, char op, long b) {
        long res = 0L;

        switch (op) {
            case '+':
                res = a + b;
                break;
            case '-':
                res = a - b;
                break;
            case '*':
                res = a * b;
                break;
        }

        return res;
    }
}*/
